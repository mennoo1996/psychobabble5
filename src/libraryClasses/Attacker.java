package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Attacker extends FieldPlayer {

	/**
	 * Constructor
	 * @param price				- the recommended price of the attacker
	 * @param team				- the team name of the attacker
	 * @param name				- the name of the attacker
	 * @param age				- the age of the attacker
	 * @param number			- the number of the attacker
	 * @param goals				- the number of goals of the attacker
	 * @param assists			- the number of assists of the attacker
	 * @param yellowcards		- the number of yellow cards of the attacker
	 * @param redcards			- the number of red cards of the attacker
	 * @param daysInjured		- the the number of days injured of the attacker
	 * @param daysSuspended		- the the number of days suspended of the attacker
	 * @param isEligible		- the boolean which indicates if the attacker is eligible to play
	 * @param dribblingValue	- the dribbling value of the attacker
	 * @param finishingValue	- the finishing value of the attacker
	 * @param defenseValue		- the defense value of the attacker
	 * @param staminaValue		- the stamina value of the attacker
	 */
	public Attacker(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int dribblingValue, int finishingValue, int defenseValue,
			int staminaValue) {
		super(price, team, name, age, number, goals, assists, yellowcards, redcards,
				daysInjured, daysSuspended, isEligible, dribblingValue, finishingValue,
				defenseValue, staminaValue);
		this.setPlayerType("Attacker");
	}
	
	/**
	 * Constructor
	 * @param price				- the recommended price of the attacker
	 * @param team				- the team name of the attacker
	 * @param name				- the name of the attacker
	 * @param age				- the age of the attacker
	 * @param number			- the number of the attacker
	 * @param goals				- the number of goals of the attacker
	 * @param assists			- the number of assists of the attacker
	 * @param yellowcards		- the number of yellow cards of the attacker
	 * @param redcards			- the number of red cards of the attacker
	 * @param daysInjured		- the the number of days injured of the attacker
	 * @param daysSuspended		- the the number of days suspended of the attacker
	 * @param isEligible		- the boolean which indicates if the attacker is eligible to play
	 * @param dribblingValue	- the dribbling value of the attacker
	 * @param finishingValue	- the finishing value of the attacker
	 * @param defenseValue		- the defense value of the attacker
	 * @param staminaValue		- the stamina value of the attacker
	 * @param daysNotForSale	- the number of days the attacker is not for sale.
	 */
	public Attacker(BigDecimal price, String team, String name, int age,
			int number, int goals, int assists, int yellowcards, int redcards,
			int daysInjured, int daysSuspended, boolean isEligible,
			int dribblingValue, int finishingValue, int defenseValue,
			int staminaValue, int daysNotForSale) {
		super(price, team, name, age, number, goals, assists, yellowcards, redcards,
				daysInjured, daysSuspended, isEligible, dribblingValue, finishingValue,
				defenseValue, staminaValue, daysNotForSale);
		this.setPlayerType("Attacker");
	}
	
}
