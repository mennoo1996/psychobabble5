package xmlIO;
import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import libraryClasses.*;

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
	public static void writeCompetition(String libraryFileName, String schemeFileName, Competition competition) {
		writeLibrary(libraryFileName, competition.getLibrary(), competition.getRoundsPlayed());
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
		
		double budget = 0;
		try {
			budget = Double.parseDouble(teamElement.getAttribute("budget"));
		} catch(Exception e) {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		    Number number = 0;
			try {
				number = format.parse(teamElement.getAttribute("budget"));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    budget = number.doubleValue();
		}
		
		NodeList standingsList = teamElement.getElementsByTagName("standings");
		Element standingsElement = (Element)standingsList.item(0);
		Standings standings = readStandings(standingsElement, teamName);
		
		Team myTeam = new Team(teamName, budget, standings);
		
		NodeList playerList = teamElement.getElementsByTagName("player");
		
		for(int p = 0; p < playerList.getLength(); p++) {
			Node player = playerList.item(p);
			myTeam.add(readPlayer(player, teamName));
		}
		
		NodeList positionsList = teamElement.getElementsByTagName("positions");
		Element positionsElement = (Element)positionsList.item(0);
		
		Positions positions = readPositions(positionsElement, myTeam);
		myTeam.setPositions(positions);
		
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
	
	private static Positions readPositions(Element positionsElement, Team team) {
		Player[] positionsArray = new Player[11];
		for(int i = 0; i < 11; i++) {
			String playerString = "player" + (i+1);
			NodeList playerList = positionsElement.getElementsByTagName(playerString);
			Element playerElement = (Element)playerList.item(0);
			
			int age = Integer.parseInt(getNodeValue(playerElement, "age"));
			String name = getNodeValue(playerElement, "name");
			
			Player player = team.getPlayerForNameAndAge(name, age);
			positionsArray[i] = player;
		}
		Positions positions = new Positions(positionsArray);
		return positions;
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
		
		teamElement.appendChild(writePositions(team.getPositions(), doc));
		
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
	
	private static Element writePositions(Positions positions, Document doc) {
		Element positionsElement = doc.createElement("positions");
		Player[] positionsArray = positions.getPositionArray();
		for(int i = 0; i < 11; i++) {
			String playerString = "player" + (i+1);
			Element playerElement = doc.createElement(playerString);
			
			Element ageElement = doc.createElement("age");
			playerElement.appendChild(ageElement);
			ageElement.appendChild(doc.createTextNode(String.format("%d", positionsArray[i].getAge())));
			
			Element nameElement = doc.createElement("name");
			playerElement.appendChild(nameElement);
			nameElement.appendChild(doc.createTextNode(positionsArray[i].getName()));
			
			positionsElement.appendChild(playerElement);
		}
		return positionsElement;
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
		
		Element goalsElement = doc.createElement("goals");
		playerElement.appendChild(goalsElement);
		goalsElement.appendChild(doc.createTextNode(String.format("%d", player.getGoals())));
		
		Element assistsElement = doc.createElement("assists");
		playerElement.appendChild(assistsElement);
		assistsElement.appendChild(doc.createTextNode(String.format("%d", player.getAssists())));
		
		Element	yellowCardsElement = doc.createElement("yellowCards");
		playerElement.appendChild(yellowCardsElement);
		yellowCardsElement.appendChild(doc.createTextNode(String.format("%d", player.getYellowcards())));
		
		Element redCardsElement = doc.createElement("redCards");
		playerElement.appendChild(redCardsElement);
		redCardsElement.appendChild(doc.createTextNode(String.format("%d", player.getRedcards())));
		
		Element	daysInjuredElement = doc.createElement("daysInjured");
		playerElement.appendChild(daysInjuredElement);
		daysInjuredElement.appendChild(doc.createTextNode(String.format("%d", player.getDaysInjured())));
		
		Element daysSuspendedElement = doc.createElement("daysSuspended");
		playerElement.appendChild(daysSuspendedElement);
		daysSuspendedElement.appendChild(doc.createTextNode(String.format("%d", player.getDaysSuspended())));
		
		Element daysNotForSaleElement = doc.createElement("daysNotForSale");
		playerElement.appendChild(daysNotForSaleElement);
		daysNotForSaleElement.appendChild(doc.createTextNode(String.format("%d", player.getDaysNotForSale())));
		
		String eligible;
		if(player.isEligible()) {
			eligible = "1";
		} else {
			eligible = "0";
		}
		
		Element eligibleElement = doc.createElement("eligible");
		playerElement.appendChild(eligibleElement);
		eligibleElement.appendChild(doc.createTextNode(eligible));
		
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
		int goals = Integer.parseInt(getNodeValue(element, "goals"));
		int assists = Integer.parseInt(getNodeValue(element, "assists"));
		int redCards = Integer.parseInt(getNodeValue(element, "redCards"));
		int yellowCards = Integer.parseInt(getNodeValue(element, "yellowCards"));
		int daysInjured = Integer.parseInt(getNodeValue(element, "daysInjured"));
		int daysSuspended = Integer.parseInt(getNodeValue(element, "daysSuspended"));
		int daysNotForSale = Integer.parseInt(getNodeValue(element, "daysNotForSale"));
		
		boolean eligible;
		if(getNodeValue(element, "eligible").equals("1")) {
			eligible = true;
		} else {
			eligible = false;
		}

		Player player;
		
		if(playerType.equals("Attacker") || playerType.equals("Defender") || playerType.equals("Midfielder")) {
			// player is attacker | defender | midfielder
			int dribblingValue = Integer.parseInt(getNodeValue(element, "dribblingValue"));
			int finishingValue = Integer.parseInt(getNodeValue(element, "finishingValue"));
			int defenseValue = Integer.parseInt(getNodeValue(element, "defenseValue"));
			int staminaValue = Integer.parseInt(getNodeValue(element, "staminaValue"));

			if(playerType.equals("Attacker")) {
				player = new Attacker(price, teamName, name, age, number, goals, assists, yellowCards, redCards, daysInjured, daysSuspended, eligible, dribblingValue, finishingValue, defenseValue, staminaValue, daysNotForSale);
			} else if(playerType.equals("Midfielder")) {
				player = new Midfielder(price, teamName, name, age, number, goals, assists, yellowCards, redCards, daysInjured, daysSuspended, eligible, dribblingValue, finishingValue, defenseValue, staminaValue, daysNotForSale);
			} else if(playerType.equals("Defender")) {
				player = new Defender(price, teamName, name, age, number, goals, assists, yellowCards, redCards, daysInjured, daysSuspended, eligible, dribblingValue, finishingValue, defenseValue, staminaValue, daysNotForSale);
			} else {
				player = null;
			}
			return player;
		} else if (playerType.equals("Goalkeeper")){
			// player is a goalkeeper
			int goalkeeperValue = Integer.parseInt(getNodeValue(element, "goalkeeperValue"));
			player = new Goalkeeper(price, teamName, name, age, number, goals, assists, yellowCards, redCards, daysInjured, daysSuspended, eligible, goalkeeperValue, daysNotForSale);
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
	
	public static GameList readGameList(String gameListFileName) {
		try {
			File xmlFile = new File(gameListFileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			GameList res = new GameList();
			
			NodeList saveList = doc.getElementsByTagName("save");
			for(int i = 0; i < saveList.getLength(); i++) {
				Node save = saveList.item(i);
				if(save.getNodeType() == Node.ELEMENT_NODE) {

					Element saveElement = (Element)save;
					res.add(readGame(saveElement));
				}
			}
			return res;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Game readGame(Element saveElement) {
		String name = getNodeValue(saveElement, "name");
		String dataFile = getNodeValue(saveElement, "datafile");
		String schemeFile = getNodeValue(saveElement, "schemefile");
		String teamName = getNodeValue(saveElement, "teamname");
		
		return new Game(name, dataFile, schemeFile, teamName);
	}
	
	public static void writeGameList(String fileName, GameList gameList) {
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element saveListElement = doc.createElement("savelist");
			doc.appendChild(saveListElement);
			
			for(Game game : gameList.getGames()) {
				saveListElement.appendChild(writeGame(game, doc));
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
	
	private static Element writeGame(Game game, Document doc) {
		Element gameElement = doc.createElement("save");
		
		Element nameElement = doc.createElement("name");
		gameElement.appendChild(nameElement);
		nameElement.appendChild(doc.createTextNode(game.getName()));
		
		Element dataFileElement = doc.createElement("datafile");
		gameElement.appendChild(dataFileElement);
		dataFileElement.appendChild(doc.createTextNode(game.getSavefileData()));
		
		Element schemeFileElement = doc.createElement("schemefile");
		gameElement.appendChild(schemeFileElement);
		schemeFileElement.appendChild(doc.createTextNode(game.getSavefileScheme()));
		
		Element teamNameElement = doc.createElement("teamname");
		gameElement.appendChild(teamNameElement);
		teamNameElement.appendChild(doc.createTextNode(game.getTeam().getTeamName()));
		
		return gameElement;
		
	}
}
