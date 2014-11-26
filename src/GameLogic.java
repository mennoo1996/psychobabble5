import java.util.Random;


public class GameLogic {
	
	public static int getWinner (Team t1, Team t2) {
		int t1rating = CurrentXIRating.getCurrentXIRating(t1).getTotal();
		int t2rating = CurrentXIRating.getCurrentXIRating(t2).getTotal();
		
		t1rating= (int) Math.pow(1.03, t1rating);
		t2rating= (int) Math.pow(1.03, t2rating);
		
		int t1luck = GameLogic.randomGenerator(0, 50);
		int t2luck = GameLogic.randomGenerator(0, 50);
		
		t1luck = 1+ ((t1luck-25)/100);
		t2luck = 1+ ((t2luck-25)/100);
		
		t1rating = t1rating*t1luck;
		t2rating = t2rating*t2luck;
		
		t1rating = (int)1.15*t1rating;
		
		int t1RandomNumber = GameLogic.randomGenerator(1, t1rating);
		int t2RandomNumber = GameLogic.randomGenerator(1, t2rating);
		
		if ((Math.abs(t1RandomNumber-t2RandomNumber))<3000) {
			return 0;
		} else if (t1RandomNumber>t2RandomNumber) {
			return 1;
		} else return 2;
	}
	
	
	public static int randomGenerator(int min, int max) {
		Random rand = new Random(System.currentTimeMillis());
		int randomNum=rand.nextInt((max-min)+1)+min;
		return randomNum;
		
	}

}
