package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUnitAction {

	ActionStrategy gammaActionStrategy;
	ActionStrategy noActionStrategy;
	GameImpl game;
	
	@Before
	public void setUp(){
		game = new GameImpl();
		gammaActionStrategy = new GammaActionStrategy();
		noActionStrategy = new NoActionStrategy();
	}
	
	@Test
	public void testNoActionStrategyOnSettler() {
		Position p = new Position(4,3);
		Tile originalTile = game.getTileAt(p);
		Tile newTile = noActionStrategy.performActionAt(originalTile);
		Unit unit = newTile.getUnit();
		assertEquals(GameConstants.SETTLER, unit.getTypeString());
	}
	
	@Test
	public void testNoActionStrategyOnArcher() {
		Position p = new Position(2,0);
		Tile originalTile = game.getTileAt(p);
		Tile newTile = noActionStrategy.performActionAt(originalTile);
		Unit unit = newTile.getUnit();
		assertEquals(3, unit.getDefensiveStrength());
	}
	
	@Test
	public void testGammaActionStrategyOnSettler(){
		Position p = new Position(4,3);
		Tile originalTile = game.getTileAt(p);
		Tile newTile = gammaActionStrategy.performActionAt(originalTile);
		City city = newTile.getCity();
		assertNull(newTile.getUnit());
		assertEquals(Player.RED, city.getOwner());
	}
	
	@Test
	public void testGammaActionStrategyOnArcher(){
		Position p = new Position(2,0);
		Tile originalTile = game.getTileAt(p);
		Tile newTile = gammaActionStrategy.performActionAt(originalTile);
		Unit unit = newTile.getUnit();
		assertEquals(6, unit.getDefensiveStrength());
		assertEquals(0, unit.getMoveCount());
	}
	
	@Test
	public void testGammaActionStrategyTwiceOnArcher(){
		Position p = new Position(2,0);
		Tile originalTile = game.getTileAt(p);
		Tile newTile = gammaActionStrategy.performActionAt(originalTile);
		Tile secondNewTile = gammaActionStrategy.performActionAt(newTile);
		Unit unit = secondNewTile.getUnit();
		assertEquals(3, unit.getDefensiveStrength());
		assertEquals(1, unit.getMoveCount());
	}
	
	@Test
	public void testGammaActionStrategyOnArcherMoved(){
		Position p = new Position(2,0);
		Tile originalTile = game.getTileAt(p);
		originalTile.getUnit().setMoveCount(0);
		Tile newTile = gammaActionStrategy.performActionAt(originalTile);
		Unit unit = newTile.getUnit();
		assertEquals(6, unit.getDefensiveStrength());
		assertEquals(0, unit.getMoveCount());
	}
}
