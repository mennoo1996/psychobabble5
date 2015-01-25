package game;

import gameLogic.TransferList;

import java.util.Date;

import libraryClasses.Team;
import xmlIO.XMLParser;


public class Game {
	
	private String name;
	private String savefileData;
	private String savefileScheme;
	private Team team;
	private Competition competition;
	private Date date;
	private TransferList transferList;
	
	/**
	 * Constructor
	 * @param name				- the name of the player
	 * @param savefileData		- the filename of the data file
	 * @param savefileScheme	- the filename of the scheme file
	 * @param teamname			- the teamname
	 * @param transferList		- the pending transferlist
	 */
	public Game (String name, String savefileData, String savefileScheme, String teamname, TransferList transferList) {
		this.name=name;
		this.savefileData=savefileData;
		this.savefileScheme=savefileScheme;
		this.date = new Date();
		this.setTransferList(transferList);
		competition=XMLParser.readCompetition(savefileData, savefileScheme);
		team=competition.getLibrary().getTeamForName(teamname);
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
	
	/**
	 * Method which saves the game to the given filenames.
	 *
	 */
	public void save() {
		XMLParser.writeCompetition(savefileData, savefileScheme, competition);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [name=" + name + ", savefileData=" + savefileData
				+ ", savefileScheme=" + savefileScheme + ", team=" + team.getTeamName()
				+ ", date=" + date + "]";
	}


	/**
	 * @return the savefileData
	 */
	public String getSavefileData() {
		return savefileData;
	}

	/**
	 * @param savefileData the savefileData to set
	 */
	public void setSavefileData(String savefileData) {
		this.savefileData = savefileData;
	}

	/**
	 * @return the savefileScheme
	 */
	public String getSavefileScheme() {
		return savefileScheme;
	}

	/**
	 * @param savefileScheme the savefileScheme to set
	 */
	public void setSavefileScheme(String savefileScheme) {
		this.savefileScheme = savefileScheme;
	}

	/**
	 * @return the transferList
	 */
	public TransferList getTransferList() {
		return transferList;
	}


	/**
	 * @param transferList the transferList to set
	 */
	public void setTransferList(TransferList transferList) {
		this.transferList = transferList;
	}
	

}
