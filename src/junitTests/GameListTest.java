package junitTests;

import static org.junit.Assert.*;
import game.Game;
import game.GameList;
import gameLogic.TransferList;

import java.util.ArrayList;

import org.junit.Test;

public class GameListTest {

	@Test
	public void testGameList() {
		GameList gl = new GameList();
		assertNotNull(gl.getGames());
		
	}

	@Test
	public void testNewgame() {
		GameList gl = new GameList();
		Game g = gl.newgame("Menno", "Arsenal");
		assertEquals(g.getName(), "Menno");
		assertEquals(g.getSavefileData(), "savefiles/1-data.xml");
		assertEquals(g.getSavefileScheme(), "savefiles/1-scheme.xml");
		assertEquals(g.getTeam().getTeamName(), "Arsenal");
	}

	@Test
	public void testLoadgame() {
		GameList gl = new GameList();
		Game g = gl.newgame("Menno", "Arsenal");
		Game g2 = gl.loadgame("Menno");
		assertEquals(g, g2);
		assertNull(gl.loadgame("bart"));
	}

	@Test
	public void testAdd() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal", new TransferList());
		GameList gl = new GameList();
		gl.add(g);
		assertTrue(gl.getGames().contains(g));
	}

	@Test
	public void testGet() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal", new TransferList());
		GameList gl = new GameList();
		gl.add(g);
		assertEquals(g, gl.get(0));
		assertNull(gl.get(-1));
		assertNull(gl.get(1));
		
	}

	@Test
	public void testGetGames() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal", new TransferList());
		GameList gl = new GameList();
		gl.add(g);
		ArrayList<Game> expected = new ArrayList<Game>();
		expected.add(g);
		assertEquals(expected, gl.getGames());
	}

	@Test
	public void testSetGames() {
		ArrayList<Game> array = new ArrayList<Game>();
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal", new TransferList());
		GameList gl = new GameList();
		array.add(g);
		gl.setGames(array);
		assertEquals(array, gl.getGames());
		
	}

	@Test
	public void testToString() {
		Game g = new Game("Menno", "files/competitionDatabase_v5.xml", "files/competition-scheme.xml", "Arsenal", new TransferList());
		GameList gl = new GameList();
		gl.add(g);
		String expected = g.toString() + "\n";
		assertEquals(expected, gl.toString());
	}

}
