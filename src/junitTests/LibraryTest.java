package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import libraryClasses.Competition;
import libraryClasses.CompetitionScheme;
import libraryClasses.Library;
import libraryClasses.Standings;
import libraryClasses.Team;

import org.junit.Test;

import xmlIO.XMLParser;

public class LibraryTest {

	@Test
	public void testLibrary() {
		Library l = new Library();
		assertNotNull(l.getLibrary());
	}

	@Test
	public void testAdd() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		Library l = new Library();
		Team t = competition.getLibrary().getLibrary().get(0);
		assertEquals(l.getLibrary().size(), 0);
		l.add(t);
		assertEquals(l.getLibrary().size(), 1);
		assertTrue(l.getLibrary().contains(t));
	}

	@Test
	public void testGetTeamForName() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		Library l = new Library();
		assertNull(l.getTeamForName("Arsenal"));
		Team arsenal = competition.getLibrary().getLibrary().get(0);
		l.add(arsenal);
		assertEquals(l.getTeamForName("Arsenal"), arsenal);
		assertNull(l.getTeamForName("Burnley"));
	}

	@Test
	public void testToString() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		Library l = new Library();
		Team t = competition.getLibrary().getLibrary().get(0);
		l.add(t);
		Team t2 = competition.getLibrary().getLibrary().get(1);
		l.add(t2);
		String expected = "Library:\n\n" + t.toString() + "\n\n" + t2.toString();
		assertEquals(expected, l.toString());
	}

	@Test
	public void testGetLibrary() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		Library l = new Library();
		Team t = competition.getLibrary().getLibrary().get(0);
		l.add(t);
		ArrayList<Team> expected = new ArrayList<Team>();
		expected.add(t);
		assertEquals(expected, l.getLibrary());
		
	}

	@Test
	public void testSetLibrary() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		Library l = new Library();
		Team t = competition.getLibrary().getLibrary().get(0);
		l.add(t);
		Team t2 = competition.getLibrary().getLibrary().get(1);
		ArrayList<Team> al = new ArrayList<Team>();
		al.add(t2);
		l.setLibrary(al);
		assertEquals(al, l.getLibrary());
	}
	
	@Test
	public void testEquals() {
		Library l = new Library();
		assertTrue(l.equals(l));
		assertFalse(l.equals(null));
		assertFalse(l.equals(new CompetitionScheme()));
		Library l2 = new Library();
		l.setLibrary(null);
		assertFalse(l.equals(l2));
		l2.setLibrary(null);
		assertTrue(l.equals(l2));
		Library l3 = new Library();
		Library l4 = new Library();
		assertTrue(l3.equals(l4));
		l3.add(new Team("", 1, new Standings(1, 2, 3, 4, 5, "team1")));
		assertFalse(l3.equals(l4));
	}
}


