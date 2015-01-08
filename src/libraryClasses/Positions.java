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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "Positions [";
		
		for(int i = 0; i < 11; i++) {
			res += (i + 1) + "=" + positionArray[i].getName() + ",";
		}
		
		res += "]";
		
		return res;
	}
	
	
	
}
