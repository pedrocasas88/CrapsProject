package crapsProject;

import javax.swing.JFrame;

/**
 * The viewer of the JFrame for the game Craps!
 * @author Luis Fernandez & Pedro Casas
 *
 */
public class diceRollingViewer 
{
	public static void main(String[] args) 
	{
		JFrame frame = new DiceRollingFrame();
		frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
