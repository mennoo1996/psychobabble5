import java.math.BigDecimal;

/**
 * 
 * @author Bart de Jonge
 *
 */
public abstract class FieldPlayer extends Player {

	private int dribblingValue, finishingValue, defenseValue, staminaValue;
	
	/**
	 * Constructor
	 * 
	 * @param price			- The price of the fieldplayer
	 * @param team			- The team of the fieldplayer
	 * @param name			- The name of the fieldplayer
	 * @param age			- The age of the fieldplayer
	 * @param attackValue	- The attack value of the fieldplayer
	 * @param defenseValue	- The defense value of the fieldplayer
	 * @param staminaValue	- The stamina value of the fieldplayer
	 */
	public FieldPlayer(BigDecimal price, String team, String name, int age,
			int dribblingValue, int finishingValue, int defenseValue, int staminaValue, int number) {
		super(price, team, name, age, number);
		this.dribblingValue = dribblingValue;
		this.finishingValue = finishingValue;
		this.defenseValue = defenseValue;
		this.staminaValue = staminaValue;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FieldPlayer [dribblingValue=" + dribblingValue
				+ ", finishingValue=" + finishingValue + ", defenseValue="
				+ defenseValue + ", staminaValue=" + staminaValue + ", price=" + getPrice() + ", team=" + getTeam() + ", playerType="
						+ getPlayerType() + ", name=" + getName() + ", age=" + getAge() + ", number=" + getNumber() + "]";
	}

	

	/**
	 * @return the dribblingValue
	 */
	public int getDribblingValue() {
		return dribblingValue;
	}


	/**
	 * @param dribblingValue the dribblingValue to set
	 */
	public void setDribblingValue(int dribblingValue) {
		this.dribblingValue = dribblingValue;
	}


	/**
	 * @return the finishingValue
	 */
	public int getFinishingValue() {
		return finishingValue;
	}


	/**
	 * @param finishingValue the finishingValue to set
	 */
	public void setFinishingValue(int finishingValue) {
		this.finishingValue = finishingValue;
	}


	/**
	 * @return the defenseValue
	 */
	public int getDefenseValue() {
		return defenseValue;
	}

	/**
	 * @param defenseValue - the defenseValue to set
	 */
	public void setDefenseValue(int defenseValue) {
		this.defenseValue = defenseValue;
	}

	/**
	 * @return the staminaValue
	 */
	public int getStaminaValue() {
		return staminaValue;
	}

	/**
	 * @param staminaValue - the staminaValue to set
	 */
	public void setStaminaValue(int staminaValue) {
		this.staminaValue = staminaValue;
	}	
	
}
