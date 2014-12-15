package xmlIO;

import libraryClasses.Competition;
import libraryClasses.Player;
import libraryClasses.Team;
import gameLogic.*;


public class TestXMLParse {

	public static void main(String[] args) {
//		Competition competition = XMLParser.readCompetition("players Database by team with empty standings.xml", "competition-scheme.xml");
		
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v3.xml", "files/competition-scheme.xml");
		
		System.out.println(competition.toString());
		
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
		
		System.out.println(competition.getLibrary().getLibrary().get(0).getCurrentTeam());
		TransferList existingTransfers = new TransferList();
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		Team myteam = competition.getLibrary().getLibrary().get(1);
		System.out.println(TransferLogic.requestTransfer(p, myteam,  20000000, competition.getLibrary(), existingTransfers));
		System.out.println(p);
		System.out.println(myteam);
		System.out.println(competition.getLibrary().getLibrary().get(0).getCurrentTeam());
		System.out.println(TransferLogic.requestTransfer(p, myteam, 33000000, competition.getLibrary(), existingTransfers));
	}
}


