package swinggui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.BaseTween;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class GenFrame extends JFrame implements ActionListener /*implements TweenAccessor<GenTable>*/ {
	private GenTable currentTable;
	private TweenManager animManager;
	
	public GenFrame() {
		// Initialize new manager
		Tween.registerAccessor(GenTable.class, new GenTableAccessor());
		animManager = new TweenManager();
		
		initUI();
	}
	
	/**
	 * Initialize the main window
	 */
	public final void initUI() {
		
		// Define layout manager
		setLayout(new BorderLayout());
		
		// Initialize table
		currentTable = new GenTable();
		
		// Initialize header bar
		Header docNav = new Header(this);
		
		add(docNav, BorderLayout.NORTH);
		add(currentTable, BorderLayout.SOUTH);
		
		// Proper formatting
		pack(); //no packing pls
		
		// Make fullscreen
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int) tk.getScreenSize().getWidth();
		int ySize = (int) tk.getScreenSize().getHeight();
		setSize(xSize, ySize);
		
		setTitle("Football Manager 2015");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton possibleMenuB = (JButton) e.getSource();
			System.out.println(possibleMenuB.getText());
			
			// Event transition
			GenTable repTable = new GenTable();
			
			repTable.setToolTipText(possibleMenuB.getText());
			// Hide the new table to begin with and add to frame
			Color repColor = repTable.getBackground();
			add(repTable, BorderLayout.SOUTH);
			
			remove(currentTable);
			currentTable = repTable;
			revalidate();
			// Hide the old table
			// KEEPING THIS COMMENTED OUT UNTIL TABLE PAINT FUNCTION IS CUSTOMIZED
//			Tween.to(currentTable, GenTableAccessor.OPACITY_SCREEN, 1000)
//				.target(0)
//				.setCallback(new TweenCallback() {
//					@Override
//					public void onEvent(int event, BaseTween<?> tweens) {
//							System.out.println(event);
//							System.out.println(tweens);
//							if (event == TweenCallback.COMPLETE) {
//								System.out.println("This fired!"); 
//							}
//							remove(currentTable);
//							repaint();
//					}
//				})
//				.start(animManager);
//			// In with the new! (table)
//			Tween.to(repTable, GenTableAccessor.OPACITY_SCREEN, 2000.0f)
//				.target(1)
//				.delay(500.0f)
//				.setCallback(new TweenCallback() {
//					@Override
//					public void onEvent(int event, BaseTween<?> tweens) {
//						System.out.println("Callback fired");
//						currentTable = repTable;	
//					}
//				})
//				.start(animManager);
//			animManager.update(3000);
			
		}
	}
	
	public static void main(String[] args) {
		// Thread safe GUI adjustment
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override 
			public void run() {
				GenFrame demoWindow = new GenFrame();
				demoWindow.setVisible(true);
			}
		});
	}
}
