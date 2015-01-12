package swinggui;

import java.awt.Dimension;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import libraryClasses.Team;

@SuppressWarnings("serial")
public class PositionsPanel extends JPanel implements DragGestureListener{
	private Team currentTeam;
	public Dimension minSize = new Dimension(20,20);
	public Dimension prefSize = new Dimension(40,20);
	JPanel PanelLeft;
	JPanel PanelRight;
	
	public PositionsPanel(Team cTeam) {
		currentTeam = cTeam;
		initUI();
	}
	
	public final void initUI() {
		SetTeamPositionsForTesting();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new Box.Filler(minSize, prefSize, null));
		
		// add the overview panels
		add(new PositionsPanel_Left(currentTeam));
		add(new PositionsPanel_Right(currentTeam));
		
		add(new Box.Filler(minSize, prefSize, null));
	}
	
	public void SetTeamPositionsForTesting(){
		
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		// TODO Auto-generated method stub
	}
}
