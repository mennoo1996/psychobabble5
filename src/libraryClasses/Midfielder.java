package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Midfielder extends FieldPlayer {

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
	 * @param daysInjured
	 * @param daysSuspended
	 * @param isEligible
	 * @param dribblingValue
	 * @param finishingValue
	 * @param defenseValue
	 * @param staminaValue
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


}
