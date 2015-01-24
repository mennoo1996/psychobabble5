/**
 * GUI class that displays a bar with useful information
 * @version 0.0.1
 */
package swinggui;

import game.Competition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import schemeClasses.Match;
import schemeClasses.Round;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class BottomBar extends JPanel {
	
	public Dimension minSize = new Dimension(20,0);
	public Dimension prefSize = new Dimension(40,0);
	private Competition currentComp;
	private Team currentTeam;
	
	JPanel BarHolder;
	JLabel moneyLabel;
	JLabel teamLabel;
	JLabel pointsLabel;
	JLabel roundLabel;
	JLabel nextLabel;
	
	private Font barfont = new Font("Avenir", Font.ROMAN_BASELINE, 14);
	
	/**
	 * Create and initialize BottomBar object
	 * @param cComp current competition
	 * @param cTeam current team
	 */
	public BottomBar(Competition cComp, Team cTeam) {
		currentComp = cComp;
		currentTeam = cTeam;
		
		setMaximumSize(new Dimension(2000,200));
		
		initUI();
	}
	
	/**
	 * Initialize the GUI elements
	 */
	public final void initUI() {
		setLayout(new BorderLayout(0,0));
		BarHolder = new JPanel();
		add(BarHolder, BorderLayout.SOUTH);
	}
	
	/**
	 * Force the BottomBar to display a string in a specified color
	 * @param text Text to display
	 * @param color Color to display text in
	 */
	public void showString(String text, Color color){
		System.out.println(text);
		//removeAll();
		remove(BarHolder);
		
		BarHolder = new JPanel();
		add(BarHolder, BorderLayout.SOUTH);
		BarHolder.setAlignmentY(BOTTOM_ALIGNMENT);
		
		BarHolder.setLayout(new BoxLayout(BarHolder, BoxLayout.X_AXIS));

		//left filler
		BarHolder.add(new Box.Filler(minSize, prefSize, null));
				
		//visible panel
		JPanel HelperBox = new JPanel();
		HelperBox.setName("Southpanel"); HelperBox.setOpaque(false);
		HelperBox.setMinimumSize(new Dimension(900,60));
		HelperBox.setPreferredSize(new Dimension(900,60));
		HelperBox.setMaximumSize(new Dimension(900,60));
		BarHolder.add(HelperBox);
		
		//image next
		JLabel teamNext = new JLabel(createImageIcon("images/icon_empty.png"));
		teamNext.setMaximumSize(new Dimension(45,45));
		HelperBox.add(teamNext);
		
		//String
		JLabel stringLabel = new JLabel(text);
		stringLabel.setFont(barfont);
		stringLabel.setForeground(color);
		HelperBox.add(stringLabel);
				
		//image next
		JLabel teamNext2 = new JLabel(createImageIcon("images/icon_empty.png"));
		teamNext2.setMaximumSize(new Dimension(45,45));
		HelperBox.add(teamNext);
		
		//right filler
		BarHolder.add(new Box.Filler(minSize, prefSize, null));
		
		revalidate();
		repaint();
	}
	
	/**
	 * Force the BottomBar to display player statistics again
	 */
	public void showStats(){
		remove(BarHolder);
		
		//helper panel
		BarHolder = new JPanel();
		add(BarHolder, BorderLayout.SOUTH);
		BarHolder.setAlignmentY(BOTTOM_ALIGNMENT);
		BarHolder.setLayout(new BoxLayout(BarHolder, BoxLayout.X_AXIS));
		
		//left filler
		BarHolder.add(new Box.Filler(minSize, prefSize, null));
		
		//visible panel
		JPanel HelperBox = new JPanel();
		HelperBox.setName("Southpanel"); HelperBox.setOpaque(false);
		HelperBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10,5));
		HelperBox.setMinimumSize(new Dimension(900,60));
		HelperBox.setPreferredSize(new Dimension(900,60));
		HelperBox.setMaximumSize(new Dimension(900,60));
		BarHolder.add(HelperBox);
		
		//image team
		JLabel teamImage = new JLabel(createImageIcon("images/icon_ball.png"));
		teamImage.setMaximumSize(new Dimension(45,45));
		HelperBox.add(teamImage);
		
		//team
		teamLabel = new JLabel(currentTeam.getTeamName());
		teamLabel.setFont(barfont);
		HelperBox.add(teamLabel);
		
		//image cash
		JLabel teamCash = new JLabel(createImageIcon("images/icon_cash.png"));
		teamCash.setMaximumSize(new Dimension(45,45));
		HelperBox.add(teamCash);
		
		//cash
		moneyLabel = new JLabel();
		moneyLabel.setFont(barfont);
		updateMoney();
		HelperBox.add(moneyLabel);
		
		//image cup
		JLabel teamScore = new JLabel(createImageIcon("images/icon_cup.png"));
		teamScore.setMaximumSize(new Dimension(45,45));
		HelperBox.add(teamScore);
		
		//standings
		pointsLabel = new JLabel("Points: " +  currentTeam.getStandings().getPoints());
		pointsLabel.setFont(barfont);
		HelperBox.add(pointsLabel);
		
		//image next
		JLabel teamNext = new JLabel(createImageIcon("images/icon_flag.png"));
		teamNext.setMaximumSize(new Dimension(45,45));
		HelperBox.add(teamNext);
		
		//round
		Round nextRound = currentComp.getScheme().getRound(currentComp.getRoundsPlayed() + 1);
		String opponentName = null;
		if(currentComp.getRoundsPlayed() < 38) {
			ArrayList<Match> matches = nextRound.getMatches();
			for(int i=0; i<matches.size(); i++) {
				if(matches.get(i).getTeam1().equals(currentTeam.getTeamName())) {
					opponentName = matches.get(i).getTeam2();
					break;
				} else if(matches.get(i).getTeam2().equals(currentTeam.getTeamName())){
					opponentName = matches.get(i).getTeam1();
					break;
				}
			}
		}
		roundLabel = new JLabel("Round: " + currentComp.getRoundsPlayed() + ", next: " + opponentName);
		roundLabel.setFont(barfont);
		HelperBox.add(roundLabel);
		
		//right filler
		BarHolder.add(new Box.Filler(minSize, prefSize, null));
		
		revalidate();
		repaint();
	}
	
	/**
	 * Simply reload cash string in the BottomBar
	 */
	public void updateMoney(){
		
		BigDecimal big = new BigDecimal(currentTeam.getBudget()); 
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		big = big.setScale(2, BigDecimal.ROUND_DOWN);
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);		
		String priceString = formatter.format(big.longValue());
		moneyLabel.setText("Fundings: $" + priceString);
		
	}
	
	/**Function to create ImageIcons, just in case I have trouble finding them again
	 * @param path			- ImageIcon file location
	 * @return				- new ImageIcon object
	 */
	public ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, null);
		}
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
}


