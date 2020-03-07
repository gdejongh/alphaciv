package alphaciv.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

import org.junit.Test;

public class TestUnitSetup {
	
	private Game game;

	@Before
	public void before() {
		game = new GameImpl();
	}
	
	@Test
	public void shouldBeArcherAt20() {
		assertEquals(GameConstants.ARCHER, game.getUnitAt(new Position(2,0)).getTypeString());
	}
	
	@Test
	public void shouldBeLegionAt32() {
		assertEquals(GameConstants.LEGION, game.getUnitAt(new Position(3,2)).getTypeString());
	}
	
	@Test
	public void shouldBeSettlerAt43() {
		assertEquals(GameConstants.SETTLER, game.getUnitAt(new Position(4,3)).getTypeString());
	}
	
	@Test
	public void shouldMoveUnit() {
		Position before = new Position(2,0);
		Position after = new Position(2,1);
		boolean moved = game.moveUnit(before, after);
		assertTrue(moved);
		assertNull(game.getUnitAt(before));
		assertEquals(GameConstants.ARCHER,game.getUnitAt(after).getTypeString());
	}
	
	@Test
	public void shouldNotAllowIllegalMovement() {
		Position before = new Position(2,0);
		Position attemptedPosition = new Position(5,5); 
		boolean moved = game.moveUnit(before,attemptedPosition);
		assertFalse(moved);
		assertEquals(GameConstants.ARCHER,game.getUnitAt(before).getTypeString());
		assertNull(game.getUnitAt(attemptedPosition));
	}
}
