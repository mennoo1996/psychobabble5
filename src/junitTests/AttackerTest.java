package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import libraryClasses.Attacker;

import org.junit.Test;

public class AttackerTest {

	@Test
	public void testGetPlayerTypeAttacker() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker.getPlayerType(), "Attacker");
	}
	
	@Test
	public void testEqualsTrue() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(attacker1, attacker2);
	}
	
	@Test
	public void testEqualsFalse() {
		Attacker attacker1 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBo2y", 18, 21, 7, 3, 2, 3, 13, 8, false, 88, 96, 96, 80);
		assertFalse(attacker1.equals(attacker2));
	}

}
