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
	 * @param goalkeeperValue
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

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getPlayerType() + " [name=" + this.getName() + ", age=" + this.getAge() + ", team=" + this.getTeam()
				+ ", number=" + this.getNumber() + ", price=" + this.getPrice() + ", goalkeeperValue=" + this.goalkeeperValue
				+ ", goals=" + this.getGoals() + ", assists=" + this.getAssists() + ", yellowCards=" + this.getYellowcards()
				+ ", redCards=" + this.getRedcards();
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
