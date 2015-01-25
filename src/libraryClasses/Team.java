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
	private Positions positions;
	private boolean isMax;
	
	/**
	 * Constructor
	 * @param teamName	- the team name
	 * @param budget	- the budget of the team
	 * @param standings	- the standings of the team
	 */
	public Team(String teamName, double budget, Standings standings) {
		team = new ArrayList<Player>();
		currentTeam = new ArrayList<Player>();
		this.teamName = teamName;
		this.budget = budget;
		this.standings = standings;
		this.positions=new Positions();
		isMax=false;
	}
	
	/**
	 * Method to print al ineligible players to the console
	 * 	-
	 */
	public void printPlayersNotEligible() {
		for (int i=0;i<team.size();i++) {
			if (!team.get(i).isEligible()) {
				System.out.println(team.get(i));
			}
		}
	}
	
	/**
	 * Method which checks if a Team has got a CurrentTeam that is allowed to play
	 * 
	 * @param currentTeam	- The Team
	 * @return	- true if the team is eligible to play, false otherwise
	 */
	public static Boolean isEligible(ArrayList<Player> currentTeam) {
		
		if(currentTeam.size() == 11) {
			int goalkeepers = 0;
			for(int i = 0; i < 11; i++) {
				if (!(currentTeam.get(i).isEligible())) {
					return false;
				}
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
	
	/**
	 * Method which deletes a player if it is in the team
	 * @param player	- the player to delete
	 */
	public void deleteIfInCurrentTeam(Player player) {
		if (positions.contains(player)) {
			boolean itsdone=false;
			for (int i=0;i<team.size();i++) {
				if (!itsdone) {
					
					Player p = team.get(i);
					if (p.getPlayerType().equals(player.getPlayerType()) && !(positions.contains(p)) && p.isEligible()) {
						this.replacePlayerInCurrentTeam(player, p);
						itsdone=true;
					}
				}
			}
			if (!itsdone) {
				for (int i=0;i<team.size();i++) {
					if (!itsdone) {
						Player p = team.get(i);
						if(((p instanceof FieldPlayer && player instanceof FieldPlayer) || (p instanceof Goalkeeper && player instanceof Goalkeeper)) && !(positions.contains(p)) && p.isEligible()) {
							this.replacePlayerInCurrentTeam(player, p);
							itsdone=true;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Method which checks if the current team is eligible to play
	 * @return	- boolean with the result
	 */
	public boolean isEligible() {
		int goalkeepers=0;
		for (int i=0;i<11;i++) {
			if (positions.getPositionArray()[i]==null) {
				return false;
			} else if (!positions.getPositionArray()[i].isEligible()) {
				return false;
			}
			if (positions.getPositionArray()[i] instanceof Goalkeeper) {
				goalkeepers++;
			}
		}
		if (goalkeepers!=1) {
			return false;
		} return true;
	}
	
	/**
	 * Method to update the standings of the team
	 * @param result		- the result of the match (won, draw or lost)
	 * @param goalsFor		- number of goals for
	 * @param goalsAgainst	- number of goals against
	 */
	public void updateStandings(String result, int goalsFor, int goalsAgainst){
		standings.updateStandings(result, goalsFor, goalsAgainst);
		if (result.equals("won")) {
			budget+=1000000;
		} if (result.equals("draw")) {
			budget+=500000;
		} if (result.equals("lost")) {
			budget+=100000;
		} if (goalsAgainst==0) {
			budget+=100000;
		} if (goalsFor-goalsAgainst>=3) {
			budget+=500000;
		}
		
	}
	
	/**
	 * Method to get a player for a given name and age
	 * @param name	- the name of the player
	 * @param age	- the age of the player
	 * @return	- the player
	 */
	public Player getPlayerForNameAndAge(String name, int age) {
		for(Player player : team) {
			if(player.getName().equals(name) && player.getAge() == age) {
				return player;
			}
		}
		return null;
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
	
	/**
	 * Method to set the first 11 eligible players as the current team
	 * 	-
	 */
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
			
		}
	}
	
	/**
	 * Method to set the positions of the team as the current team
	 * 	-
	 */
	public void setPositionsAsCurrentTeam() {
		ArrayList<Player> res = new ArrayList<Player>();
		
		Player[] playerArray = positions.getPositionArray();
		
		for(int i = 0; i < 11; i++) {
			res.add(playerArray[i]);
		}
		
		try {
			this.setCurrentTeam(res);
		} catch (Exception e) {
			
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
		if (!isMax) {
		
			team.add(player);
			if (team.size()==30) {
				
				isMax=true;
			}
		}
	}
	
	/**
	 * Method to add a player to the current the team
	 * @param player	- the player to add
	 */
	public void addToCurrentTeam(Player player) {
		currentTeam.add(player);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = teamName + " with budget: " + budget;
		res += "\n" + standings.toString();
		res += "\n" + positions.toString();
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
		if (team!=null) {
			if (team.size()<=30) {
				this.team = team;
					if (team.size()==30) {
						isMax=true;
					} else {
						isMax=false;
					}
				}
			} else {
			this.team=null;
		}
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
	
	public void replacePlayerInCurrentTeam(Player playerout, Player playerin) {
		currentTeam.remove(playerout);
		currentTeam.add(playerin);
		Player[] array = positions.getPositionArray();
		for (int i=0;i<array.length;i++) {
			if (array[i].equals(playerout)) {
				array[i]=playerin;
			}
		}
	}

	/**
	 * @return the positions
	 */
	public Positions getPositions() {
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(Positions positions) {
		this.positions = positions;
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
		Team other = (Team) obj;
		if (Double.doubleToLongBits(budget) != Double
				.doubleToLongBits(other.budget))
			return false;
		if (currentTeam == null) {
			if (other.currentTeam != null)
				return false;
		} else if (!currentTeam.equals(other.currentTeam))
			return false;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		if (standings == null) {
			if (other.standings != null)
				return false;
		} else if (!standings.equals(other.standings))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}
	
	/**
	 * Method to change a place in the positions
	 * @param playerout - the player to be deleted
	 * @param playerin	- the player to be added
	 */
	public void changePositions(Player playerout, Player playerin) {
		Player[] array = positions.getPositionArray();
		for (int i=0;i<array.length;i++) {
			if (array[i]!=null) {
			if (array[i].equals(playerout)) {
				array[i]=playerin;
			}}
		} 
	}

	/**
	 * @return the isMax
	 */
	public boolean isMax() {
		return isMax;
	}

	/**
	 * @param isMax the isMax to set
	 */
	public void setMax(boolean isMax) {
		this.isMax = isMax;
	}
	
}
