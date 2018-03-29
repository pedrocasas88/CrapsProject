package crapsProject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

/**
 * The JFrame that holds the JButtons, JPanel and drawing JPanel
 * @author Luis Fernandez & Pedro Casas
 *
 */
@SuppressWarnings("serial")
public class DiceRollingFrame extends JFrame 
{
	//the JPanels, JButtons, JTextFields and JLabels
	private JPanel buttonPanel;
	private JButton playButton;
	private JButton rollButton;
	private DrawingPanel diePanel;
	private JTextField txtResult;
	private JLabel lblResult;
	private JPanel txtPanel;
	private JLabel lblSumOfDice;
	private JTextField txtSumOfDice;
	private JLabel lblPoints;
	private JTextField txtFieldPoints;
	
	//the calculations
	private int sumOfDice;
	private int playerPoints;

	//the points from the dice
	//the wining rolls
	private final int LUCKY_SEVEN = 7;
	private final int YO_LEVEN = 11;

	//the losing rolls
	private final int SNAKE_EYES = 2;
	private final int ACE_DEUCE = 3;
	private final int CRAPS = 7; // After the second roll 7 make the player lose.
	private final int BOX_CARS = 12;
	

	/**
	 * The constructor
	 */
	public DiceRollingFrame() 
	{
		
		//The title
		super ("Craps");
		

		//set up the drawing area
		diePanel = new DrawingPanel();
		diePanel.setBackground(Color.GREEN);
		diePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		//set up JPanel with buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout (1,2));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				playButton(e);
			}
		});

		rollButton = new JButton("Roll");
		rollButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				rollButton(e);
			}
		});
		rollButton.setEnabled(false);

		buttonPanel.add(playButton);
		buttonPanel.add(rollButton);
		
		//set up the JPanel for JLabels and JTextFields
		txtPanel = new JPanel();
		txtPanel.setBackground(new Color(34, 139, 34));
		
		lblSumOfDice = new JLabel("Sum: ");
		txtPanel.add(lblSumOfDice);
		
		txtSumOfDice = new JTextField();
		txtSumOfDice.setEditable(false);
		txtSumOfDice.setHorizontalAlignment(SwingConstants.CENTER);
		txtSumOfDice.setBackground(new Color(173, 255, 47));
		txtPanel.add(txtSumOfDice);
		txtSumOfDice.setColumns(2);
		
		lblPoints = new JLabel("Points: ");
		txtPanel.add(lblPoints);
		
		txtFieldPoints = new JTextField();
		txtFieldPoints.setEditable(false);
		txtFieldPoints.setHorizontalAlignment(SwingConstants.CENTER);
		txtPanel.add(txtFieldPoints);
		txtFieldPoints.setColumns(2);

		lblResult = new JLabel("Result: ");
		txtPanel.add(lblResult);

		txtResult = new JTextField();
		txtPanel.add(txtResult);
		txtResult.setBackground(new Color(173, 255, 47));
		txtResult.setHorizontalAlignment(SwingConstants.CENTER);
		txtResult.setEditable(false);
		txtResult.setColumns(8);
		
		//the location of the JPanels
		getContentPane().add(txtPanel, BorderLayout.NORTH);
		getContentPane().add(diePanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * If the user pass the first roll, the button play is not enable and
	 * go to button roll
	 * @param event the click of the button Play
	 */
	protected void playButton(ActionEvent event) 
	{
		try
		{
			URL url = Menu.class.getResource("dice_rolling.wav");
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			Clip music = AudioSystem.getClip();
			music.open(audio);
			music.loop(0);
		}
		catch (Exception ex) {}
		
		diePanel.drawDice(DrawingPanel.ROLL);
		diePanel.rollDie();
	
		sumOfDice = DrawingPanel.sumOfDice; 
		txtSumOfDice.setText("" + sumOfDice);
	
		if (sumOfDice == LUCKY_SEVEN || sumOfDice == YO_LEVEN)
		{
			txtResult.setText( "You win!" );
			try
			{
				URL url = Menu.class.getResource("win.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(url);
				Clip music = AudioSystem.getClip();
				music.open(audio);
				music.loop(0);
			}
			catch (Exception ex) {}
			
		}
	
		else if (sumOfDice == SNAKE_EYES || sumOfDice == ACE_DEUCE || sumOfDice == BOX_CARS)
		{
			txtResult.setText( "Sorry, you lose." );
			try
			{
				URL url = Menu.class.getResource("lose.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(url);
				Clip music = AudioSystem.getClip();
				music.open(audio);
				music.loop(0);
			}
			catch (Exception ex) {}
		}
	
		else
		{
			// set the point and output result
			playerPoints = sumOfDice;
			//System.out.println("PlayerPoins: " + playerPoints);

			txtResult.setText( "Roll again!" );

			// update the text field Points
			txtFieldPoints.setText("" + playerPoints);	
			playButton.setEnabled( false );
			rollButton.setEnabled( true );
		}
	}
	
	/**
	 * After the first roll the user get to use this button for the 
	 * consecutive rolls depending of the random results.
	 * @param e the click of the button Roll
	 */
	protected void rollButton(ActionEvent event) 
	{

		try
		{
			URL url = Menu.class.getResource("dice_rolling.wav");
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			Clip music = AudioSystem.getClip();
			music.open(audio);
			music.loop(0);
		}
		catch (Exception ex) {}
		
		diePanel.drawDice( DrawingPanel.ROLL );
		diePanel.rollDie();
	
		sumOfDice = DrawingPanel.sumOfDice;
		txtSumOfDice.setText( "" + sumOfDice );

		

		  // determine outcome of roll, player matches point
	     if ( sumOfDice == playerPoints )
	     {
	        txtResult.setText( "You win!!!" );
	        try
			{
				URL url = Menu.class.getResource("win.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(url);
				Clip music = AudioSystem.getClip();
				music.open(audio);
				music.loop(0);
			}
			catch (Exception ex) {}
	        
	        rollButton.setEnabled( false );
	        playButton.setEnabled( true );
	     }
	      // determine outcome of roll, player loses
	     else if ( sumOfDice == CRAPS )
	     {
	        txtResult.setText( "Sorry, you lose." ); 
	        try
			{
				URL url = Menu.class.getResource("lose.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(url);
				Clip music = AudioSystem.getClip();
				music.open(audio);
				music.loop(0);
			}
			catch (Exception ex) {}
	        rollButton.setEnabled( false );
	        playButton.setEnabled( true );
	     } 
	 } 
}
