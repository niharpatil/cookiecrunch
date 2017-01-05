import static org.junit.Assert.*;

import org.junit.Test;

public class ShooterTest {

	@Test
	public void ammoCount(){
		Shooter shooter = new Shooter(100,100);
		
		//ammo is set in GameSpace reset() method
		assertEquals(0,shooter.get_ammo());
	}
	
	@Test
	public void setAmmoMethod(){
		Shooter shooter = new Shooter(100,100);
		shooter.set_ammo(50);
		assertEquals(50,shooter.get_ammo());
	}
	
	@Test
	public void ammoMinusStateUpdate(){
		Shooter shooter = new Shooter(100,100);
		shooter.set_ammo(50);
		assertEquals(50,shooter.get_ammo());
		for(int i = 0; i < 30; i++){
			shooter.ammo_minus();
		}
		assertEquals(20,shooter.get_ammo());
		shooter.ammo_minus();
		assertEquals(19, shooter.get_ammo());
	}
	
	@Test
	public void doesNotExceedLowerBoundaryLimits(){
		Shooter shooter = new Shooter(100,100);
		shooter.pos_x = -1;
		shooter.pos_y = -1;
		shooter.clip();
		assertEquals(0,shooter.pos_x);
		assertEquals(0,shooter.pos_y);
	}
	
	//accounts for the size of the shooter object
	@Test
	public void doesNotExceedUpperBoundaryLimits(){
		Shooter shooter = new Shooter(100,100);
		shooter.pos_x = 101;
		shooter.pos_y = 101;
		shooter.clip();
		System.out.println(shooter.pos_y);
		assertEquals(100 - shooter.SIZE,shooter.pos_x);
		assertEquals(100 - shooter.SIZE + 10,shooter.pos_y); //height is half of width
	}
}
