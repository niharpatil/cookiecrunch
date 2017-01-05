import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obstacle extends GameObj{

	public static final int SIZE = 20;
	public static final int INIT_VEL_X = 5;
	public static final int INIT_VEL_Y = 5;
	private final String obstacleImgLocation = "src/garbage.png";
	private BufferedImage obstacleImg;
	
	public Obstacle(int courtWidth, int courtHeight, Location location) {
		super(INIT_VEL_X, INIT_VEL_Y, location.getX_coord(), location.getY_coord(), SIZE, SIZE, courtWidth,
				courtHeight);
		try {
			if (obstacleImg == null) {
				obstacleImg = ImageIO.read(new File(obstacleImgLocation));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(obstacleImg,pos_x,pos_y, width,height,null);
	}
	
}
