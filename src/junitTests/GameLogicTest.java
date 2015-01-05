package junitTests;

import static org.junit.Assert.*;
import libraryClasses.Competition;
import libraryClasses.Team;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xmlIO.XMLParser;

public class GameLogicTest {

	@Before
	public void setUp() throws Exception {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v3.xml", "files/competition-scheme.xml");
		
//		System.out.println(competition.toString());
		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInjuredPlayers() {
		
	}

	@Test
	public void testGetInjuriesLength() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmountOfRedCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRedCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmountOfYellowCards() {
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
	public void testTeam2WintLoserScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testTeam2WintWinnerScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testTeam1WintLoserScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testTeam1WintWinnerScore() {
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

	@Test
	public void testGetRandomNumberForTeam() {
		fail("Not yet implemented");
	}

}
