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
	
	public boolean contains(Player p) {
		for (int i=0; i<positionArray.length; i++) {
			Player p2 = positionArray[i];
			if (p.equals(p2)) {
				return true;
			}
		}
		return false;
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
			if (positionArray[i]!=null) {
				res += (i + 1) + "=" + positionArray[i].getName() + ",";
			} else {
				res+= (i+1) + "=null,";
			}
		}
		res += "]";
		
		return res;
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
	
	public void remove (Player p) {
		for (int i=0;i<positionArray.length;i++) {
			if (positionArray[i].equals(p)) {
				positionArray[i]=null;
			}
		}
	}
	
	
	
}
