package tests;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class thoroughly tests the winning functionality of the game class.
 * 
 * @author Michael Kiley
 *
 */
public class TestWinning {

	private GameImpl game;
	private AlphaWinningStrategy alphaWinningStrategy;
	private BetaWinningStrategy betaWinningStrategy;
	
	
	@Before
	public void setUp() throws Exception {
	    game = new GameImpl();
	    alphaWinningStrategy = new AlphaWinningStrategy();
	    betaWinningStrategy = new BetaWinningStrategy();
	}
	
	//Integration tests- game integrates with AlphaStragetgy properly
	@Test
	public void redWinsWhenAgeReaches3000() {
		playRounds(game, 10);
		assertEquals(game.getWinner(), Player.RED);
	}
	
	private void playRounds(Game game, int rounds){
		for(int i = 0; i<rounds; i++){
			game.endOfTurn();
			game.endOfTurn();
		}
	}
	
	//Testing AlphaWinningStrategy
	@Test
	public void testAlphaWinningRedWinsWhenAgeIs3000BC() {
		assertEquals(alphaWinningStrategy.getWinner(null, -3000, null), Player.RED);
	}
	
	@Test
	public void testAlphaWinningWinnerIsNullWhenAgeis4000BC() {
		assertEquals(alphaWinningStrategy.getWinner(null, -4000, null), null);
	}

	@Test
	public void testAlphaWinningWinnerIsNullWhenAgeIs1500BC() {
		assertEquals(alphaWinningStrategy.getWinner(new Board(1,1), -1500, null), null);
	}
	
	//Testing BetaWinningStrategy - also indirectly test integration with GameImpl class because we need the game to pass the board into the winning strategy here 
	@Test
	public void testBetaWinningRedWinsWhenRedHasAllCities() {
		game.setWinningStrategy(betaWinningStrategy);
		TileImpl redCityTile = new TileImpl(GameConstants.PLAINS, new Position(2,2));
		redCityTile.addCity(new CityImpl(Player.RED));
		game.setBoard(new Board(5, 5, new TileImpl[]{redCityTile}));
		assertEquals(Player.RED, game.getWinner());
	}
	
	@Test
	public void testBetaWinningBlueWinsWhenBlueHasAllCities() {
		game.setWinningStrategy(betaWinningStrategy);
		TileImpl blueCityTile = new TileImpl(GameConstants.PLAINS, new Position(1,3));
		blueCityTile.addCity(new CityImpl(Player.BLUE));
		game.setBoard(new Board(5, 5, new TileImpl[]{blueCityTile}));
		assertEquals(Player.BLUE, game.getWinner());
	}
	
	@Test
	public void testBetaWinningWinnerIsNullWhenRedAndBlueBothHaveCities() {
		game.setWinningStrategy(betaWinningStrategy);
		assertEquals(null, game.getWinner());
	}
	
	// Test EpsilonWinningStrat
	
	@Test
	public void shouldNotHaveWinner(){
		HashMap<Player,Integer> map = new HashMap<Player,Integer>();
		map.put(Player.RED, 0);
		map.put(Player.BLUE, 0);
		EpsilonWinningStrategy strat = new EpsilonWinningStrategy();
		assertNull(strat.getWinner(null, 0, map));
	}
	
	@Test
	public void redShouldWin(){
		HashMap<Player,Integer> map = new HashMap<Player,Integer>();
		map.put(Player.RED, 3);
		map.put(Player.BLUE, 0);
		EpsilonWinningStrategy strat = new EpsilonWinningStrategy();
		assertEquals(Player.RED, strat.getWinner(null, 0, map));
	}
	
	@Test
	public void blueShouldWin(){
		HashMap<Player,Integer> map = new HashMap<Player,Integer>();
		map.put(Player.RED, 0);
		map.put(Player.BLUE, 5);
		EpsilonWinningStrategy strat = new EpsilonWinningStrategy();
		assertEquals(Player.BLUE, strat.getWinner(null, 0, map));
	}

	
}
