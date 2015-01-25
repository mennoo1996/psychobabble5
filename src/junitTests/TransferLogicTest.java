package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.Competition;
import gameLogic.GameLogic;
import gameLogic.TransferInProgress;
import gameLogic.TransferList;
import gameLogic.TransferLogic;
import libraryClasses.Library;
import libraryClasses.Player;
import libraryClasses.Team;

import org.junit.After;
import org.junit.Test;

import xmlIO.XMLParser;

public class TransferLogicTest {

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetAnswerForExistingTransfer() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Player player = competition.getLibrary().getLibrary().get(0).getTeam().get(1);
		TransferList existingTransfers = new TransferList();
		assertFalse(TransferLogic.getAnswerForExistingTransfer(player, 1000000, competition.getLibrary(), existingTransfers, 200000000));
		assertTrue(TransferLogic.getAnswerForExistingTransfer(player, 1000000000, competition.getLibrary(), existingTransfers, 100000));
	}

	@Test
	public void testGetAnswer() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Player player = competition.getLibrary().getLibrary().get(0).getTeam().get(5);
		assertTrue(TransferLogic.getAnswer(player, 40000000, competition.getLibrary(), new TransferList()));
		player = competition.getLibrary().getLibrary().get(0).getTeam().get(19);
		assertFalse(TransferLogic.getAnswer(player, 10000, competition.getLibrary(), new TransferList()));
		player = competition.getLibrary().getLibrary().get(0).getTeam().get(21);
		assertTrue(TransferLogic.getAnswer(player, 100000000, competition.getLibrary(), new TransferList()));
		ArrayList<Player> team = competition.getLibrary().getLibrary().get(0).getTeam();
		team.remove(21);
		player = competition.getLibrary().getLibrary().get(0).getTeam().get(20);
		assertFalse(TransferLogic.getAnswer(player, 100000000, competition.getLibrary(), new TransferList()));
		player = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		assertFalse(TransferLogic.getAnswer(player, 34000000, competition.getLibrary(), new TransferList()));
		GameLogic.setSeed(72);
		Library library = competition.getLibrary();
		assertFalse(TransferLogic.getAnswer(player, 35000000*1.02, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.02, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.07, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 35000000*1.07, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 35000000*1.12, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.12, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.17, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 35000000*1.17, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 35000000*1.22, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.22, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.27, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 35000000*1.27, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 35000000*1.32, library, new TransferList()));
		player = library.getLibrary().get(0).getTeam().get(1);
		assertFalse(TransferLogic.getAnswer(player, 1200000*0.89, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 1200000*0.94, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 1200000*0.94, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 1200000*1.02, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 1200000*1.02, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 1200000*1.07, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 1200000*1.07, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 1200000*1.12, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 1200000*1.12, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 1200000*1.17, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 1200000*1.17, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 1200000*1.22, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 1200000*1.22, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 1200000*1.27, library, new TransferList()));
		player = library.getLibrary().get(0).getTeam().get(13);
		assertFalse(TransferLogic.getAnswer(player, 7000000*0.89, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 7000000*0.94, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 7000000*0.94, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 7000000*1.02, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 7000000*1.02, library, new TransferList()));
		assertFalse(TransferLogic.getAnswer(player, 7000000*1.07, library, new TransferList()));
		GameLogic.setSeed(61);
		assertTrue(TransferLogic.getAnswer(player, 7000000*1.07, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 7000000*1.12, library, new TransferList()));
		GameLogic.setSeed(72);
		assertFalse(TransferLogic.getAnswer(player, 7000000*1.12, library, new TransferList()));
		assertTrue(TransferLogic.getAnswer(player, 7000000*1.17, library, new TransferList()));
	}

	@Test
	public void testRequestTransfer() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Player player = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		Team playersTeam = competition.getLibrary().getLibrary().get(1);
		Library library = competition.getLibrary();
		TransferList existingTransfers = new TransferList();
		GameLogic.setSeed(72);
		String expected = "Congratulations! Your bid of 100,000,000 got accepted and Sánchez is now part of your team";
		String result = TransferLogic.requestTransfer(player, playersTeam, 100000000, library, existingTransfers);
		assertEquals(expected, result);
		existingTransfers.addTransfer(new TransferInProgress(player, 40000000, 20000000));
		expected = "Aston Villa did not accept your offer of 30,000,000 for Sánchez. They have indicated they want at least 40,000,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 30000000, library, existingTransfers);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 5,000 for Sánchez. They have indicated they want at least 31,500,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 5000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 32,000,000 for Sánchez. They have indicated they want at least 35,000,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 32000000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 36,000,000 for Sánchez. They have indicated they want at least 36,750,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 36000000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 37,000,000 for Sánchez. They have indicated they want at least 38,500,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 37000000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 39,000,000 for Sánchez. They have indicated they want at least 40,250,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 39000000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 40,500,000 for Sánchez. They have indicated they want at least 42,000,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 40500000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 43,000,000 for Sánchez. They have indicated they want at least 43,750,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 43000000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 44,000,000 for Sánchez. They have indicated they want at least 45,500,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 44000000, library, new TransferList());
		assertEquals(expected, result);
		expected = "Congratulations! Your bid of 60,000,000 got accepted and Sánchez is now part of your team";
		result = TransferLogic.requestTransfer(player, playersTeam, 60000000, library, new TransferList());
		assertEquals(expected, result);
		TransferList existingTransfers2 = new TransferList();
		TransferLogic.requestTransfer(player, playersTeam, 15000000, library, existingTransfers2);
		GameLogic.setSeed(61);
		result = TransferLogic.requestTransfer(player, playersTeam, 60000000, library, existingTransfers2);
		expected = "Congratulations! Your bid of 60,000,000 got accepted and Sánchez is now part of your team";
		assertEquals(expected, result);
		ArrayList<Player> currentTeam = library.getLibrary().get(1).getCurrentTeam();
		currentTeam.add(player);
		TransferLogic.requestTransfer(player, playersTeam, 15000000, library, existingTransfers2);
		result = TransferLogic.requestTransfer(player, playersTeam, 60000000, library, existingTransfers2);
		assertEquals(expected, result);
		GameLogic.setSeed(72);
		TransferList tl3 = new TransferList();
		tl3.addTransfer(new TransferInProgress(player, 5000, 6000));
		result = TransferLogic.requestTransfer(player, playersTeam, 20000000, library, tl3);
		expected = "Aston Villa did not accept your offer of 20,000,000 for Sánchez. They have indicated they want at least 31,500,000 for this player";
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 32,000,000 for Sánchez. They have indicated they want at least 35,000,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 32000000, library, tl3);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 36,000,000 for Sánchez. They have indicated they want at least 36,750,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 36000000, library, tl3);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 37,000,000 for Sánchez. They have indicated they want at least 38,500,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 37000000, library, tl3);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 39,000,000 for Sánchez. They have indicated they want at least 40,250,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 39000000, library, tl3);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 40,500,000 for Sánchez. They have indicated they want at least 42,000,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 40500000, library, tl3);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 43,000,000 for Sánchez. They have indicated they want at least 43,750,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 43000000, library, tl3);
		assertEquals(expected, result);
		expected = "Aston Villa did not accept your offer of 44,000,000 for Sánchez. They have indicated they want at least 45,500,000 for this player";
		result = TransferLogic.requestTransfer(player, playersTeam, 44000000, library, tl3);
		assertEquals(expected, result);
		
		
	}

	@Test
	public void testRequestSell() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Player player = competition.getLibrary().getLibrary().get(0).getTeam().get(0);
		player.setCanBeSold(false);
		player.setDaysNotForSale(3);
		Team playersTeam = competition.getLibrary().getLibrary().get(0);
		Library library = competition.getLibrary();
		String result = TransferLogic.requestSell(player, playersTeam, 1, library);
		String expected = "You can't try to sell this player until after 3 more rounds";
		assertEquals(expected, result);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 200000000, library);
		expected = "Your player was not bought by any team, due to the fact that none of the teams has got enough money to fulfill your asking price";
		assertEquals(expected, result);
		player.setCanBeSold(true);
		GameLogic.setSeed(61);
		result = TransferLogic.requestSell(player, playersTeam, 30000000, library);
		expected = "Congratulations! Sánchez got bought by Chelsea for the price of 30,000,000";
		assertEquals(expected, result);
		result = TransferLogic.requestSell(player, playersTeam, 34000000, library);
		expected = "Congratulations! Sánchez got bought by Chelsea for the price of 34,000,000";
		assertEquals(expected, result);
		GameLogic.setSeed(72);
		result = TransferLogic.requestSell(player, playersTeam, 34000000, library);
		expected = "Unfortunately your player didn't get bought. Lowering the asking price might increase the chances for a team buying your player.";
		assertEquals(expected, result);
		GameLogic.setSeed(61);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 35500000, library);
		expected = "Congratulations! Sánchez got bought by Liverpool for the price of 35,500,000";
		assertEquals(expected, result);
		result = TransferLogic.requestSell(player, playersTeam, 37000000, library);
		expected = "Congratulations! Sánchez got bought by Manchester United for the price of 37,000,000";
		assertEquals(expected, result);
		result = TransferLogic.requestSell(player, playersTeam, 39000000, library);
		expected = "Congratulations! Sánchez got bought by Manchester City for the price of 39,000,000";
		assertEquals(expected, result);
		result = TransferLogic.requestSell(player, playersTeam, 41000000, library);
		expected = "Congratulations! Sánchez got bought by Manchester City for the price of 41,000,000";
		assertEquals(expected, result);
		library.getLibrary().get(1).setBudget(2000000000);
		result = TransferLogic.requestSell(player, playersTeam, 43000000, library);
		expected = "Congratulations! Sánchez got bought by Aston Villa for the price of 43,000,000";
		assertEquals(expected, result);
		result = TransferLogic.requestSell(player, playersTeam, 44000000, library);
		expected = "Congratulations! Sánchez got bought by Aston Villa for the price of 44,000,000";
		assertEquals(expected, result);
		GameLogic.setSeed(72);
		expected = "Unfortunately your player didn't get bought. Lowering the asking price might increase the chances for a team buying your player.";
		result = TransferLogic.requestSell(player, playersTeam, 35500000, library);
		assertEquals(expected, result);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 37000000, library);
		assertEquals(expected, result);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 39000000, library);
		assertEquals(expected, result);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 41000000, library);
		assertEquals(expected, result);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 43000000, library);
		assertEquals(expected, result);
		player.setCanBeSold(true);
		result = TransferLogic.requestSell(player, playersTeam, 44000000, library);
		assertEquals(expected, result);
		
		
		
	}

}
