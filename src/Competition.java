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
	
	public void playRound() {
		Round currentRound = scheme.getRounds().get(roundsPlayed);
		
		for(int i = 0; i < currentRound.getMatches().size(); i++) {
			Match match = currentRound.getMatches().get(i);
			
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
	
	public void printStandings() {
		System.out.println("Rounds played: " + roundsPlayed);
		
		for(int i = 0; i < library.getLibrary().size(); i++) {
			System.out.println(library.getLibrary().get(i).getTeamName() + ": " + library.getLibrary().get(i).getStandings().toString());
		}
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
