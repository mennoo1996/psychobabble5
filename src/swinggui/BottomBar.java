package swinggui;

import game.Competition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libraryClasses.Team;

@SuppressWarnings("serial")
public class BottomBar extends JPanel {
	
	private Competition currentComp;
	private Team currentTeam;
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
	JPanel BarHolder;
	
	JLabel moneyLabel;
	JLabel teamLabel;
	JLabel pointsLabel;
	
	private Font barfont = new Font("Avenir", Font.ROMAN_BASELINE, 14);
	
	public BottomBar(Competition cComp, Team cTeam) {
		currentComp = cComp;
		currentTeam = cTeam;
		
		setMaximumSize(new Dimension(2000,200));
		
		initUI();
	}
	
	public final void initUI() {
		setLayout(new BorderLayout(0,0));
		BarHolder = new JPanel();
		add(BarHolder, BorderLayout.SOUTH);
	}
	
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
				
		//String
		JLabel stringLabel = new JLabel(text);
		stringLabel.setFont(barfont);
		stringLabel.setForeground(color);
		HelperBox.add(stringLabel);
				
		//right filler
		BarHolder.add(new Box.Filler(minSize, prefSize, null));
		
		revalidate();
		repaint();
	}
	
	public void showStats(){
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
		HelperBox.setLayout(new FlowLayout(FlowLayout.CENTER, 50,5));
		HelperBox.setMinimumSize(new Dimension(900,60));
		HelperBox.setPreferredSize(new Dimension(900,60));
		HelperBox.setMaximumSize(new Dimension(900,60));
		BarHolder.add(HelperBox);
		
		//team
		teamLabel = new JLabel(currentTeam.getTeamName());
		teamLabel.setFont(barfont);
		HelperBox.add(teamLabel);
		
		//cash
		moneyLabel = new JLabel();
		moneyLabel.setFont(barfont);
		updateMoney();
		HelperBox.add(moneyLabel);
		
		//standings
		pointsLabel = new JLabel("Current points: " +  currentTeam.getStandings().getPoints());
		pointsLabel.setFont(barfont);
		HelperBox.add(pointsLabel);
		
		//right filler
		BarHolder.add(new Box.Filler(minSize, prefSize, null));
		
		revalidate();
		repaint();
	}
	
	public void updateMoney(){
		
		BigDecimal big = new BigDecimal(currentTeam.getBudget()); 
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		big = big.setScale(2, BigDecimal.ROUND_DOWN);
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);		
		String priceString = formatter.format(big.longValue());
		moneyLabel.setText("Fundings: $" + priceString);
		
	}
	
}
