package junitTests;

import static org.junit.Assert.*;
import gameLogic.TransferList;
import libraryClasses.Match;

import org.junit.Test;

public class MatchTest {

	@Test
	public void testMatch() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam1(), "team1");
		assertEquals(m.getTeam2(), "team2");
		assertEquals(m.getScoreTeam1(), -1);
		assertEquals(m.getScoreTeam2(), -1);
	}

	@Test
	public void testToString() {
		Match m = new Match ("team1", "team2");
		String expected = "Match [team1=team1, team2=team2]";
		assertEquals(expected, m.toString());
	}

	@Test
	public void testGetTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam1(), "team1");
		m.setTeam1("team3");
		assertEquals(m.getTeam1(), "team3");
	}

	@Test
	public void testSetTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam1(), "team1");
		m.setTeam1("Arsenal");
		assertEquals(m.getTeam1(), "Arsenal");
	}

	@Test
	public void testGetTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam2(), "team2");
		m.setTeam2("Chelsea");
		assertEquals(m.getTeam2(), "Chelsea");
	}

	@Test
	public void testSetTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam2(), "team2");
		m.setTeam2("Manchester City");
		assertEquals(m.getTeam2(), "Manchester City");
	}

	@Test
	public void testSetScoreTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam1(), -1);
		m.setScoreTeam1(5);
		assertEquals(m.getScoreTeam1(), 5);
	}

	@Test
	public void testSetScoreTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam2(), -1);
		m.setScoreTeam2(6);
		assertEquals(m.getScoreTeam2(), 6);
	}

	@Test
	public void testGetScoreTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam1(), -1);
		m.setScoreTeam1(5);
		assertEquals(m.getScoreTeam1(), 5);
	}

	@Test
	public void testGetScoreTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam2(), -1);
		m.setScoreTeam2(2);
		assertEquals(m.getScoreTeam2(), 2);
	}

	@Test
	public void testGetWinner() {
		Match m = new Match ("team1", "team2");
		m.setScoreTeam1(0);
		m.setScoreTeam2(1);
		assertEquals(m.getWinner(), 2);
		m.setScoreTeam1(1);
		assertEquals(m.getWinner(), 0);
		m.setScoreTeam1(2);
		assertEquals(m.getWinner(), 1);
	}
	
	@Test
	public void testEquals() {
		Match m = new Match ("team1", "team2");
		assertTrue(m.equals(m));
		assertFalse(m.equals(null));
		assertFalse(m.equals(new TransferList()));
		Match m2 = new Match ("team1", "team2");
		Match m3 = new Match ("team1", "team2");
		m2.setScoreTeam1(2);
		m3.setScoreTeam2(3);
		assertFalse(m.equals(m2));
		assertFalse(m.equals(m3));
		m.setTeam1(null);
		m2.setScoreTeam1(-1);
		assertFalse(m.equals(m2));
		m.setTeam1("a");
		assertFalse(m.equals(m2));
		m.setTeam2(null);
		m2.setTeam1("a");
		assertFalse(m.equals(m2));
		m.setTeam1(null);
		m2.setTeam1(null);
		Match m4 = new Match (null, "team2");
		Match m5 = new Match (null, "team2");
		assertTrue(m4.equals(m5));
		Match m6 = new Match ("team1", null);
		Match m7 = new Match ("team1", null);
		assertTrue(m6.equals(m7));
		Match m8 = new Match ("a", "b");
		Match m9 = new Match ("a", "c");
		assertFalse(m8.equals(m9));
	}

}
