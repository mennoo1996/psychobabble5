package libraryClasses;

public class Positions {

	private Player[] positionArray;
	
	public Positions(){
		positionArray = new Player[11];
	}
	
	public Positions(Player[] positionArray){
		this.positionArray = positionArray;
	}

	/**
	 * @return the positionArray
	 */
	public Player[] getPositionArray() {
		return positionArray;
	}

	/**
	 * @param positionArray the positionArray to set
	 */
	public void setPositionArray(Player[] positionArray) {
		this.positionArray = positionArray;
	}
	
}
