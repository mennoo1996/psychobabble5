package libraryClasses;

import java.util.ArrayList;
import java.io.*;

public class GameList {
	
	private ArrayList<Game> games;
	
	public GameList() {
		games = new ArrayList<Game>();
	}
	
	public Game newgame(String name, String teamname) {
		int gamenumber = games.size()+1;
		String savefileData = "savefiles/" + gamenumber + "-data.xml";
		String savefileScheme = "savefiles/" + gamenumber + "-scheme.xml";
		try {
			PrintWriter writer = new PrintWriter (new FileWriter (savefileData));
			BufferedReader reader = new BufferedReader (new FileReader("files/competitionDatabase_v4.xml"));
			String templine = reader.readLine();
			while (templine!=null) {
				writer.println(templine);
				templine=reader.readLine();
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while copying the standard database file");
		}
		
		try {
			PrintWriter writer = new PrintWriter (new FileWriter (savefileScheme));
			BufferedReader reader = new BufferedReader (new FileReader("files/competition-scheme.xml"));
			String templine = reader.readLine();
			while (templine!=null) {
				writer.println(templine);
				templine=reader.readLine();
			}
			writer.close();
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Something went wrong while copying the standard scheme file");
		}
		Game res = new Game(name, savefileData, savefileScheme, teamname);
		games.add(res);
		
		return res;
		
	}
	
	public Game loadgame(String name) {
		
		for (int i=0;i<games.size(); i++) {
			if (games.get(i).getName().equals(name)) {
				return games.get(i);
			}
		} return null;

	}
	
	public void add (Game g) {
		games.add(g);
	}
	
	public Game get (int index) {
		if (index>=0 && index<games.size()) {
			return games.get(index);
		} return null;
	}

	/**
	 * @return the games
	 */
	public ArrayList<Game> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public String toString() {
		String res = "";
		for (int i=0;i<games.size();i++) {
			res += games.get(i).toString() + "\n";
		}
		return res;
	}
	
	
	
	
}
