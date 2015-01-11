package libraryClasses;

import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class GameList {
	
	private ArrayList<Game> games;
	
	public GameList() {
		games = new ArrayList<Game>();
	}
	
	public Game newgame(String name, String teamname) {
		int gamenumber = games.size()+1;
		String savefile = "savefiles/" + gamenumber + ".xml";
		try {
			PrintWriter writer = new PrintWriter (new FileWriter (savefile));
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
		Game res = new Game(name, savefile, teamname);
		games.add(res);
		
		return res;
		
	}

}
