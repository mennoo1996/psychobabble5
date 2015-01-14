package junitTests;

import static org.junit.Assert.*;
import game.Competition;
import gameLogic.TransferInProgress;
import libraryClasses.Player;
import libraryClasses.Team;

import org.junit.Test;

import xmlIO.XMLParser;

public class TransferInProgressTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testTransferInProgress() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p, 5, 20);
		assertEquals(t.getPlayer(), p);
		assertEquals(t.getPriceReturned(), 5, 5);
		assertEquals(t.getBid(), 20, 5);
		TransferInProgress t2 = new TransferInProgress(p, 5, 20);
		assertEquals(t, t2);
	}

	@Test
	public void testGetPlayer() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p, 5, 5);
		assertEquals(p, t.getPlayer());
	}

	@Test
	public void testSetPlayer() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p1 = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		Player p2 = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p1, 5, 5);
		assertEquals(p1, t.getPlayer());
		t.setPlayer(p2);
		assertEquals(p2, t.getPlayer());
	}

	@Test
	public void testGetPriceReturned() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p, 5, 20);
		assertEquals(t.getPriceReturned(), 5, 5);
		assertNotEquals(t.getPriceReturned(), 20);
	}

	@Test
	public void testSetPriceReturned() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p, 5, 20);
		assertEquals(t.getPriceReturned(), 5, 5);
		t.setPriceReturned(10);
		assertEquals(t.getPriceReturned(), 10, 0);
	}

	@Test
	public void testGetBid() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t= new TransferInProgress(p, 10, 20);
		assertEquals(t.getBid(), 20, 1);
	}

	@Test
	public void testSetBid() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p, 20, 25);
		assertEquals(t.getBid(), 25, 0);
		t.setBid(30);
		assertEquals(t.getBid(), 30, 0);
	}
	
	@Test
	public void testEquals() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		Player p = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		TransferInProgress t = new TransferInProgress(p, 20, 25);
		assertTrue(t.equals(t));
		assertFalse(t.equals(null));
		assertFalse(t.equals(p));
		TransferInProgress t2 = new TransferInProgress (p, 20, 30);
		assertFalse(t.equals(t2));
		TransferInProgress t3 = new TransferInProgress (p, 10, 25);
		assertFalse(t.equals(t3));
		Player p2 = competition.getLibrary().getLibrary().get(0).getTeam().get(1);
		TransferInProgress t4 = new TransferInProgress(p2, 20, 25);
		assertFalse(t.equals(t4));
		TransferInProgress t5 = new TransferInProgress (p, 20, 25);
		assertTrue(t.equals(t5));
		TransferInProgress t6 = new TransferInProgress(null, 20, 25);
		assertFalse(t6.equals(t3));
		TransferInProgress t7 = new TransferInProgress (null, 20, 25);
		assertTrue(t6.equals(t7));
	}

}
