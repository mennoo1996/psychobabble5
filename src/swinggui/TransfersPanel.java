package swinggui;

import game.Competition;
import gameLogic.TransferList;
import gameLogic.TransferLogic;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import libraryClasses.Player;
import libraryClasses.Team;
@SuppressWarnings("serial")
public class TransfersPanel extends JPanel implements MouseListener {
	
	private MouseListener buttonListener;
	
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40,20);
	private TransfersPanel_Left left;
	private TransfersPanel_Center center;
	private TransfersPanel_Right right;
	
	private boolean isleft = true;
	private int selectedIndex;
	private Player selectedPlayer;
	private Competition currentCompetition;
	private Team currentTeam;
	
	private TransferList transfers;
	
	public TransfersPanel(Team cTeam, Competition cComp) {
		//buttonListener = detailRefresher;
		currentCompetition = cComp;
		currentTeam = cTeam;
		initUI();
	}
	
	public final void initUI() {

		transfers = new TransferList();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSize, prefSize, null));
		
		// add the overview panels
		left = new TransfersPanel_Left(currentTeam, this);
		add(left);
		center = new TransfersPanel_Center(this);
		add(center);
		right = new TransfersPanel_Right(currentCompetition, currentTeam, this);
		add(right);
		right.noSelection();
		center.showPlayer(isleft, left.getPlayer(0));
		add(new Box.Filler(minSize, prefSize, null));
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof PlayerScrollPanel_Right){
			PlayerScrollPanel_Right panel = (PlayerScrollPanel_Right) e.getSource();
			selectedIndex = panel.getPlayerIndex();
			
			if(isleft){
				left.noSelection();
				right.firstSelection(selectedIndex);
			}
			else{
				right.newSelection(selectedIndex);
			}
			isleft = false;
			center.showPlayer(isleft, right.getPlayer(selectedIndex));
		}
		if(e.getSource() instanceof PlayerScrollPanel_Left){
			PlayerScrollPanel_Left panel = (PlayerScrollPanel_Left) e.getSource();
			selectedIndex = panel.getPlayerIndex();
			if(!isleft){
				right.noSelection();
				left.firstSelection(selectedIndex);
			}
			else{
				left.newSelection(selectedIndex);
			}
			isleft = true;
			center.showPlayer(isleft, left.getPlayer(selectedIndex));
		}
		if(e.getSource() instanceof JTextField){
			isleft = false;
			center.showPlayer(isleft, right.getPlayer(0));
			left.noSelection();
		}
		if(e.getSource() instanceof JButton){
			if(isleft){
				TransferLogic.requestSell(center.getPlayer(), currentTeam, center.getPrice(isleft), currentCompetition.getLibrary());
			}
			else{
				TransferLogic.requestTransfer(center.getPlayer(), currentTeam, center.getPrice(isleft), currentCompetition.getLibrary(), transfers);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
