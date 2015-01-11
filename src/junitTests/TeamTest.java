package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import libraryClasses.Attacker;
import libraryClasses.Competition;
import libraryClasses.Defender;
import libraryClasses.Goalkeeper;
import libraryClasses.Midfielder;
import libraryClasses.Player;
import libraryClasses.Positions;
import libraryClasses.Standings;
import libraryClasses.Team;

import org.junit.Test;

import xmlIO.XMLParser;

public class TeamTest {

	@Test
	public void testTeam() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		assertNotNull(t.getTeam());
		assertEquals(t.getTeamName(), "team1");
		assertEquals(t.getBudget(), 10, 0);
		assertEquals(t.getStandings(), s);
	}

	@Test
	public void testIsEligible() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		ArrayList<Player> currentTeam = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		for (int i=0;i<10;i++) {
			currentTeam.add(attacker);
			
		}
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 80);
		currentTeam.add(goalkeeper);
		assertTrue(Team.isEligible(currentTeam));
		currentTeam.remove(0);
		assertFalse(Team.isEligible(currentTeam));
		currentTeam.get(0).setEligible(false);
		currentTeam.add(attacker);
		assertFalse(Team.isEligible(currentTeam));
		currentTeam.remove(0);
		currentTeam.add(goalkeeper);
		for (int i=0;i<currentTeam.size();i++) {
			currentTeam.get(i).setEligible(true);
		}
		assertFalse(Team.isEligible(currentTeam));
		
		
		
	}

	@Test
	public void testUpdateStandings() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		t.updateStandings("won", 1, 2);
		assertEquals(t.getStandings().getWon(), 2);
		assertEquals(t.getStandings().getGoalsFor(), 5);
		assertEquals(t.getStandings().getGoalsAgainst(), 7);
	}

	@Test
	public void testGetCurrentTeam() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 80);
		t.addToCurrentTeam(attacker);
		ArrayList<Player> expected = new ArrayList<Player>();
		expected.add(attacker);
		assertEquals(expected, t.getCurrentTeam());
		
	}

	@Test
	public void testSetCurrentTeam() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 80);
		ArrayList<Player> array = new ArrayList<Player>();
		for (int i=0;i<10;i++) {
			array.add(attacker);
		}
		array.add(goalkeeper);
		try {
		t.setCurrentTeam(array);} catch (Exception e) {}
		assertEquals(t.getCurrentTeam(), array);
		array.remove(0);
		try {
			t.setCurrentTeam(array);} catch (Exception e) {assertEquals(e.getMessage(), "A playing team should have 11 players of whom 1 is a goalkeeper");}
		}
	

	@Test
	public void testSetFirst11AsCurrentTeam() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 80);
		Midfielder midfielder = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Defender defender = new Defender(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		ArrayList<Player> expected = new ArrayList<Player>();
		for (int i=0;i<3;i++) {
			t.add(attacker);
			expected.add(attacker);
		}
		
		for (int i=0;i<4;i++) {
			t.add(midfielder);
			expected.add(midfielder);
		}
		
		for (int i=0;i<3;i++) {
			t.add(defender);
			expected.add(defender);
		}
		t.add(goalkeeper);
		expected.add(goalkeeper);
		t.setFirst11AsCurrentTeam();
		assertEquals(expected, t.getCurrentTeam());
		
		ArrayList<Player> expected2 = new ArrayList<Player>();
		Standings s2 = new Standings (1, 2, 3, 4, 5, "team1");
		Team t2 = new Team("team1", 10, s);
		for (int i=0;i<4;i++) {
			t2.add(attacker);
			expected2.add(attacker);
		}
		
		for (int i=0;i<4;i++) {
			t2.add(midfielder);
			expected2.add(midfielder);
		}
		
		for (int i=0;i<4;i++) {
			t2.add(defender);
		}
		expected2.add(defender);
		expected2.add(defender);
		
		t2.add(goalkeeper);
		t2.add(goalkeeper);
		expected2.add(goalkeeper);
		
		t2.setFirst11AsCurrentTeam();
		assertEquals(expected2, t2.getCurrentTeam());
		t2.getTeam().remove(13);
		t2.getTeam().remove(12);
		t2.setFirst11AsCurrentTeam();
		
		
		
		
	}

	@Test
	public void testGetBudget() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		assertEquals(t.getBudget(), 10, 0);
		t.setBudget(20);
		assertEquals(t.getBudget(), 20, 0);
	}

	@Test
	public void testSetBudget() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		assertEquals(t.getBudget(), 10, 0);
		t.setBudget(200);
		assertEquals(t.getBudget(), 200, 0);
	}

	@Test
	public void testAdd() {
		Standings s = new Standings (1, 2, 3, 4, 5, "team1");
		Team t = new Team("team1", 10, s);
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		t.add(attacker);
		assertTrue(t.getTeam().contains(attacker));
	}
	
	@Test
	public void testAddToCurrentTeam() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		t.addToCurrentTeam(attacker);
		assertTrue(t.getCurrentTeam().contains(attacker));
	}

	@Test
	public void testToString() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");
		
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		Player[] array = t.getPositions().getPositionArray();
		
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		for (int i=0;i<array.length;i++) {
			array[i]=attacker;
		}
		t.add(attacker);
		String expected = "team1 with budget: 10.0\n" + t.getStandings().toString() + "\n"+ t.getPositions().toString() + "\n" + attacker.toString();
		System.out.println(t.toString());
		System.out.println(expected);
		assertEquals(expected, t.toString());
	}

	@Test
	public void testGetStandings() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		assertEquals(t.getStandings(), s);
		t.updateStandings("won", 1, 1);
		Standings s2 = new Standings (2, 2, 3, 5, 6, "team1");
		assertEquals(t.getStandings(), s2);
	}

	@Test
	public void testSetStandings() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		assertEquals(t.getStandings(), s);
		Standings s2 = new Standings (2, 3, 4, 5, 6, "team2");
		t.setStandings(s2);
		assertEquals(t.getStandings(), s2);
	}

	@Test
	public void testGetTeam() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		ArrayList<Player> expected = new ArrayList<Player>();
		assertEquals(expected, t.getTeam());
		
	}

	@Test
	public void testSetTeam() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		ArrayList<Player> array = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		array.add(attacker);
		t.setTeam(array);
		assertEquals(t.getTeam(), array);
	}

	@Test
	public void testGetTeamName() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		assertEquals(t.getTeamName(), "team1");
		t.setTeamName("team2");
		assertEquals(t.getTeamName(), "team2");
	}

	@Test
	public void testSetTeamName() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		assertEquals(t.getTeamName(), "team1");
		t.setTeamName("Arsenal");
		assertEquals(t.getTeamName(), "Arsenal");
	}

	@Test
	public void testReplacePlayerInCurrentTeam() {
		Competition competition = XMLParser.readCompetition("files/competitionDatabase_v5.xml", "files/competition-scheme.xml");

		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = competition.getLibrary().getLibrary().get(0);
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 19, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		t.addToCurrentTeam(attacker);
		t.getPositions().getPositionArray()[0]=attacker;
		assertTrue(t.getCurrentTeam().contains(attacker));
		t.replacePlayerInCurrentTeam(attacker, attacker2);
		assertFalse(t.getCurrentTeam().contains(attacker));
		assertTrue(t.getCurrentTeam().contains(attacker2));
	}

	@Test
	public void testGetPositions() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		Positions p = new Positions();
		assertEquals(t.getPositions(), p);
		
	}

	@Test
	public void testSetPositions() {
		Positions p = new Positions();
		Player[] array = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		array[0]=attacker;
		p.setPositionArray(array);
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		t.setPositions(p);
		assertEquals(t.getPositions(), p);
	}
	
	@Test
	public void testEquals() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Team t = new Team ("team1", 10, s);
		assertTrue(t.equals(t));
		assertFalse(t.equals(null));
		assertFalse(t.equals(new Positions()));
		Team t2 = new Team ("team1", 20, s);
		assertFalse(t.equals(t2));
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		t2.setBudget(10);
		t2.setPositions(null);
		assertFalse(t2.equals(t));
		t.setPositions(null);
		assertTrue(t.equals(t2));
		Positions p = new Positions();
		Player[] array = new Player[11];
		array[0]=attacker;
		p.setPositionArray(array);
		t.setPositions(new Positions());
		t2.setPositions(p);
		assertFalse(t.equals(t2));
		t.setPositions(p);
		assertTrue(t.equals(t2));
		t.setStandings(null);
		assertFalse(t.equals(t2));
		t2.setStandings(null);
		assertTrue(t.equals(t2));
		t.setStandings(new Standings(1, 2, 3, 4, 5, "team1"));
		t2.setStandings(new Standings(1, 2, 3, 4, 5, "team1"));
		assertTrue(t.equals(t2));
		t2.setStandings(new Standings(1, 3, 4, 5, 6, "team2"));
		assertFalse(t.equals(t2));
		t2.setStandings(new Standings(1, 2, 3, 4, 5, "team1"));
		t.setTeam(null);
		assertFalse(t.equals(t2));
		t2.setTeam(null);
		assertTrue(t.equals(t2));
		t.setTeam(new ArrayList<Player>());
		ArrayList<Player> array2 = new ArrayList<Player>();
		array2.add(attacker);
		t2.setTeam(array2);
		assertFalse(t.equals(t2));
		t.setTeam(array2);
		assertTrue(t.equals(t2));
		t.setTeamName(null);
		assertFalse(t.equals(t2));
		t2.setTeamName(null);
		assertTrue(t.equals(t2));
		t.setTeamName("1");
		assertFalse(t.equals(t2));
		t2.setTeamName("1");
		assertTrue(t.equals(t2));
		
	}

}
