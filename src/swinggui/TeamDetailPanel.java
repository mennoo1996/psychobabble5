package swinggui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import libraryClasses.Team;
import libraryClasses.Player;

public class TeamDetailPanel extends JPanel {
	
	Team detailTeam;
	ActionListener playListener;
	
	public TeamDetailPanel(Team selTeam, ActionListener getPlayingListener) {
		detailTeam = selTeam;
		playListener = getPlayingListener;
		
		initUI();
	}
	
	public final void initUI() {
		
		setBackground(Color.WHITE);
		setOpaque(false);
		
		// Panel name is team name
		JPanel title = new JPanel();
		JLabel titleLabel = new JLabel(detailTeam.getTeamName());
		titleLabel.setMinimumSize(new Dimension(0, 40));
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 40));
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(160,160,160)));
		title.add(titleLabel);
		add(title);
		
		// Details panel
		
		
		// Finalize selection panel
		JPanel finishPanel = new JPanel();
		
		JButton finalizeSelection = new JButton("Play as this team");
		finalizeSelection.putClientProperty("teamName", detailTeam.getTeamName());
		finalizeSelection.addActionListener(playListener);
		
		finishPanel.add(finalizeSelection, BorderLayout.CENTER);
		
		add(finishPanel);
	}
}
