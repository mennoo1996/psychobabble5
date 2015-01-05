package xmlIO;

import libraryClasses.Competition;
import libraryClasses.Player;
import libraryClasses.Team;
import gameLogic.*;


public class TestXMLParse {

	public static void main(String[] args) {
//		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v3.xml", "files/competition-scheme.xml");
		
//		System.out.println(competition.toString());
		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		competition.playRound();
//		
//		for(int i = 0; i < 38; i++) {
//			competition.playRound();
//		}
//		
//		System.out.println(competition.standingsToString());
		
//		XMLParser.writeCompetition("files/competitionDatabase_v3.xml", competition);
//		
//		System.out.println(GameLogic.randomGenerator(1,50,5));
//		System.out.println(GameLogic.randomGenerator(1,100,5));
//		System.out.println(GameLogic.randomGenerator(1,150,5));
		
		for (int i=0;i<200;i++) {
			GameLogic.setSeed(i);
			GameLogic.setTesting(true);
			System.out.println(i + " " + GameLogic.randomGenerator(0, 100));
		}
		
		
		}
}


