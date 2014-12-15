package gameLogic;
import libraryClasses.*;
import java.util.*;

public class TransferLogic {
	
	public static boolean getAnswer(Player player, double bid, Library library, TransferList existingTransfers) {
		Team team = library.getTeamForName(player.getTeam());
		ArrayList<Player> playerlist = new ArrayList<Player>();
		for (int i=0;i<team.getTeam().size();i++) {
			if (player.getPlayerType().equals(team.getTeam().get(i).getPlayerType())) {
				playerlist.add(team.getTeam().get(i));
			}
		}
		int[] ratings = new int[playerlist.size()];
		if(player.getPlayerType().equals("Attacker")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]= (int) (0.6*p.getFinishingValue() + 0.2*p.getDribblingValue() + 0.2*p.getStaminaValue());
			}
		} else if (player.getPlayerType().equals("Midfielder")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]=(int) (0.15*p.getFinishingValue() + 0.4*p.getDribblingValue() + 0.3*p.getStaminaValue() + 0.15*p.getDefenseValue());
				
			}
		} else if (player.getPlayerType().equals("Defender")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]=(int) (0.05*p.getDribblingValue() + 0.25*p.getStaminaValue() + 0.7*p.getDefenseValue());
				
			}
		} else {
			for (int i=0;i<playerlist.size();i++) {
				Goalkeeper p = (Goalkeeper)playerlist.get(i);
				ratings[i]=p.getGoalkeeperValue();
			}
		}
		
		if (player.getPlayerType().equals("Goalkeeper") && playerlist.size()==1) {
			
			return false;
		}
		
		int playerid=playerlist.indexOf(player);
		int typecounter=1;
		for (int i=0;i<ratings.length;i++) {
			if (i==playerid) {
				
			} else {
				if (ratings[playerid]<ratings[i]) {
					typecounter++;
				}
			}
		}
		int random = GameLogic.randomGenerator(1, 100);
		double percentage = bid/player.getPrice().doubleValue()*100-100;
		if (typecounter==1||typecounter==2) {
			if(percentage<0) {
				return false;
				
			} else if (percentage>=0 && percentage<5) {
				if (random<=10) {
					return true;
				} else return false;
				
				
			} else if (percentage>=5 && percentage<10) {
				if (random<=20) {
					return true;
				} else return false;
			} else if (percentage>=10 && percentage<15) {
				if (random<=30) {
					return true;
				} else return false;
			} else if (percentage>=15 && percentage<20) {
				if (random<=40) {
					return true;
				} else return false;
			} else if (percentage>=20 && percentage<25) {
				if (random<=50) {
					return true;
				} else return false;
			} else if (percentage>=25 && percentage<30) {
				if (random<=75) {
					return true;
				} else return false;
			} else return true;
		} else if (typecounter==3 || typecounter==4 || typecounter==5) {
			if (percentage<-10) {
				return false;
			} else if (percentage>=-10 && percentage<0) {
				if (random<=5) {
					return true;
				} else return false;
			} else if (percentage>=0 && percentage <5) {
				if (random<=15) {
					return true;
				} else return false;
			} else if (percentage>=5 && percentage<10) {
				if (random<=30) {
					return true;
				} else return false;
			} else if (percentage>=10 && percentage<15) {
				if (random<=45) {
					return true;
				} else return false;
			} else if (percentage>=15 && percentage<20) {
				if (random<=60) {
					return true;
				} else return false;
			} else if (percentage>=20 && percentage<25) {
				if (random<=80) {
					return true;
				} else return false;
			} else return true;
		} else {
			if (percentage<-10) {
				return false;
			} else if (percentage>=-10 && percentage<0) {
				if (random<=20) {
					return true;
				} else return false;
			} else if (percentage>=0 && percentage<5) {
				if (random<=45) {
					return true;
				} else return false;
			} else if (percentage>=5 && percentage<10) {
				if (random<=70) {
					return true;
				} else return false;
			} else if (percentage>=10 && percentage<15) {
				if (random<=85) {
					return true;
				} else return false;
			} else return true;
		}
		
		
	}
		
		
		
		
		
		
		
	
	
	

}
