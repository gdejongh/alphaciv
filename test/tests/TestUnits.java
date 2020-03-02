package tests;

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
public class TestUnits {

	@Test
	public void shouldReturnBlueAsOwner() {
		Unit blue = new UnitImpl(Player.BLUE, GameConstants.ARCHER);
		assertEquals(Player.BLUE, blue.getOwner());
	}
	
	@Test
	public void shouldReturnRedAsOwner() {
		Unit red = new UnitImpl(Player.RED, GameConstants.ARCHER);
		assertEquals(Player.RED, red.getOwner());
	}

	@Test
	public void shouldReturnArcherWithCorrectStats() {
		Unit arch = new UnitImpl(null, GameConstants.ARCHER);
		assertEquals(1,arch.getMoveCount());
		assertEquals(3,arch.getDefensiveStrength());
		assertEquals(2,arch.getAttackingStrength());
	}
	
	@Test
	public void shouldReturnLegionWithCorrectStats() {
		Unit legion = new UnitImpl(null, GameConstants.LEGION);
		assertEquals(1,legion.getMoveCount());
		assertEquals(2,legion.getDefensiveStrength());
		assertEquals(4,legion.getAttackingStrength());
	}
	
	@Test
	public void shouldReturnSettlerWithCorrectStats() {
		Unit settler = new UnitImpl(null, GameConstants.SETTLER);
		assertEquals(1, settler.getMoveCount());
		assertEquals(3, settler.getDefensiveStrength());
		assertEquals(0, settler.getAttackingStrength());
	}
}
