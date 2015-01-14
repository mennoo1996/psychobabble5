package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import libraryClasses.*;

import org.junit.Test;

import schemeClasses.CompetitionScheme;
import schemeClasses.Round;

public class CompetitionSchemeTest {

	@Test
	public void testCompetitionScheme() {
		CompetitionScheme c = new CompetitionScheme();
		assertNotNull(c.getRounds());
		
	}

	@Test
	public void testAdd() {
		CompetitionScheme c = new CompetitionScheme();
		Round r = new Round(5);
		c.add(r);
		assertTrue(c.getRounds().contains(r));
		
	}

	@Test
	public void testGetRound() {
		CompetitionScheme c = new CompetitionScheme();
		Round r1 = new Round(1);
		Round r2 = new Round(2);
		c.add(r1);
		c.add(r2);
		assertEquals(c.getRound(1), r1);
		assertEquals(c.getRound(2), r2);
		assertNull(c.getRound(3));
	}

	@Test
	public void testToString() {
		CompetitionScheme c = new CompetitionScheme();
		Round r = new Round(1);
		c.add(r);
		String expected = "Competition: \n\n" +  r.toString() + "\n\n";
		assertEquals(expected, c.toString());
	}

	@Test
	public void testGetRounds() {
		CompetitionScheme c= new CompetitionScheme();
		Round r = new Round(5);
		c.add(r);
		ArrayList<Round> expected = new ArrayList<Round>();
		expected.add(r);
		assertEquals(expected, c.getRounds());
	}

	@Test
	public void testSetRounds() {
		CompetitionScheme c = new CompetitionScheme();
		Round r= new Round(5);
		ArrayList<Round> array = new ArrayList<Round>();
		array.add(r);
		c.setRounds(array);
		assertEquals(array, c.getRounds());
	}

	@Test
	public void testEqualsObject() {
		CompetitionScheme c = new CompetitionScheme();
		assertTrue(c.equals(c));
		assertFalse(c.equals(null));
		assertFalse(c.equals(new Round(5)));
		c.setRounds(null);
		CompetitionScheme c2 = new CompetitionScheme();
		assertFalse(c.equals(c2));
		c.setRounds(new ArrayList<Round>());
		Round r= new Round(5);
		c2.add(r);
		assertFalse(c.equals(c2));
		c.setRounds(null);
		c2.setRounds(null);
		assertTrue(c.equals(c2));
		c.setRounds(new ArrayList<Round>());
		c2.setRounds(new ArrayList<Round>());
		assertTrue(c.equals(c2));
	}

}
