package libraryClasses;


public class TestXMLParse {

	public static void main(String[] args) {
		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
		for(Team team : competition.getLibrary().getLibrary()) {
			team.setFirst11AsCurrentTeam();
		}
		
		for(int i = 0; i < 38; i++) {
			competition.playRound();
		}
		
		System.out.println(competition.standingsToString());
		
		
//		System.out.println(competition.toString());
		
	}
}


