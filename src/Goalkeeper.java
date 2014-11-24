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
	 * @param goalkeeperValue
	 */
	public Goalkeeper(BigDecimal price, String team, String name, int age,
			int goalkeeperValue, int number) {
		super(price, team, name, age, number);
		this.goalkeeperValue = goalkeeperValue;
		this.setPlayerType("Goalkeeper");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FieldPlayer [goalkeepValue=" + goalkeeperValue + ", price=" + getPrice() + ", team=" + getTeam() + ", playerType="
						+ getPlayerType() + ", name=" + getName() + ", age=" + getAge() + ", number=" + getNumber() + "]";
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
