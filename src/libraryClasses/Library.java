package libraryClasses;
import java.util.ArrayList;

/**
 * 
 * @author Bart de Jonge
 *
 */
public class Library {

	private ArrayList<Team> library;

	/**
	 *  Constructor which initialises an empty library
	 */
	public Library() {
		library = new ArrayList<Team>();
	}
	
	/**
	 * Method to add a team to the library
	 * @param team	- team to add
	 */
	public void add(Team team) {
		library.add(team);
	}
	
	public Team getTeamForName(String teamName) {
		for(int i = 0; i < library.size(); i++) {
			if(teamName.equals(library.get(i).getTeamName())) {
				return library.get(i);
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "Library:";
		for(int i = 0; i < library.size(); i++) {
			res += "\n\n" + library.get(i).toString(); 
		}
		return res;
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
