package gameLogic;
import java.util.*;
import java.util.stream.DoubleStream;

import libraryClasses.FieldPlayer;
import libraryClasses.Player;
import libraryClasses.Team;

/** This class contains all the GameLogic ; it calculates the results, goalmakers, assistmakers and who got yellow/red cards
 * It will be expanded with injuries
 * @author Menno
 *
 */
public class GameLogic {
	
	private static int rngCounter = (int)System.currentTimeMillis();
	
	
	/** Returns the players of t that got injured in the match
	 * 
	 * @param t the team
	 * @return an ArrayList with players that got an injury;the length is not yet specified
	 */
	public static ArrayList<Player> getInjuredPlayers (Team t) {
		ArrayList<Player> tCurrentTeam = t.getCurrentTeam();
		ArrayList<Player> injuredPlayers = new ArrayList<Player>();
		
		for (int i=0;i<tCurrentTeam.size();i++) {
			int random = GameLogic.randomGenerator(1, 100, -1);
			if (random<=3) {
				injuredPlayers.add(tCurrentTeam.get(i));
			}
		}
		return injuredPlayers;
	}
	/** Returns the lengths of the injuries of the players that got injured
	 * 
	 * @param injuredPlayers the ArrayList containing the injured players (comes from getInjuredPlayers())
	 * @return an array of ints that represent the lengths of the injuries of the players in injuredPlayers
	 */
	public static int[] getInjuriesLength (ArrayList<Player> injuredPlayers) {
		int[] injurieslengths = new int[injuredPlayers.size()];
		for (int i=0;i<injuredPlayers.size();i++) {
			int random = GameLogic.randomGenerator(1, 100, -1);
			// 75% chance for a 1 match injury
			if (random <=75) {
				injurieslengths[i]=1;
			// 15% chance for a 3 match injury
			} else if (random > 75 && random <=90) {
				injurieslengths[i]=3;
			// 7% chance for a 6 match injury
			} else if (random > 90 && random <=97) {
				injurieslengths[i]=6;
			// 3% chance for a 12 match injury
			} else {
				injurieslengths[i]=12;
			}
		}
		
		return injurieslengths;
	}
	
	/** This method calculates the amount of red cards a team got in a match
	 * 
	 * @param t the team
	 * @return the amount of red cards for this team
	 */
	public static int getAmountOfRedCards(Team t) {
		int random = GameLogic.randomGenerator(1, 100, -1);
		//calculate the amount of red cards for team 1 based on random number
		if (random <=90) {
			return 0;
		} else if (random >90 && random <=99) {
			return 1;
		} else {
			return 2;
		}
		
	}
	/** This method calculates which players of a team got the red cards
	 * 
	 * @param amount the amount of red cards for the team
	 * @param t the team
	 * @return an ArrayList with players that got the red cards
	 */
	public static ArrayList<Player> getRedCards(int amount, Team t) {
		ArrayList<Player> playersWithRed = new ArrayList<Player>();
		for (int i=0;i<amount;i++) {
			int random = GameLogic.randomGenerator(0, 10, -1);
			playersWithRed.add(t.getCurrentTeam().get(random));
		}
		return playersWithRed;
	}
	/**This method calculates the amount of yellow cards a team got in a match
	 * 
	 * @param t the team
	 * @return the amount of yellow cards for this team
	 */
	public static int getAmountOfYellowCards(Team t) {
		int random = GameLogic.randomGenerator(1, 100, -1);
		// calculate the amount of yellow cards for team 2, based on certain boundares and the random number
		if (random <=5) {
			return 0;
		} else if (random >5 && random <=65) {
			return 1;
		} else if (random>65 && random <=85) {
			return 2;
		} else if (random>85 && random <=93) {
			return 3;
		} else if (random>93 && random <=98) {
			return 4;
		} else {
			return 5;
		}
	}
	/** This method calculates which players of a team got the yellow cards
	 * 
	 * @param amount the amount of yellow cards for the team
	 * @param t the team
	 * @return an ArrayList of Players that got yellow cards
	 */
	public static ArrayList<Player> getYellowCards (int amount, Team t) {
		int random =0;
		ArrayList<Player> playersWithYellow = new ArrayList<Player>();
		for (int i=0;i<amount;i++) {
			random = GameLogic.randomGenerator(0, 10, -1);
			playersWithYellow.add(t.getCurrentTeam().get(random));
		} return playersWithYellow;
	}
	/** This method returns the players that scored the goals
	 * 
	 * @param t1 The home-playing team
	 * @param t2 The away-playing team
	 * @param score1 The score for t1
	 * @param score2 The score for t2
	 * @return An ArrayList of FieldPlayers that made the goals for t1 and t2 respectively. An empty 
	 * ArrayList if the score was 0-0
	 */
	public static ArrayList<FieldPlayer> getGoals (Team t, int score) {
		// first retrieve the current team
		ArrayList<Player> tplayers = t.getCurrentTeam();
		// make an ArrayList that will contain the fieldplayers of t1's current team and one that will contain
		// the fieldplayers of t2's current team
		ArrayList<FieldPlayer> tfieldplayers = new ArrayList<FieldPlayer>();
		// For every player of the current team, check if it is a FieldPlayer and if this is true, add him to t1fieldplayers
		for (int i=0;i<11;i++) {
			Player a = tplayers.get(i);
			if (a instanceof FieldPlayer) {
				tfieldplayers.add((FieldPlayer) a);
			}
			
		}
		
		// Retrieve all the finishing ratings for the fieldplayers and put them in an array
		int[] tfinishingRatings = new int[10];
		for (int i=0;i<10;i++) {
			
			
			tfinishingRatings[i]=tfieldplayers.get(i).getFinishingValue();
		}
		
		// On every element of the arrays that contain the finishing ratings, compute the calculation 1.05^finishing rating
		// This is to increase the gaps, to get more realistic goalmakers further on
		for (int i=0;i<10;i++) {
			tfinishingRatings[i]=(int) Math.pow(1.05, tfinishingRatings[i]);
			
			
		}
		// Compute the total finishing rating
		// Note: actually we mean: The total 1.05^finishing rating, but from now on, that will just be called finishing rating
		int tfinishingtotal=0;
		
		for (int i=0;i<10;i++) {
			tfinishingtotal+=tfinishingRatings[i];
		}
		
		ArrayList<FieldPlayer> playersWithGoals = new ArrayList<FieldPlayer>();
		
		/* Every element in the array of finishing ratings now becomes the sum of all its successors.
			For example: If the array first contained the values {5, 2, 6, 8, 15}
			It will now contain the values {5, 7, 13, 21, 36}
			Note: the final element is now equal to t1finishingtotal or t2finishingtotal for t1 respectively t2
		*/
		for (int i=0;i<10;i++) {
			if (i>0) {
				tfinishingRatings[i]+=tfinishingRatings[i-1];
				
			}
		}
		
		// Generate a random number between 1 and the finishingtotal
		// The numbers in the array of finishingratings are now the boundaries to determine the player that scored
		// Players with a higher finishing ranking have of course a greater percentage of the total
		// Find the right player and add him to the ArrayList
		for (int i=0;i<score;i++) {
			int random = GameLogic.randomGenerator(1, tfinishingtotal, -1);
			for (int j=0;j<10;j++) {
				if (random<=tfinishingRatings[j]) {
					playersWithGoals.add(tfieldplayers.get(j));
					break;
				}
			}
		}
		
		
		return playersWithGoals;
	}
	
	
	/** The method returns the players that made the assists
	 * 
	 * @param t1 The home-playing team
	 * @param t2 The away-playing team
	 * @param score1 The score of t1
	 * @param score2 The score of t2
	 * @return An ArrayList of FieldPlayers who made the assists. An empty ArrayList if the score is 0-0
	 */
	public static ArrayList<FieldPlayer> getAssists(Team t, int score) {
		// Note: The comments in this method are short. For a more detailed explanation, see getGoals(), which
		// uses the same calculation method, only with a different player attribute
		// get the current teams of t1 and t2
		ArrayList<Player> tplayers = t.getCurrentTeam();
		
		// Retrieve the fieldplayers of t1 and t2 and put them in the right ArrayLists
		ArrayList<FieldPlayer> tfieldplayers = new ArrayList<FieldPlayer>();
		
		for (int i=0;i<11;i++) {
			Player a = tplayers.get(i);
			if (a instanceof FieldPlayer) {
				tfieldplayers.add((FieldPlayer) a);
			}
			
		}
		
		
		
		// Put the dribbling values of all the fieldplayers in arrays
		int[] tdribblingRatings = new int[10];
		
		for (int i=0;i<10;i++) {
			
			
			tdribblingRatings[i]=tfieldplayers.get(i).getDribblingValue();
		}
		
		// Compute the 1.05^dribbling rating for all elements of these arrays
		for (int i=0;i<10;i++) {
			tdribblingRatings[i]=(int) Math.pow(1.05, tdribblingRatings[i]);
			
			
		}
		
		int tdribblingtotal=0;
		
		// Compute the total dribbling rating
		for (int i=0;i<10;i++) {
			tdribblingtotal+=tdribblingRatings[i];
			
		}
		
		ArrayList<FieldPlayer> playersWithAssists = new ArrayList<FieldPlayer>();
		
		// Give every element the value that is equal to the sum of all its successors
		for (int i=0;i<10;i++) {
			if (i>0) {
				tdribblingRatings[i]+=tdribblingRatings[i-1];
			}
		}
		
		// Generate a random number between 1 and the total dribbling value, find the players that corresponds with this number
		// and add him to the ArrayList
		for (int i=0;i<score;i++) {
			int random = GameLogic.randomGenerator(1, tdribblingtotal, -1);
			for (int j=0;j<10;j++) {
				if (random<=tdribblingRatings[j]) {
					playersWithAssists.add(tfieldplayers.get(j));
					break;
				}
			}
		}
		
		return playersWithAssists;
		
	}
	/** This method calculates the result of a match.
	 * 
	 * @param t1 The home-playing team
	 * @param t2 The away-playing team
	 * @return An array of ints representing the winner, and the score
	 */
	public static int[] getMatchResults (Team t1, Team t2) {
		// retrieve the ratings of the Current XI
		CurrentXIRating t1rating = CurrentXIRating.getCurrentXIRating(t1);
		CurrentXIRating t2rating = CurrentXIRating.getCurrentXIRating(t2);
		// calculate the winner of the match
		int result = getWinner(t1rating, t2rating);
		int[] allresults = new int[3];
		allresults[0]=result;
		// call the right method to calculate the scores, based on the winner
		if (result==0) {
			
			GameLogic.gelijkSpel(allresults, t1rating, t2rating);
			
		} else if (result==1) {
			GameLogic.Team1WintLoserScore(allresults, t1rating, t2rating);
			GameLogic.Team1WintWinnerScore(allresults, t1rating, t2rating);
			
		} else {
			GameLogic.Team2WintLoserScore(allresults, t1rating, t2rating);
			GameLogic.Team2WintWinnerScore(allresults, t1rating, t2rating);
		}
		
		return allresults;
			
		
		
	}
	/** This method calculates the scores for a match in which Team 2 wins
	 * 
	 * @param allresults an array that contains as first value 2, it will be appended with the scores
	 * @param t1rating the rating of the Current Team of t1
	 * @param t2rating the rating of the Current Team of t2
	 */
	public static void Team2WintLoserScore(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		/* The calculation of the scores follows a basic pattern
		 * First the difference of loser.finishing and winner.defending is calculated
		 * Based on this result, there are certain boundaries (percentages) that represent the chance for a score for the loser to happen
		 * I will explain one of these cases in detail, the rest follow the same pattern
		 * After the loser score is determined, a 'goal difference' is calculated, based on the difference of loser.defending and winner.finishing
		 * Again, with boundaries.
		 * Also, I will explain one of these in detail, the rest follows the same pattern
		 */
			int difference=t2rating.getDefending() - t1rating.getFinishing();
			// The difference is higher than 10 --> the defending of the winner was much better than the finishing of the loser
			// This will cause a high chance of a low score for the loser
			int random = GameLogic.randomGenerator(0, 100, -1);
			if (difference>10) {
				// A random number is generated between 0 and 100
				
				// If it is smaller than 95 (that is, in 95% of the cases), the loser gets a score of 0
				if (random <=95) {
					allresults[1]=0;
				// Otherwise the loser gets a score of 1 (5% chance)
				} else {
					allresults[1]=1;
				}
			} else if (difference >5 && difference <=10) {
				if (random <=90) {
					allresults[1]=0;
				} else {
					allresults[1]=1;
				}
			} else if (difference >0 && difference <=5) {
				if (random <=70) {
					allresults[1]=0;
				} else if (random >70 && random <=95) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			} else if (difference <=0 && difference >-5) {
				if (random <= 55) {
					allresults[1]=0;
				} else if (random >55 && random <=95) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			} else if (difference <=-5 && difference >-10) {
				if (random <=40) {
					allresults[1]=0;
				} else if (random >40 && random <=90) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			} else {
				if (random <=25) {
					allresults[1]=0;
				} else if (random>25 && random <=80) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			}
			
			
			
		}
	
	public static void Team2WintWinnerScore(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		int difference = t2rating.getFinishing() - t1rating.getDefending();
		int random = GameLogic.randomGenerator(0, 100, -1);
		// If the difference is higher than 10 (that is, the finishing of the winner was much higher that the defending of the loser)
		if (difference>10) {
			// Generate a random number between 0 and 100 (100 to keep it simple, percentages)
			// in 10 % of the cases the goal difference is 1
			if (random <=10) {
				allresults[2]=allresults[1]+1;
				// in 40 % of the cases the goal difference is 2
			} else if (random >10 && random <=50) {
				allresults[2]=allresults[1]+2;
				// in 40% of the cases the goal difference is 3
			} else if (random > 50 && random <=90) {
				allresults[2]=allresults[1]+3;
				// in 10% of the cases the goal difference is 4
			} else {
				allresults[2]=allresults[1]+4;
			}
		} else if (difference >5 && difference <=10) {
			if (random<=35) {
				allresults[2]=allresults[1]+1;
			} else if (random >35 && random <=80) {
				allresults[2]=allresults[1]+2;
			} else if (random >80 && random <=95) {
				allresults[2]=allresults[1]+3;
			} else {
				allresults[2]=allresults[1]+4;
			}
		} else if (difference >0 && difference <=5) {
			if (random <=55) {
				allresults[2]=allresults[1]+1;
			} else if (random >55 && random <=95) {
				allresults[2]=allresults[1]+2;
			} else {
				allresults[2]=allresults[1]+3;
			}
		} else if (difference <=0 && difference >-5) {
			if (random <=75) {
				allresults[2]=allresults[1]+1;
			} else {
				allresults[2]=allresults[1]+2;
			}
		} else if (difference <=-5 && difference >-10) {
			if (random <=90) {
				allresults[2]=allresults[1]+1;
			} else {
				allresults[2]=allresults[1]+2;
			}
		} else {
			if (random <=95) {
				allresults[2]=allresults[1]+1;
			} else {
				allresults[2]=allresults[1]+2;
			}
		}
	}
	/** The method calculates the scores for a match in which Team 1 wins
	 * 
	 * @param allresults an array of integers of which the first value is 1, it will be appended with the scores of both teams
	 * @param t1rating the rating of the Current Team of the home playing team
	 * @param t2rating the rating of the Current Team of the away playing team
	 */
	public static void Team1WintLoserScore(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		// This method follows the same structure as Team2Wint(), but then the other way around. Read the comments there to understand the way of calculating
		int difference=t1rating.getDefending() - t2rating.getFinishing();
		int random = GameLogic.randomGenerator(0, 100, -1);
		if (difference>10) {
			if (random <=95) {
				allresults[2]=0;
			} else {
				allresults[2]=1;
			}
		} else if (difference >5 && difference <=10) {
			if (random <=90) {
				allresults[2]=0;
			} else {
				allresults[2]=1;
			}
		} else if (difference >0 && difference <=5) {
			if (random <=70) {
				allresults[2]=0;
			} else if (random >70 && random <=95) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		} else if (difference <=0 && difference >-5) {
			if (random <= 55) {
				allresults[2]=0;
			} else if (random >55 && random <=95) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		} else if (difference <=-5 && difference >-10) {
			if (random <=40) {
				allresults[2]=0;
			} else if (random >40 && random <=90) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		} else {
			if (random <=25) {
				allresults[2]=0;
			} else if (random>25 && random <=80) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		}
		
		
	}
	
	public static void Team1WintWinnerScore(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		int difference = t1rating.getFinishing() - t2rating.getDefending();
		int random = GameLogic.randomGenerator(0, 100, -1);
		if (difference>10) {
			if (random <=10) {
				allresults[1]=allresults[2]+1;
			} else if (random >10 && random <=50) {
				allresults[1]=allresults[2]+2;
			} else if (random > 50 && random <=90) {
				allresults[1]=allresults[2]+3;
			} else {
				allresults[1]=allresults[2]+4;
			}
		} else if (difference >5 && difference <=10) {
			if (random<=35) {
				allresults[1]=allresults[2]+1;
			} else if (random >35 && random <=80) {
				allresults[1]=allresults[2]+2;
			} else if (random >80 && random <=95) {
				allresults[1]=allresults[2]+3;
			} else {
				allresults[1]=allresults[2]+4;
			}
		} else if (difference >0 && difference <=5) {
			if (random <=55) {
				allresults[1]=allresults[2]+1;
			} else if (random >55 && random <=95) {
				allresults[1]=allresults[2]+2;
			} else {
				allresults[1]=allresults[2]+3;
			}
		} else if (difference <=0 && difference >-5) {
			if (random <=75) {
				allresults[1]=allresults[2]+1;
			} else {
				allresults[1]=allresults[2]+2;
			}
		} else if (difference <=-5 && difference >-10) {
			if (random <=90) {
				allresults[1]=allresults[2]+1;
			} else {
				allresults[1]=allresults[2]+2;
			}
		} else {
			if (random <=95) {
				allresults[1]=allresults[2]+1;
			} else {
				allresults[1]=allresults[2]+2;
			}
		}
	}
	/** This method calculates the scores for a match that ends in a draw
	 * 
	 * @param allresults an array of integers of which the first value is 0, it will be appended with the scores of both teams
	 * @param t1rating the rating of the Current Team of the home playing team
	 * @param t2rating the rating of the Current Team of the away playing team
	 */
	public static void gelijkSpel(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		/*	The results of the draw are based on the difference of the finishing ratings and defending ratings of both teams
		 *	The score is calculated similarly to Team2Wint(), but now both teams get an equal score
		 *	Look at Team2Wint() for an explanation of the calculation method
		 * 
		 */
		int difference = (t1rating.getFinishing() + t2rating.getFinishing() - t1rating.getDefending() - t2rating.getDefending());
		int random = GameLogic.randomGenerator(0, 100, -1);
		if (difference >10) {
			if (random<=25) {
				allresults[1]=0;
				allresults[2]=0;
			} else if (random > 25 && random <=70) {
				allresults[1]=1;
				allresults[2]=1;
			} else if (random > 70 && random <=95) {
				allresults[1]=2;
				allresults[2]=2;
			} else {
				allresults[1]=3;
				allresults[2]=3;
			}
		} else if (difference > 5 && difference <=10) {
			if (random <=35) {
				allresults[1]=0;
				allresults[2]=0;
			} else if (random > 35 && random <=85) {
				allresults[1]=1;
				allresults[2]=1;
			} else {
				allresults[1]=2;
				allresults[2]=2;
			}
		} else if (difference > 0 && difference <=5) {
			if (random <=55) {
				allresults[1]=0;
				allresults[2]=0;
			} else if (random >55 && random <=95) {
				allresults[1]=1;
				allresults[2]=1;
			} else {
				allresults[1]=2;
				allresults[2]=2;
			}
		} else if (difference <= 0 && difference > -5 ) {
			if (random <=75) {
				allresults[1]=0;
				allresults[2]=0;
			} else {
				allresults[1]=1;
				allresults[2]=1;
			}
		} else if (difference <= -5 && difference>-10) {
			if (random <=90) {
				allresults[1]=0;
				allresults[2]=0;
			} else {
				allresults[1]=1;
				allresults[2]=1;
			}
		} else {
			if (random<=95) {
				allresults[1]=0;
				allresults[2]=0;
			} else{
				allresults[1]=1;
				allresults[2]=1;
			}
		}
	}
	/** This method generates a random number
	 * 
	 * @param min The minimum of the random number to be generated
	 * @param max The maximum of the random number to be generated
	 * @return a random number between min and max
	 */
	public static int randomGenerator(int min, int max, int seed) {
		Random rand=null;
		// Use currentTimeMillis for a truly random seed, and therefore number
		if (seed==-1) {
			
		
			rand = new Random(rngCounter);
			rngCounter++;
		} else {
			rand = new Random(seed);
		}
		// Make it maximum inclusive
		int randomNum=rand.nextInt((max-min)+1)+min;
		return randomNum;
		
	}
	/** This method returns the winner of a match
	 * 
	 * @param t1rating the rating of the Current Team of the home-playing team
	 * @param t2rating the rating of the Current Team of the away-playing team
	 * @return 0 if draw, 1 if home wins, 2 if away wins
	 */
	public static int getWinner (CurrentXIRating t1rating, CurrentXIRating t2rating) {
		// retrieve the total ratings of the current teams of the teams
		int t1totalrating = t1rating.getTotal();
		int t2totalrating = t2rating.getTotal();
		
		// calculate 1.03 ^ totalrating (this is to create bigger differences, to make results more realistic)
		t1totalrating= (int) Math.pow(1.03, t1totalrating);
		t2totalrating= (int) Math.pow(1.03, t2totalrating);
		
		// calculate the luck of both teams as a random integer between 0 and 50
		int t1luck = GameLogic.randomGenerator(0, 50, -1);
		int t2luck = GameLogic.randomGenerator(0, 50, -1);
		
		// convert the luck number into a -25% to 25% lose/bonus
		t1luck = 1+ ((t1luck-25)/100);
		t2luck = 1+ ((t2luck-25)/100);
		
		// apply the luck to the rating
		t1totalrating = t1totalrating*t1luck;
		t2totalrating = t2totalrating*t2luck;
		
		// give the home team a 15% home advantage
		t1totalrating = (int)1.15*t1totalrating;
		
		// generate random numbers between 1 and the computed totalrating, for both teams
		int t1RandomNumber = GameLogic.randomGenerator(1, t1totalrating, -1);
		int t2RandomNumber = GameLogic.randomGenerator(1, t2totalrating, -1);
		
		// The team with the higher random number wins, except if the numbers lie less than 3000 apart, then it is a draw
		if ((Math.abs(t1RandomNumber-t2RandomNumber))<3000) {
			return 0;
		} else if (t1RandomNumber>t2RandomNumber) {
			return 1;
		} else return 2;
	}
	
	
	
	

}
