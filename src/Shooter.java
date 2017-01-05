import java.awt.Color;
import java.awt.Graphics;

public class Shooter extends GameObj{
	public static final int SIZE = 20;
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	
	private double gun_theta;
	private int gun_x;
	private int gun_y;
	private int ammo;

	
	public Shooter(int courtWidth, int courtHeight){
		super(INIT_VEL_X, INIT_VEL_Y, (int)(courtWidth*0.5), courtHeight, SIZE, (int) (SIZE*0.5), courtWidth,
				courtHeight);
		gun_theta = Math.PI/2;
		gun_x = pos_x+10;
		gun_y = pos_y-35;
		ammo = 0;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(pos_x, pos_y, width, height);
		g.drawLine(pos_x+10, pos_y, gun_x, gun_y);
	}
	
	public int get_gun_x(){
		return gun_x;
	}
	
	public int get_gun_y(){
		return gun_y;
	}
	
	public int get_ammo(){
		return ammo;
	}
	
	public void set_ammo(int bullets){
		ammo = bullets;
	}
	
	public boolean hasAmmo(){
		if(ammo >= 0){
			return true;
		}
		return false;
	}
	
	public void ammo_minus(){
		ammo--;
	}
	
	public void move_gun(double delta){
		gun_theta += delta;
		if (gun_theta < 0 ){
			gun_theta = 0;
		} else if (gun_theta > Math.PI){
			gun_theta = Math.PI;
		}
		gun_x = (int)(pos_x + 10 + 25*Math.cos(gun_theta));
		gun_y = (int)(pos_y - 25*Math.sin(gun_theta));
	}
	
	public double get_gun_theta(){
		return gun_theta;
	}
	
	@Override
	public void move(){
		pos_x += v_x;
		pos_y += v_y; //not really important -- just used for readability in relation to gun movement
		gun_x += v_x;
		gun_y += v_y;
		clip();
	}
}
