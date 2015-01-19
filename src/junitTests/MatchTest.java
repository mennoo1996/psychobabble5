package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import libraryClasses.Attacker;
import libraryClasses.FieldPlayer;
import libraryClasses.Player;
import gameLogic.TransferList;

import org.junit.Test;

import schemeClasses.Match;

public class MatchTest {

	@Test
	public void testMatch() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam1(), "team1");
		assertEquals(m.getTeam2(), "team2");
		assertEquals(m.getScoreTeam1(), -1);
		assertEquals(m.getScoreTeam2(), -1);
	}

	@Test
	public void testToString() {
		Match m = new Match ("team1", "team2");
		String expected = "Match [team1=team1, team2=team2]";
		assertEquals(expected, m.toString());
	}

	@Test
	public void testGetTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam1(), "team1");
		m.setTeam1("team3");
		assertEquals(m.getTeam1(), "team3");
	}

	@Test
	public void testSetTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam1(), "team1");
		m.setTeam1("Arsenal");
		assertEquals(m.getTeam1(), "Arsenal");
	}

	@Test
	public void testGetTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam2(), "team2");
		m.setTeam2("Chelsea");
		assertEquals(m.getTeam2(), "Chelsea");
	}

	@Test
	public void testSetTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getTeam2(), "team2");
		m.setTeam2("Manchester City");
		assertEquals(m.getTeam2(), "Manchester City");
	}

	@Test
	public void testSetScoreTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam1(), -1);
		m.setScoreTeam1(5);
		assertEquals(m.getScoreTeam1(), 5);
	}

	@Test
	public void testSetScoreTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam2(), -1);
		m.setScoreTeam2(6);
		assertEquals(m.getScoreTeam2(), 6);
	}

	@Test
	public void testGetScoreTeam1() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam1(), -1);
		m.setScoreTeam1(5);
		assertEquals(m.getScoreTeam1(), 5);
	}

	@Test
	public void testGetScoreTeam2() {
		Match m = new Match ("team1", "team2");
		assertEquals(m.getScoreTeam2(), -1);
		m.setScoreTeam2(2);
		assertEquals(m.getScoreTeam2(), 2);
	}

	@Test
	public void testGetWinner() {
		Match m = new Match ("team1", "team2");
		m.setScoreTeam1(0);
		m.setScoreTeam2(1);
		assertEquals(m.getWinner(), 2);
		m.setScoreTeam1(1);
		assertEquals(m.getWinner(), 0);
		m.setScoreTeam1(2);
		assertEquals(m.getWinner(), 1);
	}
	
	@Test
	public void testMatch2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertEquals(m.getTeam1(), "team1");
		assertEquals(m.getTeam2(), "team2");
		assertEquals(m.getScore1(), 1);
		assertEquals(m.getScore2(), 2);
	}
	
	@Test
	public void testGetScore1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertEquals(m.getScore1(), 1);
		m.setScore1(4);
		assertEquals(m.getScore1(), 4);
	}
	
	@Test
	public void testSetScore1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertEquals(m.getScore1(), 1);
		m.setScore1(5);
		assertEquals(m.getScore1(), 5);
	}
	
	@Test
	public void testGetScore2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertEquals(m.getScore2(), 2);
		m.setScore2(6);
		assertEquals(m.getScore2(), 6);
	}
	
	@Test
	public void testSetScore2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertEquals(m.getScore2(), 2);
		m.setScore2(19);
		assertEquals(m.getScore2(), 19);
	}
	
	@Test
	public void testGetGoalMakerst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getGoalMakerst1());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setGoalMakerst1(res);
		assertEquals(res, m.getGoalMakerst1());
		
		
	}
	
	public void testSetGoalMakerst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getGoalMakerst1());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setGoalMakerst1(res);
		assertEquals(res, m.getGoalMakerst1());
		res.add(attacker);
		res.add(attacker);
		m.setGoalMakerst1(res);
		assertEquals(res, m.getGoalMakerst1());
		
	}
	
	@Test
	public void testGetGoalMakerst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getGoalMakerst2());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setGoalMakerst2(res);
		assertEquals(res, m.getGoalMakerst2());
		
		
	}
	
	public void testSetGoalMakerst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getGoalMakerst2());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setGoalMakerst2(res);
		assertEquals(res, m.getGoalMakerst2());
		res.add(attacker);
		res.add(attacker);
		m.setGoalMakerst2(res);
		assertEquals(res, m.getGoalMakerst2());
		
	}
	
	@Test
	public void testGetAssistMakerst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getAssistMakerst1());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setAssistMakerst1(res);
		assertEquals(res, m.getAssistMakerst1());
		
		
	}
	
	public void testSetAssistMakerst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getAssistMakerst1());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setAssistMakerst1(res);
		assertEquals(res, m.getAssistMakerst1());
		res.add(attacker);
		res.add(attacker);
		m.setAssistMakerst1(res);
		assertEquals(res, m.getAssistMakerst1());
		
	}
	
	@Test
	public void testGetAssistMakerst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getAssistMakerst2());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setAssistMakerst2(res);
		assertEquals(res, m.getAssistMakerst2());
		
		
	}
	
	public void testSetAssistMakerst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getAssistMakerst2());
		ArrayList<FieldPlayer> res = new ArrayList<FieldPlayer>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setAssistMakerst2(res);
		assertEquals(res, m.getAssistMakerst2());
		res.add(attacker);
		res.add(attacker);
		m.setAssistMakerst2(res);
		assertEquals(res, m.getAssistMakerst2());
		
	}
	
	@Test
	public void testGetYellowCardGetterst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getYellowCardGetterst1());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setYellowCardGetterst1(res);
		assertEquals(res, m.getYellowCardGetterst1());
		
		
	}
	
	public void testSetYellowCardGetterst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getYellowCardGetterst1());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setYellowCardGetterst1(res);
		assertEquals(res, m.getYellowCardGetterst1());
		res.add(attacker);
		res.add(attacker);
		m.setYellowCardGetterst1(res);
		assertEquals(res, m.getYellowCardGetterst1());
		
	}
	
	@Test
	public void testGetYellowCardGetterst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getYellowCardGetterst2());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setYellowCardGetterst2(res);
		assertEquals(res, m.getYellowCardGetterst2());
		
		
	}
	
	public void testSetYellowCardGetterst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getYellowCardGetterst2());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setYellowCardGetterst2(res);
		assertEquals(res, m.getYellowCardGetterst2());
		res.add(attacker);
		res.add(attacker);
		m.setYellowCardGetterst2(res);
		assertEquals(res, m.getYellowCardGetterst2());
		
	}
	
	@Test
	public void testGetRedCardGetterst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getRedCardGetterst1());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setRedCardGetterst1(res);
		assertEquals(res, m.getRedCardGetterst1());
		
		
	}
	
	public void testSetRedCardGetterst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getRedCardGetterst1());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setRedCardGetterst1(res);
		assertEquals(res, m.getRedCardGetterst1());
		res.add(attacker);
		res.add(attacker);
		m.setRedCardGetterst1(res);
		assertEquals(res, m.getRedCardGetterst1());
		
	}
	
	@Test
	public void testGetRedCardGetterst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getRedCardGetterst2());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setRedCardGetterst2(res);
		assertEquals(res, m.getRedCardGetterst2());
		
		
	}
	
	public void testSetRedCardGetterst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getRedCardGetterst2());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setRedCardGetterst2(res);
		assertEquals(res, m.getRedCardGetterst2());
		res.add(attacker);
		res.add(attacker);
		m.setRedCardGetterst2(res);
		assertEquals(res, m.getRedCardGetterst2());
		
	}
	
	@Test
	public void testGetInjuredPlayerst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getInjuredPlayerst1());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setInjuredPlayerst1(res);
		assertEquals(res, m.getInjuredPlayerst1());
		
		
	}
	
	public void testSetInjuredPlayerst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getInjuredPlayerst1());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setInjuredPlayerst1(res);
		assertEquals(res, m.getInjuredPlayerst1());
		res.add(attacker);
		res.add(attacker);
		m.setInjuredPlayerst1(res);
		assertEquals(res, m.getInjuredPlayerst1());
		
	}
	
	@Test
	public void testGetInjuredPlayerst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getInjuredPlayerst2());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setInjuredPlayerst2(res);
		assertEquals(res, m.getInjuredPlayerst2());
		
		
	}
	
	public void testSetInjuredPlayerst2() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getInjuredPlayerst2());
		ArrayList<Player> res = new ArrayList<Player>();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		res.add(attacker);
		m.setInjuredPlayerst2(res);
		assertEquals(res, m.getInjuredPlayerst2());
		res.add(attacker);
		res.add(attacker);
		m.setInjuredPlayerst2(res);
		assertEquals(res, m.getInjuredPlayerst2());
		
	}
	
	@Test
	public void testGetInjuriesLengthst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getInjuriesLengthst1());
		int[] res = new int[5];
		res[0]=3;
		res[4]=1;
		m.setInjuriesLengthst1(res);
		assertEquals(res, m.getInjuriesLengthst1());
		
	}
	
	@Test
	public void testSetInjuriesLengthst1() {
		Match m = new Match("team1", "team2", 1, 2);
		assertNull(m.getInjuriesLengthst1());
		int[] res = new int[5];
		res[0]=3;
		res[4]=1;
		m.setInjuriesLengthst1(res);
		assertEquals(res, m.getInjuriesLengthst1());
		res[2]=5;
		m.setInjuriesLengthst1(res);
		assertEquals(res, m.getInjuriesLengthst1());
	}
	
	@Test
	public void testGetInjuriesLengthst2() {
		Match m = new Match("team2", "team2", 2, 2);
		assertNull(m.getInjuriesLengthst2());
		int[] res = new int[5];
		res[0]=3;
		res[4]=2;
		m.setInjuriesLengthst2(res);
		assertEquals(res, m.getInjuriesLengthst2());
		
	}
	
	@Test
	public void testSetInjuriesLengthst2() {
		Match m = new Match("team2", "team2", 2, 2);
		assertNull(m.getInjuriesLengthst2());
		int[] res = new int[5];
		res[0]=3;
		res[4]=2;
		m.setInjuriesLengthst2(res);
		assertEquals(res, m.getInjuriesLengthst2());
		res[2]=5;
		m.setInjuriesLengthst2(res);
		assertEquals(res, m.getInjuriesLengthst2());
	}
	
	@Test
	public void testEquals() {
		Match m = new Match ("team1", "team2");
		assertTrue(m.equals(m));
		assertFalse(m.equals(null));
		assertFalse(m.equals(new TransferList()));
		Match m2 = new Match ("team1", "team2");
		Match m3 = new Match ("team1", "team2");
		m2.setScoreTeam1(2);
		m3.setScoreTeam2(3);
		assertFalse(m.equals(m2));
		assertFalse(m.equals(m3));
		m.setTeam1(null);
		m2.setScoreTeam1(-1);
		assertFalse(m.equals(m2));
		m.setTeam1("a");
		assertFalse(m.equals(m2));
		m.setTeam2(null);
		m2.setTeam1("a");
		assertFalse(m.equals(m2));
		m.setTeam1(null);
		m2.setTeam1(null);
		Match m4 = new Match (null, "team2");
		Match m5 = new Match (null, "team2");
		assertTrue(m4.equals(m5));
		Match m6 = new Match ("team1", null);
		Match m7 = new Match ("team1", null);
		assertTrue(m6.equals(m7));
		Match m8 = new Match ("a", "b");
		Match m9 = new Match ("a", "c");
		assertFalse(m8.equals(m9));
	}
	

}
