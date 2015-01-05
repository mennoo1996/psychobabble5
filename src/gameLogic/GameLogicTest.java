package gameLogic;

import static org.junit.Assert.*;
import libraryClasses.Competition;
import libraryClasses.Team;

import org.junit.Before;
import org.junit.Test;

import xmlIO.XMLParser;

public class GameLogicTest {
	
	@Before
	public void runBeforeEveryTest() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v3.xml", "files/competition-scheme.xml");
	
		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
	} 

	@Test
	public void testGetInjuredPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInjuriesLength() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRedCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYellowCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGoals() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAssists() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMatchResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testTeam2Wint() {
		fail("Not yet implemented");
	}

	@Test
	public void testTeam1Wint() {
		fail("Not yet implemented");
	}

	@Test
	public void testGelijkSpel() {
		fail("Not yet implemented");
	}

	@Test
	public void testRandomGenerator() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWinner() {
		fail("Not yet implemented");
	}

}
