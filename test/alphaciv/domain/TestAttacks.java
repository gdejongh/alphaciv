package alphaciv.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class thoroughly tests the interaction of attacking units.
 * 
 * @author Michael Kiley
 *
 */
public class TestAttacks {

	private GameImpl game;
	
	@Before
	public void setUp() throws Exception {
	    game = new GameImpl();
	}
	
	@Test
	public void redCanAttackBlue() {
		boolean successful = game.moveUnit(new Position(4,3), new Position(3,2));
		Unit unit = game.getUnitAt(new Position(3,2));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.SETTLER, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(4,3)));
	}
	
	@Test
	public void blueCanAttackRed() {
		game.endOfTurn();
		boolean successful = game.moveUnit(new Position(3,2), new Position(4,3));
		Unit unit = game.getUnitAt(new Position(4,3));
		assertTrue(successful);
		assertEquals(Player.BLUE, unit.getOwner());
		assertEquals(GameConstants.LEGION, unit.getTypeString());
		assertNull(game.getUnitAt(new Position(3,2)));
	}
	
	@Test
	public void redCanTakeBlueCity() {
		City blueCity = new CityImpl(Player.BLUE);
		TileImpl blueCityTile = new TileImpl(GameConstants.PLAINS, new Position(0,0));
		blueCityTile.addCity(blueCity);
		
		Unit redArcher = new UnitImpl(Player.RED, GameConstants.ARCHER);
		TileImpl redUnitTile = new TileImpl(GameConstants.PLAINS, new Position(0,1));
		redUnitTile.addUnit(redArcher);
		
		TileImpl[] specialTiles = {blueCityTile, redUnitTile};
		game.setBoard(new Board(1,2,specialTiles));
		
		boolean successful = game.moveUnit(new Position(0,1), new Position(0,0));
		Unit unit = game.getUnitAt(new Position(0,0));
		assertTrue(successful);
		assertEquals(Player.RED, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		City city = game.getCityAt(new Position(0,0));
		assertEquals(city.getOwner(), Player.RED);
		assertNull(game.getUnitAt(new Position(0,1)));
	}
	
	@Test
	public void blueCanTakeRedCity() {
		City redCity = new CityImpl(Player.RED);
		TileImpl redCityTile = new TileImpl(GameConstants.PLAINS, new Position(0,0));
		redCityTile.addCity(redCity);
		
		Unit blueArcher = new UnitImpl(Player.BLUE, GameConstants.ARCHER);
		TileImpl blueUnitTile = new TileImpl(GameConstants.PLAINS, new Position(0,1));
		blueUnitTile.addUnit(blueArcher);
		
		TileImpl[] specialTiles = {redCityTile, blueUnitTile};
		game.setBoard(new Board(1,2,specialTiles));
		
		game.endOfTurn();
		boolean successful = game.moveUnit(new Position(0,1), new Position(0,0));
		Unit unit = game.getUnitAt(new Position(0,0));
		assertTrue(successful);
		assertEquals(Player.BLUE, unit.getOwner());
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		City city = game.getCityAt(new Position(0,0));
		assertEquals(city.getOwner(), Player.BLUE);
		assertNull(game.getUnitAt(new Position(0,1)));
	}
	
	//Unit test EpsilonAttackStrategy
	@Test
	public void strongerAttackBeatsWeakerDefense(){
		Board board = new Board(3,4);
		
		Unit red = new UnitImpl(Player.RED, GameConstants.ARCHER);
		Unit blue = new UnitImpl(Player.BLUE, GameConstants.LEGION);
		
		Position redPosition = new Position(1,1);
		Position bluePosition = new Position(1,2);
		
		board.addUnit(redPosition, red);
		board.addUnit(bluePosition, blue);
		
		Tile redTile = board.getTileAt(redPosition);
		Tile blueTile = board.getTileAt(bluePosition);
		
		EpsilonAttackStrategy strat = new EpsilonAttackStrategy(new ThreeDice());
		assertFalse(strat.attack(redTile,blueTile,board));
		assertTrue(strat.attack(blueTile,redTile,board));
	}
	
	@Test
	public void weakerWinsWithHelpOfFriends(){
		Board board = new Board(3,4);
		Unit red = new UnitImpl(Player.RED, GameConstants.ARCHER);
		Unit blue = new UnitImpl(Player.BLUE, GameConstants.LEGION);
		
		Position redPosition = new Position(1,1);
		Position bluePosition = new Position(1,2);
		
		board.addUnit(new Position(1,1), red);
		board.addUnit(new Position(0,0), new UnitImpl(Player.RED, GameConstants.LEGION));
		board.addUnit(new Position(1,0), new UnitImpl(Player.BLUE, GameConstants.LEGION));
		
		board.addUnit(new Position(1,2), blue);
		
		EpsilonAttackStrategy strat = new EpsilonAttackStrategy(new ThreeDice());
		assertTrue(strat.attack(board.getTileAt(redPosition),board.getTileAt(bluePosition),board));
		assertFalse(strat.attack(board.getTileAt(bluePosition),board.getTileAt(redPosition),board));
	}
	
	@Test
	public void alwaysWinAgainstEmptyTile(){
		Board board = new Board(3,4);
		Unit red = new UnitImpl(Player.RED, GameConstants.ARCHER);
		
		Position redPosition = new Position(1,1);
		Position bluePosition = new Position(1,2);
		
		board.addUnit(new Position(1,1), red);
		
		EpsilonAttackStrategy strat = new EpsilonAttackStrategy(new ThreeDice());
		assertTrue(strat.attack(board.getTileAt(redPosition),board.getTileAt(bluePosition),board));
	}
	
	
	
}
