package libraryClasses;

import java.util.Date;

import xmlIO.XMLParser;

public class Game {
	
	private String name;
	private String savefileData;
	private String savefileScheme;
	private Team team;
	private Competition competition;
	private Date date;
	
	public Game (String name, String savefileData, String savefileScheme, String teamname) {
		this.name=name;
		this.savefileData=savefileData;
		this.savefileScheme=savefileScheme;
		this.date = new Date();
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
	
	public void save() {
		XMLParser.writeCompetition(savefileData, savefileScheme, competition);
	}







	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [name=" + name + ", savefileData=" + savefileData
				+ ", savefileScheme=" + savefileScheme + ", team=" + team
				+ ", competition=" + competition + ", date=" + date + "]";
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
	
	
	
	
	
	
	
	
	
	

}
