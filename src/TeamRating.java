import java.util.*;
public class TeamRating {
	
	private int finishing;
	private int dribbling;
	private int stamina;
	private int defending;
	

	private int goalkeeping;
	/**
	 * @param finishing
	 * @param dribbling
	 * @param stamina
	 * @param defending
	 * @param goalkeeping
	 */
	public TeamRating(int finishing, int dribbling, int stamina, int defending,
			int goalkeeping) {
		super();
		this.finishing = finishing;
		this.dribbling = dribbling;
		this.stamina = stamina;
		this.defending = defending;
		this.goalkeeping = goalkeeping;
	}
	
	public static TeamRating calculateTeamRating(Team t) {
		int finishing, dribbling, stamina, defending, goalkeeping;
		ArrayList<Player> list = t.getTeam();
		ArrayList<Integer> attackers = new ArrayList<Integer>();
		ArrayList<Integer> midfielders = new ArrayList<Integer>();
		ArrayList<Integer> defenders = new ArrayList<Integer>();
		ArrayList<Integer> goalkeepers = new ArrayList<Integer>();
		Player tempPlayer;
		for (int i=0;i<list.size();i++) {
			tempPlayer=list.get(i);
			if (tempPlayer instanceof Attacker) {
				attackers.add(i);
			
			} else if (tempPlayer instanceof Midfielder) {
				midfielders.add(i);
			} else if (tempPlayer instanceof Defender) {
				defenders.add(i);
			} else {
				goalkeepers.add(i);
			}
			
		}
		
		finishing=0;
		for (int i=0;i<attackers.size();i++) {
			finishing+=10*((FieldPlayer) list.get(attackers.get(i))).getFinishingValue();
			
			
		}
		for (int i=0;i<midfielders.size();i++) {
			finishing+=7*((FieldPlayer) list.get(midfielders.get(i))).getFinishingValue();
		}
		finishing = finishing/(10*attackers.size() + 7*midfielders.size());
		
		dribbling=0;
		for (int i=0;i<attackers.size();i++) {
			dribbling+=2*((FieldPlayer) list.get(attackers.get(i))).getDribblingValue();
		}
		
		for (int i=0;i<midfielders.size();i++) {
			dribbling+=5*((FieldPlayer) list.get(midfielders.get(i))).getDribblingValue();
		}
		
		for (int i=0;i<defenders.size();i++) {
			dribbling+=1*((FieldPlayer) list.get(defenders.get(i))).getDribblingValue();
		}
		dribbling=dribbling/(2*attackers.size() + 5*midfielders.size() + 1*defenders.size());
		
		
		stamina=0;
		for (int i=0;i<attackers.size();i++) {
			stamina+=1*((FieldPlayer) list.get(attackers.get(i))).getStaminaValue();
		}
		
		for (int i=0;i<midfielders.size();i++) {
			stamina+=2*((FieldPlayer) list.get(midfielders.get(i))).getStaminaValue();
		}
		
		for (int i=0;i<defenders.size();i++) {
			stamina+=1*((FieldPlayer) list.get(defenders.get(i))).getStaminaValue();
		}
		
		stamina=stamina/(attackers.size() + 2*midfielders.size() + defenders.size());
		
		
		defending=0;
		for (int i=0;i<midfielders.size();i++) {
			defending+=1*((FieldPlayer) list.get(midfielders.get(i))).getDefenseValue();
		}
		
		for (int i=0;i<defenders.size();i++) {
			defending+=10*((FieldPlayer) list.get(defenders.get(i))).getDefenseValue();
		}
		
		defending=defending/(1*midfielders.size() + 10*defenders.size());
		
		goalkeeping=0;
		for (int i=0;i<goalkeepers.size();i++) {
			goalkeeping+=((Goalkeeper) list.get(goalkeepers.get(i))).getGoalkeeperValue();
		}
		
		goalkeeping=goalkeeping/(goalkeepers.size());
		
		return new TeamRating (finishing, dribbling, stamina, defending, goalkeeping);
	}
	
	
	@Override
	public String toString() {
		return "TeamRating [finishing=" + finishing + ", dribbling="
				+ dribbling + ", stamina=" + stamina + ", defending="
				+ defending + ", goalkeeping=" + goalkeeping + "]";
	}
	

}
