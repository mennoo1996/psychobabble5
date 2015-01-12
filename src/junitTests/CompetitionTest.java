package junitTests;

import static org.junit.Assert.*;
import game.Competition;
import libraryClasses.Library;
import libraryClasses.Player;
import libraryClasses.Standings;
import libraryClasses.Team;

import org.junit.Test;

import schemeClasses.CompetitionScheme;
import schemeClasses.Round;
import xmlIO.XMLParser;

public class CompetitionTest {

	@Test
	public void testCompetition() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		assertEquals(l, com.getLibrary());
		assertEquals(c, com.getScheme());
		assertEquals(0, com.getRoundsPlayed());
	}

	

	


	@Test
	public void testToString() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		String expected = "Competition, roundsPlayed = 0\n\n" + l.toString() + "\n\n" + c.toString() + "\nEnd of competition";
		assertEquals(expected, com.toString());
	}

	@Test
	public void testGetLibrary() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		assertEquals(l, com.getLibrary());
	}

	@Test
	public void testSetLibrary() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		l.add(new Team("team1", 10, new Standings(1, 2, 3, 4, 5, "team1")));
		com.setLibrary(l);
		assertEquals(com.getLibrary(), l);
	}

	@Test
	public void testGetScheme() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		assertEquals(c, com.getScheme());
	}

	@Test
	public void testSetScheme() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		c.add(new Round(1));
		com.setScheme(c);
		assertEquals(c, com.getScheme());
		
	}

	@Test
	public void testGetRoundsPlayed() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		assertEquals(0, com.getRoundsPlayed());
	}

	@Test
	public void testSetRoundsPlayed() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		com.setRoundsPlayed(5);
		assertEquals(5, com.getRoundsPlayed());
	}

	@Test
	public void testEqualsObject() {
		Library l = new Library();
		CompetitionScheme c = new CompetitionScheme();
		Competition com = new Competition(l, c, 0);
		assertTrue(com.equals(com));
		assertFalse(com.equals(null));
		assertFalse(com.equals(new Library()));
		Competition com2 = new Competition(l, c, 0);
		com.setLibrary(null);
		assertFalse(com.equals(com2));
		com2.setLibrary(null);
		assertTrue(com.equals(com2));
		com.setLibrary(l);
		assertFalse(com.equals(com2));
		com2.setLibrary(l);
		assertTrue(com.equals(com2));
		com.setRoundsPlayed(1);
		assertFalse(com.equals(com2));
		com.setRoundsPlayed(0);
		com.setScheme(null);
		assertFalse(com.equals(com2));
		com2.setScheme(null);
		assertTrue(com.equals(com2));
		com.setScheme(c);
		assertFalse(com.equals(com2));
		
	}
	
		@Test
		public void testGetTopScorers() {
			Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
			for (int i=0;i<38;i++) {
				competition.playRound();
			}
			Player[] result = competition.getTopScorers();
			for (int i=1;i<440;i++) {
				assertTrue(result[i-1].getGoals()>=result[i].getGoals());
				
			}
			
			
		}
		@Test
		public void testGetMostAssists() {
			Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
			for (int i=0;i<38;i++) {
				competition.playRound();
			}
			Player[] result = competition.getMostAssists();
			for (int i=1;i<440;i++) {
				assertTrue(result[i-1].getAssists()>=result[i].getAssists());
				
			}
			
			
		}
		@Test
		public void testGetMostYellow() {
			Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
			for (int i=0;i<38;i++) {
				competition.playRound();
			}
			Player[] result = competition.getMostYellow();
			for (int i=1;i<440;i++) {
				assertTrue(result[i-1].getYellowcards()>=result[i].getYellowcards());
				
			}
			
			
		}
		@Test
		public void testGetMostRed() {
			Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
			for (int i=0;i<38;i++) {
				competition.playRound();
			}
			Player[] result = competition.getMostRed();
			for (int i=1;i<440;i++) {
				assertTrue(result[i-1].getRedcards()>=result[i].getRedcards());
				
			}
			
			
		}

}
