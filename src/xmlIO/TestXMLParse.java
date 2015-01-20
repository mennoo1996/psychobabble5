package xmlIO;

import game.Competition;
import schemeClasses.CompetitionScheme;



public class TestXMLParse {

	public static void main(String[] args) {
		CompetitionScheme scheme = XMLParser.readCompetitionScheme("files/test_out.xml");
		System.out.println(scheme.getRound(1).getMatches().get(4).getScore1());
		System.out.println(scheme.getRound(1).getMatches().get(4).getScore2());
		System.out.println(scheme.getRound(1).getMatches().get(4).getTeam1());
		System.out.println(scheme.getRound(1).getMatches().get(4).getTeam2());
		
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		competition.getLibrary().getLibrary().get(0).setBudget(10000000);
		System.out.println(competition.getLibrary().getLibrary().get(0).getBudget());
		competition.newSeason();
		System.out.println(competition.getLibrary().getLibrary().get(0).getBudget());

		
	}
}


