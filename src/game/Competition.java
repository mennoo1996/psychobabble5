package game;
import gameLogic.GameLogic;

import java.util.ArrayList;
import java.util.Collections;

import schemeClasses.CompetitionScheme;
import schemeClasses.Match;
import schemeClasses.Round;
import libraryClasses.FieldPlayer;
import libraryClasses.Library;
import libraryClasses.Player;
import libraryClasses.Standings;
import libraryClasses.Team;
import libraryClasses.sortStandingsByPoints;

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
			
			int[] result = GameLogic.getMatchResults(team1, team2);
			
			// Set the scores
			match.setScoreTeam1(result[1]);
			match.setScoreTeam2(result[2]);
			
			if(result[0] == 0) {
				team1.updateStandings("draw", result[1], result[2]);
				team2.updateStandings("draw", result[1], result[2]);
			} else if(result[0] == 1) {
				team1.updateStandings("won", result[1], result[2]);
				team2.updateStandings("lost", result[2], result[1]);
				GameLogic.changePositions(team2);
			} else if(result[0] == 2) {
				team1.updateStandings("lost", result[1], result[2]);
				team2.updateStandings("won", result[2], result[1]);
				GameLogic.changePositions(team1);
			}
			
			boolean confirmed=true;
			ArrayList<FieldPlayer> goalMakerst1 = new ArrayList<FieldPlayer>();
			ArrayList<FieldPlayer> goalMakerst2 = new ArrayList<FieldPlayer>();
			ArrayList<FieldPlayer> assistMakerst1 = new ArrayList<FieldPlayer>();
			ArrayList<FieldPlayer> assistMakerst2 = new ArrayList<FieldPlayer>();
			do {
				confirmed=true;
				goalMakerst1 = GameLogic.getGoals(team1, result[1]);
				goalMakerst2 = GameLogic.getGoals(team2, result[2]);
				
				
				assistMakerst1 = GameLogic.getAssists(team1, result[1]);
				assistMakerst2 = GameLogic.getAssists(team2, result[2]);
				for (int i=0;i<goalMakerst1.size();i++) {
					if (goalMakerst1.get(i).equals(assistMakerst1.get(i))) {
						confirmed=false;
					}
				}
				for (int i=0;i<goalMakerst2.size();i++) {
					
					if (goalMakerst2.get(i).equals(assistMakerst2.get(i))) {
						confirmed=false;
					}
				}
				
			} while (!confirmed);
			match.setAssistMakerst1(assistMakerst1);
			match.setAssistMakerst2(assistMakerst2);
			for (Player a:assistMakerst1) {
				a.madeAssist();
			}
			
			for (Player a:assistMakerst2) {
				a.madeAssist();
			}
			for (Player a:goalMakerst1) {
				a.madeGoal();
			}
			
			for (Player a:goalMakerst2) {
				a.madeGoal();
			}
			match.setGoalMakerst1(goalMakerst1);
			match.setGoalMakerst2(goalMakerst2);
			match.setGoalMakerst1(goalMakerst1);
			match.setGoalMakerst2(goalMakerst2);
			
			
			int amountyellowt1 = GameLogic.getAmountOfYellowCards(team1);
			int amountyellowt2 = GameLogic.getAmountOfYellowCards(team2);
			ArrayList<Player> yellowCardGetterst1 = GameLogic.getYellowCards(amountyellowt1, team1);
			ArrayList<Player> yellowCardGetterst2 = GameLogic.getYellowCards(amountyellowt2, team2);
			match.setYellowCardGetterst1(yellowCardGetterst1);
			match.setYellowCardGetterst2(yellowCardGetterst2);
			for (Player a:yellowCardGetterst1) {
				a.gotYellow();
			}
			
			for (Player a:yellowCardGetterst2) {
				a.gotYellow();
			}
			
			int amountredt1 = GameLogic.getAmountOfRedCards(team1);
			int amountredt2 = GameLogic.getAmountOfRedCards(team2);
			ArrayList<Player> redCardGetterst1 = GameLogic.getRedCards(amountredt1, team1);
			ArrayList<Player> redCardGetterst2 = GameLogic.getRedCards(amountredt2, team2);
			match.setRedCardGetterst1(redCardGetterst1);
			match.setRedCardGetterst2(redCardGetterst2);
			for (Player a:redCardGetterst1) {
				a.gotRed();
				a.gotSuspension(2);
			}
			
			for (Player a:redCardGetterst2) {
				a.gotRed();
				a.gotSuspension(2);
			}
			
			ArrayList<Player> playersWithInjuriest1 = GameLogic.getInjuredPlayers(team1);
			ArrayList<Player> playersWithInjuriest2 = GameLogic.getInjuredPlayers(team2);
			match.setInjuredPlayerst1(playersWithInjuriest1);
			match.setInjuredPlayerst2(playersWithInjuriest2);
			int[] injurieslengthst1 = GameLogic.getInjuriesLength(playersWithInjuriest1);
			int[] injurieslengthst2 = GameLogic.getInjuriesLength(playersWithInjuriest2);
			match.setInjuriesLengthst1(injurieslengthst1);
			match.setInjuriesLengthst2(injurieslengthst2);
			for (int i=0;i<playersWithInjuriest1.size();i++) {
				playersWithInjuriest1.get(i).gotInjury(injurieslengthst1[i]+1);
			}
			
			for (int i=0;i<playersWithInjuriest2.size();i++) {
				playersWithInjuriest2.get(i).gotInjury(injurieslengthst2[i]+1);
			}
		}
		
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			for (int j=0;j<t.getTeam().size();j++) {
				t.getTeam().get(j).roundPlayed();
			}
		}
		
		

		this.roundsPlayed++;
	}
	
	/**
	 * Method which returns a sorted arraylist with standings
	 * @return arraylist
	 */
	public ArrayList<Standings> getSortedStandings() {
		ArrayList<Standings> standings = new ArrayList<Standings>();
		
		for(int i = 0; i < library.getLibrary().size(); i++) {
			Standings standing =  library.getLibrary().get(i).getStandings();
			standing.setTeamName(library.getLibrary().get(i).getTeamName());
			standings.add(standing);				
		}
		
		Collections.sort(standings, new sortStandingsByPoints());
		return standings;
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
		res += String.format("%-20s%10s%10s%10s%10s%15s%15s%15s", "Team", "Points", "Won", "Draw", "Lost", "      Goal Difference", "Goals for", "   Goals against");
		res += "\n----------------------------------------------------------------------------------------------------------------\n";
		for(int p = 0; p < standings.size(); p++) {
			Standings standing = standings.get(p);
			res += String.format("%-20s%10d%10d%10d%10d%15d%15d%15d", standing.getTeamName(), standing.getPoints(), standing.getWon(), standing.getDraw(), standing.getLost(), standing.getGoalDifference(), standing.getGoalsFor(), standing.getGoalsAgainst()) + "\n";
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
		Competition other = (Competition) obj;
		if (library == null) {
			if (other.library != null)
				return false;
		} else if (!library.equals(other.library))
			return false;
		if (roundsPlayed != other.roundsPlayed)
			return false;
		if (scheme == null) {
			if (other.scheme != null)
				return false;
		} else if (!scheme.equals(other.scheme))
			return false;
		return true;
	}
	
	public Player[] getTopScorers() {
		Player[] res = new Player[440];
		int counter=0;
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			for (int j=0;j<t.getTeam().size();j++) {
				res[counter]=t.getTeam().get(j);
				counter++;
			}
		} 
		
		Player temp;
		for (int j=0;j<res.length;j++) {
			for (int i=1;i<res.length-j;i++) {
				if (res[i-1].getGoals()>res[i].getGoals()) {
					temp=res[i];
					res[i]=res[i-1];
					res[i-1]=temp;
				}
			}
		}
		
		Player[] res2 = new Player[440];
		for (int i=0;i<440;i++) {
			res2[i]=res[439-i];
		}
		
		
		
		
		return res2;
	}
	
	public Player[] getMostAssists() {
		Player[] res = new Player[440];
		int counter=0;
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			for (int j=0;j<t.getTeam().size();j++) {
				res[counter]=t.getTeam().get(j);
				counter++;
			}
		} 
		
		Player temp;
		for (int j=0;j<res.length;j++) {
			for (int i=1;i<res.length-j;i++) {
				if (res[i-1].getAssists()>res[i].getAssists()) {
					temp=res[i];
					res[i]=res[i-1];
					res[i-1]=temp;
				}
			}
		}
		
		Player[] res2 = new Player[440];
		for (int i=0;i<440;i++) {
			res2[i]=res[439-i];
		}
		
		
		
		
		return res2;
	}
	
	public Player[] getMostYellow() {
		Player[] res = new Player[440];
		int counter=0;
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			for (int j=0;j<t.getTeam().size();j++) {
				res[counter]=t.getTeam().get(j);
				counter++;
			}
		} 
		
		Player temp;
		for (int j=0;j<res.length;j++) {
			for (int i=1;i<res.length-j;i++) {
				if (res[i-1].getYellowcards()>res[i].getYellowcards()) {
					temp=res[i];
					res[i]=res[i-1];
					res[i-1]=temp;
				}
			}
		}
		
		Player[] res2 = new Player[440];
		for (int i=0;i<440;i++) {
			res2[i]=res[439-i];
		}
		
		
		
		
		return res2;
	}
	
	public Player[] getMostRed() {
		Player[] res = new Player[440];
		int counter=0;
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			for (int j=0;j<t.getTeam().size();j++) {
				res[counter]=t.getTeam().get(j);
				counter++;
			}
		} 
		
		Player temp;
		for (int j=0;j<res.length;j++) {
			for (int i=1;i<res.length-j;i++) {
				if (res[i-1].getRedcards()>res[i].getRedcards()) {
					temp=res[i];
					res[i]=res[i-1];
					res[i-1]=temp;
				}
			}
		}
		
		Player[] res2 = new Player[440];
		for (int i=0;i<440;i++) {
			res2[i]=res[439-i];
		}
		
		
		
		
		return res2;
	}
	
	
	
	
	
	
	
}
