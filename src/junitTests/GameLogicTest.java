package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameLogic.CurrentXIRating;
import gameLogic.GameLogic;
import libraryClasses.Competition;
import libraryClasses.FieldPlayer;
import libraryClasses.Player;
import libraryClasses.Team;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xmlIO.XMLParser;

public class GameLogicTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		GameLogic.setTesting(false);
		GameLogic.setSeed((int)System.currentTimeMillis());
	}

	@Test
	public void testGetInjuredPlayers() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		
		GameLogic.setTesting(true);
		Team t = competition.getLibrary().getLibrary().get(0);
		GameLogic.setSeed(17);
		ArrayList<Player> result = GameLogic.getInjuredPlayers(t);
		assertTrue(result.size()==0);
		GameLogic.setSeed(18);
		result = GameLogic.getInjuredPlayers(t);
		assertEquals(result.size(), 11);
		
	}

	@Test
	public void testGetInjuriesLength() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		ArrayList<Player> injuredPlayers = new ArrayList<Player>();
		injuredPlayers.add(competition.getLibrary().getLibrary().get(0).getTeam().get(0));
		GameLogic.setSeed(18);
		int[] result = GameLogic.getInjuriesLength(injuredPlayers);
		assertEquals(result[0], 1);
		GameLogic.setSeed(1);
		result = GameLogic.getInjuriesLength(injuredPlayers);
		assertEquals(result[0], 3);
		GameLogic.setSeed(13);
		result = GameLogic.getInjuriesLength(injuredPlayers);
		assertEquals(result[0], 6);
		GameLogic.setSeed(57);
		result = GameLogic.getInjuriesLength(injuredPlayers);
		assertEquals(result[0], 12);
		
	}

	@Test
	public void testGetAmountOfRedCards() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		GameLogic.setSeed(18);
		int result = GameLogic.getAmountOfRedCards(competition.getLibrary().getLibrary().get(0));
		assertEquals(0, result);
		GameLogic.setSeed(13);
		result = GameLogic.getAmountOfRedCards(competition.getLibrary().getLibrary().get(0));
		assertEquals(1, result);
		GameLogic.setSeed(195);
		result = GameLogic.getAmountOfRedCards(competition.getLibrary().getLibrary().get(0));
		assertEquals(2, result);
	}

	@Test
	public void testGetRedCards() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Team t = competition.getLibrary().getLibrary().get(0);
		GameLogic.setSeed(0);
		ArrayList<Player> result = GameLogic.getRedCards(1, t);
		ArrayList<Player> expected = new ArrayList<Player>();
		expected.add(t.getTeam().get(20));
		assertEquals(expected, result);
	}

	@Test
	public void testGetAmountOfYellowCards() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Team t = competition.getLibrary().getLibrary().get(0);
		GameLogic.setSeed(18);
		int result = GameLogic.getAmountOfYellowCards(t);
		assertEquals(0, result);
		GameLogic.setSeed(7);
		result = GameLogic.getAmountOfYellowCards(t);
		assertEquals(1, result);
		GameLogic.setSeed(17);
		result = GameLogic.getAmountOfYellowCards(t);
		assertEquals(2, result);
		GameLogic.setSeed(13);
		result = GameLogic.getAmountOfYellowCards(t);
		assertEquals(3, result);
		GameLogic.setSeed(140);
		result = GameLogic.getAmountOfYellowCards(t);
		assertEquals(4, result);
		GameLogic.setSeed(195);
		result = GameLogic.getAmountOfYellowCards(t);
		assertEquals(5, result);
		
	}

	@Test
	public void testGetYellowCards() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");		
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Team t = competition.getLibrary().getLibrary().get(0);
		GameLogic.setSeed(0);
		ArrayList<Player> result = GameLogic.getYellowCards(1, t);
		ArrayList<Player> expected = new ArrayList<Player>();
		expected.add(t.getTeam().get(20));
		assertEquals(expected, result);
		
	}

	@Test
	public void testGetGoals() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Team t = competition.getLibrary().getLibrary().get(0);
		ArrayList<FieldPlayer> result = GameLogic.getGoals(t, 0);
		assertEquals(new ArrayList<FieldPlayer>(), result);
		GameLogic.setSeed(0);
		ArrayList<FieldPlayer> expected = new ArrayList<FieldPlayer>();
		expected.add((FieldPlayer) t.getTeam().get(15));
		result=GameLogic.getGoals(t, 1);
		assertEquals(expected, result);
	}

	@Test
	public void testGetAssists() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		Team t = competition.getLibrary().getLibrary().get(0);
		ArrayList<FieldPlayer> result = GameLogic.getAssists(t, 0);
		assertEquals(new ArrayList<FieldPlayer>(), result);
		GameLogic.setSeed(0);
		ArrayList<FieldPlayer> expected = new ArrayList<FieldPlayer>();
		expected.add((FieldPlayer) t.getTeam().get(3));
		result=GameLogic.getAssists(t, 1);
		assertEquals(expected, result);
	}

	@Test
	public void testGetMatchResults() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		GameLogic.setSeed(0);
		Team t1 = competition.getLibrary().getLibrary().get(0);
		Team t2 = competition.getLibrary().getLibrary().get(1);
		int[] result = GameLogic.getMatchResults(t1, t2);
		assertEquals(1, result[0]);
		assertEquals(2, result[1]);
		assertEquals(0, result[2]);
		
		GameLogic.setSeed(80);
		result = GameLogic.getMatchResults(t1, t2);
		assertEquals(1, result[0]);
		assertEquals(1, result[1]);
		assertEquals(0, result[2]);
		
		GameLogic.setSeed(82);
		result = GameLogic.getMatchResults(t1, t2);
		assertEquals(1, result[0]);
		assertEquals(2, result[1]);
		assertEquals(0, result[2]);
	}

	@Test
	public void testTeam2WintLoserScore() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		int[] allresults = new int[3];
		CurrentXIRating r1 = new CurrentXIRating (0,0,0,20,0,0);
		CurrentXIRating r2 = new CurrentXIRating (9,0,0,0,0,0);
		CurrentXIRating r3 = new CurrentXIRating (14, 0,0,0,0,0);
		CurrentXIRating r4 = new CurrentXIRating (19, 0,0,0,0,0);
		CurrentXIRating r5 = new CurrentXIRating (24, 0,0,0,0,0);
		CurrentXIRating r6 = new CurrentXIRating (29, 0,0,0,0,0);
		CurrentXIRating r7 = new CurrentXIRating (34, 0,0,0,0,0);
		GameLogic.setSeed(18);
		GameLogic.Team2WintLoserScore(allresults, r2, r1);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintLoserScore(allresults, r2, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(18);
		GameLogic.Team2WintLoserScore(allresults, r3, r1);
		assertEquals(0,allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintLoserScore(allresults , r3, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintLoserScore(allresults, r4, r1);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.Team2WintLoserScore(allresults, r4, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintLoserScore(allresults, r4, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintLoserScore(allresults, r5, r1);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.Team2WintLoserScore(allresults, r5, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintLoserScore(allresults, r5, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintLoserScore(allresults, r6, r1);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.Team2WintLoserScore(allresults, r6, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintLoserScore(allresults, r6, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintLoserScore(allresults, r7, r1);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(13);
		GameLogic.Team2WintLoserScore(allresults, r7, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintLoserScore(allresults, r7, r1);
		assertEquals(2, allresults[1]);
		
	}

	@Test
	public void testTeam2WintWinnerScore() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		int[] allresults=new int[3];
		allresults[1]=0;
		CurrentXIRating r1 = new CurrentXIRating (0,0,0,20,0,0);
		CurrentXIRating r2 = new CurrentXIRating (9,0,0,0,0,0);
		CurrentXIRating r3 = new CurrentXIRating (14, 0,0,0,0,0);
		CurrentXIRating r4 = new CurrentXIRating (19, 0,0,0,0,0);
		CurrentXIRating r5 = new CurrentXIRating (24, 0,0,0,0,0);
		CurrentXIRating r6 = new CurrentXIRating (29, 0,0,0,0,0);
		CurrentXIRating r7 = new CurrentXIRating (34, 0,0,0,0,0);
		GameLogic.setSeed(20);
		GameLogic.Team2WintWinnerScore(allresults, r1, r7);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(12);
		GameLogic.Team2WintWinnerScore(allresults, r1, r7);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.Team2WintWinnerScore(allresults, r1, r7);
		assertEquals(3, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintWinnerScore(allresults, r1, r7);
		assertEquals(4, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintWinnerScore(allresults, r1, r6);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(13);
		GameLogic.Team2WintWinnerScore(allresults, r1, r6);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.Team2WintWinnerScore(allresults, r1, r6);
		assertEquals(3, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintWinnerScore(allresults, r1, r6);
		assertEquals(4, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintWinnerScore(allresults, r1, r5);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.Team2WintWinnerScore(allresults, r1, r5);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintWinnerScore(allresults, r1, r5);
		assertEquals(3, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintWinnerScore(allresults, r1, r4);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintWinnerScore(allresults, r1, r4);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintWinnerScore(allresults, r1, r3);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintWinnerScore(allresults, r1, r3);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team2WintWinnerScore(allresults, r1, r2);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team2WintWinnerScore(allresults, r1, r2);
		assertEquals(2, allresults[2]);
		
	}

	@Test
	public void testTeam1WintLoserScore() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		int[] allresults = new int[3];
		CurrentXIRating r1 = new CurrentXIRating (0,0,0,20,0,0);
		CurrentXIRating r2 = new CurrentXIRating (9,0,0,0,0,0);
		CurrentXIRating r3 = new CurrentXIRating (14, 0,0,0,0,0);
		CurrentXIRating r4 = new CurrentXIRating (19, 0,0,0,0,0);
		CurrentXIRating r5 = new CurrentXIRating (24, 0,0,0,0,0);
		CurrentXIRating r6 = new CurrentXIRating (29, 0,0,0,0,0);
		CurrentXIRating r7 = new CurrentXIRating (34, 0,0,0,0,0);
		GameLogic.setSeed(18);
		GameLogic.Team1WintLoserScore(allresults, r1, r2);
		assertEquals(0, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintLoserScore(allresults, r1, r2);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(18);
		GameLogic.Team1WintLoserScore(allresults, r1, r3);
		assertEquals(0,allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintLoserScore(allresults , r1, r3);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintLoserScore(allresults, r1, r4);
		assertEquals(0, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.Team1WintLoserScore(allresults, r1, r4);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintLoserScore(allresults, r1, r4);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintLoserScore(allresults, r1, r5);
		assertEquals(0, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.Team1WintLoserScore(allresults, r1, r5);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintLoserScore(allresults, r1, r5);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintLoserScore(allresults, r1, r6);
		assertEquals(0, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.Team1WintLoserScore(allresults, r1, r6);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintLoserScore(allresults, r1, r6);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintLoserScore(allresults, r1, r7);
		assertEquals(0, allresults[2]);
		GameLogic.setSeed(13);
		GameLogic.Team1WintLoserScore(allresults, r1, r7);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintLoserScore(allresults, r1, r7);
		assertEquals(2, allresults[2]);
	}

	@Test
	public void testTeam1WintWinnerScore() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		int[] allresults=new int[3];
		allresults[2]=0;
		CurrentXIRating r1 = new CurrentXIRating (0,0,0,20,0,0);
		CurrentXIRating r2 = new CurrentXIRating (9,0,0,0,0,0);
		CurrentXIRating r3 = new CurrentXIRating (14, 0,0,0,0,0);
		CurrentXIRating r4 = new CurrentXIRating (19, 0,0,0,0,0);
		CurrentXIRating r5 = new CurrentXIRating (24, 0,0,0,0,0);
		CurrentXIRating r6 = new CurrentXIRating (29, 0,0,0,0,0);
		CurrentXIRating r7 = new CurrentXIRating (34, 0,0,0,0,0);
		GameLogic.setSeed(20);
		GameLogic.Team1WintWinnerScore(allresults, r7, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(12);
		GameLogic.Team1WintWinnerScore(allresults, r7, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.Team1WintWinnerScore(allresults, r7, r1);
		assertEquals(3, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintWinnerScore(allresults, r7, r1);
		assertEquals(4, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintWinnerScore(allresults, r6, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(13);
		GameLogic.Team1WintWinnerScore(allresults, r6, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.Team1WintWinnerScore(allresults, r6, r1);
		assertEquals(3, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintWinnerScore(allresults, r6, r1);
		assertEquals(4, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintWinnerScore(allresults, r5, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.Team1WintWinnerScore(allresults, r5, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintWinnerScore(allresults, r5, r1);
		assertEquals(3, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintWinnerScore(allresults, r4, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintWinnerScore(allresults, r4, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintWinnerScore(allresults, r3, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintWinnerScore(allresults, r3, r1);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.Team1WintWinnerScore(allresults, r2, r1);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.Team1WintWinnerScore(allresults, r2, r1);
		assertEquals(2, allresults[1]);
		
	}

	@Test
	public void testGelijkSpel() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		int[] allresults = new int[3];
		CurrentXIRating r1 = new CurrentXIRating(0,0,0,0,0,0);
		CurrentXIRating r2 = new CurrentXIRating(-11, 0,0,0,0,0);
		CurrentXIRating r3 = new CurrentXIRating(-6, 0,0,0,0,0);
		CurrentXIRating r4 = new CurrentXIRating(-1, 0,0,0,0,0);
		CurrentXIRating r5 = new CurrentXIRating(4,0,0,0,0,0);
		CurrentXIRating r6 = new CurrentXIRating(9,0,0,0,0,0);
		CurrentXIRating r7 = new CurrentXIRating(14,0,0,0,0,0);
		GameLogic.setSeed(20);
		GameLogic.gelijkSpel(allresults, r1, r7);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(13);
		GameLogic.gelijkSpel(allresults, r1, r7);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.gelijkSpel(allresults, r1, r7);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.gelijkSpel(allresults, r1, r7);
		assertEquals(3, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.gelijkSpel(allresults, r1, r6);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(13);
		GameLogic.gelijkSpel(allresults, r1, r6);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.gelijkSpel(allresults, r1, r6);
		assertEquals(2, allresults[2]);
		GameLogic.setSeed(20);
		GameLogic.gelijkSpel(allresults, r1, r5);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.gelijkSpel(allresults, r1, r5);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.gelijkSpel(allresults, r1, r5);
		assertEquals(2, allresults[1]);
		GameLogic.setSeed(20);
		GameLogic.gelijkSpel(allresults, r1, r4);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.gelijkSpel(allresults, r1, r4);
		assertEquals(1, allresults[1]);
		GameLogic.setSeed(16);
		GameLogic.gelijkSpel(allresults, r1, r3);
		assertEquals(0, allresults[2]);
		GameLogic.setSeed(180);
		GameLogic.gelijkSpel(allresults, r1, r3);
		assertEquals(1, allresults[2]);
		GameLogic.setSeed(16);
		GameLogic.gelijkSpel(allresults, r1, r2);
		assertEquals(0, allresults[1]);
		GameLogic.setSeed(180);
		GameLogic.gelijkSpel(allresults, r1, r2);
		assertEquals(1, allresults[2]);
	}

	@Test
	public void testRandomGenerator() {
		int result=GameLogic.randomGenerator(0, 100);
		assertTrue(result<=100);
		assertTrue(result>0);
		GameLogic.setTesting(true);
		GameLogic.setSeed(180);
		assertEquals(GameLogic.randomGenerator(0,100), 100);
	}

	

	@Test
	public void testGetRandomNumberForTeam() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		for(Team t : competition.getLibrary().getLibrary()) {
			t.setFirst11AsCurrentTeam();
		}
		GameLogic.setTesting(true);
		GameLogic.setSeed(20);
		Team t = competition.getLibrary().getLibrary().get(0);
		CurrentXIRating r = CurrentXIRating.getCurrentXIRating(t);
		int result = GameLogic.getRandomNumberForTeam(r, true);
		assertEquals(36246, result);
		
	}

}
