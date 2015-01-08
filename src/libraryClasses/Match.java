package libraryClasses;

public class Match {

	private String team1, team2;
	private int score1, score2;
	
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
