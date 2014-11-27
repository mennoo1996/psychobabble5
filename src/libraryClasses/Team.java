package libraryClasses;
import java.util.ArrayList;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Team {
	
	private ArrayList<Player> team;
	private ArrayList<Player> currentTeam;
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
	
	/**
	 * Method which checks if an arrayList contains 11 players of whom 1 is a goalkeeper
	 * 
	 * @param currentTeam	- The arraylist
	 * @return				- true or false
	 */
	public static Boolean isEligible(ArrayList<Player> currentTeam) {
		if(currentTeam.size() == 11) {
			int goalkeepers = 0;
			for(int i = 0; i < 11; i++) {
				if(currentTeam.get(i) instanceof Goalkeeper) {
					goalkeepers++;
				}
			}
			if(goalkeepers == 1) {
				return true;
			}
		}
		return false;
	}
	
	public void updateStandings(String result, int goalsFor, int goalsAgainst){
		standings.updateStandings(result, goalsFor, goalsAgainst);
	}
	
	/**
	 * @return the currentTeam
	 */
	public ArrayList<Player> getCurrentTeam() {
		return currentTeam;
	}
	

	/**
	 * @param currentTeam the currentTeam to set
	 */
	public void setCurrentTeam(ArrayList<Player> currentTeam) throws Exception{
		if(isEligible(currentTeam)) {
			this.currentTeam = currentTeam;	
		} else {
			throw(new Exception("A playing team should have 11 players of whom 1 is a goalkeeper"));
		}
	}
	
	public void setFirst11AsCurrentTeam() {
		ArrayList<Player> res = new ArrayList<Player>();
		
		int fieldPlayers = 0, goalkeepers = 0;
		
		for(int i = 0; i < team.size(); i++) {
			Player player = team.get(i);
			if((player instanceof Attacker || player instanceof Midfielder || player instanceof Defender) && fieldPlayers < 10) {
				fieldPlayers++;
				res.add(player);
			} else if(player instanceof Goalkeeper && goalkeepers < 1) {
				goalkeepers++;
				res.add(player);
			}
		}
		
		try {
			this.setCurrentTeam(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
