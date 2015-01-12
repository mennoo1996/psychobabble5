package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import libraryClasses.Attacker;
import libraryClasses.Midfielder;

import org.junit.Test;

import schemeClasses.Match;

public class PlayerTest {

	@Test
	public void testRoundPlayedRound1Injured() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		assertEquals(attacker.getDaysInjured(), 1);
	}
	
	@Test
	public void testRoundPlayedRound1Suspended() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		assertEquals(attacker.getDaysSuspended(), 0);
	}
	
	@Test
	public void testRoundPlayedRound1Eligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		assertEquals(attacker.isEligible(), false);
	}
	
	@Test
	public void testRoundPlayedRound2Injured() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		attacker.roundPlayed();
		assertEquals(attacker.getDaysInjured(), 0);
	}
	
	@Test
	public void testRoundPlayedRound2Suspended() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		attacker.roundPlayed();
		assertEquals(attacker.getDaysSuspended(), 0);
	}
	
	@Test
	public void testRoundPlayedRound2Eligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		attacker.roundPlayed();
		assertEquals(attacker.isEligible(), true);
	}
	
	@Test
	public void testRoundPlayedRound3Injured() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		attacker.roundPlayed();
		attacker.roundPlayed();
		assertEquals(attacker.getDaysInjured(), 0);
	}
	
	@Test
	public void testRoundPlayedRound3Suspended() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		attacker.roundPlayed();
		attacker.roundPlayed();
		assertEquals(attacker.getDaysSuspended(), 0);
	}
	
	@Test
	public void testRoundPlayedRound1InjuredZeroSuspended() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 1, 2, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		assertEquals(attacker.getDaysSuspended(), 1);
	}
	
	@Test
	public void testRoundPlayedRound1InjuredZeroInjured() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 1, 2, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		assertEquals(attacker.getDaysInjured(), 0);
	}
	
	@Test
	public void testRoundPlayedRound1InjuredZeroEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 1, 2, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		assertEquals(attacker.isEligible(), false);
	}
	
	@Test
	public void testRoundPlayedRound3Eligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.roundPlayed();
		attacker.roundPlayed();
		attacker.roundPlayed();
		assertEquals(attacker.isEligible(), true);
	}
	
	@Test
	public void testRoundPlayedDaysNotForSale() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		assertEquals(attacker.getDaysNotForSale(), 0);
		attacker.setCanBeSold(false);
		attacker.roundPlayed();
		assertEquals(attacker.isCanBeSold(), true);
		attacker.setDaysNotForSale(3);
		attacker.roundPlayed();
		assertEquals(attacker.getDaysNotForSale(), 2);
	}
	
	@Test
	public void testTriedToSell() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		attacker.setCanBeSold(true);
		attacker.triedToSell();
		assertFalse(attacker.isCanBeSold());
		assertEquals(attacker.getDaysNotForSale(), 3);
	}

	@Test
	public void testGotInjuryEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 0, 0, true, 88, 96, 45, 80);
		attacker.gotInjury(15);
		assertEquals(attacker.isEligible(), false);
		assertEquals(attacker.getDaysInjured(), 15);
	}
	
	@Test
	public void testGotInjuryNotEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 0, 0, true, 88, 96, 45, 80);
		attacker.gotInjury(13);
		assertEquals(attacker.isEligible(), false);
		assertEquals(attacker.getDaysInjured(), 13);
	}

	@Test
	public void testGotSuspensionEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 0, 0, true, 88, 96, 45, 80);
		attacker.gotSuspension(12);
		assertEquals(attacker.isEligible(), false);
		assertEquals(attacker.getDaysSuspended(), 12);
	}
	
	@Test
	public void testGotSuspensionNotEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 0, 0, true, 88, 96, 45, 80);
		attacker.gotSuspension(9);
		assertEquals(attacker.isEligible(), false);
		assertEquals(attacker.getDaysSuspended(), 9);
	}

	@Test
	public void testGetDaysInjured() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getDaysInjured(), 13);
	}

	@Test
	public void testSetDaysInjured() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setDaysInjured(7);
		assertEquals(attacker.getDaysInjured(), 7);
	}

	@Test
	public void testGetDaysSuspended() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getDaysSuspended(), 5);
	}

	@Test
	public void testSetDaysSuspended() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setDaysSuspended(12);
		assertEquals(attacker.getDaysSuspended(), 12);
	}

	@Test
	public void testMadeGoal() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.madeGoal();
		assertEquals(attacker.getGoals(), 8);
	}

	@Test
	public void testMadeAssist() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.madeAssist();
		assertEquals(attacker.getAssists(), 4);
	}

	@Test
	public void testGotYellow() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.gotYellow();
		assertEquals(attacker.getYellowcards(), 3);
	}

	@Test
	public void testGotRed() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.gotRed();
		assertEquals(attacker.getRedcards(), 2);
	}

	@Test
	public void testGetGoals() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getGoals(), 7);
	}

	@Test
	public void testSetGoals() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setGoals(14);
		assertEquals(attacker.getGoals(), 14);
	}

	@Test
	public void testGetAssists() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getAssists(), 3);
	}

	@Test
	public void testSetAssists() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setAssists(7);
		assertEquals(attacker.getAssists(), 7);
	}

	@Test
	public void testGetYellowcards() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getYellowcards(), 2);
	}

	@Test
	public void testSetYellowcards() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setYellowcards(5);;
		assertEquals(attacker.getYellowcards(), 5);
	}

	@Test
	public void testGetRedcards() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getRedcards(), 1);
	}

	@Test
	public void testSetRedcards() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setRedcards(3);
		assertEquals(attacker.getRedcards(), 3);
	}

	@Test
	public void testGetNumber() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getNumber(), 42);
	}

	@Test
	public void testSetNumber() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setNumber(21);;
		assertEquals(attacker.getNumber(), 21);
	}

	@Test
	public void testGetPrice() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getPrice(), new BigDecimal(250000.00));
	}

	@Test
	public void testSetPrice() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setPrice(new BigDecimal(340000.00));
		assertEquals(attacker.getPrice(), new BigDecimal(340000.00));
	}

	@Test
	public void testGetTeam() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getTeam(), "Arsenal");
	}

	@Test
	public void testSetTeam() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setTeam("Everton");
		assertEquals(attacker.getTeam(), "Everton");
	}

	@Test
	public void testSetPlayerType() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setPlayerType("myPlayerType");
		assertEquals(attacker.getPlayerType(), "myPlayerType");
	}

	@Test
	public void testGetName() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getName(), "OOPBoy");
	}

	@Test
	public void testSetName() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setName("Projectkiller");
		assertEquals(attacker.getName(), "Projectkiller");
	}

	@Test
	public void testGetAge() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getAge(), 18);
	}

	@Test
	public void testSetAge() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setAge(27);
		assertEquals(attacker.getAge(), 27);
	}

	@Test
	public void testIsEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.isEligible(), false);
	}

	@Test
	public void testSetEligible() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setEligible(true);
		assertEquals(attacker.isEligible(), true);
	}
	
	@Test
	public void testEqualsTrue() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsTrueSameObject() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker1, attacker1);
	}
	
	@Test
	public void testEqualsNull() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = null;
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsInstance() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Match match2 = new Match("team1", "team2");
		assertNotEquals(attacker1, match2);
	}
	
	@Test
	public void testEqualsPrice() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(340000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsPriceNull() {
		Attacker attacker1 = new Attacker(null, "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(340000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsPriceNullBoth() {
		Attacker attacker1 = new Attacker(null, "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(null, "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsTeam() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Everton", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsTeamNull() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), null, "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Everton", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsTeamNullBoth() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), null, "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), null, "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsName() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "Gekko", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsNameNull() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", null, 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsNameNullBoth() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", null, 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", null, 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsAge() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 26, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsNumber() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 46, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsGoals() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 5, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsAssists() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 6, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsYellowCards() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 4, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsRedCards() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 0, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsDaysInjured() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 17, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsDaysSuspended() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 2, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsEligible() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsPlayertype() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Midfielder midfielder2 = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertNotEquals(attacker1, midfielder2);
	}
	
	@Test
	public void testEqualsPlayertypeNull() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker1.setPlayerType(null);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsPlayertypeNullBoth() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker1.setPlayerType(null);
		attacker2.setPlayerType(null);
		assertEquals(attacker1, attacker2);
	}

}
