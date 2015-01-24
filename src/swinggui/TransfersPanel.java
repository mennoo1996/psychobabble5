/**
 * GUI Class that displays the Transfers Window
 */
package swinggui;

import game.Competition;
import gameLogic.TransferList;
import gameLogic.TransferLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import libraryClasses.Team;

@SuppressWarnings("serial")
public class TransfersPanel extends JPanel implements MouseListener, ActionListener {
	
	private Dimension minSize = new Dimension(20,20);
	private Dimension prefSize = new Dimension(40,20);
	private JComponent fillLeft;
	private TransfersPanel_Left left;
	private TransfersPanel_Center center;
	private TransfersPanel_Right right;
	private JComponent fillRight;
	private BottomBar bottomBar;
	
	private boolean isleft = true;
	private int selectedIndex;
	private Competition currentCompetition;
	private Team currentTeam;
	
	private TransferList transfers;
	
	/**
	 * Create and initialize a TransfersPanel
	 * @param cTeam - current Team
	 * @param cComp - current Competition
	 * @param bar - bottombar (used to display messages)
	 */
	public TransfersPanel(Team cTeam, Competition cComp, BottomBar bar) {
		bottomBar = bar;
		currentCompetition = cComp;
		currentTeam = cTeam;
		initUI();
	}
	
	/**
	 * Initialize the GUI elements contained in the NamePanel
	 */
	public final void initUI() {

		transfers = new TransferList();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		//left filler
		fillLeft = new Box.Filler(minSize, prefSize, null);
		add(fillLeft);
		
		// add the overview panels
		left = new TransfersPanel_Left(currentTeam, this);
		add(left);
		center = new TransfersPanel_Center(this, currentTeam);
		add(center);
		right = new TransfersPanel_Right(currentCompetition, currentTeam, this);
		add(right);
		right.noSelection();
		center.showPlayer(isleft, left.getPlayer(0));
		
		//right filler
		fillRight = new Box.Filler(minSize, prefSize, null);
		add(fillRight);
		
	}

	/**
	 * Mouse Listener method
	 * @param e - MouseEvent that is heard
	 */
	public void mouseDetect(MouseEvent e) {
		if(e.getSource() instanceof PlayerScrollPanel_Right){
			PlayerScrollPanel_Right panel = (PlayerScrollPanel_Right) e.getSource();
			selectedIndex = panel.getPlayerIndex();
			bottomBar.showStats();
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
			bottomBar.showStats();
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
		
	}
	
	/**
	 * unused required method
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseDetect(e);
	}

	/**
	 * (Re)load all TransfersPanel GUI content when necessary
	 */
	public void reload(){
		
		remove(fillRight); 
		remove(right); remove(center); remove(left);
		remove(fillLeft);
		
		//left filler
		fillLeft = new Box.Filler(minSize, prefSize, null);
		add(fillLeft);
		
		// add the overview panels
		left = new TransfersPanel_Left(currentTeam, this);
		add(left);
		center = new TransfersPanel_Center(this, currentTeam);
		add(center);
		right = new TransfersPanel_Right(currentCompetition, currentTeam, this);
		add(right);
		right.noSelection();
		center.showPlayer(isleft, left.getPlayer(0));
		
		//right filler
		fillRight = new Box.Filler(minSize, prefSize, null);
		add(fillRight);
		
	}
	
	/**
	 * unused required method
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * unused required method
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDetect(e);
	}

	/**
	 * unused required method
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * unused required method
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ActionListener listener method
	 * @param e - heard event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			String Answer;
			String Answer2;
			
			if(isleft){
				Answer = TransferLogic.requestSell(center.getPlayer(), currentTeam, center.getPrice(isleft), currentCompetition.getLibrary());
				Answer2 = Answer;
				Answer = Answer.substring(0, 15); 
				//System.out.println(Answer);
				if(Answer.equals("Congratulations")){
					reload();
					bottomBar.showString(Answer2, new Color(139,228,111));
					//reload("Succes! Sold " + transferPlayer.getName(), new Color(197,253,179));
				} else if(Answer.equals("Unfortunately y")){
					bottomBar.showString(Answer2, new Color(253, 176, 176));
					//center.setMessage("Sadly, the offer was not accepted", new Color(253, 176, 176));
				} else if(Answer.equals("You can't try t")){
					bottomBar.showString(Answer2, new Color(253, 176, 176));
					//center.setMessage("Can't sell " + transferPlayer.getName() + " right now", new Color(253, 176, 176));
				}
			}
			else{
				Answer = TransferLogic.requestTransfer(center.getPlayer(), currentTeam, center.getPrice(isleft), currentCompetition.getLibrary(), transfers);
				Answer2 = Answer;
				Answer = Answer.substring(0, 15); 
				//System.out.println(Answer);
				if(Answer.equals("Congratulations")){
					reload();
					bottomBar.showString(Answer2, new Color(139,228,111));
					//reload("Succes! Bought " + transferPlayer.getName(), new Color(197,253,179));
				} else {
					bottomBar.showString(Answer2, new Color(253, 176, 176));
					//center.setMessage("Sadly, the offer was not accepted", new Color(253, 176, 176));
				}
			}
		}
		
	}
}
