
public class TestXMLParse {

	public static void main(String[] args) {

//		XMLParserOld myXMLParserOld = new XMLParserOld();
//		Library library = myXMLParserOld.readLibrary("playersDatabase by player.xml");

		
		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
		for(int i = 0; i < competition.getLibrary().getLibrary().size(); i++) {
			Team team = competition.getLibrary().getLibrary().get(i);
			team.setFirst11AsCurrentTeam();
		}
		
		
<<<<<<< HEAD
		competition.playRound();
=======
		for(int i =0; i < 15; i++) {
			competition.playRound();
		}
>>>>>>> upstream/master

		System.out.println(competition.standingsToString());
		
		XMLParser.writeCompetition("players.xml", competition);
	}	
}
