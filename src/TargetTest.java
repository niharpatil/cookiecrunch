import static org.junit.Assert.*;

import org.junit.Test;

public class TargetTest {

	@Test
	public void doesNotExceedLowerBoundaryLimits(){
		Target target = new Target(100,100, new Location(100,100), 50);
		target.pos_x = -1;
		target.pos_y = -1;
		target.clip();
		assertEquals(0,target.pos_x);
		assertEquals(0,target.pos_y);
	}
	
	//accounts for the size of the Target object
	@Test
	public void doesNotExceedUpperBoundaryLimits(){
		Target target = new Target(100,100, new Location(100,100), 50);
		target.pos_x = 101;
		target.pos_y = 101;
		target.clip();
		assertEquals(50,target.pos_x);
		assertEquals(50,target.pos_y);
	}

}
