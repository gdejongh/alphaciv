package alphaciv.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCities {
	
	private Game game;

	@Before
	public void before() {
		game = new GameImpl();
		// Red Players turn
	}
	
	@Test
	public void shouldBeRedCityAt11() {
		Position p = new Position(1,1);
		Tile t = game.getTileAt(p);
		City c = game.getCityAt(p);
		//assertEquals("City string",t.getTypeString());
		assertEquals(Player.RED, c.getOwner());
		assertEquals(1,c.getSize());
	}

	@Test
	public void shouldBeBlueCityAt41() {
		Position p = new Position(4,1);
		Tile t = game.getTileAt(p);
		City c = game.getCityAt(p);
		// Not sure what the Type String's are yet so "City string is a place holder.
		//assertEquals("City string",t.getTypeString());
		assertEquals(Player.BLUE, c.getOwner());
		assertEquals(1,c.getSize());
	}
	
	@Test
	public void shouldBeProducingArcher() {
		Position p = new Position(1,1);
		game.changeProductionInCityAt(p, GameConstants.ARCHER);
		assertEquals(GameConstants.ARCHER, game.getCityAt(p).getProduction());
	}
	
	@Test
	public void shouldNotWorkBecauseItsRedTurn() {
		Position p = new Position(4,1);
		String production = game.getCityAt(p).getProduction();
		game.changeProductionInCityAt(p, GameConstants.ARCHER);
		// This is kind of confusing, basically we find out what the city is producing,
		// try to change it, then make sure that it didn't change bc it's not the blue
		// players turn.
		assertEquals(production,game.getCityAt(p).getProduction());
	}
	
	@Test
	public void endingTurnShouldNotChangeCityPop() {
		game.endOfTurn();
		game.endOfTurn();
		assertEquals(1,game.getCityAt(new Position(1,1)).getSize());
	}
}
