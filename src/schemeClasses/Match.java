package schemeClasses;

import java.util.ArrayList;

import libraryClasses.FieldPlayer;
import libraryClasses.Player;

public class Match {

	private String team1, team2;
	private int score1, score2;
	private ArrayList<FieldPlayer> goalMakerst1, goalMakerst2, assistMakerst1, assistMakerst2;
	private ArrayList<Player> yellowCardGetterst1, yellowCardGetterst2, redCardGetterst1, redCardGetterst2, injuredPlayerst1, injuredPlayerst2;
	private int[] injuriesLengthst1, injuriesLengthst2;
	
	/**
	 * Constructor which initializes a match with the two given team names
	 * @param team1	- The first team name
	 * @param team2	- the second team name
	 */
	public Match(String team1, String team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.score1 = -1;
		this.score2 = -1;
	}
	
	/**
	 * Constructor which initializes a match with the two given team names
	 * @param team1		- The first team name
	 * @param team2		- the second team name
	 * @param score1 	- The score of team 1
	 * @param score2 	- The score of team 2
	 */
	public Match(String team1, String team2, int score1, int score2) {
		this.team1 = team1;
		this.team2 = team2;
		this.score1 = score1;
		this.score2 = score2;
	}
	
	/**
	 * @return the score1
	 */
	public int getScore1() {
		return score1;
	}

	/**
	 * @param score1 the score1 to set
	 */
	public void setScore1(int score1) {
		this.score1 = score1;
	}

	/**
	 * @return the score2
	 */
	public int getScore2() {
		return score2;
	}

	/**
	 * @param score2 the score2 to set
	 */
	public void setScore2(int score2) {
		this.score2 = score2;
	}

	/**
	 * @return the goalMakerst1
	 */
	public ArrayList<FieldPlayer> getGoalMakerst1() {
		return goalMakerst1;
	}

	/**
	 * @param goalMakerst1 the goalMakerst1 to set
	 */
	public void setGoalMakerst1(ArrayList<FieldPlayer> goalMakerst1) {
		this.goalMakerst1 = goalMakerst1;
	}

	/**
	 * @return the goalMakerst2
	 */
	public ArrayList<FieldPlayer> getGoalMakerst2() {
		return goalMakerst2;
	}

	/**
	 * @param goalMakerst2 the goalMakerst2 to set
	 */
	public void setGoalMakerst2(ArrayList<FieldPlayer> goalMakerst2) {
		this.goalMakerst2 = goalMakerst2;
	}


	/**
	 * @return the assistMakerst1
	 */
	public ArrayList<FieldPlayer> getAssistMakerst1() {
		return assistMakerst1;
	}

	/**
	 * @param assistMakerst1 the assistMakerst1 to set
	 */
	public void setAssistMakerst1(ArrayList<FieldPlayer> assistMakerst1) {
		this.assistMakerst1 = assistMakerst1;
	}
	
	/**
	 * @return the assistMakerst2
	 */
	public ArrayList<FieldPlayer> getAssistMakerst2() {
		return assistMakerst2;
	}

	/**
	 * @param assistMakerst2 the assistMakerst2 to set
	 */
	public void setAssistMakerst2(ArrayList<FieldPlayer> assistMakerst2) {
		this.assistMakerst2 = assistMakerst2;
	}

	/**
	 * @return the yellowCardGetterst1
	 */
	public ArrayList<Player> getYellowCardGetterst1() {
		return yellowCardGetterst1;
	}

	/**
	 * @param yellowCardGetterst1 the yellowCardGetterst1 to set
	 */
	public void setYellowCardGetterst1(ArrayList<Player> yellowCardGetterst1) {
		this.yellowCardGetterst1 = yellowCardGetterst1;
	}


	/**
	 * @return the yellowCardGetterst2
	 */
	public ArrayList<Player> getYellowCardGetterst2() {
		return yellowCardGetterst2;
	}


	/**
	 * @param yellowCardGetterst2 the yellowCardGetterst2 to set
	 */
	public void setYellowCardGetterst2(ArrayList<Player> yellowCardGetterst2) {
		this.yellowCardGetterst2 = yellowCardGetterst2;
	}


	/**
	 * @return the redCardGetterst1
	 */
	public ArrayList<Player> getRedCardGetterst1() {
		return redCardGetterst1;
	}

	/**
	 * @param redCardGetterst1 the redCardGetterst1 to set
	 */
	public void setRedCardGetterst1(ArrayList<Player> redCardGetterst1) {
		this.redCardGetterst1 = redCardGetterst1;
	}


	/**
	 * @return the redCardGetterst2
	 */
	public ArrayList<Player> getRedCardGetterst2() {
		return redCardGetterst2;
	}


	/**
	 * @param redCardGetterst2 the redCardGetterst2 to set
	 */
	public void setRedCardGetterst2(ArrayList<Player> redCardGetterst2) {
		this.redCardGetterst2 = redCardGetterst2;
	}


	/**
	 * @return the injuredPlayerst1
	 */
	public ArrayList<Player> getInjuredPlayerst1() {
		return injuredPlayerst1;
	}


	/**
	 * @param injuredPlayerst1 the injuredPlayerst1 to set
	 */
	public void setInjuredPlayerst1(ArrayList<Player> injuredPlayerst1) {
		this.injuredPlayerst1 = injuredPlayerst1;
	}
	
	/**
	 * @return the injuredPlayerst2
	 */
	public ArrayList<Player> getInjuredPlayerst2() {
		return injuredPlayerst2;
	}

	/**
	 * @param injuredPlayerst2 the injuredPlayerst2 to set
	 */
	public void setInjuredPlayerst2(ArrayList<Player> injuredPlayerst2) {
		this.injuredPlayerst2 = injuredPlayerst2;
	}


	/**
	 * @return the injuriesLengthst1
	 */
	public int[] getInjuriesLengthst1() {
		return injuriesLengthst1;
	}

	/**
	 * @param injuriesLengthst1 the injuriesLengthst1 to set
	 */
	public void setInjuriesLengthst1(int[] injuriesLengthst1) {
		this.injuriesLengthst1 = injuriesLengthst1;
	}

	/**
	 * @return the injuriesLengthst2
	 */
	public int[] getInjuriesLengthst2() {
		return injuriesLengthst2;
	}


	/**
	 * @param injuriesLengthst2 the injuriesLengthst2 to set
	 */
	public void setInjuriesLengthst2(int[] injuriesLengthst2) {
		this.injuriesLengthst2 = injuriesLengthst2;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Match [team1=" + team1 + ", team2=" + team2 + "]";
	}

	/**
	 * @return the team1
	 */
	public String getTeam1() {
		return team1;
	}

	/**
	 * @param team1 the team1 to set
	 */
	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	/**
	 * @return the team2
	 */
	public String getTeam2() {
		return team2;
	}

	/**
	 * @param team2 the team2 to set
	 */
	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	/**
	 * @param set team1's score
	 */
	public void setScoreTeam1(int score) {
		score1 = score;
	}
	
	/**
	 * @param set team2's score
	 */
	public void setScoreTeam2(int score) {
		score2 = score;
	}
	
	
	/**
	 * @return team1's score (-1 if not yet played)
	 */
	public int getScoreTeam1() {
		return score1;
	}
	
	/**
	 * @return team2's score (-1 if not yet played)
	 */
	public int getScoreTeam2() {
		return score2;
	}
	
	/**
	 * @return the number of the winning team (1 for team1, 2 for team2)
	 */
	public int getWinner() {
		return (score1 == score2) ? 0 : (score1 > score2) ? 1 : 2;
	}



	



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (score1 != other.score1)
			return false;
		if (score2 != other.score2)
			return false;
		if (team1 == null) {
			if (other.team1 != null)
				return false;
		} else if (!team1.equals(other.team1))
			return false;
		if (team2 == null) {
			if (other.team2 != null)
				return false;
		} else if (!team2.equals(other.team2))
			return false;
		return true;
	}
	
	
	
	
	
}
