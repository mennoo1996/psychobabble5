package junitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import libraryClasses.Attacker;
import libraryClasses.Positions;
import gameLogic.TransferInProgress;
import gameLogic.TransferList;

import org.junit.Test;

public class TransferListTest {

	@Test
	public void testTransferList() {
		TransferList tl = new TransferList();
		assertNotNull(tl.getTransfers());
		
	}

	@Test
	public void testAddTransfer() {
		TransferList tl = new TransferList();
		TransferInProgress t = new TransferInProgress(null, 1, 2);
		tl.addTransfer(t);
		assertTrue(tl.getTransfers().contains(t));
	}

	@Test
	public void testGetTransferInt() {
		TransferList tl = new TransferList();
		TransferInProgress t = new TransferInProgress(null, 1, 2);
		tl.addTransfer(t);
		assertNull(tl.getTransfer(-1));
		assertNull(tl.getTransfer(15));
		assertEquals(tl.getTransfer(0), t);
	}

	@Test
	public void testGetTransferString() {
		TransferList tl = new TransferList();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(2), "j", "4", 1, 1, 1, 1, 1, 1, 1, 1, true, 1, 1, 1, 1);
		TransferInProgress t1 = new TransferInProgress(attacker, 1, 1);
		TransferInProgress t2 = new TransferInProgress(attacker2, 1, 1);
		tl.addTransfer(t1);
		tl.addTransfer(t2);
		assertEquals(t1, tl.getTransfer("OOPBoy"));
		assertEquals(t2, tl.getTransfer("4"));
		assertNull(tl.getTransfer("Menno"));
	}

	@Test
	public void testGetTransferPlayer() {
		TransferList tl = new TransferList();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(2), "j", "4", 1, 1, 1, 1, 1, 1, 1, 1, true, 1, 1, 1, 1);
		Attacker attacker3 = new Attacker(new BigDecimal(3), "a", "5", 1, 1, 1, 1, 1, 1, 1, 1, false, 1, 1, 1, 1);
		TransferInProgress t1 = new TransferInProgress(attacker, 1, 1);
		TransferInProgress t2 = new TransferInProgress(attacker2, 1, 1);
		tl.addTransfer(t1);
		tl.addTransfer(t2);
		assertEquals(tl.getTransfer(attacker), t1);
		assertEquals(tl.getTransfer(attacker2), t2);
		assertNull(tl.getTransfer(attacker3));
		
	}

	

	@Test
	public void testTransferExists() {
		TransferList tl = new TransferList();
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(2), "j", "4", 1, 1, 1, 1, 1, 1, 1, 1, true, 1, 1, 1, 1);
		TransferInProgress t1 = new TransferInProgress(attacker, 1, 1);
		TransferInProgress t2 = new TransferInProgress(attacker2, 1, 1);
		tl.addTransfer(t1);
		assertTrue(tl.transferExists(attacker));
		assertFalse(tl.transferExists(attacker2));
		
	}

	@Test
	public void testEqualsObject() {
		TransferList tl = new TransferList();
		assertTrue(tl.equals(tl));
		assertFalse(tl.equals(null));
		assertFalse(tl.equals(new Positions()));
		TransferList tl2 = new TransferList();
		tl.setTransfers(null);
		assertFalse(tl.equals(tl2));
		tl2.setTransfers(null);
		assertTrue(tl.equals(tl2));
		Attacker attacker = new Attacker(new BigDecimal(250000), "Arsenal", "OOPBoy", 18, 42, 7, 3, 2, 1, 13, 5, true, 88, 96, 45, 80);
		Attacker attacker2 = new Attacker(new BigDecimal(2), "j", "4", 1, 1, 1, 1, 1, 1, 1, 1, true, 1, 1, 1, 1);
		ArrayList<TransferInProgress> array1 = new ArrayList<TransferInProgress>();
		ArrayList<TransferInProgress> array2 = new ArrayList<TransferInProgress>();
		tl.setTransfers(array1);
		array2.add(new TransferInProgress(attacker, 1, 1));
		tl2.setTransfers(array2);
		assertFalse(tl.equals(tl2));
		tl.setTransfers(array2);
		assertTrue(tl.equals(tl2));
		
		
	}

}
