import static org.junit.Assert.*;

import org.junit.Test;

public class LevelSettingTest {

	
	@Test
	public void correctSource(){
		LevelSetting levelSetting = new LevelSetting();
		assertEquals("src/levelSettings.txt",levelSetting.getSource());
	}
	
	@Test
	public void numberOflevels(){
		LevelSetting levelSetting = new LevelSetting();
		int num_levels = 5;
		assertEquals(num_levels,levelSetting.getLevels().size());
	}
	
	@Test
	public void testLevelSpecificConstructor(){
		try {
			LevelSetting levelSetting = new LevelSetting(2);
			assertEquals(2,levelSetting.getLevelID());
		} catch (BadLevelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
