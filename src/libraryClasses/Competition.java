package libraryClasses;
import java.util.ArrayList;
import java.util.Collections;

public class Competition {
	
	private Library library;
	private CompetitionScheme scheme;
	private int roundsPlayed;
	
	/**
	 * @param library
	 * @param scheme
	 */
	public Competition(Library library, CompetitionScheme scheme, int roundsPlayed) {
		super();
		this.library = library;
		this.scheme = scheme;
		this.roundsPlayed = roundsPlayed;
	}
	
	/**
	 * Method which simulates a round by playing all matches in the current Round
	 */
	public void playRound() {
		Round currentRound = scheme.getRound(roundsPlayed + 1);
		
		for(Match match : currentRound.getMatches()) {
			Team team1 = library.getTeamForName(match.getTeam1());
			Team team2 = library.getTeamForName(match.getTeam2());
			
			int result = GameLogic.getWinner(team1, team2);
			
			if(result == 0) {
				team1.updateStandings("draw", 0, 0);
				team2.updateStandings("draw", 0, 0);
			} else if(result == 1) {
				team1.updateStandings("won", 0, 0);
				team2.updateStandings("lost", 0, 0);
			} else if(result == 2) {
				team1.updateStandings("lost", 0, 0);
				team2.updateStandings("won", 0, 0);
			}
		}

		this.roundsPlayed++;
	}
	
	/**
	 * Method which turns the current standings into a string
	 * @return	- the string containing the current standigns
	 */
	public String standingsToString() {
		ArrayList<Standings> standings = new ArrayList<Standings>();
		
		for(int i = 0; i < library.getLibrary().size(); i++) {
			Standings standing =  library.getLibrary().get(i).getStandings();
			standing.setTeamName(library.getLibrary().get(i).getTeamName());
			standings.add(standing);				
		}
		
		Collections.sort(standings, new sortStandingsByPoints());
		String res = "Rounds played: " + roundsPlayed + "\n\n";
		res += String.format("%-20s%10s%10s%10s%10s", "Team", "Points", "Won", "Draw", "Lost");
		res += "\n--------------------------------------------------------------\n";
		for(int p = 0; p < standings.size(); p++) {
			Standings standing = standings.get(p);
			res += String.format("%-20s%10d%10d%10d%10d", standing.getTeamName(), standing.getPoints(), standing.getWon(), standing.getDraw(), standing.getLost()) + "\n";
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Competition, roundsPlayed = " + roundsPlayed + "\n\n" + library.toString() + "\n\n" + scheme.toString() + "\nEnd of competition";
	}



	/**
	 * @return the library
	 */
	public Library getLibrary() {
		return library;
	}

	/**
	 * @param library the library to set
	 */
	public void setLibrary(Library library) {
		this.library = library;
	}

	/**
	 * @return the scheme
	 */
	public CompetitionScheme getScheme() {
		return scheme;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(CompetitionScheme scheme) {
		this.scheme = scheme;
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
	
	
	
	
	
}
