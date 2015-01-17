package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

import libraryClasses.Attacker;
import libraryClasses.Defender;
import libraryClasses.Goalkeeper;
import libraryClasses.Library;
import libraryClasses.Midfielder;
import libraryClasses.Player;
import libraryClasses.Positions;
import libraryClasses.Standings;
import libraryClasses.Team;

import org.junit.Test;

import xmlIO.XMLParser;

public class XMLParserJunitTest {

	@Test
	public void testReadCompetition() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteCompetition() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadLibrary() {
		Library readLibrary = XMLParser.readLibrary("testFiles/testLibraryRead.xml");
		Attacker at11 = new Attacker(new BigDecimal(250000), "test team 1", "at11", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker at12 = new Attacker(new BigDecimal(250000), "test team 1", "at12", 18, 42, 7, 3, 2, 1, 13, 5, false, 55, 78, 24, 97);
		Attacker at13 = new Attacker(new BigDecimal(250000), "test team 1", "at13", 18, 42, 7, 3, 2, 1, 13, 5, false, 44, 56, 12, 45);
		Attacker at14 = new Attacker(new BigDecimal(250000), "test team 1", "at14", 18, 42, 7, 3, 2, 1, 13, 5, false, 12, 34, 56, 67);
		Attacker at15 = new Attacker(new BigDecimal(250000), "test team 1", "at15", 18, 42, 7, 3, 2, 1, 13, 5, false, 15, 29, 68, 57);
		Midfielder mf11 = new Midfielder(new BigDecimal(250000), "test team 1", "mf11", 18, 42, 7, 3, 2, 1, 13, 5, false, 65, 34, 58, 68);
		Midfielder mf12 = new Midfielder(new BigDecimal(250000), "test team 1", "mf12", 18, 42, 7, 3, 2, 1, 13, 5, false, 85, 68, 38, 63);
		Midfielder mf13 = new Midfielder(new BigDecimal(250000), "test team 1", "mf13", 18, 42, 7, 3, 2, 1, 13, 5, false, 81, 96, 45, 80);
		Midfielder mf14 = new Midfielder(new BigDecimal(250000), "test team 1", "mf14", 18, 42, 7, 3, 2, 1, 13, 5, false, 82, 96, 45, 80);
		Midfielder mf15 = new Midfielder(new BigDecimal(250000), "test team 1", "mf15", 18, 42, 7, 3, 2, 1, 13, 5, false, 83, 96, 45, 80);
		Defender df11 = new Defender(new BigDecimal(250000), "test team 1", "df11", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 81);
		Defender df12 = new Defender(new BigDecimal(250000), "test team 1", "df12", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 82);
		Defender df13 = new Defender(new BigDecimal(250000), "test team 1", "df13", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 83);
		Defender df14 = new Defender(new BigDecimal(250000), "test team 1", "df14", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 84);
		Defender df15 = new Defender(new BigDecimal(250000), "test team 1", "df15", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 85);
		Goalkeeper gk11 = new Goalkeeper(new BigDecimal(250000), "test team 1", "gk11", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper gk12 = new Goalkeeper(new BigDecimal(250000), "test team 1", "gk12", 18, 42, 7, 3, 2, 1, 13, 5, false, 82);
		Attacker at21 = new Attacker(new BigDecimal(250000), "test team 2", "at21", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker at22 = new Attacker(new BigDecimal(250000), "test team 2", "at22", 18, 42, 7, 3, 2, 1, 13, 5, false, 55, 78, 24, 97);
		Attacker at23 = new Attacker(new BigDecimal(250000), "test team 2", "at23", 18, 42, 7, 3, 2, 1, 13, 5, false, 44, 56, 12, 45);
		Attacker at24 = new Attacker(new BigDecimal(250000), "test team 2", "at24", 18, 42, 7, 3, 2, 1, 13, 5, false, 12, 34, 56, 67);
		Attacker at25 = new Attacker(new BigDecimal(250000), "test team 2", "at25", 18, 42, 7, 3, 2, 1, 13, 5, false, 15, 29, 68, 57);
		Midfielder mf21 = new Midfielder(new BigDecimal(250000), "test team 2", "mf21", 18, 42, 7, 3, 2, 1, 13, 5, false, 65, 34, 58, 68);
		Midfielder mf22 = new Midfielder(new BigDecimal(250000), "test team 2", "mf22", 18, 42, 7, 3, 2, 1, 13, 5, false, 85, 68, 38, 63);
		Midfielder mf23 = new Midfielder(new BigDecimal(250000), "test team 2", "mf23", 18, 42, 7, 3, 2, 1, 13, 5, false, 81, 96, 45, 80);
		Midfielder mf24 = new Midfielder(new BigDecimal(250000), "test team 2", "mf24", 18, 42, 7, 3, 2, 1, 13, 5, false, 82, 96, 45, 80);
		Midfielder mf25 = new Midfielder(new BigDecimal(250000), "test team 2", "mf25", 18, 42, 7, 3, 2, 1, 13, 5, false, 83, 96, 45, 80);
		Defender df21 = new Defender(new BigDecimal(250000), "test team 2", "df21", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 81);
		Defender df22 = new Defender(new BigDecimal(250000), "test team 2", "df22", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 82);
		Defender df23 = new Defender(new BigDecimal(250000), "test team 2", "df23", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 83);
		Defender df24 = new Defender(new BigDecimal(250000), "test team 2", "df24", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 84);
		Defender df25 = new Defender(new BigDecimal(250000), "test team 2", "df25", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 85);
		Goalkeeper gk21 = new Goalkeeper(new BigDecimal(250000), "test team 2", "gk21", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper gk22 = new Goalkeeper(new BigDecimal(250000), "test team 2", "gk22", 18, 42, 7, 3, 2, 1, 13, 5, false, 82);
		Attacker at31 = new Attacker(new BigDecimal(250000), "test team 3", "at31", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker at32 = new Attacker(new BigDecimal(250000), "test team 3", "at32", 18, 42, 7, 3, 2, 1, 13, 5, false, 55, 78, 24, 97);
		Attacker at33 = new Attacker(new BigDecimal(250000), "test team 3", "at33", 18, 42, 7, 3, 2, 1, 13, 5, false, 44, 56, 12, 45);
		Attacker at34 = new Attacker(new BigDecimal(250000), "test team 3", "at34", 18, 42, 7, 3, 2, 1, 13, 5, false, 12, 34, 56, 67);
		Attacker at35 = new Attacker(new BigDecimal(250000), "test team 3", "at35", 18, 42, 7, 3, 2, 1, 13, 5, false, 15, 29, 68, 57);
		Midfielder mf31 = new Midfielder(new BigDecimal(250000), "test team 3", "mf31", 18, 42, 7, 3, 2, 1, 13, 5, false, 65, 34, 58, 68);
		Midfielder mf32 = new Midfielder(new BigDecimal(250000), "test team 3", "mf32", 18, 42, 7, 3, 2, 1, 13, 5, false, 85, 68, 38, 63);
		Midfielder mf33 = new Midfielder(new BigDecimal(250000), "test team 3", "mf33", 18, 42, 7, 3, 2, 1, 13, 5, false, 81, 96, 45, 80);
		Midfielder mf34 = new Midfielder(new BigDecimal(250000), "test team 3", "mf34", 18, 42, 7, 3, 2, 1, 13, 5, false, 82, 96, 45, 80);
		Midfielder mf35 = new Midfielder(new BigDecimal(250000), "test team 3", "mf35", 18, 42, 7, 3, 2, 1, 13, 5, false, 83, 96, 45, 80);
		Defender df31 = new Defender(new BigDecimal(250000), "test team 3", "df31", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 81);
		Defender df32 = new Defender(new BigDecimal(250000), "test team 3", "df32", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 82);
		Defender df33 = new Defender(new BigDecimal(250000), "test team 3", "df33", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 83);
		Defender df34 = new Defender(new BigDecimal(250000), "test team 3", "df34", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 84);
		Defender df35 = new Defender(new BigDecimal(250000), "test team 3", "df35", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 85);
		Goalkeeper gk31 = new Goalkeeper(new BigDecimal(250000), "test team 3", "gk31", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper gk32 = new Goalkeeper(new BigDecimal(250000), "test team 3", "gk32", 18, 42, 7, 3, 2, 1, 13, 5, false, 82);
		
		
		Library library = new Library();
		Standings standings1 = new Standings(3,5,1,6,7,"test team 1");
		Team t1 = new Team("test team 1", 50000000, standings1);
		t1.add(at11);
		t1.add(at12);
		t1.add(at13);
		t1.add(at14);
		t1.add(at15);
		t1.add(mf11);
		t1.add(mf12);
		t1.add(mf13);
		t1.add(mf14);
		t1.add(mf15);
		t1.add(df11);
		t1.add(df12);
		t1.add(df13);
		t1.add(df14);
		t1.add(df15);
		t1.add(gk11);
		t1.add(gk12);
		Player[] pos1Array = {gk11, df11, df12, df13, df14, mf11, mf12, mf13, at11, at12, at13};
		t1.setPositions(new Positions(pos1Array));
		Standings standings2 = new Standings(6,2,8,1,3,"test team 2");
		Team t2 = new Team("test team 2", 76000000, standings2);
		t2.add(at21);
		t2.add(at22);
		t2.add(at23);
		t2.add(at24);
		t2.add(at25);
		t2.add(mf21);
		t2.add(mf22);
		t2.add(mf23);
		t2.add(mf24);
		t2.add(mf25);
		t2.add(df21);
		t2.add(df22);
		t2.add(df23);
		t2.add(df24);
		t2.add(df25);
		t2.add(gk21);
		t2.add(gk22);
		Player[] pos2Array = {gk21, df21, df22, df23, df24, mf21, mf22, mf23, at21, at22, at23};
		t2.setPositions(new Positions(pos2Array));
		Standings standings3 = new Standings(5,2,6,3,2,"test team 3");
		Team t3 = new Team("test team 3", 38000000, standings3);
		t3.add(at31);
		t3.add(at32);
		t3.add(at33);
		t3.add(at34);
		t3.add(at35);
		t3.add(mf31);
		t3.add(mf32);
		t3.add(mf33);
		t3.add(mf34);
		t3.add(mf35);
		t3.add(df31);
		t3.add(df32);
		t3.add(df33);
		t3.add(df34);
		t3.add(df35);
		t3.add(gk31);
		t3.add(gk32);
		Player[] pos3Array = {gk31, df31, df32, df33, df34, mf31, mf32, mf33, at31, at32, at33};
		t3.setPositions(new Positions(pos3Array));
		library.add(t1);
		library.add(t2);
		library.add(t3);
		assertEquals(library, readLibrary);
	}

	@Test
	public void testWriteLibrary() {
		Attacker at11 = new Attacker(new BigDecimal(250000), "test team 1", "at11", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker at12 = new Attacker(new BigDecimal(250000), "test team 1", "at12", 18, 42, 7, 3, 2, 1, 13, 5, false, 55, 78, 24, 97);
		Attacker at13 = new Attacker(new BigDecimal(250000), "test team 1", "at13", 18, 42, 7, 3, 2, 1, 13, 5, false, 44, 56, 12, 45);
		Attacker at14 = new Attacker(new BigDecimal(250000), "test team 1", "at14", 18, 42, 7, 3, 2, 1, 13, 5, false, 12, 34, 56, 67);
		Attacker at15 = new Attacker(new BigDecimal(250000), "test team 1", "at15", 18, 42, 7, 3, 2, 1, 13, 5, false, 15, 29, 68, 57);
		Midfielder mf11 = new Midfielder(new BigDecimal(250000), "test team 1", "mf11", 18, 42, 7, 3, 2, 1, 13, 5, false, 65, 34, 58, 68);
		Midfielder mf12 = new Midfielder(new BigDecimal(250000), "test team 1", "mf12", 18, 42, 7, 3, 2, 1, 13, 5, false, 85, 68, 38, 63);
		Midfielder mf13 = new Midfielder(new BigDecimal(250000), "test team 1", "mf13", 18, 42, 7, 3, 2, 1, 13, 5, false, 81, 96, 45, 80);
		Midfielder mf14 = new Midfielder(new BigDecimal(250000), "test team 1", "mf14", 18, 42, 7, 3, 2, 1, 13, 5, false, 82, 96, 45, 80);
		Midfielder mf15 = new Midfielder(new BigDecimal(250000), "test team 1", "mf15", 18, 42, 7, 3, 2, 1, 13, 5, false, 83, 96, 45, 80);
		Defender df11 = new Defender(new BigDecimal(250000), "test team 1", "df11", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 81);
		Defender df12 = new Defender(new BigDecimal(250000), "test team 1", "df12", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 82);
		Defender df13 = new Defender(new BigDecimal(250000), "test team 1", "df13", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 83);
		Defender df14 = new Defender(new BigDecimal(250000), "test team 1", "df14", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 84);
		Defender df15 = new Defender(new BigDecimal(250000), "test team 1", "df15", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 85);
		Goalkeeper gk11 = new Goalkeeper(new BigDecimal(250000), "test team 1", "gk11", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper gk12 = new Goalkeeper(new BigDecimal(250000), "test team 1", "gk12", 18, 42, 7, 3, 2, 1, 13, 5, false, 82);
		Attacker at21 = new Attacker(new BigDecimal(250000), "test team 2", "at21", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker at22 = new Attacker(new BigDecimal(250000), "test team 2", "at22", 18, 42, 7, 3, 2, 1, 13, 5, false, 55, 78, 24, 97);
		Attacker at23 = new Attacker(new BigDecimal(250000), "test team 2", "at23", 18, 42, 7, 3, 2, 1, 13, 5, false, 44, 56, 12, 45);
		Attacker at24 = new Attacker(new BigDecimal(250000), "test team 2", "at24", 18, 42, 7, 3, 2, 1, 13, 5, false, 12, 34, 56, 67);
		Attacker at25 = new Attacker(new BigDecimal(250000), "test team 2", "at25", 18, 42, 7, 3, 2, 1, 13, 5, false, 15, 29, 68, 57);
		Midfielder mf21 = new Midfielder(new BigDecimal(250000), "test team 2", "mf21", 18, 42, 7, 3, 2, 1, 13, 5, false, 65, 34, 58, 68);
		Midfielder mf22 = new Midfielder(new BigDecimal(250000), "test team 2", "mf22", 18, 42, 7, 3, 2, 1, 13, 5, false, 85, 68, 38, 63);
		Midfielder mf23 = new Midfielder(new BigDecimal(250000), "test team 2", "mf23", 18, 42, 7, 3, 2, 1, 13, 5, false, 81, 96, 45, 80);
		Midfielder mf24 = new Midfielder(new BigDecimal(250000), "test team 2", "mf24", 18, 42, 7, 3, 2, 1, 13, 5, false, 82, 96, 45, 80);
		Midfielder mf25 = new Midfielder(new BigDecimal(250000), "test team 2", "mf25", 18, 42, 7, 3, 2, 1, 13, 5, false, 83, 96, 45, 80);
		Defender df21 = new Defender(new BigDecimal(250000), "test team 2", "df21", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 81);
		Defender df22 = new Defender(new BigDecimal(250000), "test team 2", "df22", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 82);
		Defender df23 = new Defender(new BigDecimal(250000), "test team 2", "df23", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 83);
		Defender df24 = new Defender(new BigDecimal(250000), "test team 2", "df24", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 84);
		Defender df25 = new Defender(new BigDecimal(250000), "test team 2", "df25", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 85);
		Goalkeeper gk21 = new Goalkeeper(new BigDecimal(250000), "test team 2", "gk21", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper gk22 = new Goalkeeper(new BigDecimal(250000), "test team 2", "gk22", 18, 42, 7, 3, 2, 1, 13, 5, false, 82);
		Attacker at31 = new Attacker(new BigDecimal(250000), "test team 3", "at31", 18, 42, 7, 3, 2, 1, 13, 5, false, 88, 96, 45, 80);
		Attacker at32 = new Attacker(new BigDecimal(250000), "test team 3", "at32", 18, 42, 7, 3, 2, 1, 13, 5, false, 55, 78, 24, 97);
		Attacker at33 = new Attacker(new BigDecimal(250000), "test team 3", "at33", 18, 42, 7, 3, 2, 1, 13, 5, false, 44, 56, 12, 45);
		Attacker at34 = new Attacker(new BigDecimal(250000), "test team 3", "at34", 18, 42, 7, 3, 2, 1, 13, 5, false, 12, 34, 56, 67);
		Attacker at35 = new Attacker(new BigDecimal(250000), "test team 3", "at35", 18, 42, 7, 3, 2, 1, 13, 5, false, 15, 29, 68, 57);
		Midfielder mf31 = new Midfielder(new BigDecimal(250000), "test team 3", "mf31", 18, 42, 7, 3, 2, 1, 13, 5, false, 65, 34, 58, 68);
		Midfielder mf32 = new Midfielder(new BigDecimal(250000), "test team 3", "mf32", 18, 42, 7, 3, 2, 1, 13, 5, false, 85, 68, 38, 63);
		Midfielder mf33 = new Midfielder(new BigDecimal(250000), "test team 3", "mf33", 18, 42, 7, 3, 2, 1, 13, 5, false, 81, 96, 45, 80);
		Midfielder mf34 = new Midfielder(new BigDecimal(250000), "test team 3", "mf34", 18, 42, 7, 3, 2, 1, 13, 5, false, 82, 96, 45, 80);
		Midfielder mf35 = new Midfielder(new BigDecimal(250000), "test team 3", "mf35", 18, 42, 7, 3, 2, 1, 13, 5, false, 83, 96, 45, 80);
		Defender df31 = new Defender(new BigDecimal(250000), "test team 3", "df31", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 81);
		Defender df32 = new Defender(new BigDecimal(250000), "test team 3", "df32", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 82);
		Defender df33 = new Defender(new BigDecimal(250000), "test team 3", "df33", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 83);
		Defender df34 = new Defender(new BigDecimal(250000), "test team 3", "df34", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 84);
		Defender df35 = new Defender(new BigDecimal(250000), "test team 3", "df35", 18, 42, 7, 3, 2, 1, 13, 5, false, 80, 96, 45, 85);
		Goalkeeper gk31 = new Goalkeeper(new BigDecimal(250000), "test team 3", "gk31", 18, 42, 7, 3, 2, 1, 13, 5, false, 80);
		Goalkeeper gk32 = new Goalkeeper(new BigDecimal(250000), "test team 3", "gk32", 18, 42, 7, 3, 2, 1, 13, 5, false, 82);
		
		
		Library library = new Library();
		Standings standings1 = new Standings(3,5,1,6,7,"test team 1");
		Team t1 = new Team("test team 1", 50000000, standings1);
		t1.add(at11);
		t1.add(at12);
		t1.add(at13);
		t1.add(at14);
		t1.add(at15);
		t1.add(mf11);
		t1.add(mf12);
		t1.add(mf13);
		t1.add(mf14);
		t1.add(mf15);
		t1.add(df11);
		t1.add(df12);
		t1.add(df13);
		t1.add(df14);
		t1.add(df15);
		t1.add(gk11);
		t1.add(gk12);
		Player[] pos1Array = {gk11, df11, df12, df13, df14, mf11, mf12, mf13, at11, at12, at13};
		t1.setPositions(new Positions(pos1Array));
		Standings standings2 = new Standings(6,2,8,1,3,"test team 2");
		Team t2 = new Team("test team 2", 76000000, standings2);
		t2.add(at21);
		t2.add(at22);
		t2.add(at23);
		t2.add(at24);
		t2.add(at25);
		t2.add(mf21);
		t2.add(mf22);
		t2.add(mf23);
		t2.add(mf24);
		t2.add(mf25);
		t2.add(df21);
		t2.add(df22);
		t2.add(df23);
		t2.add(df24);
		t2.add(df25);
		t2.add(gk21);
		t2.add(gk22);
		Player[] pos2Array = {gk21, df21, df22, df23, df24, mf21, mf22, mf23, at21, at22, at23};
		t2.setPositions(new Positions(pos2Array));
		Standings standings3 = new Standings(5,2,6,3,2,"test team 3");
		Team t3 = new Team("test team 3", 38000000, standings3);
		t3.add(at31);
		t3.add(at32);
		t3.add(at33);
		t3.add(at34);
		t3.add(at35);
		t3.add(mf31);
		t3.add(mf32);
		t3.add(mf33);
		t3.add(mf34);
		t3.add(mf35);
		t3.add(df31);
		t3.add(df32);
		t3.add(df33);
		t3.add(df34);
		t3.add(df35);
		t3.add(gk31);
		t3.add(gk32);
		Player[] pos3Array = {gk31, df31, df32, df33, df34, mf31, mf32, mf33, at31, at32, at33};
		t3.setPositions(new Positions(pos3Array));
		library.add(t1);
		library.add(t2);
		library.add(t3);
		XMLParser.writeLibrary("testFiles/testLibraryWrite.xml", library, 3);
		String res = "";
		
		try {
			File file = new File("testFiles/testLibraryWrite.xml");
			Scanner sc = new Scanner(file);
			res = sc.nextLine();
			sc.close();
		} catch(Exception e) {
			
		}
		
		assertEquals(res, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><library roundsPlayed=\"3\"><team budget=\"50000000.00\" teamName=\"test team 1\"><standings><won>3</won><draw>5</draw><lost>1</lost><goalsFor>6</goalsFor><goalsAgainst>7</goalsAgainst></standings><player><price>250000.00</price><playerType>Attacker</playerType><name>at11</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>88</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at12</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>55</dribblingValue><finishingValue>78</finishingValue><defenseValue>24</defenseValue><staminaValue>97</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at13</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>44</dribblingValue><finishingValue>56</finishingValue><defenseValue>12</defenseValue><staminaValue>45</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at14</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>12</dribblingValue><finishingValue>34</finishingValue><defenseValue>56</defenseValue><staminaValue>67</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at15</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>15</dribblingValue><finishingValue>29</finishingValue><defenseValue>68</defenseValue><staminaValue>57</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf11</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>65</dribblingValue><finishingValue>34</finishingValue><defenseValue>58</defenseValue><staminaValue>68</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf12</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>85</dribblingValue><finishingValue>68</finishingValue><defenseValue>38</defenseValue><staminaValue>63</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf13</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>81</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf14</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>82</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf15</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>83</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df11</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>81</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df12</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>82</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df13</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>83</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df14</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>84</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df15</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>85</staminaValue></player><player><price>250000.00</price><playerType>Goalkeeper</playerType><name>gk11</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><goalkeeperValue>80</goalkeeperValue></player><player><price>250000.00</price><playerType>Goalkeeper</playerType><name>gk12</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><goalkeeperValue>82</goalkeeperValue></player><positions><player1><age>18</age><name>gk11</name></player1><player2><age>18</age><name>df11</name></player2><player3><age>18</age><name>df12</name></player3><player4><age>18</age><name>df13</name></player4><player5><age>18</age><name>df14</name></player5><player6><age>18</age><name>mf11</name></player6><player7><age>18</age><name>mf12</name></player7><player8><age>18</age><name>mf13</name></player8><player9><age>18</age><name>at11</name></player9><player10><age>18</age><name>at12</name></player10><player11><age>18</age><name>at13</name></player11></positions></team><team budget=\"76000000.00\" teamName=\"test team 2\"><standings><won>6</won><draw>2</draw><lost>8</lost><goalsFor>1</goalsFor><goalsAgainst>3</goalsAgainst></standings><player><price>250000.00</price><playerType>Attacker</playerType><name>at21</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>88</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at22</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>55</dribblingValue><finishingValue>78</finishingValue><defenseValue>24</defenseValue><staminaValue>97</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at23</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>44</dribblingValue><finishingValue>56</finishingValue><defenseValue>12</defenseValue><staminaValue>45</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at24</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>12</dribblingValue><finishingValue>34</finishingValue><defenseValue>56</defenseValue><staminaValue>67</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at25</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>15</dribblingValue><finishingValue>29</finishingValue><defenseValue>68</defenseValue><staminaValue>57</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf21</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>65</dribblingValue><finishingValue>34</finishingValue><defenseValue>58</defenseValue><staminaValue>68</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf22</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>85</dribblingValue><finishingValue>68</finishingValue><defenseValue>38</defenseValue><staminaValue>63</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf23</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>81</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf24</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>82</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf25</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>83</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df21</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>81</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df22</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>82</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df23</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>83</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df24</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>84</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df25</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>85</staminaValue></player><player><price>250000.00</price><playerType>Goalkeeper</playerType><name>gk21</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><goalkeeperValue>80</goalkeeperValue></player><player><price>250000.00</price><playerType>Goalkeeper</playerType><name>gk22</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><goalkeeperValue>82</goalkeeperValue></player><positions><player1><age>18</age><name>gk21</name></player1><player2><age>18</age><name>df21</name></player2><player3><age>18</age><name>df22</name></player3><player4><age>18</age><name>df23</name></player4><player5><age>18</age><name>df24</name></player5><player6><age>18</age><name>mf21</name></player6><player7><age>18</age><name>mf22</name></player7><player8><age>18</age><name>mf23</name></player8><player9><age>18</age><name>at21</name></player9><player10><age>18</age><name>at22</name></player10><player11><age>18</age><name>at23</name></player11></positions></team><team budget=\"38000000.00\" teamName=\"test team 3\"><standings><won>5</won><draw>2</draw><lost>6</lost><goalsFor>3</goalsFor><goalsAgainst>2</goalsAgainst></standings><player><price>250000.00</price><playerType>Attacker</playerType><name>at31</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>88</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at32</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>55</dribblingValue><finishingValue>78</finishingValue><defenseValue>24</defenseValue><staminaValue>97</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at33</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>44</dribblingValue><finishingValue>56</finishingValue><defenseValue>12</defenseValue><staminaValue>45</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at34</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>12</dribblingValue><finishingValue>34</finishingValue><defenseValue>56</defenseValue><staminaValue>67</staminaValue></player><player><price>250000.00</price><playerType>Attacker</playerType><name>at35</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>15</dribblingValue><finishingValue>29</finishingValue><defenseValue>68</defenseValue><staminaValue>57</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf31</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>65</dribblingValue><finishingValue>34</finishingValue><defenseValue>58</defenseValue><staminaValue>68</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf32</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>85</dribblingValue><finishingValue>68</finishingValue><defenseValue>38</defenseValue><staminaValue>63</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf33</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>81</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf34</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>82</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Midfielder</playerType><name>mf35</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>83</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>80</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df31</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>81</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df32</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>82</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df33</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>83</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df34</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>84</staminaValue></player><player><price>250000.00</price><playerType>Defender</playerType><name>df35</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><dribblingValue>80</dribblingValue><finishingValue>96</finishingValue><defenseValue>45</defenseValue><staminaValue>85</staminaValue></player><player><price>250000.00</price><playerType>Goalkeeper</playerType><name>gk31</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><goalkeeperValue>80</goalkeeperValue></player><player><price>250000.00</price><playerType>Goalkeeper</playerType><name>gk32</name><age>18</age><number>42</number><goals>7</goals><assists>3</assists><yellowCards>2</yellowCards><redCards>1</redCards><daysInjured>13</daysInjured><daysSuspended>5</daysSuspended><daysNotForSale>0</daysNotForSale><eligible>0</eligible><goalkeeperValue>82</goalkeeperValue></player><positions><player1><age>18</age><name>gk31</name></player1><player2><age>18</age><name>df31</name></player2><player3><age>18</age><name>df32</name></player3><player4><age>18</age><name>df33</name></player4><player5><age>18</age><name>df34</name></player5><player6><age>18</age><name>mf31</name></player6><player7><age>18</age><name>mf32</name></player7><player8><age>18</age><name>mf33</name></player8><player9><age>18</age><name>at31</name></player9><player10><age>18</age><name>at32</name></player10><player11><age>18</age><name>at33</name></player11></positions></team></library>");
	}

	@Test
	public void testReadCompetitionScheme() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteScheme() {
	}

	@Test
	public void testReadGameList() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteGameList() {
		fail("Not yet implemented");
	}

}
