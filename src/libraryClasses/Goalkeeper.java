package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Goalkeeper extends Player {

	private int goalkeeperValue;
	
	/**
	 * Constructor
	 * @param price				- the recommended price of the goalkeeper
	 * @param team				- the team name of the goalkeeper
	 * @param name				- the name of the goalkeeper
	 * @param age				- the age of the goalkeeper
	 * @param number			- the number of the goalkeeper
	 * @param goals				- the number of goals of the goalkeeper
	 * @param assists			- the number of assists of the goalkeeper
	 * @param yellowcards		- the number of yellow cards of the goalkeeper
	 * @param redcards			- the number of red cards of the goalkeeper
	 * @param daysInjured		- the the number of days injured of the goalkeeper
	 * @param daysSuspended		- the the number of days suspended of the goalkeeper
	 * @param isEligible		- the boolean which indicates if the goalkeeper is eligible to play
	 * @param goalkeeperValue	- the goalkeeper value of the goalkeeper
	 */
	public Goalkeeper(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int goalkeeperValue) {
		super(price, team, name, age, number, goals, assists, yellowcards,
				redcards, daysInjured, daysSuspended, isEligible);
		this.goalkeeperValue = goalkeeperValue;
		this.setPlayerType("Goalkeeper");
	}
	
	/**
	 * Constructor
	 * @param price				- the recommended price of the goalkeeper
	 * @param team				- the team name of the goalkeeper
	 * @param name				- the name of the goalkeeper
	 * @param age				- the age of the goalkeeper
	 * @param number			- the number of the goalkeeper
	 * @param goals				- the number of goals of the goalkeeper
	 * @param assists			- the number of assists of the goalkeeper
	 * @param yellowcards		- the number of yellow cards of the goalkeeper
	 * @param redcards			- the number of red cards of the goalkeeper
	 * @param daysInjured		- the the number of days injured of the goalkeeper
	 * @param daysSuspended		- the the number of days suspended of the goalkeeper
	 * @param isEligible		- the boolean which indicates if the goalkeeper is eligible to play
	 * @param goalkeeperValue	- the goalkeeper value of the goalkeeper
	 * @param daysNotForSale	- the number of days the goalkeeper is not for sale.
	 */
	public Goalkeeper(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int goalkeeperValue, int daysNotForSale) {
		super(price, team, name, age, number, goals, assists, yellowcards,
				redcards, daysInjured, daysSuspended, isEligible, daysNotForSale);
		this.goalkeeperValue = goalkeeperValue;
		this.setPlayerType("Goalkeeper");
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getPlayerType() + " [name=" + this.getName() + ", age=" + this.getAge() + ", team=" + this.getTeam()
				+ ", number=" + this.getNumber() + ", price=" + this.getPrice() + ", goalkeeperValue=" + this.getGoalkeeperValue()
				+ ", goals=" + this.getGoals() + ", assists=" + this.getAssists() + ", yellowCards=" + this.getYellowcards()
				+ ", redCards=" + this.getRedcards() + ", daysInjured=" + this.getDaysInjured() + ", daysSuspended=" + this.getDaysSuspended()
				+ ", eligible=" + this.isEligible() + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		Goalkeeper other = (Goalkeeper) obj;
		if (goalkeeperValue != other.goalkeeperValue) {
			return false;
		}
		return true;
	}


	/**
	 * @return the goalkeeperValue
	 */
	public int getGoalkeeperValue() {
		return goalkeeperValue;
	}

	/**
	 * @param goalkeeperValue - the goalkeeperValue to set
	 */
	public void setGoalkeeperValue(int goalkeeperValue) {
		this.goalkeeperValue = goalkeeperValue;
	}
	
}
