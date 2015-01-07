package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import libraryClasses.Attacker;
import libraryClasses.Player;
import libraryClasses.Positions;

import org.junit.Test;

public class PositionsTest {

	@Test
	public void testPositions() {
		Player[] expected = new Player[11];
		Positions p = new Positions();
		Player[] result = p.getPositionArray();
		assertEquals(expected, result);
	}

	@Test
	public void testPositionsPlayerArray() {
		Player[] array1 = new Player[11];
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		array1[0]=attacker;
		array1[5]=attacker;
		Player[] array2 = new Player[11];
		array2[0]=attacker;
		array2[5]=attacker;
		Positions p = new Positions(array2);
		Player[] result = p.getPositionArray();
		assertEquals(result[0], array1[0]);
		assertEquals(result[5], array1[5]);
	}

	@Test
	public void testGetPositionArray() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		Player[] array = new Player[11];
		array[0]=attacker;
		Positions p = new Positions(array);
		Player[] result = p.getPositionArray();
		assertEquals(result, array);
	}

	@Test
	public void testSetPositionArray() {
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 2, 1, false, 88, 96, 45, 80);
		Player[] array1 = new Player[11];
		array1[0]=attacker;
		array1[1]=attacker;
		Positions p = new Positions(array1);
		Player[] array2 = new Player[11];
		array2[2]=attacker;
		array2[3]=attacker;
		p.setPositionArray(array2);
		Player[] result = p.getPositionArray();
		assertNull(result[0]);
		assertNotNull(result[2]);
		assertEquals(result[2], attacker);
	}

}
