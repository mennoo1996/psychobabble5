package junitTests;

import static org.junit.Assert.*;
import libraryClasses.Standings;

import org.junit.Test;

import schemeClasses.Match;

public class StandingsTest {

	@Test
	public void testStandings() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getWon(), 1);
		assertEquals(s.getDraw(), 2);
		assertEquals(s.getLost(), 3);
		assertEquals(s.getGoalsFor(), 4);
		assertEquals(s.getGoalsAgainst(), 5);
		assertEquals(s.getTeamName(), "team1");
		assertEquals(s.getPoints(), 5);
		assertEquals(s.getGoalDifference(), -1);
	}

	@Test
	public void testToString() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		String expected = "Standings [points=5, goalDifference=-1, won=1, draw=2, lost=3, goalsFor=4, goalsAgainst=5]";
		assertEquals(expected, s.toString());
	}

	@Test
	public void testUpdateStandings() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		s.updateStandings("won", 2, 3);
		assertEquals(s.getWon(), 2);
		assertEquals(s.getGoalsFor(), 6);
		assertEquals(s.getGoalsAgainst(), 8);
		assertEquals(s.getPoints(), 8);
		assertEquals(s.getGoalDifference(), -2);
		s.updateStandings("draw", 0, 0);
		assertEquals(s.getDraw(), 3);
		assertEquals(s.getGoalsFor(), 6);
		assertEquals(s.getGoalsAgainst(), 8);
		assertEquals(s.getPoints(), 9);
		assertEquals(s.getGoalDifference(), -2);
		s.updateStandings("lost", 0, 2);
		assertEquals(s.getLost(), 4);
		assertEquals(s.getGoalsFor(), 6);
		assertEquals(s.getGoalsAgainst(), 10);
		assertEquals(s.getPoints(), 9);
		assertEquals(s.getGoalDifference(), -4);
		s.updateStandings("win", 1, 1);
		s.updateStandings("loss", 1, 1);
		s.updateStandings("iets randoms", 1, 2);
		
	}

	@Test
	public void testGetWon() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getWon(), 1);
		s.setWon(2);
		assertEquals(s.getWon(), 2);
	}

	@Test
	public void testSetWon() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getWon(), 1);
		s.setWon(4);
		assertEquals(s.getWon(), 4);
	}

	@Test
	public void testGetDraw() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getDraw(), 2);
		s.setDraw(4);
		assertEquals(s.getDraw(), 4);
	}

	@Test
	public void testSetDraw() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getDraw(), 2);
		s.setDraw(5);
		assertEquals(s.getDraw(), 5);
	}

	@Test
	public void testGetLost() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getLost(), 3);
		s.setLost(6);
		assertEquals(s.getLost(), 6);
	}

	@Test
	public void testSetLost() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getLost(), 3);
		s.setLost(7);
		assertEquals(s.getLost(), 7);
	}

	@Test
	public void testGetPoints() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getPoints(), 5);
		s.setPoints(6);
		assertEquals(s.getPoints(), 6);
	}

	@Test
	public void testSetPoints() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getPoints(), 5);
		s.setPoints(50);
		assertEquals(s.getPoints(), 50);
	}

	@Test
	public void testGetGoalsFor() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getGoalsFor(), 4);
		s.setGoalsFor(6);
		assertEquals(s.getGoalsFor(), 6);
	}

	@Test
	public void testSetGoalsFor() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getGoalsFor(), 4);
		s.setGoalsFor(2);
		assertEquals(s.getGoalsFor(), 2);
	}

	@Test
	public void testGetGoalsAgainst() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getGoalsAgainst(), 5);
		s.setGoalsAgainst(6);
		assertEquals(s.getGoalsAgainst(), 6);
	}

	@Test
	public void testSetGoalsAgainst() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getGoalsAgainst(), 5);
		s.setGoalsAgainst(20);
		assertEquals(s.getGoalsAgainst(), 20);
	}

	@Test
	public void testGetGoalDifference() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getGoalDifference(), -1);
		s.setGoalDifference(5);
		assertEquals(s.getGoalDifference(), 5);
	}

	@Test
	public void testSetGoalDifference() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getGoalDifference(), -1);
		s.setGoalDifference(6);
		assertEquals(s.getGoalDifference(), 6);
	}

	@Test
	public void testGetTeamName() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getTeamName(), "team1");
		s.setTeamName("team2");
		assertEquals(s.getTeamName(), "team2");
	}

	@Test
	public void testSetTeamName() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertEquals(s.getTeamName(), "team1");
		s.setTeamName("Arsenal");
		assertEquals(s.getTeamName(), "Arsenal");
	}

	@Test
	public void testEqualsObject() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		assertTrue(s.equals(s));
		assertFalse(s.equals(null));
		assertFalse(s.equals(new Match("team 1", "team 2")));
		Standings s2 = new Standings (2, 2, 3, 4, 5, "team1");
		Standings s3= new Standings (1, 3, 3, 4, 5, "team1");
		Standings s4 = new Standings (1, 2, 4, 4, 5, "team1");
		Standings s5 = new Standings (1, 2, 3, 5, 5, "team1");
		Standings s6 = new Standings (1, 2, 3, 4, 6, "team1");
		assertFalse(s.equals(s2));
		assertFalse(s.equals(s3));
		assertFalse(s.equals(s4));
		assertFalse(s.equals(s5));
		assertFalse(s.equals(s6));
		Standings s7 = new Standings (1, 2, 3, 5, 5, "team1");
		s7.setGoalDifference(-1);
		Standings s8 = new Standings (1, 2, 3, 4, 6, "team1");
		s8.setGoalDifference(-1);
		assertFalse(s.equals(s7));
		assertFalse(s.equals(s8));
		Standings s9 = new Standings (1, 2, 3, 4, 5, "team1");
		Standings s10 = new Standings (1, 2, 3, 4, 5, null);
		assertFalse(s10.equals(s9));
		s9.setTeamName(null);
		assertTrue(s9.equals(s10));
		s9.setTeamName("team1");
		s10.setTeamName("team2");
		assertFalse(s9.equals(s10));
		s10.setTeamName("team1");
		s10.setWon(5);
		assertFalse(s9.equals(s10));

		
	}

}
