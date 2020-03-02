package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alphaciv.Aging;
import alphaciv.AlphaAttackStrategy;
import alphaciv.AlphaCityStrategy;
import alphaciv.AlphaConstructionStrategy;
import alphaciv.AttackStrategy;
import alphaciv.BetaAging;
import alphaciv.BetaWinningCondition;
import alphaciv.CityStrategy;
import alphaciv.ConstructionStrategy;
import alphaciv.Game;
import alphaciv.GameImpl;
import alphaciv.Position;
import alphaciv.Unit;
import alphaciv.UnitImpl;
import alphaciv.WinningCondition;

public class TestGammaCiv {
	private Game gammaGame;
	
	  /** Fixture for alphaciv testing. */
	  @Before
	  public void setUp() {
		WinningCondition betaWin = new BetaWinningCondition();
		Aging betaAge = new BetaAging();
		ConstructionStrategy alphaConstruct = new AlphaConstructionStrategy();
		CityStrategy cityStrat = new AlphaCityStrategy();
		AttackStrategy attack = new AlphaAttackStrategy();
	    gammaGame = new GameImpl(betaWin, betaAge, alphaConstruct, cityStrat, attack);
	  }
	  
	  // test for archer actions
	  @Test
	  public void shouldDoubleArcherStrengthAndShouldNotMove() {
		UnitImpl u = (UnitImpl) gammaGame.getUnitAt(new Position(2,0));
		u.setDefensiveStrength(3);
	    assertEquals( "Archer strength should be 3 to start",
	      3, u.getDefensiveStrength());
	    gammaGame.performUnitActionAt(new Position(2,0));
	    assertEquals( "Archer strength should be 6", 6, u.getDefensiveStrength());
	    assertEquals( "Archer shouldn't be able to move", 0, u.getMoveCount());
	  }
	  
	  public void shouldRemoveArcherFortification() {
		  UnitImpl u = (UnitImpl) gammaGame.getUnitAt(new Position(2,0));
		    assertEquals( "Archer strength should be 3 to start",
		      3, u.getDefensiveStrength());
		    gammaGame.performUnitActionAt(new Position(2,0));
		    assertEquals( "Archer strength should be 6", 6, u.getDefensiveStrength());
		    gammaGame.performUnitActionAt(new Position(2,0));
		    assertEquals( "Archer shouldn't be fortified", 3, u.getDefensiveStrength());
	  }
	  
	  // test for settler actions
	  @Test
	  public void shouldBuildCity() {
		  UnitImpl u = (UnitImpl) gammaGame.getUnitAt(new Position(4,3));
		    gammaGame.performUnitActionAt(new Position(4,3));
		    assertNotNull( "City at 4,3", gammaGame.getCityAt(new Position(4,3)));
		    assertNull( "Settler is gone", gammaGame.getUnitAt(new Position(4,3)));
	  }

}
