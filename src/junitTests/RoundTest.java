package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import libraryClasses.Match;
import libraryClasses.Round;

import org.junit.Test;

public class RoundTest {

	@Test
	public void testRound() {
		Round r = new Round (5);
		assertNotNull(r.getMatches());
		assertEquals(r.getRoundNumber(), 5);
		
	}

	@Test
	public void testAdd() {
		Round r = new Round(1);
		Match m = new Match("team 1", "team 2");
		r.add(m);
		assertTrue(r.getMatches().contains(m));
	}

	@Test
	public void testToString() {
		Round r = new Round(5);
		Match m1 = new Match("team1", "team2");
		Match m2 = new Match("team3", "team4");
		r.add(m1);
		r.add(m2);
		String expected = "Round: 5\n" + m1.toString() + "\n" + m2.toString();
		assertEquals(expected, r.toString());
	}

	@Test
	public void testGetRoundNumber() {
		Round r = new Round(1);
		assertEquals(r.getRoundNumber(), 1);
		r.setRoundNumber(2);
		assertEquals(r.getRoundNumber(), 2);
	}

	@Test
	public void testSetRoundNumber() {
		Round r = new Round(1);
		assertEquals(r.getRoundNumber(), 1);
		r.setRoundNumber(2);
		assertEquals(r.getRoundNumber(), 2);
	}

	@Test
	public void testGetMatches() {
		Round r = new Round(1);
		Match m = new Match ("team1", "team2");
		r.add(m);
		ArrayList<Match> expected = new ArrayList<Match>();
		expected.add(m);
		assertEquals(expected, r.getMatches());
	}

	@Test
	public void testSetMatches() {
		Round r = new Round(1);
		Match m = new Match("team1", "team2");
		ArrayList<Match> array = new ArrayList<Match>();
		array.add(m);
		r.setMatches(array);
		assertEquals(r.getMatches(), array);
	}

	@Test
	public void testEqualsObject() {
		Round r= new Round(1);
		assertTrue(r.equals(r));
		assertFalse(r.equals(null));
		assertFalse(r.equals(new Match("1", "2")));
		Round r2 = new Round(1);
		r2.add(new Match("1", "2"));
		r.setMatches(null);
		assertFalse(r.equals(r2));
		r2.setMatches(null);
		assertTrue(r.equals(r2));
		r.setMatches(new ArrayList<Match>());
		r2.setMatches(new ArrayList<Match>());
		r.add(new Match("3", "4"));
		r2.add(new Match("5", "6"));
		assertFalse(r.equals(r2));
		Round r3 = new Round(2);
		Round r4 = new Round(3);
		assertFalse(r3.equals(r4));
		
	}

}
