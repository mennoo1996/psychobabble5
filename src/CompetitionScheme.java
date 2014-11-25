import java.util.ArrayList;


public class CompetitionScheme {

	private ArrayList<Round> rounds;
//	private Standings standings;
	
	public CompetitionScheme() {
		this.rounds = new ArrayList<Round>();
//		this.standings = standings;
	}
	
//	public void playRound() {
//		
//	}
	
	public void add(Round round) {
		rounds.add(round);
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

//	/**
//	 * @return the standings
//	 */
//	public Standings getStandings() {
//		return standings;
//	}
//
//	/**
//	 * @param standings the standings to set
//	 */
//	public void setStandings(Standings standings) {
//		this.standings = standings;
//	}
	
	
	
}
