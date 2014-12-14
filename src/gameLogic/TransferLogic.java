package gameLogic;
import libraryClasses.*;
import java.util.*;

public class TransferLogic {
	
	public static requestTransfer(Player player, double bid, Library library) {
		Team team = library.getTeamForName(player.getTeam());
		ArrayList<Player> playerlist = new ArrayList<Player>();
		for (int i=0;i<team.getTeam().size();i++) {
			if (player.getPlayerType().equals(team.getTeam().get(i).getPlayerType())) {
				playerlist.add(team.getTeam().get(i));
			}
		}
		
		int[] ratings = new int[]
		for (int i=0;i<playerlist.size();i++) {
			
		}
	}

}
