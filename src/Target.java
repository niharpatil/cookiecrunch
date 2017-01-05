import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Target extends GameObj {
	
	public static final int INIT_VEL_X = 5;
	public static final int INIT_VEL_Y = 5;
	private String targetImageLocation = "src/cookie.png";
	private BufferedImage img;
	
	public Target(int courtWidth, int courtHeight, Location location, int size) {
		super(INIT_VEL_X, INIT_VEL_Y, location.getX_coord(), location.getX_coord(), size, size, courtWidth,
				courtHeight);
		try {
			if (img == null) {
				img = ImageIO.read(new File(targetImageLocation));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}
	
	
	public void draw(Graphics g){
		g.drawImage(img, pos_x, pos_y, width,height,null);
	}
}
