import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.*;
	
@SuppressWarnings("serial")
public class GameSpace extends JPanel{
	private LinkedList<Obstacle> obstacles;
	private Shooter shooter;
	private LinkedList<Bullet> bullets;
	private Target target;
	private String message;
	private LevelSetting levelSetting;
	private String username;
	private WinnersController winnersController;
	private boolean playing;
	private JLabel status;
	private int level;
	private boolean reset_pressed = true;
		
	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 300;
	public static final int SHOOTER_VELOCITY = 6;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	
	public GameSpace(JLabel status){
		this.status = status;
		obstacles = new LinkedList<Obstacle>();
		bullets = new LinkedList<Bullet>();
		winnersController = new WinnersController();
		playing = true;
		
		message = "";
		
		setBorder(BorderFactory.createLineBorder(Color.RED));
		

		setFocusable(true);
		
		getUserName();
		
		Timer timer = new Timer(35, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(reset_pressed){
					return;
				} else {
					tick();
				}
			}	
		});
		
		timer.start();
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					shooter.v_x = -SHOOTER_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					shooter.v_x = SHOOTER_VELOCITY;
				else if(e.getKeyCode() == KeyEvent.VK_UP)
					shooter.move_gun(.1);
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)
					shooter.move_gun(-.1);
				else if (e.getKeyCode() == KeyEvent.VK_SPACE){
					shooter.ammo_minus();
					if(shooter.hasAmmo()){
						Bullet bullet = bullets.get(bullets.size() - 1 - shooter.get_ammo());
						bullet.v_x = (int)(5 * Math.cos(shooter.get_gun_theta()));
						bullet.v_y = (int)(-5 * Math.sin(shooter.get_gun_theta()));
						bullet.shoot();
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				shooter.v_x = 0;
				shooter.v_y = 0;
			}
		});
		
		
	}
	
	public void getUserName(){
		String inputValue = JOptionPane.showInputDialog("Enter your username");
		username = inputValue;

		
	}
	
	public void getLevel(){

		LinkedList<Integer> levels = new LinkedList<Integer>();
		
		levelSetting = new LevelSetting();
		
		levels = levelSetting.getLevels();
		
		LinkedList<String> levels_list = new LinkedList<String>();
		for(int i : levels){
			levels_list.add(Integer.toString(i));
		}
		
	    int level = JOptionPane.showOptionDialog(null, "Select a level", "Level Selection",
	        JOptionPane.WARNING_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, levels_list.toArray(), null);
	    
	    level = level+1;
	    
	    this.level = level;
		try {
			levelSetting = new LevelSetting(this.level);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void reset() {
		reset_pressed = true;
		getLevel();
		bullets.clear();
		obstacles.clear();
		target = null;
		
		for(int i=0; i < 10; i++){
			bullets.add(new Bullet(COURT_WIDTH,COURT_HEIGHT));
		}
		
		shooter = new Shooter(COURT_WIDTH, COURT_HEIGHT);
		shooter.set_ammo(bullets.size());
		target = new Target(COURT_WIDTH,COURT_HEIGHT,levelSetting.getTargetLocation(),levelSetting.getTargetSize());
		
		for(Location ol : levelSetting.getObstacleLocations()){
			obstacles.add(new Obstacle(COURT_WIDTH,COURT_HEIGHT,ol));
		}
		reset_pressed = false;
		playing = true;
		message = "";
		
		requestFocusInWindow();

	}
	
	public void paintComponent(Graphics g){
		if(playing){
			status.setText("Ammo: " + Integer.toString(shooter.get_ammo()));
			super.paintComponent(g);
			shooter.draw(g);
			target.draw(g);
			for(Obstacle o : obstacles){
				o.draw(g);
			}
			for(Bullet bullet : bullets){
				bullet.draw(g,shooter.get_gun_x(),shooter.get_gun_y());
			}
			
		} else {
			status.setText("Game Over: " + message);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER: " + message, 50, 50);
		}	
	}
	
	void tick(){
		if(playing){
			target.move();
			target.bounce(target.hitWall());
			shooter.move();
			for(Obstacle o : obstacles){
				o.move();
				o.bounce(o.hitWall());
			}
			for(Bullet bullet : bullets){
				bullet.move();
				bullet.bounce(bullet.hitWall());
				if(bullet.intersects(target)){
					if(bullet.shot()){
						message = "You win!";
						playing = false;
						winnersController.addWinner(username, levelSetting.getLevelID());
					}	
				} 
				for(Obstacle o : obstacles){
					if (bullet.intersects(o)){
						if(bullet.shot()){
							message = "You lose - you hit an obstacle";
							playing = false;
						}
					}
				}
			}
			if(!shooter.hasAmmo()){
				message = "You lose - ran out of ammo";
				playing = false;
			}
			repaint();
		}
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
