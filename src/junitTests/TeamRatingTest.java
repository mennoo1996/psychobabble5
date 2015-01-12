package junitTests;

import static org.junit.Assert.*;
import libraryClasses.Library;
import libraryClasses.Team;
import game.Competition;
import gameLogic.TeamRating;

import org.junit.Test;

import xmlIO.XMLParser;

public class TeamRatingTest {

	@Test
	public void testTeamRating() {
		TeamRating t = new TeamRating(1, 2, 3, 4, 5, 6);
		assertEquals(t.getFinishing(), 1);
		assertEquals(t.getDribbling(), 2);
		assertEquals(t.getStamina(), 3);
		assertEquals(t.getDefending(), 4);
		assertEquals(t.getGoalkeeping(), 5);
		assertEquals(t.getTotal(), 6);
		
	}

	@Test
	public void testCalculateTeamRating() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		Team t = competition.getLibrary().getLibrary().get(0);
		TeamRating tr = TeamRating.calculateTeamRating(t);
		TeamRating expected = new TeamRating(77, 67, 73, 75, 78, 370);
		assertEquals(expected, tr);
		
		
	}

	@Test
	public void testToString() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		String expected = "TeamRating [finishing=1, dribbling=2, stamina=3, defending=4, goalkeeping=5, total=6]";
		assertEquals(expected, r.toString());
	}

	@Test
	public void testGetTotal() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getTotal(), 6);
		r.setTotal(2);
		assertEquals(r.getTotal(), 2);
	}

	@Test
	public void testGetFinishing() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getFinishing(), 1);
		r.setFinishing(3);
		assertEquals(r.getFinishing(), 3);
	}

	@Test
	public void testSetFinishing() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getFinishing(), 1);
		r.setFinishing(40);
		assertEquals(r.getFinishing(), 40);
	}

	@Test
	public void testGetDribbling() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getDribbling(), 2);
		r.setDribbling(4);
		assertEquals(r.getDribbling(), 4);
	}

	@Test
	public void testSetDribbling() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getDribbling(), 2);
		r.setDribbling(40);
		assertEquals(r.getDribbling(), 40);
	}

	@Test
	public void testGetStamina() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getStamina(), 3);
		r.setStamina(5);
		assertEquals(r.getStamina(), 5);
	}

	@Test
	public void testSetStamina() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getStamina(), 3);
		r.setStamina(50);
		assertEquals(r.getStamina(), 50);
	}

	@Test
	public void testGetDefending() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getDefending(), 4);
		r.setDefending(5);
		assertEquals(r.getDefending(), 5);
	}

	@Test
	public void testSetDefending() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getDefending(), 4);
		r.setDefending(30);
		assertEquals(r.getDefending(), 30);
		
	}

	@Test
	public void testGetGoalkeeping() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getGoalkeeping(), 5);
		r.setGoalkeeping(4);
		assertEquals(r.getGoalkeeping(), 4);
	}

	@Test
	public void testSetGoalkeeping() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getGoalkeeping(), 5);
		r.setGoalkeeping(50);
		assertEquals(r.getGoalkeeping(), 50);
	}

	@Test
	public void testSetTotal() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertEquals(r.getTotal(), 6);
		r.setTotal(50);
		assertEquals(r.getTotal(), 50);
	}
	
	@Test
	public void testEquals() {
		TeamRating r = new TeamRating (1, 2, 3, 4, 5, 6);
		assertTrue(r.equals(r));
		assertFalse(r.equals(null));
		assertFalse(r.equals(new Library()));
		TeamRating r2 = new TeamRating (2, 2, 3, 4, 5, 6);
		TeamRating r3 = new TeamRating (1, 3, 3, 4, 5, 6);
		TeamRating r4 = new TeamRating (1, 2, 4, 4, 5, 6);
		TeamRating r5 = new TeamRating (1, 2, 3, 5, 5, 6);
		TeamRating r6 = new TeamRating (1, 2, 3, 4, 6, 6);
		TeamRating r7 = new TeamRating (1, 2, 3, 4, 5, 7);
		TeamRating r8 = new TeamRating (1, 2, 3, 4, 5, 6);
		assertFalse(r.equals(r2));
		assertFalse(r.equals(r3));
		assertFalse(r.equals(r4));
		assertFalse(r.equals(r5));
		assertFalse(r.equals(r6));
		assertFalse(r.equals(r7));
		assertTrue(r.equals(r8));
	}

}
