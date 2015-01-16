package xmlIO;

import game.Competition;


public class TestXMLParse {

	public static void main(String[] args) {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		competition.playRound();
		competition.playRound();
		competition.playRound();
		competition.playRound();
		competition.playRound();
		competition.playRound();
		competition.playRound();
		competition.playRound();
		System.out.println(competition.getScheme().toString());
		XMLParser.writeScheme("files/test_out.xml", competition.getScheme());
	}
}


