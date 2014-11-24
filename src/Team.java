import java.util.ArrayList;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Team {
	
	private ArrayList<Player> team;
	private String teamName;
	private double budget;
	private Standings standings;

	/**
	 * Constructor which initialises an empty team with the given team name
	 * @param teamName - The name of the team
	 */
	public Team(String teamName, double budget, Standings standings) {
		team = new ArrayList<Player>();
		this.teamName = teamName;
		this.budget = budget;
		this.standings = standings;
	}
	
	public void updateStandings(String result, int goalsFor, int goalsAgainst){
		standings.updateStandings(result, goalsFor, goalsAgainst);
	}
	
	/**
	 * @return the budget
	 */
	public double getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * Method to add a player to the team
	 * @param player - player to add
	 */
	public void add(Player player) {
		team.add(player);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = teamName + " with budget: " + budget;
		res += "\n" + standings.toString();
		for(int i = 0; i < team.size(); i++) {
			res += "\n" + team.get(i);
		}
		return res;
	}
	
	

	/**
	 * @return the standings
	 */
	public Standings getStandings() {
		return standings;
	}

	/**
	 * @param standings the standings to set
	 */
	public void setStandings(Standings standings) {
		this.standings = standings;
	}

	/**
	 * @return the team
	 */
	public ArrayList<Player> getTeam() {
		return team;
	}

	/**
	 * @param team - the team to set
	 */
	public void setTeam(ArrayList<Player> team) {
		this.team = team;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName - the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
