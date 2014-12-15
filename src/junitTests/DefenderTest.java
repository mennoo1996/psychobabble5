package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;

import libraryClasses.Defender;

import org.junit.Test;

public class DefenderTest {

	@Test
	public void testGetPlayerTypeDefender() {
		Defender defender = new Defender(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(defender.getPlayerType(), "Defender");
	}

	@Test
	public void testEqualsTrue() {
		Defender defender1 = new Defender(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Defender defender2 = new Defender(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(defender1, defender2);
	}
	
	@Test
	public void testEqualsFalse() {
		Defender defender1 = new Defender(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Defender defender2 = new Defender(new BigDecimal(250000), "Arsenal", "OOPBo2y", 18, 21, 7, 3, 2, 3, 13, 8, false, 88, 96, 96, 80);
		assertFalse(defender1.equals(defender2));
	}
}
