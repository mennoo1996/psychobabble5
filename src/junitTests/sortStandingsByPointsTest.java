package junitTests;

import static org.junit.Assert.*;
import libraryClasses.Standings;
import libraryClasses.sortStandingsByPoints;

import org.junit.Test;

public class sortStandingsByPointsTest {

	@Test
	public void testCompare() {
		Standings s = new Standings(1, 2, 3, 4, 5, "team1");
		Standings s2 = new Standings(2, 2, 3, 4, 5, "team1");
		Standings s3 = new Standings(1, 2, 3, 4, 5, "team1");
		assertEquals(1, new sortStandingsByPoints().compare(s, s2));
		assertEquals(0, new sortStandingsByPoints().compare(s, s3));
		assertEquals(-1, new sortStandingsByPoints().compare(s2, s));
		
	}

}
