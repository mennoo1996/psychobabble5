package libraryClasses;
import java.util.ArrayList;


public class CompetitionScheme {

	private ArrayList<Round> rounds;
	
	/**
	 * Constructor which initiliazes an empty competitionScheme
	 */
	public CompetitionScheme() {
		this.rounds = new ArrayList<Round>();
	}
	
	/**
	 * Method to add a round to the competition scheme
	 * @param round
	 */
	public void add(Round round) {
		rounds.add(round);
	}
	
	/**
	 * Method which return the round confirming to the given round number
	 * @param roundNumber	- the number of the round you want
	 * @return				- the round
	 */
	public Round getRound(int roundNumber) {
		for(Round round : rounds) {
			if(round.getRoundNumber() == roundNumber) {
				return round;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "Competition: \n\n";
		
		for(int i = 0; i < rounds.size(); i++) {
			res += rounds.get(i).toString() + "\n\n";
		}
		
		return res;
	}

	/**
	 * @return the rounds
	 */
	public ArrayList<Round> getRounds() {
		return rounds;
	}

	/**
	 * @param rounds the rounds to set
	 */
	public void setRounds(ArrayList<Round> rounds) {
		this.rounds = rounds;
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
		CompetitionScheme other = (CompetitionScheme) obj;
		if (rounds == null) {
			if (other.rounds != null)
				return false;
		} else if (!rounds.equals(other.rounds))
			return false;
		return true;
	}
	
	
	
	
}
