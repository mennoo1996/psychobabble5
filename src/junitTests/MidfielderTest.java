package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import libraryClasses.Defender;
import libraryClasses.Midfielder;

import org.junit.Test;

public class MidfielderTest {

	@Test
	public void testGetPlayerTypeMidfielder() {
		Midfielder midfielder = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(midfielder.getPlayerType(), "Midfielder");
	}

	@Test
	public void testEqualsTrue() {
		Midfielder midfielder1 = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Midfielder midfielder2 = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		assertEquals(midfielder1, midfielder2);
	}
	
	@Test
	public void testEqualsFalse() {
		Midfielder midfielder1 = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Midfielder midfielder2 = new Midfielder(new BigDecimal(250000), "Arsenal", "OOPBo2y", 18, 21, 7, 3, 2, 3, 13, 8, false, 88, 96, 96, 80);
		assertFalse(midfielder1.equals(midfielder2));
	}
}
