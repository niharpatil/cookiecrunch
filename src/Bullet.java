import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends GameObj{
	
	public static final int SIZE = 27;
	public static final int INIT_POS_X = 170;
	public static final int INIT_POS_Y = 170;
	public static final int INIT_VEL_X = 2;
	public static final int INIT_VEL_Y = 3;
	private static final String img_file = "src/cookie_monster.png";
	private static BufferedImage img;

	private boolean shot = false;
	
	public Bullet(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE,
				courtWidth, courtHeight);
		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
		
	}
	
	//unshot draw method
	public void draw(Graphics g, int gun_x, int gun_y) {
		g.setColor(Color.RED);
		if(shot){
			g.drawImage(img, pos_x, pos_y, width, height, null);
		} else {
			pos_x = gun_x-((int)(width/2.0));
			pos_y = gun_y-((int)(height/2.0));
			g.drawImage(img, pos_x, pos_y, width, height, null);
		}
	}

	
	public boolean shot(){
		return shot;
	}
	public void shoot(){
		shot = true;
	}
	
}
