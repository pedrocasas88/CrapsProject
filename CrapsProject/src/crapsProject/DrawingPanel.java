package crapsProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The drawing of the dice and calculation of the random rolls.
 * @author Luis Fernandez & Pedro Casas
 *
 */
@SuppressWarnings("serial")
public class DrawingPanel extends JPanel
{
	public final static int ROLL = 1;
	
	private int rollingDice;
	
	private static int die1; 
	private static int die2; 
	public static int sumOfDice;
	
	//the size of the die
	private int HEIGHT = 70;
	private int WIDTH = 70;
	//for the animation
	private Timer timer;
	private final int DELAY = 75;
	//numbers of rolls
	private int roll;
	private int MaxRoll = 12;
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//the location of the dice in the JPanel
		drawDie(g, die1, 295, 250);
		drawDie(g, die2, 365, 325);
	}

	private void drawDie(Graphics g, int side, int x, int y) 
	{
		if(rollingDice == ROLL)
		{
		//drawing the die in 2D; just the front face
		g.setColor(Color.RED);
		g.fillRect(x, y, HEIGHT , WIDTH);
		
		//The border line of the die
		g.setColor(Color.BLACK);
		g.drawRect(x, y, HEIGHT , WIDTH);

		//The side of the die: based on the recurrence of dots.
		if (side % 2 == 1) // for the odd numbers: 1, 3 & 5
		{
			g.setColor(Color.WHITE);
			g.fillOval(x+28, y+28, 15, 15); // middle dot
		}
		if (side > 1)
		{
			g.setColor(Color.WHITE);
			g.fillOval(x+8, y+8, 15, 15); // upper left dot
		}
		if (side > 1)
		{	
			g.setColor(Color.WHITE);
			g.fillOval(x+48, y+48, 15,15); // bottom right dot
		}
		if (side > 3)
		{	
			g.setColor(Color.WHITE);
			g.fillOval(x+48, y+8, 15, 15); // upper right dot
		}
		if (side > 3)
		{
			g.setColor(Color.WHITE);
			g.fillOval(x+8, y+48, 15, 15); // bottom left dot
		}
		if (side == 6)
		{
			g.setColor(Color.WHITE);
			g.fillOval(x+28, y+8, 15, 15); // upper middle dot
			g.fillOval(x+28, y+48, 15, 15); // bottom middle dot
		}
		
		g.setColor(Color.RED);
		Font myFont = new Font ("Helvetica", 1, 17);
		g.setFont (myFont);
		g.drawString("Die 1: " + die1, 5, 30);
		g.drawString("Die 2: " + die2, 5, 50);
		g.drawString("Sum of dice: " + sumOfDice, 5, 70);
		g.drawString("Points: " + sumOfDice, 5, 90);
		
		}
	}
	
	/**
	 * Run the animation of the dice rolling randomly twelve times.
	 */
	public int rollDie()
	{
		timer = new Timer(DELAY, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				//randomDice();
				die1 = (int)(Math.random()*6) + 1;
				die2 = (int)(Math.random()*6) + 1;
				sumOfDice = die1 + die2;
				System.out.println(sumOfDice);
				repaint();
				roll++; // to count how many times the dice roll.
				if (roll == MaxRoll) 
				{
					timer.stop();
				}
			}
		});
		reset();
		return sumOfDice;
			
		}

	/**
	public static int randomDice()
	{
		die1 = (int)(Math.random()*6) + 1;
		die2 = (int)(Math.random()*6) + 1;	
		
		sumOfDice = die1 + die2;
	
		return sumOfDice;	
	}
	*/


	/**
	 * Reset the roll to 0.
	 */
	public void reset() 
	{
		roll = 0;
		timer.start(); 
	}
	
	/*
	 * Make visible the dice in the JPanel
	 */
	public void drawDice (int Dice)
	{
		rollingDice = Dice;
		repaint();
	}	
}
