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
		Library other = (Library) obj;
		if (library == null) {
			if (other.library != null)
				return false;
		} else if (!library.equals(other.library))
			return false;
		return true;
	}
	
	

}
