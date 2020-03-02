package tests;

import org.junit.*;

import alphaciv.Aging;
import alphaciv.AlphaAging;
import alphaciv.AlphaAttackStrategy;
import alphaciv.AlphaCityStrategy;
import alphaciv.AlphaConstructionStrategy;
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
import alphaciv.ZetaWinningCondition;
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
public class TestZetaCiv {
	private Game zetaGame;
	private Tile oceans;
	private Tile hills;
	private Tile mountains;
	private CityImpl redCity = new CityImpl(new Position(1,1), Player.RED, GameConstants.ARCHER);
	private CityImpl blueCity = new CityImpl(new Position(4,1), Player.BLUE, GameConstants.LEGION);
	private CityImpl blueCity2 = new CityImpl(new Position(4,0), Player.BLUE, GameConstants.LEGION);
	private CityImpl blueCity3 = new CityImpl(new Position(5,0), Player.BLUE, GameConstants.LEGION);
	private UnitImpl redArcher = new UnitImpl(new Position(2,0), GameConstants.ARCHER, Player.RED);
	private UnitImpl redSettler = new UnitImpl(new Position(4,3), GameConstants.SETTLER, Player.RED);
	private UnitImpl blueLegion = new UnitImpl(new Position(3,2), GameConstants.LEGION, Player.BLUE);

	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		ArrayList<CityImpl> cities = new ArrayList<CityImpl>();
		cities.add(redCity);
		cities.add(blueCity);
		cities.add(blueCity2);
		cities.add(blueCity3);
		oceans = new TileImpl(new Position (1,0), GameConstants.OCEANS);
		hills = new TileImpl(new Position (0, 1), GameConstants.HILLS);
		mountains = new TileImpl(new Position (2,2), GameConstants.MOUNTAINS);
		ArrayList<Tile> board = new ArrayList<Tile>();
		board.add(oceans);
		board.add(hills);
		board.add(mountains);
		ArrayList<UnitImpl> units = new ArrayList<UnitImpl>();
		units.add(blueLegion);
		units.add(redArcher);
		units.add(redSettler);
		
		WinningCondition zetaWin = new ZetaWinningCondition();
		Aging alphaAge = new AlphaAging();
		ConstructionStrategy deltaConstruct = new DeltaConstructionStrategy(cities, units, board);
		CityStrategy cityStrat = new AlphaCityStrategy();
		AttackStrategy attack = new AlphaAttackStrategy();
		zetaGame = new GameImpl(zetaWin, alphaAge, deltaConstruct, cityStrat, attack);
	} 
	
	@Test
	  public void shouldLetPlayerWhoConquersAllCitiesWin() {
		assertEquals( "There should be no winner at the start of the game",
				null, zetaGame.getWinner());
		endRound(10);
		//blue city 4,1 and red player on 4,3
		UnitImpl u = (UnitImpl) zetaGame.getUnitAt(new Position(4,3));
		zetaGame.moveUnit(u.getPosition(), new Position(4,2));
		endRound(1);
		zetaGame.moveUnit(u.getPosition(), new Position(4,1));
		endRound(1);
		zetaGame.moveUnit(u.getPosition(), new Position(4,0));
		endRound(1);
		zetaGame.moveUnit(u.getPosition(), new Position(5,0));
		endRound(1);
	    assertEquals( "The winner is red", Player.RED, zetaGame.getWinner());
	  }
	
	@Test
	public void shouldLetPlayerWhoWinsMoreThan3WinGameAfter20Rounds() {
		assertEquals("There should be no winner at the start of the game", null, zetaGame.getWinner());
		endRound(20);
		assertEquals("The number of red attacks is 0", 0, ((GameImpl) zetaGame).getRedAttacks());
		UnitImpl u = (UnitImpl) zetaGame.getUnitAt(new Position(4,3));
		zetaGame.moveUnit(u.getPosition(), new Position(4,2));
		assertEquals("The number of red attacks is 1", 1, ((GameImpl) zetaGame).getRedAttacks());
		endRound(1);
		zetaGame.moveUnit(u.getPosition(), new Position(4,1));
		assertEquals("The number of red attacks is 2", 2, ((GameImpl) zetaGame).getRedAttacks());
		//city at 4,0 and 5,0
		endRound(1);
		zetaGame.moveUnit(u.getPosition(), new Position(4,0));
		endRound(1);
		assertEquals("The number of red attacks is 3", 3, ((GameImpl) zetaGame).getRedAttacks());
		endRound(1);
		assertEquals("The winner is red", Player.RED, zetaGame.getWinner());
	}

	//helper method for endOfTurn
	public void endRound(int numRounds){
		int numTurns = numRounds*2;
		for(int i=0; i<numTurns;i++){
			zetaGame.endOfTurn();
		}
	}
}