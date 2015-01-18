package swinggui;

import game.Competition;
import gameLogic.TransferList;
import gameLogic.TransferLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import libraryClasses.Player;
import libraryClasses.Team;
@SuppressWarnings("serial")
public class TransfersPanel extends JPanel implements MouseListener {
	
	private MouseListener buttonListener;
	
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40,20);
	private JComponent fillLeft;
	private TransfersPanel_Left left;
	private TransfersPanel_Center center;
	private TransfersPanel_Right right;
	private JComponent fillRight;
	
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
		
		//left filler
		fillLeft = new Box.Filler(minSize, prefSize, null);
		add(fillLeft);
		
		// add the overview panels
		left = new TransfersPanel_Left(currentTeam, this);
		add(left);
		center = new TransfersPanel_Center(this);
		add(center);
		right = new TransfersPanel_Right(currentCompetition, currentTeam, this);
		add(right);
		right.noSelection();
		center.showPlayer(isleft, left.getPlayer(0));
		
		//right filler
		fillRight = new Box.Filler(minSize, prefSize, null);
		add(fillRight);
		
	}

	public void mouseDetect(MouseEvent e) {
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
			String Answer;
			Player transferPlayer = center.getPlayer();
			if(isleft){
				Answer = TransferLogic.requestSell(center.getPlayer(), currentTeam, center.getPrice(isleft), currentCompetition.getLibrary());
				Answer = Answer.substring(0, 15); 
				System.out.println(Answer);
				if(Answer.equals("Congratulations")){
					reload("Succes! Sold " + transferPlayer.getName(), new Color(197,253,179));
				} else if(Answer.equals("Unfortunately y")){
					center.setMessage("Sadly, the offer was not accepted", new Color(253, 176, 176));
				} else if(Answer.equals("You can't try t")){
					center.setMessage("Can't sell " + transferPlayer.getName() + " right now", new Color(253, 176, 176));
				}
			}
			else{
				Answer = TransferLogic.requestTransfer(center.getPlayer(), currentTeam, center.getPrice(isleft), currentCompetition.getLibrary(), transfers);
				Answer = Answer.substring(0, 15); 
				System.out.println(Answer);
				if(Answer.equals("Congratulations")){
					reload("Succes! Bought " + transferPlayer.getName(), new Color(197,253,179));
				} else {
					center.setMessage("Sadly, the offer was not accepted", new Color(253, 176, 176));
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseDetect(e);
	}

	public void reload(String message, Color color){
		
		remove(fillRight); 
		remove(right); remove(center); remove(left);
		remove(fillLeft);
		
		//left filler
		fillLeft = new Box.Filler(minSize, prefSize, null);
		add(fillLeft);
		
		// add the overview panels
		left = new TransfersPanel_Left(currentTeam, this);
		add(left);
		center = new TransfersPanel_Center(this);
		add(center);
		right = new TransfersPanel_Right(currentCompetition, currentTeam, this);
		add(right);
		right.noSelection();
		center.showPlayer(isleft, left.getPlayer(0));
		center.setMessage(message, color);
		
		//right filler
		fillRight = new Box.Filler(minSize, prefSize, null);
		add(fillRight);
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDetect(e);
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
