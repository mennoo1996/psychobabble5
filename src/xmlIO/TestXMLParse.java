package xmlIO;

import java.util.ArrayList;
import java.util.Date;

import libraryClasses.Competition;
import libraryClasses.Game;
import libraryClasses.GameList;
import libraryClasses.Player;
import libraryClasses.Team;
import gameLogic.*;


public class TestXMLParse {

	public static void main(String[] args) {
//		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
//		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v4.xml", "files/competition-scheme.xml");
		
		
//		System.out.println(competition.toString());
//		
//		for(Team t : competition.getLibrary().getLibrary()) {
//			t.setPositionsAsCurrentTeam();;
//		}
//		competition.playRound();
//		
//		for(int i = 0; i < 37; i++) {
//			System.out.println("Round: " + i);
//			competition.playRound();
//		}
//		
//		System.out.println(competition.standingsToString());
		
//		XMLParser.writeCompetition("files/competitionDatabase_v5.xml", competition);
//		
//		System.out.println(GameLogic.randomGenerator(1,50,5));
//		System.out.println(GameLogic.randomGenerator(1,100,5));
//		System.out.println(GameLogic.randomGenerator(1,150,5));
		
//		for (int i=0;i<200;i++) {
//			GameLogic.setSeed(i);
//			GameLogic.setTesting(true);
//			System.out.println(i + " " + GameLogic.randomGenerator(1, 100));
//		}
		
//		System.out.println(competition.getLibrary().getLibrary().get(0).getTeam().get(0));
//		System.out.println(competition.getLibrary().getLibrary().get(0));
//		

//		ArrayList<Team> libraryArray = competition.getLibrary().getLibrary();
//		for(int i = 0; i < 20; i++) {
//			System.out.println(libraryArray.get(i).getTeamName());
//			if(libraryArray.get(i).getTeamName().equals("Leicester City")) {
//				for (Player player :libraryArray.get(i).getPositions().getPositionArray()) {
//					System.out.println(player.toString());
//				}
//			}
//			System.out.println(libraryArray.get(i).toString());
//			System.out.println("\n\n");
//		}
		
//		GameList gl = new GameList();
//		Game g = gl.newgame("Menno", "Arsenal");
//		for (int i=0;i<10;i++) {g.getCompetition().playRound();}
//		g.save();
//		
//		
		
		GameList gl = XMLParser.readGameList("files/saves.xml");
		XMLParser.writeGameList("files/test_out.xml", gl);
		
	}
}


