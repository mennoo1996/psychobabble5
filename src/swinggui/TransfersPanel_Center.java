/**
 * GUI Class that displays the centermost Transfers panel
 */
package swinggui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import libraryClasses.FieldPlayer;
import libraryClasses.Goalkeeper;
import libraryClasses.Player;
import libraryClasses.Team;

@SuppressWarnings("serial")
public class TransfersPanel_Center extends JPanel implements DocumentListener{
	
	private ActionListener buttonListener;
	
	private Team currentTeam;
	
	private JPanel playerPanel;
	private JPanel statPanel;
	private JPanel sellPanel;
	private JPanel buyPanel;
	private JPanel messagePanel;
	private JLabel messagePanel2;
	
	private JTextField buyField;
	private JTextField sellField;
	private JButton buyButton;
	private JButton sellButton;
	private JLabel leftLabel;
	private JLabel rightLabel;
	private boolean isLeft;
	private Player player;
	
	private Font fontPlayername = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	private Font fontPlayerattr = new Font("Avenir", Font.ROMAN_BASELINE, 11);
	private Font fontSelected = new Font("Avenir", Font.ROMAN_BASELINE, 12);
	
	/**
	 * Create and initialize a TransfersPanel_Left
	 * @param teamActionListener - ActionListener to listen to button inputs
	 * @param cTeam - Currently selected team of which to display players
	 */
	public TransfersPanel_Center(ActionListener teamActionListener, Team cTeam) {
		currentTeam = cTeam;
		buttonListener = teamActionListener;
		initUI();
	}
	
	/**
	 * Initialize the GUI elements contained in the TransfersPanel_Left
	 */
	public final void initUI(){
		
		setOpaque(false);
		setName("Panel");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//panel title
		JPanel titlepanel = new JPanel();
		JLabel title = new JLabel("Selected");
		title.setMinimumSize(new Dimension(0,40));
		title.setPreferredSize(new Dimension(title.getPreferredSize().width, 40));
		titlepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		titlepanel.add(title);
		titlepanel.setMaximumSize(new Dimension(2000, 100));
		add(titlepanel);

		//content begins here
		playerPanel = new JPanel();
		statPanel = new JPanel();
		buyPanel = new JPanel();
		sellPanel = new JPanel();
		messagePanel = new JPanel();
		
		add(playerPanel);
		add(statPanel);
		add(sellPanel);
		add(buyPanel);
		add(messagePanel);
		//content ends here
		
		setMinimumSize(new Dimension(100,580));
		setPreferredSize(new Dimension(350,580));
		setMaximumSize(new Dimension(900,580));
		
	}
	
	/**
	 * Reload GUI elements to display selected Player
	 * @param isleft - bool to choose from either left or right-panel players
	 * @param play - player to show
	 */
	public void showPlayer(boolean isleft, Player play){
		player = play;
		isLeft = isleft;
		remove(messagePanel);
		remove(buyPanel);
		remove(sellPanel);
		remove(statPanel);
		remove(playerPanel);
		playerPanel = new JPanel(new BorderLayout());

		//shirt
		ImageIcon myImageIcon;// = createImageIcon("images/Shirts/T-Shirt.png");
		if(player.getDaysInjured() > 0) {
			//injury sticker
			myImageIcon = createImageIcon("images/red-cross.png");
		} else if(player.getDaysSuspended() > 0) {
			//suspension sticker
			myImageIcon = createImageIcon("images/red-card.png");
		} else {
			//t-shirt
			myImageIcon = createImageIcon("images/Shirts/T-Shirt.png");
		}
		
		JLabel label1 = new JLabel ("") {
		    @Override
		    public void paintComponent (Graphics g) {
		        super.paintComponent (g);
		        g.drawImage (myImageIcon.getImage(), 0, 0, 75, 75, null);
		    }
		};
		label1.setPreferredSize(new Dimension(75,75));
		label1.setOpaque(false);
		playerPanel.add(label1, BorderLayout.WEST);
		
		//Player Name, T-Shirt Panel
		
		String name = player.getName();
		String team = player.getTeam();
		JPanel namePanel = new JPanel(); namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		namePanel.add(new Box.Filler(new Dimension(0,8), new Dimension(0,8), new Dimension(0,8)));
		namePanel.add(new JLabel(name));
		
		JLabel Team = new JLabel(team); Team.setFont(new Font("Avenir", Font.ROMAN_BASELINE, 16));
		namePanel.add(Team);
		playerPanel.add(namePanel, BorderLayout.CENTER); 
		playerPanel.setMaximumSize(new Dimension(2000, 250));
		
		//Player Stats Panel
		statPanel = new JPanel();
		statPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		statPanel.setMinimumSize(new Dimension(0, 60));
		statPanel.setPreferredSize(new Dimension(50, 60));
		statPanel.setMaximumSize(new Dimension(2000, 60));
		if(player.getPlayerType() != "Goalkeeper"){
			FieldPlayer player1 = (FieldPlayer) player;
			
			int[] Attr = {	player1.getDefenseValue(),
							player1.getDribblingValue(),
							player1.getFinishingValue(),
							player1.getStaminaValue()
			};
			JLabel[] AttrLabel = {	new JLabel("	Def: " + Integer.toString(Attr[0])),
									new JLabel("	Dri: " + Integer.toString(Attr[1])),
									new JLabel("	Fin: " + Integer.toString(Attr[2])),
									new JLabel("	Sta: " + Integer.toString(Attr[3]))		
			};	
			
			for(int e = 0; e < 4; e++){
				AttrLabel[e].setFont(fontPlayerattr);
				statPanel.add(AttrLabel[e]);
			}
		}
		else{
			Goalkeeper player1 = (Goalkeeper) player;
			JLabel attr = new JLabel("Goalkeeper statistic value: " + player1.getGoalkeeperValue());
			attr.setFont(fontPlayerattr);
			statPanel.add(attr);
		}
		JLabel playerType = new JLabel("Player type: " + player.getPlayerType());
		playerType.setAlignmentY(CENTER_ALIGNMENT);
		playerType.setFont(fontPlayername);
		statPanel.add(playerType);
		
		//price string
		BigDecimal bd = player.getPrice();
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);		
		String priceString = formatter.format(bd.longValue());
		
		//sellpanel with normal content when isLeft, or gray content when !isLeft
		sellPanel = new JPanel(new BorderLayout());
		JPanel sellPanel2 = new JPanel();
		sellPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		
		//selllabel
		JLabel sellLabel = new JLabel("Suggested price: $" + priceString);
		sellLabel.setFont(fontSelected);
		
		//selltextfield
		sellField = new JTextField(10);
		sellField.setName("textField");
		sellField.getDocument().addDocumentListener(this);
		
		//sellbutton
		sellButton = new JButton("Sell player");
		sellButton.setName("Test");
		sellButton.setEnabled(false);
		
		//leftwarning
		leftLabel = new JLabel();
		leftLabel.setFont(fontPlayerattr);
		leftLabel.setForeground(new Color(255,0,0));
		
		sellPanel2.add(sellButton);
		sellPanel2.add(sellLabel);
		sellPanel2.add(sellField);
		sellPanel2.add(leftLabel);
		
		if(!isLeft){
			sellPanel.setOpaque(true);
			sellPanel.setBackground(new Color(240,240,240));
			sellLabel.setText("select a player from your team");
			sellField.setEnabled(false);
		}
		else{
			sellField.setEnabled(true);
			sellButton.addActionListener(buttonListener);
		}
		
		sellPanel.add(sellPanel2, BorderLayout.CENTER);
		
		//buypanel with normal content when !isLeft, or gray content when isLeft
		
		buyPanel = new JPanel(new BorderLayout());
		JPanel buyPanel2 = new JPanel();
		buyPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180,180,180)));
		
		//buylabel
		JLabel buyLabel = new JLabel("Suggested price: $" + priceString);
		buyLabel.setFont(fontSelected);
		
		//buytextfield
		buyField=  new JTextField(10);
		buyField.setName("textField");
		buyField.getDocument().addDocumentListener(this);
		
		//buybutton
		buyButton = new JButton("Buy player");
		buyButton.setName("Test");
		buyButton.setEnabled(false);

		//rightwarning
		rightLabel = new JLabel("");
		rightLabel.setFont(fontPlayerattr);
		rightLabel.setForeground(new Color(255,0,0));
		
		buyPanel2.add(buyButton);
		buyPanel2.add(buyLabel);
		buyPanel2.add(buyField);
		buyPanel2.add(rightLabel);
		
		if(isLeft){
			buyLabel.setText("select a player from other teams");
			buyPanel.setOpaque(true);
			buyPanel.setBackground(new Color(240,240,240));
			buyField.setEnabled(false);
			buyButton.setEnabled(false);
		}
		else{
			buyField.setEnabled(true);
			buyButton.setEnabled(false);
			buyButton.addActionListener(buttonListener);
		}
		buyPanel.add(buyPanel2, BorderLayout.CENTER);
		
		//messagepanel
		messagePanel = new JPanel();
		messagePanel.setOpaque(true);
		messagePanel2 = new JLabel("Transfer players here");
		messagePanel2.setFont(fontPlayerattr);
		messagePanel.add(messagePanel2);
		
		add(playerPanel);
		add(statPanel);
		add(sellPanel);
		add(buyPanel);
		add(messagePanel);
		
		revalidate();
		repaint();
		
	}
	
	/**
	 * Check price for size
	 * @param isSell - to choose between fields for selling and buying
	 * @return price, in int
	 */
	public int getPrice(boolean isSell){
		String stringPrice;
		if(isSell){
			stringPrice = sellField.getText();
		}
		else{
			stringPrice = buyField.getText();
		}
		int Price = Integer.parseInt(stringPrice);
		if(Price>100000000){
			Price=100000000;
		}
		return Price;
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
	
	/**
	 * Return selected Player object
	 * @return player - selected Player object
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Set message at bottom panel
	 * @param message - String to show
	 * @param color - Color to show string in
	 */
	public void setMessage(String message, Color color){
		messagePanel.setBackground(color);
		messagePanel2.setText(message);
	}
	
	/**
	 * Check price integer for validity, and, if viable, allow transfer buttons to show
	 */
	public void checkInt(){
		JTextField input;
		JLabel output;
		JButton button;
		if(isLeft){
			input = sellField;
			output = leftLabel;
			button = sellButton;
		}
		else{
			input = buyField;
			output = rightLabel;
			button = buyButton;
		}
		output.setText("");
		button.setEnabled(true);
		setMessage("Transfer players here", new Color(255,255,255));
		try{
			int Price = Integer.parseInt(input.getText());
			if(Price > currentTeam.getBudget() && !isLeft) {
				output.setText("Current budget too small!");
				setMessage("", new Color(245,245,245));
				button.setEnabled(false);
			} else {
				button.setEnabled(true);
			}
		}
		catch(NumberFormatException e){
			if(input.getText().equals("")){
				output.setText("");
				setMessage("Transfer players here", new Color(255,255,255));
				button.setEnabled(false);
			}
			else{
				output.setText("Please enter a number!");
				setMessage("", new Color(245,245,245));
				button.setEnabled(false);
			}
		}
		
	}
	
	/**
	 * unused required method
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		checkInt();
	}

	/**
	 * unused required method
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		checkInt();
	}

	/**
	 * unused required method
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}
	
}

