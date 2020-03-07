package alphaciv.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class thoroughly tests the UnitImpl class.
 * 
 * @author GabeDeJongh
 *
 */
public class TestWorkForceFocus {

	private GameImpl game;
	
	@Before
	public void setUp() throws Exception {
	    game = new GameImpl();
	}
	
	@Test
	public void shouldHaveOceanAt10() {
		Position p = new Position(1,1);
		game.changeWorkForceFocusInCityAt(p, GameConstants.foodFocus);
		assertEquals(GameConstants.foodFocus, game.getCityAt(p).getWorkforceFocus());
	}
	
}
