import java.awt.Graphics;

public class GameObj {

	public int pos_x; 
	public int pos_y;

	public int width;
	public int height;
	
	public int v_x;
	public int v_y;

	public int max_x;
	public int max_y;


	public GameObj(int v_x, int v_y, int pos_x, int pos_y, 
		int width, int height, int court_width, int court_height){
		this.v_x = v_x;
		this.v_y = v_y;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.width = width;
		this.height = height;

		this.max_x = court_width - width;
		this.max_y = court_height - height;

	}


	public void move(){
		pos_x += v_x;
		pos_y += v_y;

		clip();
	}

	public void clip(){
		if (pos_x < 0) pos_x = 0;
		else if (pos_x > max_x) pos_x = max_x;

		if (pos_y < 0) pos_y = 0;
		else if (pos_y > max_y) pos_y = max_y;
	}

	
	public boolean intersects(GameObj obj){
		return (pos_x + width >= obj.pos_x
				&& pos_y + height >= obj.pos_y
				&& obj.pos_x + obj.width >= pos_x 
				&& obj.pos_y + obj.height >= pos_y);
	}


	public void bounce(Direction d) {
		if (d == null) return;
		switch (d) {
		case UP:    v_y = Math.abs(v_y); break;  
		case DOWN:  v_y = -Math.abs(v_y); break;
		case LEFT:  v_x = Math.abs(v_x); break;
		case RIGHT: v_x = -Math.abs(v_x); break;
		}
	}
	
	public Direction hitWall() {
		if (pos_x + v_x < 0)
			return Direction.LEFT;
		else if (pos_x + v_x > max_x)
			return Direction.RIGHT;
		if (pos_y + v_y < 0)
			return Direction.UP;
		else if (pos_y + v_y > max_y)
			return Direction.DOWN;
		else return null;
	}

	public void draw(Graphics g) {
	}
}
