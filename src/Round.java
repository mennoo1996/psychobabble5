import java.util.ArrayList;


public class Round {

	private ArrayList<Match> matches;
	private int roundNumber;
	
	/**
	 * Constructor which initializes an empty round with the given roundNumber
	 * @param roundNumber	- the number of the round
	 */
	public Round(int roundNumber) {
		matches = new ArrayList<Match>();
		this.roundNumber = roundNumber;
	}
	
	/**
	 * Method to add a match to the round
	 * @param match	- the match to add
	 */
	public void add(Match match) {
		this.matches.add(match);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "Round: " + roundNumber;
		
		for(int i = 0; i < matches.size(); i++) {
			res += "\n" + matches.get(i).toString();
		}
	
		return res;
	}

	/**
	 * @return the roundNumber
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * @param roundNumber the roundNumber to set
	 */
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	/**
	 * @return the matches
	 */
	public ArrayList<Match> getMatches() {
		return matches;
	}

	/**
	 * @param matches the matches to set
	 */
	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}
	
	
	
}
