package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import libraryClasses.Attacker;

import org.junit.Test;

public class FieldPlayerTest {

	@Test
	public void testToString() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.toString(), "Attacker [name=OOPBoy, age=18, team=Arsenal, number=42, price=250000, dribblingValue=88, finishingValue=96, defenseValue=45, staminaValue=80, goals=7, assists=3, yellowCards=2, redCards=1, daysInjured=13, daysSuspended=5, eligible=false]");
	}

	@Test
	public void testGetDribblingValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getDribblingValue(), 88);
	}

	@Test
	public void testSetDribblingValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setDribblingValue(85);
		assertEquals(attacker.getDribblingValue(), 85);
	}

	@Test
	public void testGetFinishingValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getFinishingValue(), 96);
	}

	@Test
	public void testSetFinishingValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setFinishingValue(91);
		assertEquals(attacker.getFinishingValue(), 91);
	}

	@Test
	public void testGetDefenseValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getDefenseValue(), 45);
	}

	@Test
	public void testSetDefenseValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setDefenseValue(58);
		assertEquals(attacker.getDefenseValue(), 58);
	}

	@Test
	public void testGetStaminaValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getStaminaValue(), 80);
	}

	@Test
	public void testSetStaminaValue() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		attacker.setStaminaValue(87);
		assertEquals(attacker.getStaminaValue(), 87);
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
	public void testEqualsDribbling() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 67, 96, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsFinishing() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 89, 45, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsDefense() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 42, 80);
		assertNotEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsStamina() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 87);
		assertNotEquals(attacker1, attacker2);
	}

}
