import static org.junit.Assert.*;

import org.junit.Test;

public class BadLevelExceptionTest {

	@Test(expected = BadLevelException.class)
	public void exceptionThrowing() throws BadLevelException, Exception{
			LevelSetting levelSetting = new LevelSetting(17);
	}
}
