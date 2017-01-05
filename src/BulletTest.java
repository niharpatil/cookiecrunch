import static org.junit.Assert.*;

import org.junit.Test;

public class BulletTest {

	@Test
	public void doesNotExceedLowerBoundaryLimits(){
		Bullet bullet = new Bullet(100,100);
		bullet.pos_x = -1;
		bullet.pos_y = -1;
		bullet.clip();
		assertEquals(0,bullet.pos_x);
		assertEquals(0,bullet.pos_y);
	}
	
	//accounts for the size of the Bullet object
	@Test
	public void doesNotExceedUpperBoundaryLimits(){
		Bullet bullet = new Bullet(100,100);
		bullet.pos_x = 101;
		bullet.pos_y = 101;
		bullet.clip();
		assertEquals(73,bullet.pos_x);
		assertEquals(73,bullet.pos_y);
	}

}
