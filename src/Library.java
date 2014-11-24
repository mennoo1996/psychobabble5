import java.util.ArrayList;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Library {

	private ArrayList<Team> library;
	private int roundsPlayed;

	/**
	 *  Constructor which initialises an empty library
	 */
	public Library(int roundsPlayed) {
		library = new ArrayList<Team>();
		this.roundsPlayed = roundsPlayed;
	}
	
	/**
	 * Method to add a team to the library
	 * @param team	- team to add
	 */
	public void add(Team team) {
		library.add(team);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "Library, roundsPlayed=" + roundsPlayed;
		for(int i = 0; i < library.size(); i++) {
			res += "\n\n" + library.get(i).toString(); 
		}
		return res;
	}

	/**
	 * @return the roundsPlayed
	 */
	public int getRoundsPlayed() {
		return roundsPlayed;
	}

	/**
	 * @param roundsPlayed the roundsPlayed to set
	 */
	public void setRoundsPlayed(int roundsPlayed) {
		this.roundsPlayed = roundsPlayed;
	}

	/**
	 * @return the library
	 */
	public ArrayList<Team> getLibrary() {
		return library;
	}

	/**
	 * @param library - the library to set
	 */
	public void setLibrary(ArrayList<Team> library) {
		this.library = library;
	}

}
