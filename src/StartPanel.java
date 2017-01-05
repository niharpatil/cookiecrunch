import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	
	public  StartPanel(JFrame frame){
		setSize(400,400);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		JButton instructionButton = new JButton("Instructions");
		instructionButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "The objective of this game is to shoot your CookieMonsters at the Cookie. You can control your \n"
						+ "shooter with the left and right keys to move the shooter left and right respectively. Note that a \n"
						+ "shooter can only move left or right. \n"
						+ "\n"
						+ "To control the angle that the gun shoots your CookieMonsters at, use the Up and Down keys on the keyboard. \n"
						+ "Note that all CookieMonsters are shot with the same velocity; so, be careful to time your shots. \n"
						+ "You can shoot a CookieMonster by tapping the Space bar. The number of CookieMonsters you have left, or ammo, \n"
						+ "is limited. You have only 5 shots allowed per game. If you attempt to shoot a CookieMonster after you \n"
						+ "have exhausted your 5 shots, then you lose the game. You can keep track of how many CookieMonsters you \n"
						+ "have left by viewing 'ammo' at the top of the frame. \n"
						+ "\n"
						+ "If any of your CookieMonsters ever collides with a trash can  (after being shot from the shooter), then \n"
						+ "the game is over and you lose. Note that if a trash can  collides with a CookieMonster before the CookieMonster \n"
						+ "has been shot from the shooter, nothing happens. \n");
			}
		});
		this.add(instructionButton);
	}
	
}
