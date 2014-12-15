package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import libraryClasses.Attacker;
import libraryClasses.Goalkeeper;

import org.junit.Test;

public class GoalkeeperTest {
	
	@Test
	public void testGetPlayerTypeGoalkeeper() {
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		assertEquals(goalkeeper.getPlayerType(), "Goalkeeper");
	}

	@Test
	public void testToString() {
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		assertEquals(goalkeeper.toString(), "Goalkeeper [name=OOPBoy, age=18, team=Arsenal, number=42, price=250000, goalkeeperValue=80, goals=7, assists=3, yellowCards=2, redCards=1, daysInjured=13, daysSuspended=5, eligible=false]");
	}

	@Test
	public void testGetGoalkeeperValue() {
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		assertEquals(goalkeeper.getGoalkeeperValue(), 80);
	}

	@Test
	public void testSetGoalkeeperValue() {
		Goalkeeper goalkeeper = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		goalkeeper.setGoalkeeperValue(96);
		assertEquals(goalkeeper.getGoalkeeperValue(), 96);
	}
	
	@Test
	public void testEqualsTrue() {
		Goalkeeper goalkeeper1 = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper goalkeeper2 = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		assertEquals(goalkeeper1, goalkeeper2);
	}
	
	@Test
	public void testEqualsTrueSameObject() {
		Goalkeeper goalkeeper1 = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		assertEquals(goalkeeper1, goalkeeper1);
	}
	
	@Test
	public void testEqualsSuper() {
		Goalkeeper goalkeeper1 = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper goalkeeper2 = new Goalkeeper(new BigDecimal(340000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		assertNotEquals(goalkeeper1, goalkeeper2);
	}
	
	@Test
	public void testEqualsGoalkeeperValue() {
		Goalkeeper goalkeeper1 = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper goalkeeper2 = new Goalkeeper(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 89);
		assertNotEquals(goalkeeper1, goalkeeper2);
	}

}
