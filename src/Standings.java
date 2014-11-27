
public class Standings {

	private int won, draw, lost, points, goalsFor, goalsAgainst, goalDifference;
	private String teamName;
	

	/**
	 * @param won
	 * @param draw
	 * @param lost
	 * @param goalsFor
	 * @param goalsAgainst
	 */
	public Standings(int won, int draw, int lost, int goalsFor, int goalsAgainst, String teamName) {
		this.won = won;
		this.draw = draw;
		this.lost = lost;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.teamName = teamName;
		
		this.points = 3*won + draw;
		this.goalDifference = goalsFor - goalsAgainst;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Standings [points=" + points + ", goalDifference="
				+ goalDifference + ", won=" + won + ", draw=" + draw + ", lost=" + lost
				+ ", goalsFor=" + goalsFor
				+ ", goalsAgainst=" + goalsAgainst +  "]";
	}
	
	public void updateStandings(String result, int goalsFor, int goalsAgainst){
		switch(result) {
		case "won":
		case "win":
			won++;
			break;
		case "draw":
			draw++;
			break;
		case "lost":
		case "loss":
			lost++;
			break;
		}
		
		this.goalsFor += goalsFor;
		this.goalsAgainst += goalsAgainst;
		
		this.points = 3*won + draw;
		this.goalDifference += (goalsFor - goalsAgainst);
	}

	/**
	 * @return the won
	 */
	public int getWon() {
		return won;
	}

	/**
	 * @param won the won to set
	 */
	public void setWon(int won) {
		this.won = won;
	}

	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}

	/**
	 * @return the lost
	 */
	public int getLost() {
		return lost;
	}

	/**
	 * @param lost the lost to set
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the goalsFor
	 */
	public int getGoalsFor() {
		return goalsFor;
	}

	/**
	 * @param goalsFor the goalsFor to set
	 */
	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	/**
	 * @return the goalsAgainst
	 */
	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	/**
	 * @param goalsAgainst the goalsAgainst to set
	 */
	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	/**
	 * @return the goalDifference
	 */
	public int getGoalDifference() {
		return goalDifference;
	}

	/**
	 * @param goalDifference the goalDifference to set
	 */
	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	
	
	
}
