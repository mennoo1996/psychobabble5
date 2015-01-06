package swinggui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.plaf.synth.SynthLookAndFeel;

import xmlIO.XMLParser;

import libraryClasses.Competition;
import libraryClasses.Team;

//import aurelienribon.tweenengine.Tween;
//import aurelienribon.tweenengine.TweenManager;

public class Frame_Main extends JFrame implements ActionListener{
	
	private JPanel curPanel;
	private String current;
	private Competition curComp;
	private int roundNum;
	
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	
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
	
	public Frame_Main() {
		current = "nada";
		
		// Currently only supports one season
		roundNum = 0;
		curComp = XMLParser.readCompetition("files/competitionDatabase_v3.xml", "files/competition-scheme.xml");
		
		// Initialize teams
		for(Team team : curComp.getLibrary().getLibrary()) {
			team.setFirst11AsCurrentTeam();
		}
		
		System.out.println(curComp.getLibrary().getTeamForName("Manchester United").getTeam().toString());
		
		initUI();
	}
	
	public final void initUI(){
		//set Look And Feel
		SynthLookAndFeel synth = new SynthLookAndFeel();
		try {
			synth.load(Frame_Main.class.getResourceAsStream("lookandfeel.xml"), Frame_Main.class);
		} catch (ParseException e) {
			System.out.println("lookandfeel.xml not found");
		}
		try {
			UIManager.setLookAndFeel(synth);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		//initialize some stuff
		setTitle("Football Manager 2015");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(1280, 720));
		setSize(1280, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//toolkit
		Toolkit tk = Toolkit.getDefaultToolkit(); //what is this even?
		int boxwidth = (int) tk.getScreenSize().getWidth();
		int boxheight = (int) tk.getScreenSize().getHeight();
		
		//Header panel
		Header header = new Header(this);
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		add(header);
		
		//Center panel begins here
		OverviewPanel overviewPanel = new OverviewPanel(curComp);
		
		// set current screen string
		current = "overview";
		
		//Center panel ends here
		curPanel = overviewPanel;
		add(curPanel, BorderLayout.CENTER);
		
		//temporary, for the help text or something, I'll fix it later
		JPanel Helper = new JPanel();
		Helper.setLayout(new BoxLayout(Helper, BoxLayout.X_AXIS));
		Helper.add(new Box.Filler(minSize, prefSize, prefSize));
		JPanel HelperBox = new JPanel();
		HelperBox.setName("Panel");
		Helper.add(HelperBox);
		Helper.add(new Box.Filler(minSize, prefSize, prefSize));
		add(Helper);
		
		//southern space
		add(Box.createRigidArea(new Dimension(boxwidth,108)));
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				Frame_Main Mainwindow = new Frame_Main();
				Mainwindow.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// For now the main frame only listens on buttons from the header
		// bar. May need to adjust this in a future revision
		if (e.getSource() instanceof JButton) {
			JButton possibleMenuB = (JButton) e.getSource();
			
			switch (possibleMenuB.getText()) {
				case "overview ":
					System.out.println("Overview button was clicked.");
					
					// Switch to overview panel if not current
					if (!current.equals("overview")) {
						current = "overview";
						System.out.println("Current screen is: " + current);
						
						// Initialize new JPanel and remove current pane
						OverviewPanel replOverview = new OverviewPanel(curComp);
						remove(curPanel);
						
						// Refresh the view
						add(replOverview, BorderLayout.CENTER, 1);
						curPanel = replOverview;
						revalidate();
						repaint();
					}
					break;
				case "statistics":
					System.out.println("Statistics button was clicked.");
					
					// Switch to statistics panel if not current
					if (!current.equals("statistics")) {
						current = "statistics";
						System.out.println("Current screen is: " + current);
						
						// Initialize new JPanel and remove current pane
						StatisticsPanel replStatview = new StatisticsPanel(curComp);
						remove(curPanel);
						
						// Refresh the view
						add(replStatview, BorderLayout.CENTER, 1);
						curPanel = replStatview;
						revalidate();
						repaint();
					}
					break;
				case "":
					System.out.println("Play button was clicked.");
					
					// Should this be encapsulated by a game logic class method?
					// For the demo this runs through the entire season (round by round)
					
					if (roundNum < 38) {
						curComp.playRound();
						roundNum++;
					} else {
						// trigger an event signalling the start of the
						// next season?
					}
										
					// Then display statistics page showcasing the results of the season
					current = "statistics";
					
					// Initialize new JPanel and remove current pane
					StatisticsPanel replStatview = new StatisticsPanel(curComp);
					remove(curPanel);
					
					// Refresh the view
					add(replStatview, BorderLayout.CENTER, 1);
					curPanel = replStatview;
					revalidate();
					repaint();
					
					break;
				case "positions ":
					System.out.println("Positions button was clicked.");
					
					// Switch to positions panel if not current
					if (!current.equals("positions")) {
						current = "positions";
						System.out.println("Current screen is: " + current);
						
						// Initialize new JPanel and remove current pane
						PositionsPanel replPositsview = new PositionsPanel(curComp);
						remove(curPanel);
						
						// Refresh the view
						add(replPositsview, BorderLayout.CENTER, 1);
						curPanel = replPositsview;
						revalidate();
						repaint();
					}
					break;
				case "transfers":
					System.out.println("Transfers button was clicked.");
					
					// Switch to transfers panel if not current
					if (!current.equals("transfers")) {
						current = "transfers";
						System.out.println("Current screen is: " + current);
						
						// Initialize new JPanel and remove current pane
						TransfersPanel replTransfview = new TransfersPanel();
						remove(curPanel);
						
						// Refresh the view
						add(replTransfview, BorderLayout.CENTER, 1);
						curPanel = replTransfview;
						revalidate();
						repaint();
					}
					break;
				default:
					System.out.println("This might not be a menu bar item!");
					break;
			}
		}
		
	}

}
