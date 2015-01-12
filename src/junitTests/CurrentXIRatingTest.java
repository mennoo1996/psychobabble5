package junitTests;

import static org.junit.Assert.*;
import libraryClasses.Player;
import libraryClasses.Team;

import org.junit.Test;

import xmlIO.XMLParser;
import game.Competition;
import gameLogic.*;
public class CurrentXIRatingTest {

	@Test
	public void testCurrentXIRating() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getFinishing(), 1);
		assertEquals(r.getDribbling(), 2);
		assertEquals(r.getStamina(), 3);
		assertEquals(r.getDefending(), 4);
		assertEquals(r.getGoalkeeping(), 5);
		assertEquals(r.getTotal(), 6);
		CurrentXIRating r2 = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r, r2);
	}

	@Test
	public void testToString() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		String expected = "CurrentXIRating [finishing=1, dribbling=2, stamina=3, defending=4, goalkeeping=5, total=6]";
		assertEquals(expected, r.toString());
	}

	@Test
	public void testGetFinishing() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getFinishing(), 1);
		r.setFinishing(2);
		assertEquals(r.getFinishing(), 2);
	}

	@Test
	public void testSetFinishing() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getFinishing(), 1);
		r.setFinishing(3);
		assertEquals(r.getFinishing(), 3);
	}

	@Test
	public void testGetStamina() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getStamina(), 3);
		r.setStamina(5);
		assertEquals(r.getStamina(), 5);
	}

	@Test
	public void testSetStamina() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getStamina(), 3);
		r.setStamina(30);
		assertEquals(r.getStamina(), 30);
	}

	@Test
	public void testGetDefending() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getDefending(), 4);
		r.setDefending(2);
		assertEquals(r.getDefending(), 2);
	}

	@Test
	public void testSetDefending() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getDefending(), 4);
		r.setDefending(40);
		assertEquals(r.getDefending(), 40);
	}

	@Test
	public void testGetGoalkeeping() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getGoalkeeping(), 5);
		r.setGoalkeeping(4);
		assertEquals(r.getGoalkeeping(), 4);
		
	}

	@Test
	public void testSetGoalkeeping() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getGoalkeeping(), 5);
		r.setGoalkeeping(40);
		assertEquals(r.getGoalkeeping(), 40);
	}

	@Test
	public void testGetTotal() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getTotal(), 6);
		r.setTotal(5);
		assertEquals(r.getTotal(), 5);
	}

	@Test
	public void testSetTotal() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getTotal(), 6);
		r.setTotal(50);
		assertEquals(r.getTotal(), 50);
	}

	@Test
	public void testGetCurrentXIRating() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Team t = competition.getLibrary().getLibrary().get(0);
		CurrentXIRating expected = new CurrentXIRating(82, 67, 72, 79, 80, 380);
		assertEquals(expected, CurrentXIRating.getCurrentXIRating(t));
		Player p = t.getTeam().get(19);
		t.replacePlayerInCurrentTeam(t.getTeam().get(4), p);
		expected.setDribbling(66);
		expected.setStamina(70);
		expected.setDefending(78);
		expected.setTotal(376);
		assertEquals(expected, CurrentXIRating.getCurrentXIRating(t));
		
		
	}

	@Test
	public void testEqualsObject() {
		CurrentXIRating r = new CurrentXIRating (1, 2, 3, 4, 5, 6);
		assertTrue(r.equals(r));
		assertFalse(r.equals(null));
		assertFalse(r.equals(new TransferList()));
		CurrentXIRating r2 = new CurrentXIRating (2, 2, 3, 4, 5, 6);

		CurrentXIRating r3 = new CurrentXIRating (1, 3, 3, 4, 5, 6);
		CurrentXIRating r4 = new CurrentXIRating (1, 2, 4, 4, 5, 6);
		CurrentXIRating r5 = new CurrentXIRating (1, 2, 3, 5, 5, 6);
		CurrentXIRating r6 = new CurrentXIRating (1, 2, 3, 4, 6, 6);
		CurrentXIRating r7 = new CurrentXIRating (1, 2, 3, 4, 5, 7);
		assertFalse(r.equals(r2));
		assertFalse(r.equals(r3));
		assertFalse(r.equals(r4));
		assertFalse(r.equals(r5));
		assertFalse(r.equals(r6));
		assertFalse(r.equals(r7));
		
		
		CurrentXIRating r8 = new CurrentXIRating (1, 2, 3, 4, 5, 6);
		assertTrue(r.equals(r8));	
	}
	

	@Test
	public void testGetDribbling() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getDribbling(), 2);
		r.setDribbling(4);
		assertEquals(r.getDribbling(), 4);
		
	}

	@Test
	public void testSetDribbling() {
		CurrentXIRating r = new CurrentXIRating(1, 2, 3, 4, 5, 6);
		assertEquals(r.getDribbling(), 2);
		r.setDribbling(40);
		assertEquals(r.getDribbling(), 40);
	}

}
