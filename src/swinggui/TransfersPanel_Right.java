package swinggui;

import game.Competition;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import libraryClasses.Player;
import libraryClasses.Team;

public class TransfersPanel_Right extends JPanel implements DocumentListener{

	private Competition currentCompetition;
	private Team currentTeam;
	private int oldSelection;
	private ArrayList<PlayerScrollPanel_Right> selections;
	private ArrayList<Player> Players;
	private ArrayList<Player> team;
	private MouseListener changeTeamListener;
	
	//defined because I need them to be accessible from the outside
	private JPanel ScrollPaneContent;
	private JTextField textField;
	private JScrollPane ScrollPane;
	private Boolean TextEmpty;
	
	public TransfersPanel_Right(Competition cComp, Team cTeam, MouseListener detailRefresher) {
		oldSelection = 0;
		Players = new ArrayList<Player>();
		currentCompetition = cComp;
		currentTeam = cTeam;
		selections = new ArrayList<PlayerScrollPanel_Right>();
		changeTeamListener = detailRefresher;
		initUI();
	}

	public final void initUI(){
		
		team = currentTeam.getTeam();
		
		//massive player array filling here (dunno if it's heavy, but maybe we should thread this?)
		for (int i=0;i<currentCompetition.getLibrary().getLibrary().size();i++) {
			Team t = currentCompetition.getLibrary().getLibrary().get(i);
			for (int j=0;j<t.getTeam().size();j++) {
				boolean add = true;
				for(int k=0;k<team.size();k++){
					if(team.get(k).equals(t.getTeam().get(j))){
						add = false;
					}
				}
				if(add){
					Players.add(t.getTeam().get(j));
				}
			}
		}
		
		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("All Players");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		titlepanel.add(title);
		titlepanel.setMaximumSize(new Dimension(2000, 100));
		add(titlepanel);

		//content begins here
		
		//text input JPanel here
		JPanel TextInputBoxPanel = new JPanel();
		TextInputBoxPanel.setOpaque(true);
		TextInputBoxPanel.setBackground(new Color(240,240,240));
		TextInputBoxPanel.setMinimumSize(new Dimension(TextInputBoxPanel.getWidth(), 41));
		TextInputBoxPanel.setMaximumSize(new Dimension(20000, 41));
		TextInputBoxPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		textField = new JTextField(18); textField.addMouseListener(changeTeamListener);
		TextEmpty = true;
		textField.getDocument().addDocumentListener(this);
		TextInputBoxPanel.add(textField);
		
		
		//top level JPanel holds all content for the scrollable.
		ScrollPaneContent = new JPanel(); ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		
		//add stuff to ScrollPaneContent (make this function so you can recall the damned thing, lol)
		for(int w=0;w<Players.size();w++){
			PlayerScrollPanel_Right panel = new PlayerScrollPanel_Right(Players.get(w), changeTeamListener, w);
			selections.add(panel);
			ScrollPaneContent.add(panel);
		}
		
		ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);

		selections.get(0).toggleSelected();
		add(TextInputBoxPanel);
		add(ScrollPane);
		
		//content ends here
		
		setMinimumSize(new Dimension(100,580));
		setPreferredSize(new Dimension(450,580));
		setMaximumSize(new Dimension(900,580));
	}
	
	public void Search(String Search){
		try {
			selections.get(oldSelection).toggleSelected();
			selections = new ArrayList<PlayerScrollPanel_Right>();
			oldSelection = 0;
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		remove(ScrollPaneContent);
		remove(ScrollPane);
		String search = Search.toLowerCase();
		boolean empty = true;
		ScrollPaneContent = new JPanel(); ScrollPaneContent.setLayout(new BoxLayout(ScrollPaneContent, BoxLayout.Y_AXIS));
		int i = 0;
		for(int w=0;w<Players.size();w++){
			if(Players.get(w).getName().toLowerCase().contains(search) || Players.get(w).getTeam().toLowerCase().contains(search)){
//				boolean add = false;
//				for(int k=0;k<currentTeam.getTeam().size();k++){
//						if(Players.get(w).equals(currentTeam.getTeam().get(k))){
//							add=true;
//					}
//				}
//				if(add==false){
//					
//				}
				PlayerScrollPanel_Right panel = new PlayerScrollPanel_Right(Players.get(w), changeTeamListener, i);
				ScrollPaneContent.add(panel);
				selections.add(panel);
				empty = false;
				i++;
			}
		}
		if(empty){
			ScrollPaneContent = new JPanel();
			JLabel nothing = new JLabel("No results");
			nothing.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 12));
			ScrollPaneContent.add(nothing);
			ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		}
		else{
			ScrollPane = new JScrollPane(ScrollPaneContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		}

		try {
			selections.get(0).toggleSelected();
		} catch (IndexOutOfBoundsException e) {
			
		}
		ScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(ScrollPane);
		revalidate();
		repaint();
	}
	
	public Player getPlayer(int index){
		return selections.get(index).getPlayer();
	}
	
	public void newSelection(int newIndex) {
		selections.get(oldSelection).toggleSelected();
		selections.get(newIndex).toggleSelected();
		oldSelection = newIndex;
	}
	
	public void noSelection(){
		selections.get(oldSelection).toggleSelected();
		oldSelection = 0;
	}
	
	public void firstSelection(int newIndex){
		selections.get(newIndex).toggleSelected();
		oldSelection = newIndex;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		Search(textField.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Search(textField.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
}
