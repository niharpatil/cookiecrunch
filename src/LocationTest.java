import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void locationConstructor(){
		Location location = new Location(10,11);
		assertEquals(10,location.getX_coord());
		assertEquals(11,location.getY_coord());
	}

}
