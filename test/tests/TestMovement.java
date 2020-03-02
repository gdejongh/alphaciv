package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class thoroughly tests movement implementation of the game class.
 * 
 * @author Michael Kiley
 *
 */
public class TestMovement {

	private GameImpl game;
	
	@Before
	public void setUp() throws Exception {
	    game = new GameImpl();
	}
	
	@Test
	public void unitCanMoveOneUpward() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(2,1));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
	}
	//todo
	@Test
	public void unitCanMoveOneDownward() {
		Unit unitToAdd = new UnitImpl(Player.RED, GameConstants.ARCHER);
		TileImpl tile = new TileImpl(GameConstants.PLAINS, new Position(1,1));
		tile.addUnit(unitToAdd);
		
		TileImpl[] specialTiles = {tile};
		game.setBoard(new Board(3,3,specialTiles));
		
		boolean successful = game.moveUnit(new Position(1,1), new Position(1,0));
		Unit unit = game.getUnitAt(new Position(1,0));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(1,1)));
	}
	
	@Test
	public void unitCanMoveOneLeft() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(2,1));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
	}
	
	@Test
	public void unitCanMoveOneRight() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(2,1));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
	}
	
	@Test
	public void unitCanMoveOneDiagonally() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(2,1));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
	}
	//todo
	@Test
	public void cannotMoveUnitToNonadjacentTile() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(2,1));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
	}
	
	@Test
	public void onlyOneMovePerUnitPerRound() {
		game.moveUnit(new Position(2,0), new Position(2,1));
		boolean successful = game.moveUnit(new Position(2,1), new Position(2,0));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertFalse(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
	}
	
	@Test
	public void redCannotMoveBlueUnit() {
		boolean successful = game.moveUnit(new Position(3,2), new Position(3,3));
		assertFalse(successful);
		Unit unit = game.getUnitAt(new Position(3,2));
		assertEquals(GameConstants.LEGION, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(3,3)));
	}
	
	@Test
	public void cannotMoveUnitOntoMountain() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(1,1));
		assertFalse(successful);
		Unit unit = game.getUnitAt(new Position(2,0));
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(1,1)));
	}
	
	@Test
	public void canMoveMoreThanOneUnitPerRound() {
		boolean successful = game.moveUnit(new Position(2,0), new Position(2,1));
		Unit unit = game.getUnitAt(new Position(2,1));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,0)));
		
		boolean secondSuccessful = game.moveUnit(new Position(4,3), new Position(4,4));
		Unit unit2 = game.getUnitAt(new Position(4,4));
		assertTrue(secondSuccessful);
		assertEquals(Player.RED, unit2.getOwner());
		assertEquals(GameConstants.SETTLER, unit2.getTypeString());
		assertNull(game.getUnitAt(new Position(4,3)));
	}
	
	@Test
	public void onlyOneBLueUnitAllowedPerTile() {
		game.endOfTurn();
		game.endOfTurn();
		
		game.endOfTurn();
		game.endOfTurn();
		
		game.endOfTurn();
		game.endOfTurn();
		
		game.endOfTurn();
		game.endOfTurn();
		
		game.endOfTurn();
		
		boolean successful = game.moveUnit(new Position(3,2), new Position(3,1));
		assertFalse(successful);
		
		Unit unit = game.getUnitAt(new Position(3,1));
		assertEquals(Player.BLUE, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		
		Unit unit2 = game.getUnitAt(new Position(3,2));
		assertEquals(Player.BLUE, unit2.getOwner());
		assertEquals(GameConstants.LEGION, unit2.getTypeString());
		
	}
	
	@Test
	public void movementResetsAfterEachRound() {
		game.moveUnit(new Position(2,0), new Position(2,1));
		
		game.endOfTurn();
		game.endOfTurn();
		
		boolean successful = game.moveUnit(new Position(2,1), new Position(2,0));
		Unit unit = game.getUnitAt(new Position(2,0));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(2,1)));
	}
	
	
	
	
	
	
}
