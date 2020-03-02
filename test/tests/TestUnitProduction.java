package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class thoroughly tests the unit production functionality of the game class.
 * 
 * @author Michael Kiley
 *
 */
public class TestUnitProduction {

private Game game;
	
	@Before
	public void setUp() throws Exception {
	    game = new GameImpl();
	}
	
	@Test
	public void citiesProduce6EveryRound() {
		//One round
		game.endOfTurn();
		game.endOfTurn();
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProductionCount(), 6);
	}
	
	@Test
	public void productionCanBeSetToArcher() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProduction(), GameConstants.ARCHER);
	}
	
	@Test
	public void productionCanBeSetToSettler() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.SETTLER);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProduction(), GameConstants.SETTLER);
	}
	
	@Test
	public void productionCanBeSetToLegion() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProduction(), GameConstants.LEGION);
	}
	
	@Test
	public void cityProducesArchers() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
		
		//Two rounds
		playRounds(game, 2);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProductionCount(), 2);
		Unit unit = game.getUnitAt(new Position(1,1));
		//assertNotNull(unit);
		assertEquals(unit.getOwner(), Player.RED);
		assertEquals(unit.getTypeString(), GameConstants.ARCHER);
	}
	
	@Test
	public void producedArchersArePlacedClockwiseAroundCity() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
		
		//Four rounds
		playRounds(game, 4);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProductionCount(), 4);
		Unit unit1 = game.getUnitAt(new Position(1,1));
		assertEquals(unit1.getOwner(), Player.RED);
		assertEquals(unit1.getTypeString(), GameConstants.ARCHER);
		Unit unit2 = game.getUnitAt(new Position(0,1));
		assertEquals(unit2.getOwner(), Player.RED);
		assertEquals(unit2.getTypeString(), GameConstants.ARCHER);
	}
	
	@Test
	public void cityProducesSettlers() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.SETTLER);
		
		playRounds(game, 5);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProductionCount(), 0);
		Unit unit1 = game.getUnitAt(new Position(1,1));
		assertEquals(unit1.getOwner(), Player.RED);
		assertEquals(unit1.getTypeString(), GameConstants.SETTLER);
	}
	
	@Test
	public void cityProducesLegions() {
		game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
		
		playRounds(game, 3);
		
		City city = game.getCityAt(new Position(1,1));
		assertEquals(city.getProduction(), GameConstants.LEGION);
		assertEquals(city.getProductionCount(), 3);
		Unit unit1 = game.getUnitAt(new Position(1,1));
		assertEquals(unit1.getOwner(), Player.RED);
		assertEquals(unit1.getTypeString(), GameConstants.LEGION);
	}
	
	private void playRounds(Game game, int rounds){
		for(int i = 0; i<rounds; i++){
			game.endOfTurn();
			game.endOfTurn();
		}
	}
	
}
