package libraryClasses;

public class TestXMLParse {

	public static void main(String[] args) {
		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
//		System.out.println(competition.toString());
		
		for(int i = 0; i < competition.getLibrary().getLibrary().size(); i++) {
			Team team = competition.getLibrary().getLibrary().get(i);
			team.setFirst11AsCurrentTeam();
		}
		
<<<<<<< HEAD:src/TestXMLParse.java
		
<<<<<<< HEAD
		competition.playRound();
=======
		for(int i =0; i < 15; i++) {
=======
		for(int i =0; i < 38; i++) {
>>>>>>> upstream/master:src/libraryClasses/TestXMLParse.java
			competition.playRound();
		}
>>>>>>> upstream/master

		System.out.println(competition.standingsToString());
		
		XMLParser.writeCompetition("demoOut.xml", competition);
		
	}
		
		
}

