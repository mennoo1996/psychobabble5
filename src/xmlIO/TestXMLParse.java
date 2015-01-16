package xmlIO;

import libraryClasses.Player;
import libraryClasses.Team;
import game.Competition;
import game.Game;
import game.GameList;
import gameLogic.CurrentXIRating;
import gameLogic.GameLogic;
import gameLogic.TeamRating;
import gameLogic.TransferInProgress;
import gameLogic.TransferLogic;
import libraryClasses.FieldPlayer;


public class TestXMLParse {

	public static void main(String[] args) {
//		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		
//		System.out.println(competition.getLibrary().getLibrary().get(0).getTeam().get(0));
//		for (int i=0;i<20;i++) {
//			Team t = competition.getLibrary().getLibrary().get(i);
//			for (int j=0;j<20;j++) {
//				Player p = t.getTeam().get(j);
//				int temp = ((FieldPlayer) p).getFinishingValue();
//				((FieldPlayer) p).setFinishingValue(((FieldPlayer) p).getDribblingValue());
//				((FieldPlayer) p).setDribblingValue(temp);
//				int a=p.getAge();
//			}
//			
//		}
//		
//		System.out.println(competition.getLibrary().getLibrary().get(0).getTeam().get(0));

		
		
		
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
//			System.out.println(competition.getLibrary().getLibrary().get(0).getBudget());
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

		
		
//		XMLParser.writeGameList("files/saves.xml", gl);
		
//		GameList gl =XMLParser.readGameList("files/saves.xml");
//		System.out.println(gl);
//		int nul=0, een=0;
//		for (int i=0;i<10000;i++) {
//		int res = GameLogic.randomGenerator(0, 1);
//		if (res==0) {
//			nul++;
//		} else {
//			een++;
//		}
//		
//			} 
//		System.out.println(nul);
//		System.out.println(een);
		
//		for (int i=0;i<20;i++) {
//			System.out.println(TeamRating.calculateTeamRating(competition.getLibrary().getLibrary().get(i)).toString());
//		}'
		
//		for (int i=0;i<38;i++) {
//			competition.playRound();
//		}
		
//		TransferLogic.AutoTransfer(competition.getLibrary().getLibrary().get(0), competition.getLibrary());
		
		GameList gl = new GameList();
		Game g1=gl.newgame("Menno", "Arsenal");
		Game g2=gl.newgame("Bart", "Burnley");
		g1.getTransferList().addTransfer(new TransferInProgress(competition.getLibrary().getLibrary().get(0).getTeam().get(0), 100000000, 100000000));
		XMLParser.writeGameList("files/saves_v5.xml", gl);
		GameList gl2 = XMLParser.readGameList("files/saves_v5.xml");
		System.out.println(gl2.toString());
	}
}


