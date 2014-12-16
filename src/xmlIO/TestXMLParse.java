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
//		
//		for(int i = 0; i < 38; i++) {
//			competition.playRound();
//		}
//		
//		System.out.println(competition.standingsToString());
		
//		XMLParser.writeCompetition("files/competitionDatabase_v3.xml", competition);
//		
		
		Team playersTeam = competition.getLibrary().getLibrary().get(0);
		Player player = playersTeam.getTeam().get(0);
		int askingPrice = (int) (10*player.getPrice().doubleValue());
		
		System.out.println(TransferLogic.requestSell(player, playersTeam, askingPrice, competition.getLibrary()));
		System.out.println(player.isCanBeSold());
		System.out.println(TransferLogic.requestSell(player, playersTeam, askingPrice, competition.getLibrary()));
		competition.playRound();
		System.out.println(TransferLogic.requestSell(player, playersTeam, askingPrice, competition.getLibrary()));
		
		}
}


