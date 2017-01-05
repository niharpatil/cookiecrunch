import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.SwingUtilities;

public class Game implements Runnable{
	
	@Override
	public void run() {
		//Code only run at the very beginning of game play
		final JFrame startFrame = new JFrame("Welcome");
		startFrame.setLocation(300,300);
		startFrame.setSize(400, 400);
		StartPanel startPanel = new StartPanel(startFrame);
		startPanel.setLayout(new FlowLayout());
		
		JButton startButton = new JButton("Start Game");
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startFrame.dispose();
				start_game();
			}
		});
		
		startPanel.add(startButton);

		startFrame.add(startPanel,BorderLayout.NORTH);
		startFrame.pack();
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setVisible(true);
	}
	
	public void start_game(){
		final JFrame frame = new JFrame("Game Frame");
		frame.setLocation(300, 300);
		frame.setResizable(false);
		JLabel status = new JLabel();		
		
		final JPanel control_panel = new JPanel();
		control_panel.add(status);
		frame.add(control_panel, BorderLayout.NORTH);
		
		final GameSpace space = new GameSpace(status);
		space.setBackground(Color.BLACK);
		frame.add(space, BorderLayout.CENTER);
		
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				space.reset();
			}
		});
		
		control_panel.add(reset);
		
		final JButton getWinners = new JButton("Winners");
		getWinners.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String winner_list = "";
				LinkedList<String> winnersRetriever = new WinnersRetriever().getWinners();
				for(String s : winnersRetriever){
					winner_list = winner_list + s + "\n";
				}
				JTextArea textArea = new JTextArea(winner_list);
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(true);  
				textArea.setWrapStyleWord(true); 
				scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
				JOptionPane.showMessageDialog(null, scrollPane, "Winners",  
                        JOptionPane.YES_NO_OPTION);
			}
		});
		
		control_panel.add(getWinners);
		// Put the frame on the screen

		frame.pack();
		space.reset();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
