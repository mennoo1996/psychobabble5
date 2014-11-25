
public class TestXMLParse {

	public static void main(String[] args) {

//		XMLParserOld myXMLParserOld = new XMLParserOld();
//		Library library = myXMLParserOld.readLibrary("playersDatabase by player.xml");

		
		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		System.out.println(competition.toString());
		System.out.println("\n\n\n");
		competition.printStandings();
		
		System.out.println("\n\n\n");
		competition.getLibrary().getLibrary().get(0).updateStandings("won", 10, 2);
		competition.printStandings();
		
	}	
}