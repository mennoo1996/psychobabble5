package libraryClasses;
import java.util.Random;
import java.util.*;


public class GameLogic {
	
	public static ArrayList<Player> getRedCards (Team t1, Team t2) {
		int amountofred1=0, amountofred2=0;
		ArrayList<Player> playersWithRed = new ArrayList<Player>();
		int random = GameLogic.randomGenerator(1, 100);
		if (random <=90) {
			amountofred1=0;
		} else if (random >90 && random <=99) {
			amountofred1=1;
		} else {
			amountofred1=2;
		}
		
		random = GameLogic.randomGenerator(1, 100);
		if (random <=90) {
			amountofred2=0;
		} else if (random >90 && random <=99) {
			amountofred2=1;
		} else {
			amountofred2=2;
		}
		
		for (int i=0;i<amountofred1;i++) {
			random=GameLogic.randomGenerator(0,10);
			playersWithRed.add(t1.getCurrentTeam().get(random));
			
		}
		
		for (int i=0;i<amountofred2;i++) {
			
			random=GameLogic.randomGenerator(0, 10);
			playersWithRed.add(t2.getCurrentTeam().get(random));
		}
		return playersWithRed;
	}
	
	public static ArrayList<Player> getYellowCards (Team t1, Team t2) {
		int amountofyellow1=0, amountofyellow2=0;
		ArrayList<Player> playersWithYellow = new ArrayList<Player>();
		int random = GameLogic.randomGenerator(1, 100);
		if (random <=5) {
			amountofyellow1=0;
		} else if (random >5 && random <=65) {
			amountofyellow1=1;
		} else if (random>65 && random <=85) {
			amountofyellow1=2;
		} else if (random>85 && random <=93) {
			amountofyellow1=3;
		} else if (random>93 && random <=98) {
			amountofyellow1=4;
		} else {
			amountofyellow1=5;
		}
		
		random = GameLogic.randomGenerator(1, 100);
		if (random <=5) {
			amountofyellow2=0;
		} else if (random >5 && random <=65) {
			amountofyellow2=1;
		} else if (random>65 && random <=85) {
			amountofyellow2=2;
		} else if (random>85 && random <=93) {
			amountofyellow2=3;
		} else if (random>93 && random <=98) {
			amountofyellow2=4;
		} else {
			amountofyellow2=5;
		}
		
		for (int i=0;i<amountofyellow1;i++) {
			random=GameLogic.randomGenerator(0,10);
			playersWithYellow.add(t1.getCurrentTeam().get(random));
			
		}
		
		for (int i=0;i<amountofyellow2;i++) {
			
			random=GameLogic.randomGenerator(0, 10);
			playersWithYellow.add(t2.getCurrentTeam().get(random));
		}
		return playersWithYellow;
		
	}
	
	public static ArrayList<FieldPlayer> getGoals (Team t1, Team t2, int score1, int score2) {
		ArrayList<Player> t1players = t1.getCurrentTeam();
		ArrayList<Player> t2players = t2.getCurrentTeam();
		
		ArrayList<FieldPlayer> t1fieldplayers = new ArrayList<FieldPlayer>();
		ArrayList<FieldPlayer> t2fieldplayers = new ArrayList<FieldPlayer>();
		for (int i=0;i<11;i++) {
			Player a = t1players.get(i);
			if (a instanceof FieldPlayer) {
				t1fieldplayers.add((FieldPlayer) a);
			}
			
		}
		
		for (int i=0;i<11;i++) {
			Player a = t2players.get(i);
			if (a instanceof FieldPlayer) {
				t2fieldplayers.add((FieldPlayer) a);
			}
		}
		
		
		int[] t1finishingRatings = new int[10];
		int[] t2finishingRatings = new int[10];
		for (int i=0;i<10;i++) {
			
			t2finishingRatings[i]=t2fieldplayers.get(i).getFinishingValue();
			t1finishingRatings[i]=t1fieldplayers.get(i).getFinishingValue();
		}
		
		
		for (int i=0;i<10;i++) {
			t1finishingRatings[i]=(int) Math.pow(1.05, t1finishingRatings[i]);
			t2finishingRatings[i]=(int) Math.pow(1.05, t2finishingRatings[i]);
			
		}
		
		int t1finishingtotal=0;
		int t2finishingtotal=0;
		
		for (int i=0;i<10;i++) {
			t1finishingtotal+=t1finishingRatings[i];
			t2finishingtotal+=t2finishingRatings[i];
		}
		
		ArrayList<FieldPlayer> playersWithGoals = new ArrayList<FieldPlayer>();
		
		for (int i=0;i<10;i++) {
			if (i>0) {
				t1finishingRatings[i]+=t1finishingRatings[i-1];
				t2finishingRatings[i]+=t2finishingRatings[i-1];
			}
		}
		
		for (int i=0;i<score1;i++) {
			int random = GameLogic.randomGenerator(1, t1finishingtotal);
			for (int j=0;j<10;j++) {
				if (random<=t1finishingRatings[j]) {
					playersWithGoals.add(t1fieldplayers.get(j));
					break;
				}
			}
		}
		
		for (int i=0;i<score2;i++) {
			int random = GameLogic.randomGenerator(1, t2finishingtotal);
			for (int j=0;j<10;j++) {
				if (random<=t2finishingRatings[j]) {
					playersWithGoals.add(t2fieldplayers.get(j));
					break;
				}
			}
		}
		
		return playersWithGoals;
	}
	
	public static ArrayList<FieldPlayer> getAssists(Team t1, Team t2, int score1, int score2) {
		ArrayList<Player> t1players = t1.getCurrentTeam();
		ArrayList<Player> t2players = t2.getCurrentTeam();
		
		ArrayList<FieldPlayer> t1fieldplayers = new ArrayList<FieldPlayer>();
		ArrayList<FieldPlayer> t2fieldplayers = new ArrayList<FieldPlayer>();
		for (int i=0;i<11;i++) {
			Player a = t1players.get(i);
			if (a instanceof FieldPlayer) {
				t1fieldplayers.add((FieldPlayer) a);
			}
			
		}
		
		for (int i=0;i<11;i++) {
			Player a = t2players.get(i);
			if (a instanceof FieldPlayer) {
				t2fieldplayers.add((FieldPlayer) a);
			}
		}
		
		
		int[] t1dribblingRatings = new int[10];
		int[] t2dribblingRatings = new int[10];
		for (int i=0;i<10;i++) {
			
			t2dribblingRatings[i]=t2fieldplayers.get(i).getDribblingValue();
			t1dribblingRatings[i]=t1fieldplayers.get(i).getDribblingValue();
		}
		
		
		for (int i=0;i<10;i++) {
			t1dribblingRatings[i]=(int) Math.pow(1.05, t1dribblingRatings[i]);
			t2dribblingRatings[i]=(int) Math.pow(1.05, t2dribblingRatings[i]);
			
		}
		
		int t1dribblingtotal=0;
		int t2dribblingtotal=0;
		
		for (int i=0;i<10;i++) {
			t1dribblingtotal+=t1dribblingRatings[i];
			t2dribblingtotal+=t2dribblingRatings[i];
		}
		
		ArrayList<FieldPlayer> playersWithAssists = new ArrayList<FieldPlayer>();
		
		for (int i=0;i<10;i++) {
			if (i>0) {
				t1dribblingRatings[i]+=t1dribblingRatings[i-1];
				t2dribblingRatings[i]+=t2dribblingRatings[i-1];
			}
		}
		
		for (int i=0;i<score1;i++) {
			int random = GameLogic.randomGenerator(1, t1dribblingtotal);
			for (int j=0;j<10;j++) {
				if (random<=t1dribblingRatings[j]) {
					playersWithAssists.add(t1fieldplayers.get(j));
					break;
				}
			}
		}
		
		for (int i=0;i<score2;i++) {
			int random = GameLogic.randomGenerator(1, t2dribblingtotal);
			for (int j=0;j<10;j++) {
				if (random<=t2dribblingRatings[j]) {
					playersWithAssists.add(t2fieldplayers.get(j));
					break;
				}
			}
		}
		
		return playersWithAssists;
		
	}
	
	public static int[] getMatchResults (Team t1, Team t2) {
		
		CurrentXIRating t1rating = CurrentXIRating.getCurrentXIRating(t1);
		CurrentXIRating t2rating = CurrentXIRating.getCurrentXIRating(t2);
		int result = getWinner(t1rating, t2rating);
		int[] allresults = new int[3];
		allresults[0]=result;
		if (result==0) {
			
			GameLogic.gelijkSpel(allresults, t1rating, t2rating);
			
		} else if (result==1) {
			GameLogic.Team1Wint(allresults, t1rating, t2rating);
			
		} else {
			GameLogic.Team2Wint(allresults, t1rating, t2rating);
		}
		
		return allresults;
			
		
		
	}
	
		public static void Team2Wint(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
			int difference=t1rating.getDefending() - t2rating.getFinishing();
			if (difference>10) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=95) {
					allresults[1]=0;
				} else {
					allresults[1]=1;
				}
			} else if (difference >5 && difference <=10) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=90) {
					allresults[1]=0;
				} else {
					allresults[1]=1;
				}
			} else if (difference >0 && difference <=5) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=70) {
					allresults[1]=0;
				} else if (random >70 && random <=95) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			} else if (difference <=0 && difference >-5) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <= 55) {
					allresults[1]=0;
				} else if (random >55 && random <=95) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			} else if (difference <=-5 && difference >-10) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=40) {
					allresults[1]=0;
				} else if (random >40 && random <=90) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			} else {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=25) {
					allresults[1]=0;
				} else if (random>25 && random <=80) {
					allresults[1]=1;
				} else {
					allresults[1]=2;
				}
			}
			
			difference = t1rating.getFinishing() - t2rating.getDefending();
			if (difference>10) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=10) {
					allresults[2]=allresults[1]+1;
				} else if (random >10 && random <=50) {
					allresults[2]=allresults[1]+2;
				} else if (random > 50 && random <=90) {
					allresults[2]=allresults[1]+3;
				} else {
					allresults[2]=allresults[1]+4;
				}
			} else if (difference >5 && difference <=10) {
				int random = GameLogic.randomGenerator(0,  100);
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
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=55) {
					allresults[2]=allresults[1]+1;
				} else if (random >55 && random <=95) {
					allresults[2]=allresults[1]+2;
				} else {
					allresults[2]=allresults[1]+3;
				}
			} else if (difference <=0 && difference >-5) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=75) {
					allresults[2]=allresults[1]+1;
				} else {
					allresults[2]=allresults[1]+2;
				}
			} else if (difference <=-5 && difference >-10) {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=90) {
					allresults[2]=allresults[1]+1;
				} else {
					allresults[2]=allresults[1]+2;
				}
			} else {
				int random = GameLogic.randomGenerator(0, 100);
				if (random <=95) {
					allresults[2]=allresults[1]+1;
				} else {
					allresults[2]=allresults[1]+2;
				}
			}
			
		}
		
		

		
		public static void Team1Wint(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		int difference=t1rating.getDefending() - t2rating.getFinishing();
		if (difference>10) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=95) {
				allresults[2]=0;
			} else {
				allresults[2]=1;
			}
		} else if (difference >5 && difference <=10) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=90) {
				allresults[2]=0;
			} else {
				allresults[2]=1;
			}
		} else if (difference >0 && difference <=5) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=70) {
				allresults[2]=0;
			} else if (random >70 && random <=95) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		} else if (difference <=0 && difference >-5) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <= 55) {
				allresults[2]=0;
			} else if (random >55 && random <=95) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		} else if (difference <=-5 && difference >-10) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=40) {
				allresults[2]=0;
			} else if (random >40 && random <=90) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		} else {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=25) {
				allresults[2]=0;
			} else if (random>25 && random <=80) {
				allresults[2]=1;
			} else {
				allresults[2]=2;
			}
		}
		
		difference = t1rating.getFinishing() - t2rating.getDefending();
		if (difference>10) {
			int random = GameLogic.randomGenerator(0, 100);
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
			int random = GameLogic.randomGenerator(0,  100);
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
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=55) {
				allresults[1]=allresults[2]+1;
			} else if (random >55 && random <=95) {
				allresults[1]=allresults[2]+2;
			} else {
				allresults[1]=allresults[2]+3;
			}
		} else if (difference <=0 && difference >-5) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=75) {
				allresults[1]=allresults[2]+1;
			} else {
				allresults[1]=allresults[2]+2;
			}
		} else if (difference <=-5 && difference >-10) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=90) {
				allresults[1]=allresults[2]+1;
			} else {
				allresults[1]=allresults[2]+2;
			}
		} else {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=95) {
				allresults[1]=allresults[2]+1;
			} else {
				allresults[1]=allresults[2]+2;
			}
		}
	}
	
	public static void gelijkSpel(int[] allresults, CurrentXIRating t1rating, CurrentXIRating t2rating) {
		int difference = (t1rating.getFinishing() + t2rating.getFinishing() - t1rating.getDefending() - t2rating.getDefending());
		if (difference >10) {
			int random = GameLogic.randomGenerator(0,  100);
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
			int random = GameLogic.randomGenerator(0, 100);
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
			int random = GameLogic.randomGenerator(0, 100);
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
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=75) {
				allresults[1]=0;
				allresults[2]=0;
			} else {
				allresults[1]=1;
				allresults[2]=1;
			}
		} else if (difference <= -5 && difference>-10) {
			int random = GameLogic.randomGenerator(0, 100);
			if (random <=90) {
				allresults[1]=0;
				allresults[2]=0;
			} else {
				allresults[1]=1;
				allresults[2]=1;
			}
		} else {
			int random = GameLogic.randomGenerator(0, 100);
			if (random<=95) {
				allresults[1]=0;
				allresults[2]=0;
			} else{
				allresults[1]=1;
				allresults[2]=1;
			}
		}
	}
	
	
	public static int randomGenerator(int min, int max) {
		Random rand = new Random(System.currentTimeMillis());
		int randomNum=rand.nextInt((max-min)+1)+min;
		return randomNum;
		
	}
	
	public static int getWinner (CurrentXIRating t1rating, CurrentXIRating t2rating) {
		int t1totalrating = t1rating.getTotal();
		int t2totalrating = t2rating.getTotal();
		
		t1totalrating= (int) Math.pow(1.03, t1totalrating);
		t2totalrating= (int) Math.pow(1.03, t2totalrating);
		
		int t1luck = GameLogic.randomGenerator(0, 50);
		int t2luck = GameLogic.randomGenerator(0, 50);
		
		t1luck = 1+ ((t1luck-25)/100);
		t2luck = 1+ ((t2luck-25)/100);
		
		t1totalrating = t1totalrating*t1luck;
		t2totalrating = t2totalrating*t2luck;
		
		t1totalrating = (int)1.15*t1totalrating;
		
		int t1RandomNumber = GameLogic.randomGenerator(1, t1totalrating);
		int t2RandomNumber = GameLogic.randomGenerator(1, t2totalrating);
		
		if ((Math.abs(t1RandomNumber-t2RandomNumber))<3000) {
			return 0;
		} else if (t1RandomNumber>t2RandomNumber) {
			return 1;
		} else return 2;
	}
	
	
	
	

}
