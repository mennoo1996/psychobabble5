package gameLogic;
import java.util.*;
import libraryClasses.*;

public class TransferList {
	
	private ArrayList<TransferInProgress> transfers;
	
	public TransferList() {
		transfers = new ArrayList<TransferInProgress>();
	}
	
	public void addTransfer(TransferInProgress transferIn) {
		transfers.add(transferIn);
	}
	
	public TransferInProgress getTransfer(int index) {
		return transfers.get(index);
	}
	
	public TransferInProgress getTransfer(String name) {
		for (int i=0;i<transfers.size();i++) {
			TransferInProgress t = transfers.get(i);
			if (name.equals(t.getPlayer().getName())) {
				return t;
			}
		} return null;
	}
	
	public TransferInProgress getTransfer(Player player) {
		for (int i=0;i<transfers.size();i++) {
			TransferInProgress t = transfers.get(i);
			if (player.equals(t.getPlayer())) {
				return t;
			}
		} return null;
	}
	
	public ArrayList<TransferInProgress> getTransfers() {
		return transfers;
	}
	

}
