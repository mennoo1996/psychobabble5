package libraryClasses;

import java.util.Arrays;

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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Positions other = (Positions) obj;
		if (!Arrays.equals(positionArray, other.positionArray))
			return false;
		return true;
	}
	
	
	
}
