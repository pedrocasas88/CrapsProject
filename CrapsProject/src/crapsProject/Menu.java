package crapsProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu 
{
	public static void main(String[] args) 
	{
		new Menu();
	}

	public Menu() 
	{
		JFrame frame = new JFrame("Craps Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MenuPanel());
		frame.pack();         
		frame.setLocationRelativeTo(null);         
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	public class MenuPanel extends JPanel 
	{
		private JPanel panel;
		private JButton btnStart;
		private JButton btnRules;
		private JButton btnExit;

		public MenuPanel()
		{
			setBackground(Color.GREEN);
			setPreferredSize(new Dimension(400, 400));

			panel = new JPanel(new GridLayout(3,1));
			add(panel);

			btnStart = new JButton("Start");
			btnStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					diceRollingViewer.main(null);
				}
			});

			panel.add(btnStart);

			btnRules = new JButton("Rules of the Game");
			btnRules.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					JOptionPane.showMessageDialog(panel.getComponent(0), 
							"Player "
							+ "rolls a pair of dice \n a. If the sum is 7 or 11 on the "
							+ "first throw, the shooter wins (natural); "
							+ "\n b. If the sum is 2, 3, or 12 on the first throw, the shooter loses (craps); "
							+ "\n c. If the sum is 4, 5, 6, 8, 9, or 10 on the first throw, "
							+ "\n this number becomes the player's point. "
							+ "\n The player continues "
							+ "rolling the dice until the point is \n "
							+ "rolled again (win) or a 7 is rolled (loss).");
				}

			});
			panel.add(btnRules);

			btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					System.exit(0);
				}

			});
			panel.add(btnExit);

			try
			{
				URL url = Menu.class.getResource("menu.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(url);
				Clip music = AudioSystem.getClip();
				music.open(audio);
				music.loop(-1);

			}
			catch (Exception ex) {}
		}

		@Override
		protected void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			g.drawImage(new ImageIcon(Menu.class.getResource("background.jpg")).getImage(), 0, 0, 400, 400, this);
		}
	}
}
