package tests;

import org.junit.*;

import alphaciv.Aging;
import alphaciv.AlphaAging;
import alphaciv.AlphaAttackStrategy;
import alphaciv.AlphaCityStrategy;
import alphaciv.AlphaWinningCondition;
import alphaciv.AttackStrategy;
import alphaciv.City;
import alphaciv.CityImpl;
import alphaciv.CityStrategy;
import alphaciv.ConstructionStrategy;
import alphaciv.DeltaConstructionStrategy;
import alphaciv.Tile;
import alphaciv.TileImpl;
import alphaciv.Unit;
import alphaciv.UnitImpl;
import alphaciv.WinningCondition;
import alphaciv.Game;
import alphaciv.GameConstants;
import alphaciv.GameImpl;
import alphaciv.Player;
import alphaciv.Position;

import static org.junit.Assert.*;

import java.util.ArrayList;

/** Skeleton class for AlphaCiv test cases 

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/
public class TestDeltaCiv {
  private Game deltaGame;
  private CityImpl redCity = new CityImpl(new Position(1,1), Player.RED, GameConstants.ARCHER);
	private CityImpl blueCity = new CityImpl(new Position(4,1), Player.BLUE, GameConstants.LEGION);
	private UnitImpl redArcher = new UnitImpl(new Position(2,0), GameConstants.ARCHER, Player.RED);
	private UnitImpl redSettler = new UnitImpl(new Position(4,3), GameConstants.SETTLER, Player.RED);
	private UnitImpl blueLegion = new UnitImpl(new Position(3,2), GameConstants.LEGION, Player.BLUE);
	private ArrayList<CityImpl> cities = new ArrayList<CityImpl>();
	private ArrayList<UnitImpl> units = new ArrayList<UnitImpl>();
	private ArrayList<Tile> tiles  = new ArrayList<Tile>();
	private Tile oceans = new TileImpl(new Position (1,0), GameConstants.OCEANS);
	private Tile hills = new TileImpl(new Position (0, 1), GameConstants.HILLS);
	private Tile mountains = new TileImpl(new Position (2,2), GameConstants.MOUNTAINS);
  
  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
	cities.add(new CityImpl(new Position(8,12), Player.RED, GameConstants.SETTLER));
	cities.add(new CityImpl(new Position(4,5), Player.BLUE, GameConstants.SETTLER));
	cities.add(redCity);
	cities.add(blueCity);
	units.add(redArcher);
	units.add(redSettler);
	units.add(blueLegion);
	tiles.add(oceans);
	tiles.add(hills);
	tiles.add(mountains);
	WinningCondition alphaWin = new AlphaWinningCondition();
	Aging alphaAge = new AlphaAging();
	ConstructionStrategy alphaConstruct = new DeltaConstructionStrategy(cities, units, tiles);
	CityStrategy cityStrat = new AlphaCityStrategy();
	AttackStrategy attack = new AlphaAttackStrategy();
    deltaGame = new GameImpl(alphaWin, alphaAge, alphaConstruct, cityStrat, attack);
  }

  // Tests for 2 players
  @Test
  public void shouldHaveRedCityAt1_1() {
    City c = deltaGame.getCityAt(new Position(1,1));
    assertNotNull("There should be a city at (1,1)", c);
    Player p = c.getOwner();
    assertEquals( "City at (1,1) should be owned by red",
      Player.RED, p );
  }
  
  @Test
  public void shouldHaveBlueCityAt4_1() {
    City c = deltaGame.getCityAt(new Position(4,1));
    assertNotNull("There should be a city at (4,1)", c);
    Player p = c.getOwner();
    assertEquals( "City at (4,1) should be owned by blue",
      Player.BLUE, p );
  }  
  
  @Test
  public void shouldHaveRedCityAt8_12() {
    City c = deltaGame.getCityAt(new Position(8,12));
    assertNotNull("There should be a city at (8,12)", c);
    Player p = c.getOwner();
    assertEquals( "City at (8,12) should be owned by red",
      Player.RED, p );
  }
  
  @Test
  public void shouldHaveBlueCityAt4_5() {
    City c = deltaGame.getCityAt(new Position(4,5));
    assertNotNull("There should be a city at (4,5)", c);
    Player p = c.getOwner();
    assertEquals( "City at (4,5) should be owned by blue",
      Player.BLUE, p );
  }
  
  // Test that the game is properly "set up" with the world layout
  @Test
  public void shouldHaveOceanAt1_0() {
	Tile t = deltaGame.getTileAt(new Position(1,0));
    assertNotNull("There should be a tile at (1,0)", t);
    assertEquals( "Terrain at (1,0) should be ocean",
      GameConstants.OCEANS, t.getTypeString());
  }
  
  @Test
  public void shouldHaveHillsAt0_1() {
	Tile t = deltaGame.getTileAt(new Position(0,1));
    assertNotNull("There should be a tile at (0,1)", t);
    assertEquals( "Terrain at (0,1) should be hills",
      GameConstants.HILLS, t.getTypeString());
  }
  
  @Test
  public void shouldHaveMountainsAt2_2() {
	Tile t = deltaGame.getTileAt(new Position(2,2));
    assertNotNull("There should be a tile at (2,2)", t);
    assertEquals( "Terrain at (2,2) should be mountains",
      GameConstants.MOUNTAINS, t.getTypeString());
  }
  
  @Test
  public void shouldHavePlainsEverywhereElse() {
	Tile t1 = deltaGame.getTileAt(new Position(0,13));
    assertNotNull("There should be a tile at (0,13)", t1);
    assertEquals( "Terrain at (0,13) should be plains",
      GameConstants.PLAINS, t1.getTypeString());
    Tile t2 = deltaGame.getTileAt(new Position(8,8));
    assertNotNull("There should be a tile at (8,8)", t2);
    assertEquals( "Terrain at (8,8) should be plains",
      GameConstants.PLAINS, t2.getTypeString());
    Tile t3 = deltaGame.getTileAt(new Position(14,15));
    assertNotNull("There should be a tile at (14,15)", t3);
    assertEquals( "Terrain at 14,15) should be plains",
      GameConstants.PLAINS, t3.getTypeString());
  }
  
  @Test
  public void shouldHaveRedArcherAt2_0() {
	Unit u = deltaGame.getUnitAt(new Position(2,0));
    assertNotNull("There should be a unit at (2,0)", u);
    assertEquals( "Unit at (2,0) should be archer",
      GameConstants.ARCHER, u.getTypeString());
    assertEquals( "Unit at (2,0) should be red",
    	      Player.RED, u.getOwner());
  }
  
  @Test
  public void shouldHaveBlueLegionAt3_2() {
	Unit u = deltaGame.getUnitAt(new Position(3,2));
    assertNotNull("There should be a unit at (3,2)", u);
    assertEquals( "Unit at (3,2) should be legion",
      GameConstants.LEGION, u.getTypeString());
    assertEquals( "Unit at (2,0) should be blue",
  	      Player.BLUE, u.getOwner());
  }
  
  @Test
  public void shouldHaveRedSettlerAt4_3() {
	Unit u = deltaGame.getUnitAt(new Position(4,3));
    assertNotNull("There should be a unit at (4,3)", u);
    assertEquals( "Unit at (4,3) should be settler",
      GameConstants.SETTLER, u.getTypeString());
    assertEquals( "Unit at (4,3) should be red",
  	      Player.RED, u.getOwner());
  }
  
  @Test
  public void shouldStartWithRed() {
	Player p = deltaGame.getPlayerInTurn();
	assertEquals( "Player should be red", Player.RED, p);
  }
  
  //tests for movements
  @Test
  public void shouldAllowMovementDiagonally() {
	  //red settler should be at 4,3
	UnitImpl u = (UnitImpl) deltaGame.getUnitAt(new Position(4,3));
	assertEquals( "Red settler should be able to move to (5,3)",
			true, deltaGame.moveUnit(u.getPosition(), new Position(5,3)));
	assertEquals( "Should be a red settler on (5,3)", GameConstants.SETTLER, deltaGame.getUnitAt(new Position(5,3)).getTypeString());
	assertEquals("Unit at (5,3) should be red", Player.RED, deltaGame.getUnitAt(new Position(5,3)).getOwner());
  }
  
  public void shouldAllowMovementVertically() {
	UnitImpl u = (UnitImpl) deltaGame.getUnitAt(new Position(3,2));
	assertEquals( "Blue legion should be able to move to (3,3)",
			true, deltaGame.moveUnit(u.getPosition(), new Position(3,3)));
	assertEquals( "Should be a blue legion on (3,3)", GameConstants.LEGION, deltaGame.getUnitAt(new Position(3,3)).getTypeString());
	assertEquals("Unit at (3,3) should be blue", Player.BLUE, deltaGame.getUnitAt(new Position(3,3)).getOwner());
  }
  
  public void shouldAllowMovementHorizontally() {
	  UnitImpl u = (UnitImpl) deltaGame.getUnitAt(new Position(2,0));
	  assertEquals( "Red archer should be able to move to (3,0)",
			  true, deltaGame.moveUnit(u.getPosition(), new Position(3,0)));
	  assertEquals( "Should be a red archer on (3,0)", GameConstants.ARCHER, deltaGame.getUnitAt(new Position(3,0)).getTypeString());
	  assertEquals("Unit at (3,0) should be red", Player.RED, deltaGame.getUnitAt(new Position(3,0)).getOwner());
  }
  
  public void shouldNotAllowMovement() {
	  UnitImpl u = (UnitImpl) deltaGame.getUnitAt(new Position(3,2));
	  assertEquals( "Blue settler should not be able to move to (5,2)",
			  false, deltaGame.moveUnit(u.getPosition(), new Position(5,2)));
  }
  
  @Test
  public void shouldNotAllowMovementFromNull() {
	assertEquals( "There should be no winner at the start of the game",
			false, deltaGame.moveUnit(null, new Position(1,1)));
  }
  
  @Test
  public void shouldNotAllowMovementOutsideWorld() {
	assertEquals( "There should be no winner at the start of the game",
			false, deltaGame.moveUnit(new Position(1,1), null));
  }
  
  @Test
  public void shouldRestoreMovementAtTurn() {
	  deltaGame.endOfTurn();
	  UnitImpl u = (UnitImpl) deltaGame.getUnitAt(new Position(4,3));
	  assertEquals( "U should have moves",
				1, u.getMoveCount());
  }
  
  @Test
  public void shouldNotAllowUnitToMoveAfterMovingInTurn() {
	  UnitImpl u = (UnitImpl) deltaGame.getUnitAt(new Position(2,0));
	  assertEquals( "U should have moves",
				1, u.getMoveCount());
	  deltaGame.moveUnit(new Position(2,0), new Position(2,1));
	assertEquals( "U should not have moves now",
			0, u.getMoveCount());
	assertEquals( "Should not be able to move again",
			false, deltaGame.moveUnit(u.getPosition(), new Position(2,2)));
  }
  
  @Test
  public void shouldNotAllowMovementToMountain() {
	  assertEquals( "Should not be able to move to mountain",
				false, deltaGame.moveUnit(new Position(2,1), new Position(2,2)));
  }
  
  @Test
  public void shouldNotAllowMovementToOcean() {
	  assertEquals( "Should not be able to move to mountain",
				false, deltaGame.moveUnit(new Position(0,0), new Position(1,0)));
  }
  
  @Test
  public void shouldNotAllowSameColorToMoveOnSameColor() {
	  UnitImpl u1 = (UnitImpl) deltaGame.getUnitAt(new Position(2,0));
	  UnitImpl u2 = (UnitImpl) deltaGame.getUnitAt(new Position(4,3));
	  // move 2,0 to 4,3
	  deltaGame.moveUnit(u1.getPosition(), new Position(3,0));
	  deltaGame.moveUnit(u2.getPosition(), new Position(3,3));
	  endRound(1);
	  deltaGame.moveUnit(u1.getPosition(), new Position(3,1));
	  deltaGame.moveUnit(u2.getPosition(), new Position(3,2));
	  endRound(1);
	  assertEquals( "Should not be able to move red on red",
				false, deltaGame.moveUnit(u1.getPosition(), new Position(3,2)));
  }
  
  //tests for which player is in turn
  @Test
  public void shouldSwitchPlayers() {
	  assertEquals( "Should have red player in turn",
			  Player.RED, deltaGame.getPlayerInTurn());
	  deltaGame.endOfTurn();
	  assertEquals( "Should have blue player in turn",
			  Player.BLUE, deltaGame.getPlayerInTurn());
  }
	  
	//tests for production
	  /**
	  @Test
	  public void shouldAllowPlayerToSelectArcherProduction() {
		  game.changeProductionInCityAt(new Position(4,1), GameConstants.ARCHER);
		  assertEquals( "City should now be producing archers",
				  game.getCityAt(new Position(4,1)).getProduction(), GameConstants.ARCHER);
	  }  
	  
	  @Test
	  public void shouldAllowPlayerToSelectLegionProduction() {
		  game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
		  assertEquals( "City should now be producing legion",
				  GameConstants.LEGION, game.getCityAt(new Position(1,1)).getProduction());
	  } 
	  
	  @Test
	  public void shouldAllowPlayerToSelectSettlerProduction() {
		  game.changeProductionInCityAt(new Position(4,1), GameConstants.SETTLER);
		  assertEquals( "City should now be producing settler",
				  game.getCityAt(new Position(4,1)).getProduction(), GameConstants.SETTLER);
	  }
	  
	  */
	  
	  @Test
	  public void shouldProduce6ProductionPerRound() {
		  CityImpl city = (CityImpl) deltaGame.getCityAt(new Position(1,1));
		  int previousAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have 6 more production than before",
				  previousAmount + 6, city.getProductionAmount());
	  }
	  
	  @Test
	  public void shouldDeductCostOfProduction() {
		  CityImpl city = (CityImpl) deltaGame.getCityAt(new Position(4,1));
		  //legion cost is 15
		  int treasuryAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have 6 more than before",
				  treasuryAmount + 6, city.getProductionAmount());
		  treasuryAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have 6 more than before",
				  treasuryAmount + 6, city.getProductionAmount());
		  treasuryAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have 6 more and then 15 less than before",
				  treasuryAmount + 6 -15, city.getProductionAmount());
	  }
	  
	  @Test
	  public void shouldPlaceUnitOnCityIfNoOtherIsPresent() {
		  CityImpl city = (CityImpl) deltaGame.getCityAt(new Position(1,1));
		  //archer cost is 10
		  int treasuryAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have 6 more than before",
				  treasuryAmount + 6, city.getProductionAmount());
		  treasuryAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have 6 more than before",
				  treasuryAmount + 6-10, city.getProductionAmount());
		  treasuryAmount = city.getProductionAmount();
		  endRound(1);
		  assertEquals( "City should have a new archer unit at 1,1",
				  GameConstants.ARCHER, deltaGame.getUnitAt(new Position(1,1)).getTypeString());
	  }
	  
	  @Test
	  public void shouldPlaceUnitOnNearestAdjacentTile() {
		  //archer cost is 10
		  endRound(3);
		  assertEquals( "City should have a new archer unit at 1,1",
				  GameConstants.ARCHER, deltaGame.getUnitAt(new Position(1,1)).getTypeString());
		  endRound(1);
		  assertEquals( "City should have a new archer unit at 0,1",
				  GameConstants.ARCHER, deltaGame.getUnitAt(new Position(0,1)).getTypeString());
	  }
	  
	  @Test
	  public void shouldLetRedWinBy3000() {
		  assertEquals("There should be no winner at the start of the game", null, deltaGame.getWinner());
		  endRound(10);
		  assertEquals("The winner is red", Player.RED, deltaGame.getWinner());
	  }
	  
	  public void shouldAgeBy100YearsEveryRound(){
		  assertEquals("Game starts at year -4000", -4000, deltaGame.getAge());
		  endRound(1);
		  assertEquals("Game ages by 100", -3900, deltaGame.getAge());
	  }
  
  //helper method for endOfTurn
  public void endRound(int numRounds){
	  int numTurns = numRounds*2;
	 for(int i=0; i<numTurns;i++){
		deltaGame.endOfTurn();
	}
  }
}