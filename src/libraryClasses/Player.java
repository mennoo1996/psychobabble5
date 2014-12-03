package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public abstract class Player {
	
	private BigDecimal price;
	private String team, playerType, name;
	private int age, number, goals, assists, yellowcards, redcards;

	/**
	 * @param price
	 * @param team
	 * @param playerType
	 * @param name
	 * @param age
	 * @param number
	 * @param goals
	 * @param assists
	 * @param yellowcards
	 * @param redcards
	 */
	public Player(BigDecimal price, String team, String name, 
			int age, int number, int goals, int assists,
			int yellowcards, int redcards) {
		this.price = price;
		this.team = team;
		this.name = name;
		this.age = age;
		this.number = number;
		this.goals = goals;
		this.assists = assists;
		this.yellowcards = yellowcards;
		this.redcards = redcards;
	}
	
	public abstract String toString();

	public void madeGoal() {
		goals++;
	}
	
	public void madeAssist() {
		assists++;
	}
	
	public void gotYellow() {
		yellowcards++;
	}
	
	public void gotRed() {
		redcards++;
	}
	

	/**
	 * @return the goals
	 */
	public int getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}

	/**
	 * @return the assists
	 */
	public int getAssists() {
		return assists;
	}

	/**
	 * @param assists the assists to set
	 */
	public void setAssists(int assists) {
		this.assists = assists;
	}

	/**
	 * @return the yellowcards
	 */
	public int getYellowcards() {
		return yellowcards;
	}

	/**
	 * @param yellowcards the yellowcards to set
	 */
	public void setYellowcards(int yellowcards) {
		this.yellowcards = yellowcards;
	}

	/**
	 * @return the redcards
	 */
	public int getRedcards() {
		return redcards;
	}

	/**
	 * @param redcards the redcards to set
	 */
	public void setRedcards(int redcards) {
		this.redcards = redcards;
	}


	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}



	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}



	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}



	/**
	 * @param price - the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the - team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the playerType
	 */
	public String getPlayerType() {
		return playerType;
	}

	/**
	 * @param playerType - the playerType to set
	 */
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name - the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age - the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}	
}
