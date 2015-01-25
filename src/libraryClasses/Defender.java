package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Defender extends FieldPlayer {

	/**
	 * Constructor
	 * @param price				- the recommended price of the defender
	 * @param team				- the team name of the defender
	 * @param name				- the name of the defender
	 * @param age				- the age of the defender
	 * @param number			- the number of the defender
	 * @param goals				- the number of goals of the defender
	 * @param assists			- the number of assists of the defender
	 * @param yellowcards		- the number of yellow cards of the defender
	 * @param redcards			- the number of red cards of the defender
	 * @param daysInjured		- the the number of days injured of the defender
	 * @param daysSuspended		- the the number of days suspended of the defender
	 * @param isEligible		- the boolean which indicates if the defender is eligible to play
	 * @param dribblingValue	- the dribbling value of the defender
	 * @param finishingValue	- the finishing value of the defender
	 * @param defenseValue		- the defense value of the defender
	 * @param staminaValue		- the stamina value of the defender
	 */
	public Defender(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int dribblingValue, int finishingValue, int defenseValue,
			int staminaValue) {
		super(price, team, name, age, number, goals, assists, yellowcards, redcards,
				daysInjured, daysSuspended, isEligible, dribblingValue, finishingValue,
				defenseValue, staminaValue);
		this.setPlayerType("Defender");
	}
	
	/**
	 * Constructor
	 * @param price				- the recommended price of the defender
	 * @param team				- the team name of the defender
	 * @param name				- the name of the defender
	 * @param age				- the age of the defender
	 * @param number			- the number of the defender
	 * @param goals				- the number of goals of the defender
	 * @param assists			- the number of assists of the defender
	 * @param yellowcards		- the number of yellow cards of the defender
	 * @param redcards			- the number of red cards of the defender
	 * @param daysInjured		- the the number of days injured of the defender
	 * @param daysSuspended		- the the number of days suspended of the defender
	 * @param isEligible		- the boolean which indicates if the defender is eligible to play
	 * @param dribblingValue	- the dribbling value of the defender
	 * @param finishingValue	- the finishing value of the defender
	 * @param defenseValue		- the defense value of the defender
	 * @param staminaValue		- the stamina value of the defender
	 * @param daysNotForSale	- the number of days the defender is not for sale.
	 */
	public Defender(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int dribblingValue, int finishingValue, int defenseValue,
			int staminaValue, int daysNotForSale) {
		super(price, team, name, age, number, goals, assists, yellowcards, redcards,
				daysInjured, daysSuspended, isEligible, dribblingValue, finishingValue,
				defenseValue, staminaValue, daysNotForSale);
		this.setPlayerType("Defender");
	}

	

}
