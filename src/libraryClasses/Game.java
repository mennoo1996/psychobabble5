package libraryClasses;

import java.util.Date;

import xmlIO.XMLParser;

public class Game {
	
	private String name;
	private String savefile;
	private Team team;
	private Competition competition;
	private Date date;
	
	public Game (String name, String savefile, String teamname) {
		this.name=name;
		this.savefile=savefile;
		this.date = new Date();
		competition=XMLParser.readCompetition(savefile, "files/competition-scheme.xml");
		team=competition.getLibrary().getTeamForName(teamname);
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [name=" + name + ", savefile=" + savefile + ", team="
				+ team + ", date=" + date
				+ "]";
	}
	
	
	
	
	
	

}
