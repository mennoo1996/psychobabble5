package junitTests;

import static org.junit.Assert.*;
import libraryClasses.Library;
import libraryClasses.Standings;
import libraryClasses.Team;
import game.Competition;
import game.Game;

import java.util.Date;

import org.junit.Test;

import schemeClasses.CompetitionScheme;
import xmlIO.XMLParser;

public class GameTest {

	@Test
	public void testGame() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getName(), "Menno");
		assertEquals(g.getSavefileData(), "files/competitionDatabase_v5.xml");
		assertEquals(g.getSavefileScheme(), "files/competition-scheme.xml");
		assertNotNull(g.getDate());
		assertNotNull(g.getCompetition());
		assertEquals(g.getTeam().getTeamName(), "Arsenal");
	}

	@Test
	public void testGetName() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getName(), "Menno");
		g.setName("Bart");
		assertEquals(g.getName(), "Bart");
	}

	@Test
	public void testSetName() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getName(), "Menno");
		g.setName("Mark");
		assertEquals(g.getName(), "Mark");
	}

	@Test
	public void testGetTeam() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getTeam().getTeamName(), "Arsenal");
		g.setTeam(new Team("Manchester City", 10, new Standings(1, 3, 4, 5, 5, "team1")));
		assertEquals(g.getTeam().getTeamName(), "Manchester City");
	}

	@Test
	public void testSetTeam() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getTeam().getTeamName(), "Arsenal");
		g.setTeam(new Team("Chelsea", 10, new Standings(1, 3, 4, 5, 5, "team1")));
		assertEquals(g.getTeam().getTeamName(), "Chelsea");
	
	}

	@Test
	public void testGetCompetition() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		assertEquals(competition, g.getCompetition());
	}

	@Test
	public void testSetCompetition() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		Competition competition =new Competition(new Library(), new CompetitionScheme(), 5);
		
		
		g.setCompetition(competition);
		assertEquals(competition, g.getCompetition());
		
		
	}

	@Test
	public void testGetDate() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		Date d = g.getDate();
		assertTrue(Date.UTC(d.getYear(), d.getMonth(), d.getDay(), d.getHours(), d.getMinutes(), d.getSeconds())<=System.currentTimeMillis());
		
	}

	@Test
	public void testSetDate() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		g.setDate(new Date(1400));
		assertFalse(g.getDate().getYear()==1970);
	}



	@Test
	public void testToString() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		String expected = "Game [name=Menno, savefileData=files/competitionDatabase_v5.xml, savefileScheme=files/competition-scheme.xml, team=Arsenal, date=" + g.getDate() + "]";
		assertEquals(expected, g.toString());
	}

	@Test
	public void testGetSavefileData() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getSavefileData(), "files/competitionDatabase_v5.xml");
		
	}

	@Test
	public void testSetSavefileData() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		g.setSavefileData("hoi");
		assertEquals(g.getSavefileData(), "hoi");
	}

	@Test
	public void testGetSavefileScheme() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		assertEquals(g.getSavefileScheme(), "files/competition-scheme.xml");
		
	}

	@Test
	public void testSetSavefileScheme() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal");
		g.setSavefileScheme("okay");
		assertEquals("okay", g.getSavefileScheme());
	}

}
