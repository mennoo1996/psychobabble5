
public class TestXMLParse {

	public static void main(String[] args) {

//		XMLParserOld myXMLParserOld = new XMLParserOld();
//		Library library = myXMLParserOld.readLibrary("playersDatabase by player.xml");

		
		Library library = XMLParser.readLibrary("players.xml");

		
		Team team = library.getLibrary().get(0);
		System.out.println(team.getTeamName());
		
		team.updateStandings("won", 10, 2);
		System.out.println(library.toString());
		
		
		
		XMLParser.writeLibrary("players2.xml", library);
		
		System.out.println("\n\n");
		
		Competition competition = XMLParser.readCompetition("competition-scheme.xml");
		System.out.println(competition.toString());
		
	}	
}