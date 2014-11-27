
public class Match {

	private String team1, team2;
	
	/**
	 * Constructor which initializes a match with the two given team names
	 * @param team1	- The first team name
	 * @param team2	- the second team name
	 */
	public Match(String team1, String team2) {
		this.team1 = team1;
		this.team2 = team2;
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

	
	
	
	
}
