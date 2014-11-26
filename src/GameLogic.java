import java.util.Random;


public class GameLogic {
	
	public static int[] getMatchResults (Team t1, Team t2) {
		
		CurrentXIRating t1rating = CurrentXIRating.getCurrentXIRating(t1);
		CurrentXIRating t2rating = CurrentXIRating.getCurrentXIRating(t2);
		int result = getWinner(t1rating, t2rating);
		int[] allresults = new int[3];
		allresults[0]=result;
		if (result==0) {
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
		} else if (result==1) {
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
		} else {
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
		
		return allresults;
		
		
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
