package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Midfielder extends FieldPlayer {

	/**
	 * Constructor
	 * @param price				- the recommended price of the midfielder
	 * @param team				- the team name of the midfielder
	 * @param name				- the name of the midfielder
	 * @param age				- the age of the midfielder
	 * @param number			- the number of the midfielder
	 * @param goals				- the number of goals of the midfielder
	 * @param assists			- the number of assists of the midfielder
	 * @param yellowcards		- the number of yellow cards of the midfielder
	 * @param redcards			- the number of red cards of the midfielder
	 * @param daysInjured		- the the number of days injured of the midfielder
	 * @param daysSuspended		- the the number of days suspended of the midfielder
	 * @param isEligible		- the boolean which indicates if the midfielder is eligible to play
	 * @param dribblingValue	- the dribbling value of the midfielder
	 * @param finishingValue	- the finishing value of the midfielder
	 * @param defenseValue		- the defense value of the midfielder
	 * @param staminaValue		- the stamina value of the midfielder
	 */
	public Midfielder(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int dribblingValue, int finishingValue, int defenseValue,
			int staminaValue) {
		super(price, team, name, age, number, goals, assists, yellowcards, redcards,
				daysInjured, daysSuspended, isEligible, dribblingValue, finishingValue,
				defenseValue, staminaValue);
		this.setPlayerType("Midfielder");
	}

	
	/**
	 * Constructor
	 * @param price				- the recommended price of the midfielder
	 * @param team				- the team name of the midfielder
	 * @param name				- the name of the midfielder
	 * @param age				- the age of the midfielder
	 * @param number			- the number of the midfielder
	 * @param goals				- the number of goals of the midfielder
	 * @param assists			- the number of assists of the midfielder
	 * @param yellowcards		- the number of yellow cards of the midfielder
	 * @param redcards			- the number of red cards of the midfielder
	 * @param daysInjured		- the the number of days injured of the midfielder
	 * @param daysSuspended		- the the number of days suspended of the midfielder
	 * @param isEligible		- the boolean which indicates if the midfielder is eligible to play
	 * @param dribblingValue	- the dribbling value of the midfielder
	 * @param finishingValue	- the finishing value of the midfielder
	 * @param defenseValue		- the defense value of the midfielder
	 * @param staminaValue		- the stamina value of the midfielder
	 * @param daysNotForSale	- the number of days the midfielder is not for sale.
	 */
	public Midfielder(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int dribblingValue, int finishingValue, int defenseValue,
			int staminaValue, int daysNotForSale) {
		super(price, team, name, age, number, goals, assists, yellowcards, redcards,
				daysInjured, daysSuspended, isEligible, dribblingValue, finishingValue,
				defenseValue, staminaValue, daysNotForSale);
		this.setPlayerType("Midfielder");
	}

}
