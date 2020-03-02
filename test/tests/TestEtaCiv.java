package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alphaciv.Aging;
import alphaciv.AlphaAging;
import alphaciv.AlphaAttackStrategy;
import alphaciv.AlphaCityStrategy;
import alphaciv.AlphaConstructionStrategy;
import alphaciv.AlphaWinningCondition;
import alphaciv.AttackStrategy;
import alphaciv.CityImpl;
import alphaciv.CityStrategy;
import alphaciv.ConstructionStrategy;
import alphaciv.EtaCityStrategy;
import alphaciv.Game;
import alphaciv.GameConstants;
import alphaciv.GameImpl;
import alphaciv.Position;
import alphaciv.UnitImpl;
import alphaciv.WinningCondition;
import tests.TestEpsilonCiv.FakeGame;

public class TestEtaCiv {
	private Game etaGame;
	private CityImpl redCity;
	private CityImpl blueCity;
	
	@Before
	public void setUp() {
		WinningCondition alphaWin = new AlphaWinningCondition();
		Aging alphaAge = new AlphaAging();
		ConstructionStrategy alphaConstruct = new AlphaConstructionStrategy();
		CityStrategy cityStrat = new EtaCityStrategy();
		AttackStrategy attack = new AlphaAttackStrategy();
		etaGame = new GameImpl(alphaWin, alphaAge, alphaConstruct, cityStrat,attack);
		redCity = (CityImpl) etaGame.getCityAt(new Position(1,1));
		blueCity = (CityImpl) etaGame.getCityAt(new Position(4,1));
	}

	@Test
	public void testChangeCityProduction() {
		etaGame.changeWorkForceFocusInCityAt(redCity.getPosition(), "hammer");
		assertEquals("Red City should have work force focus of production", GameConstants.productionFocus, redCity.getWorkforceFocus());
	}
	
	@Test
	public void testOptimizeCityProduction(){
		etaGame.changeWorkForceFocusInCityAt(redCity.getPosition(), "hammer");
		endRound(1);
		assertEquals("The city production amount should be 1 (1 hill, no forest and no mountain)", 
				1, redCity.getProductionAmount());
	}
	
	@Test
	public void testCityPopulationSize(){
		blueCity.setSize(2);
		//need 11 to be collected food
		blueCity.setFood(11);
		etaGame.changeWorkForceFocusInCityAt(blueCity.getPosition(), "apple");
		assertEquals("City size is 2", 2, blueCity.getSize());
		assertEquals("City food collection is 11", 11, blueCity.getFood());
		endRound(1);
		assertEquals("City size is +1", 3, blueCity.getSize());
	}

	//helper method for endOfTurn
		public void endRound(int numRounds){
			int numTurns = numRounds*2;
			for(int i=0; i<numTurns;i++){
				etaGame.endOfTurn();
			}
		}
}
