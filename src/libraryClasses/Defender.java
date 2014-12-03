package libraryClasses;
import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Defender extends FieldPlayer {

	/**
	 * @param price
	 * @param team
	 * @param name
	 * @param age
	 * @param number
	 * @param dribblingValue
	 * @param finishingValue
	 * @param defenseValue
	 * @param staminaValue
	 * @param goals
	 * @param assists
	 * @param yellowcards
	 * @param redcards
	 */
	public Defender(BigDecimal price, String team, String name, int age,
			int number, int dribblingValue, int finishingValue,
			int defenseValue, int staminaValue, int goals, int assists,
			int yellowcards, int redcards) {
		super(price, team, name, age, number, dribblingValue, finishingValue,
				defenseValue, staminaValue, goals, assists, yellowcards, redcards);
		this.setPlayerType("Defender");
	}

}
