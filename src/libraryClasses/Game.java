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



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the savefile
	 */
	public String getSavefile() {
		return savefile;
	}



	/**
	 * @param savefile the savefile to set
	 */
	public void setSavefile(String savefile) {
		this.savefile = savefile;
	}



	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}



	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}



	/**
	 * @return the competition
	 */
	public Competition getCompetition() {
		return competition;
	}



	/**
	 * @param competition the competition to set
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}



	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
	
	
	

}
