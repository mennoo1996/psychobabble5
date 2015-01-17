package xmlIO;

import schemeClasses.CompetitionScheme;



public class TestXMLParse {

	public static void main(String[] args) {
		CompetitionScheme scheme = XMLParser.readCompetitionScheme("files/test_out.xml");
		System.out.println(scheme.getRound(1).getMatches().get(4).getScore1());
		System.out.println(scheme.getRound(1).getMatches().get(4).getScore2());
		System.out.println(scheme.getRound(1).getMatches().get(4).getTeam1());
		System.out.println(scheme.getRound(1).getMatches().get(4).getTeam2());
		
	}
}


