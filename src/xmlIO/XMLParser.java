package xmlIO;
import java.io.File;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import libraryClasses.Attacker;
import libraryClasses.Competition;
import libraryClasses.CompetitionScheme;
import libraryClasses.Defender;
import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Library;
import libraryClasses.Match;
import libraryClasses.Midfielder;
import libraryClasses.Player;
import libraryClasses.Round;
import libraryClasses.Standings;
import libraryClasses.Team;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class containing a  method for parsing a xml file into a library of teamsw
 *
 */
public class XMLParser {
	
	public static Competition readCompetition(String libraryFileName, String schemeFileName) {
		try {
			File xmlFile = new File(libraryFileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			int roundsPlayed = Integer.parseInt(doc.getDocumentElement().getAttribute("roundsPlayed"));
			
			return new Competition(readLibrary(libraryFileName), readCompetitionScheme(schemeFileName), roundsPlayed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method to write a competition to a xml file
	 * 
	 * @param libraryFileName	- The file to write to
	 * @param competition		- The competition to write
	 */
	public static void writeCompetition(String libraryFileName, Competition competition) {
		writeLibrary(libraryFileName, competition.getLibrary(), competition.getRoundsPlayed());
	}
	
	/**
	 * Method which returns a Library containing the teams and players parsed from the given file name
	 * 
	 * @param fileName	- File to read library from
	 * @return			- Library containg team and players from the file
	 */
	public static Library readLibrary(String fileName) {
		try {
			// Get file, parse it and set it's element's in a Document
			File xmlFile = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			Library res = new Library();
			
			NodeList teamList = doc.getElementsByTagName("team");
			for(int i = 0; i < teamList.getLength(); i++) {
				Node team = teamList.item(i);
				if(team.getNodeType() == Node.ELEMENT_NODE) {

					Element teamElement = (Element)team;
					res.add(readTeam(teamElement));
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method which reads an team from an element
	 * 
	 * @param teamElement 	- the element containing the team
	 * @return				- the read team
	 */
	private static Team readTeam(Element teamElement) {
		String teamName = teamElement.getAttribute("teamName");
		double budget = Double.parseDouble(teamElement.getAttribute("budget"));
		
		NodeList standingsList = teamElement.getElementsByTagName("standings");
		Element standingsElement = (Element)standingsList.item(0);
		Standings standings = readStandings(standingsElement, teamName);
		
		Team myTeam = new Team(teamName, budget, standings);
		
		NodeList playerList = teamElement.getElementsByTagName("player");
		
		for(int p = 0; p < playerList.getLength(); p++) {
			Node player = playerList.item(p);
			myTeam.add(readPlayer(player, teamName));
		}
		
		return myTeam;
	}
	
	/**
	 * Method which read standings from an element
	 * @param standingsElement	- the element to read the standings from
	 * @param teamName			- The name of the team the standings belongs to
	 * @return					- the standings
	 */
	private static Standings readStandings(Element standingsElement, String teamName) {
		int won = Integer.parseInt(getNodeValue(standingsElement, "won"));
		int draw = Integer.parseInt(getNodeValue(standingsElement, "draw"));
		int lost = Integer.parseInt(getNodeValue(standingsElement, "lost"));
		int goalsFor = Integer.parseInt(getNodeValue(standingsElement, "goalsFor"));
		int goalsAgainst = Integer.parseInt(getNodeValue(standingsElement, "goalsAgainst"));

		return new Standings(won, draw, lost, goalsFor, goalsAgainst, teamName);
	}
	
	/**
	 * Method which writes an library to a file
	 * 
	 * @param fileName	- the file to write the library to
	 * @param library	- the library to write
	 */
	public static void writeLibrary(String fileName, Library library, int roundsPlayed) {
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element libraryElement = doc.createElement("library");
			doc.appendChild(libraryElement);
			libraryElement.setAttribute("roundsPlayed", String.format("%d", roundsPlayed));
			
			for(Team team : library.getLibrary()) {
				libraryElement.appendChild(writeTeam(team, doc));
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method which writes a team to an element
	 * 
	 * @param team		- the team to write
	 * @param doc		- the document to write the team to
	 * @return			- the element containing the writen team
	 */
	private static Element writeTeam(Team team, Document doc) {
		Element teamElement = doc.createElement("team");
		
		teamElement.setAttribute("teamName", team.getTeamName());
		teamElement.setAttribute("budget", String.format("%.2f", team.getBudget()));
		
		teamElement.appendChild(writeStandings(team, doc));
		
		for(int p = 0; p < team.getTeam().size(); p++) {
			teamElement.appendChild(writePlayer(team.getTeam().get(p), doc));
		}
		return teamElement;
	}
	
	/**
	 * Method which writes the standings of a team to an element
	 * 
	 * @param team	- The team to write the standings from
	 * @param doc	- The document to write the element to
	 * @return		- the element containing the read standings
	 */
	private static Element writeStandings(Team team, Document doc) {
		Element standingsElement = doc.createElement("standings");
		
		Element wonElement = doc.createElement("won");
		standingsElement.appendChild(wonElement);
		wonElement.appendChild(doc.createTextNode(String.format("%d",team.getStandings().getWon())));
		
		Element drawElement = doc.createElement("draw");
		standingsElement.appendChild(drawElement);
		drawElement.appendChild(doc.createTextNode(String.format("%d", team.getStandings().getDraw())));
		
		Element lostElement = doc.createElement("lost");
		standingsElement.appendChild(lostElement);
		lostElement.appendChild(doc.createTextNode(String.format("%d", team.getStandings().getLost())));
		
		Element goalsForElement = doc.createElement("goalsFor");
		standingsElement.appendChild(goalsForElement);
		goalsForElement.appendChild(doc.createTextNode(String.format("%d", team.getStandings().getGoalsFor())));
		
		Element goalsAgainstElement = doc.createElement("goalsAgainst");
		standingsElement.appendChild(goalsAgainstElement);
		goalsAgainstElement.appendChild(doc.createTextNode(String.format("%d", team.getStandings().getGoalsAgainst())));
		
		return standingsElement;
	}
	
	/**
	 * Method which writes a player to an element
	 * 
	 * @param player	- the player to write
	 * @param doc		- the document to write the player to
	 * @return			- the element containing the writen player
	 */
	private static Element writePlayer(Player player, Document doc) {
		Element playerElement = doc.createElement("player");
		
		Element priceElement = doc.createElement("price");
		playerElement.appendChild(priceElement);
		priceElement.appendChild(doc.createTextNode(player.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
		
		Element playerTypeElement = doc.createElement("playerType");
		playerElement.appendChild(playerTypeElement);
		playerTypeElement.appendChild(doc.createTextNode(player.getPlayerType()));
		
		Element nameElement = doc.createElement("name");
		playerElement.appendChild(nameElement);
		nameElement.appendChild(doc.createTextNode(player.getName()));
		
		Element ageElement = doc.createElement("age");
		playerElement.appendChild(ageElement);
		ageElement.appendChild(doc.createTextNode(String.format("%d", player.getAge())));
		
		Element numberElement = doc.createElement("number");
		playerElement.appendChild(numberElement);
		numberElement.appendChild(doc.createTextNode(String.format("%d", player.getNumber())));
		
		if(player instanceof Attacker || player instanceof Midfielder || player instanceof Defender) {
			// Player is a fieldplayer
			
			FieldPlayer fieldPlayer = (FieldPlayer)player;
			Element dribblingElement = doc.createElement("dribblingValue");
			playerElement.appendChild(dribblingElement);
			dribblingElement.appendChild(doc.createTextNode(String.format("%d", fieldPlayer.getDribblingValue())));
			
			Element finishingElement = doc.createElement("finishingValue");
			playerElement.appendChild(finishingElement);
			finishingElement.appendChild(doc.createTextNode(String.format("%d", fieldPlayer.getFinishingValue())));
			
			Element defenseElement = doc.createElement("defenseValue");
			playerElement.appendChild(defenseElement);
			defenseElement.appendChild(doc.createTextNode(String.format("%d", fieldPlayer.getDefenseValue())));
			
			Element staminaElement = doc.createElement("staminaValue");
			playerElement.appendChild(staminaElement);
			staminaElement.appendChild(doc.createTextNode(String.format("%d", fieldPlayer.getStaminaValue())));
			
		} else if (player instanceof Goalkeeper) {
			// player is a goalkeeper
			
			Goalkeeper goalkeeper = (Goalkeeper)player;
			
			Element goalkeeperElement = doc.createElement("goalkeeperValue");
			playerElement.appendChild(goalkeeperElement);
			goalkeeperElement.appendChild(doc.createTextNode(String.format("%d", goalkeeper.getGoalkeeperValue())));
		}
		return playerElement;
	}
	
	/**
	 * Method which parses a player of a given playerType from a given Node
	 * @param node			- node to read player from
	 * @param playerType	- type of player to read
	 * @return				- read player
	 */
	private static Player readPlayer(Node node, String teamName) {
		Element element = (Element)node;
		
		String name = getNodeValue(element, "name");
		String playerType = getNodeValue(element, "playerType");
		int age = Integer.parseInt(getNodeValue(element, "age"));
		int number = Integer.parseInt(getNodeValue(element, "number"));
		BigDecimal price = new BigDecimal(getNodeValue(element, "price"));

		Player player;
		
		if(playerType.equals("Attacker") || playerType.equals("Defender") || playerType.equals("Midfielder")) {
			// player is attacker | defender | midfielder
			int dribbling = Integer.parseInt(getNodeValue(element, "dribblingValue"));
			int finishing = Integer.parseInt(getNodeValue(element, "finishingValue"));
			int defense = Integer.parseInt(getNodeValue(element, "defenseValue"));
			int stamina = Integer.parseInt(getNodeValue(element, "staminaValue"));

			if(playerType.equals("Attacker")) {
				player = new Attacker(price, teamName, name, age, number, dribbling, finishing, defense, stamina, 0, 0, 0, 0);
			} else if(playerType.equals("Midfielder")) {
				player = new Midfielder(price, teamName, name, age, number, dribbling, finishing, defense, stamina, 0, 0, 0, 0);
			} else if(playerType.equals("Defender")) {
				player = new Defender(price, teamName, name, age, number, dribbling, finishing, defense, stamina, 0, 0, 0, 0);
			} else {
				player = null;
			}
			return player;
		} else if (playerType.equals("Goalkeeper")){
			// player is a goalkeeper
			int goalkeeperValue = Integer.parseInt(getNodeValue(element, "goalkeeperValue"));
			player = new Goalkeeper(price, teamName, name, age, goalkeeperValue, number, 0, 0, 0, 0);
		} else {
			player = null;
		}
		return player;
	}

	/**
	 * Method which get's the value for a given tagName from a given Element
	 * @param element	- Element to read tag-value from
	 * @param tagName	- Name of tag to read tag-value from
	 * @return			- Read tag-value
	 */
	private static String getNodeValue(Element element, String tagName) {
		NodeList nodeList = element.getElementsByTagName(tagName);
		Element nodeElement = (Element)nodeList.item(0);
		NodeList valueNodeList = nodeElement.getChildNodes();
		return ((Node)valueNodeList.item(0)).getNodeValue();
	}
	
	/**
	 * Method which reads a CompetitionScheme from a file
	 * 
	 * @param fileName	- the file to read the CompetitionScheme from
	 * @return			- the CompetitionScheme
	 */
	public static CompetitionScheme readCompetitionScheme(String fileName) {
		try {
			// Get file, parse it and set it's element's in a Document
			File xmlFile = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			CompetitionScheme res = new CompetitionScheme();
			NodeList roundList = doc.getElementsByTagName("roundScheme");
			
			for(int i = 0; i < roundList.getLength(); i++) {
				Element roundElement = (Element)roundList.item(i);
				Round round = readRound(roundElement);
				res.add(round);
			}
			
			return res;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method which reads a round from an element
	 * 
	 * @param roundElement 	- The element to read the round from
	 * @return				- the round
	 */
	private static Round readRound(Element roundElement) {
		int roundNumber = Integer.parseInt(roundElement.getAttribute("round"));
		NodeList matchList = roundElement.getElementsByTagName("match");
		
		Round res = new Round(roundNumber);
		
		for(int i = 0; i < matchList.getLength(); i++) {
			Element matchElement = (Element)matchList.item(i);
			Match match = readMatch(matchElement);
			res.add(match);
		}
		return res;
	}
	
	/**
	 * Method which read a match from an element
	 * 
	 * @param matchElement 	- the element to read the match from
	 * @return				- the match
	 */
	private static Match readMatch(Element matchElement) {
		String team1 = getNodeValue(matchElement, "team1");
		String team2 = getNodeValue(matchElement, "team2");
		
		Match res = new Match(team1, team2);
		return res;	
	}
}
