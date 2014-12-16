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
	private int age, number, goals, assists, yellowcards, redcards, daysInjured, daysSuspended, daysNotForSale;
	private boolean isEligible, canBeSold;

	/**
	 * @param price
	 * @param team
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
			int yellowcards, int redcards, int daysInjured, int daysSuspended,
			boolean isEligible) {
		this.price = price;
		this.team = team;
		this.name = name;
		this.age = age;
		this.number = number;
		this.goals = goals;
		this.assists = assists;
		this.yellowcards = yellowcards;
		this.redcards = redcards;
		this.daysInjured = daysInjured;
		this.daysSuspended = daysSuspended;
		this.isEligible = isEligible;
		this.playerType = "Player";
		this.canBeSold=true;
		this.daysNotForSale=0;
	}
	
	public abstract String toString();

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		if (age != other.age) {
			return false;
		}
		if (assists != other.assists) {
			return false;
		}
		if (daysInjured != other.daysInjured) {
			return false;
		}
		if (daysSuspended != other.daysSuspended) {
			return false;
		}
		if (goals != other.goals) {
			return false;
		}
		if (isEligible != other.isEligible) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (playerType == null) {
			if (other.playerType != null) {
				return false;
			}
		} else if (!playerType.equals(other.playerType)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (redcards != other.redcards) {
			return false;
		}
		if (team == null) {
			if (other.team != null) {
				return false;
			}
		} else if (!team.equals(other.team)) {
			return false;
		}
		if (yellowcards != other.yellowcards) {
			return false;
		}
		return true;
	}

	/**
	 * Decreases the amount of days a player is still not eligible by one (method called after each round)
	 */
	public void roundPlayed() {
		if (daysInjured >0) {
			daysInjured--;
		}
		
		if (daysSuspended>0) {
			daysSuspended--;
		}
		if (daysInjured==0 && daysSuspended==0) {
			setEligible(true);
		}
		if (daysNotForSale>0) {
			daysNotForSale--;
		}
		if (daysNotForSale==0) {
			setCanBeSold(true);
		}
	}
	/**
	 * Gives the player an injury
	 * @param length the length of the injury
	 */
	public void gotInjury (int length) {
		setEligible(false);
		daysInjured=length;
	}

	/**
	 * Gives the player a suspension
	 * @param length the length of the suspension
	 */
	public void gotSuspension (int length) {
		setEligible(false);
		daysSuspended = length;
		
	}
	
	
	/**
	 * @return the daysInjured
	 */
	public int getDaysInjured() {
		return daysInjured;
	}

	/**
	 * @param daysInjured the daysInjured to set
	 */
	public void setDaysInjured(int daysInjured) {
		this.daysInjured = daysInjured;
	}

	
	/**
	 * @return the daysSuspended
	 */
	public int getDaysSuspended() {
		return daysSuspended;
	}

	/**
	 * @param daysSuspended the daysSuspended to set
	 */
	public void setDaysSuspended(int daysSuspended) {
		this.daysSuspended = daysSuspended;
	}

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

	/**
	 * @return the isEligible
	 */
	public boolean isEligible() {
		return isEligible;
	}

	/**
	 * @param isEligible the isEligible to set
	 */
	public void setEligible(boolean isEligible) {
		this.isEligible = isEligible;
	}

	/**
	 * @return the canBeSold
	 */
	public boolean isCanBeSold() {
		return canBeSold;
	}

	/**
	 * @param canBeSold the canBeSold to set
	 */
	public void setCanBeSold(boolean canBeSold) {
		this.canBeSold = canBeSold;
	}
	
	public void triedToSell() {
		this.setCanBeSold(false);
		this.setDaysNotForSale(3);
	}

	/**
	 * @return the daysNotForSale
	 */
	public int getDaysNotForSale() {
		return daysNotForSale;
	}

	/**
	 * @param daysNotForSale the daysNotForSale to set
	 */
	public void setDaysNotForSale(int daysNotForSale) {
		this.daysNotForSale = daysNotForSale;
	}

	

	
	
}
