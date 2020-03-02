package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import alphaciv.Aging;
import alphaciv.AlphaAging;
import alphaciv.AlphaAttackStrategy;
import alphaciv.AlphaCityStrategy;
import alphaciv.AlphaConstructionStrategy;
import alphaciv.AlphaWinningCondition;
import alphaciv.AttackStrategy;
import alphaciv.City;
import alphaciv.CityStrategy;
import alphaciv.ConstructionStrategy;
import alphaciv.Game;
import alphaciv.GameConstants;
import alphaciv.GameImpl;
import alphaciv.Player;
import alphaciv.Position;
import alphaciv.Tile;
import alphaciv.Unit;
import alphaciv.UnitImpl;
import alphaciv.WinningCondition;

public class TestEpsilonCiv {
	private FakeGame epsilonGame;
	private Game newGame;


	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		WinningCondition alphaWin = new AlphaWinningCondition();
		Aging alphaAge = new AlphaAging();
		ConstructionStrategy alphaConstruct = new AlphaConstructionStrategy();
		CityStrategy cityStrat = new AlphaCityStrategy();
		AttackStrategy attack = new AlphaAttackStrategy();
		epsilonGame = new FakeGame(new GameImpl(alphaWin, alphaAge, alphaConstruct, cityStrat,attack));
	}

	@Test
	public void testAttackMethod() {
		//red archer with red settler nearby at 2,0 attack blue legion at 3,2
		setUpAttack();
		
		assertEquals("Red archer should have attacking strength of 2 + 1", 3, 
				epsilonGame.updateAttackStrength((UnitImpl) epsilonGame.getUnitAt(new Position(3,1))));
	}

	@Test
	public void testDefensiveStrengthAlgorithm(){
		setUpAttack();
		assertEquals("Blue legion should have defending strength of 3", 3, 
				epsilonGame.updateDefenseStrength((UnitImpl) epsilonGame.getUnitAt(new Position(4,2))));
	}
	
	@Test
	public void testOutcome(){
		setUpAttack();
		UnitImpl redArcher = (UnitImpl) epsilonGame.getUnitAt(new Position(3,1));
		UnitImpl blueLegion = (UnitImpl) epsilonGame.getUnitAt(new Position(4,2));
		
		//red archer should have the attack strength of 5 (2*2+1) and blueLegion should have 
				//(2*2) = 4
		boolean outcome = epsilonGame.attackUnit(redArcher, blueLegion, new Position(3,2));
		if((epsilonGame.updateAttackStrength(redArcher) * epsilonGame.d1)
				> (epsilonGame.updateDefenseStrength(blueLegion) * epsilonGame.d2)){
			assertEquals("The red archer should win", true, outcome);
		} else {
			assertEquals("The blue legion should win", false, outcome);
		}
	}
	
	private void setUpAttack() {
		UnitImpl redArcher = (UnitImpl) epsilonGame.getUnitAt(new Position(2,0));
		UnitImpl redSettler = (UnitImpl) epsilonGame.getUnitAt(new Position(4,3));
		Position attackPosition = new Position(3,2);

		epsilonGame.moveUnit(redArcher.getPosition(), new Position(2,1));
		epsilonGame.moveUnit(redSettler.getPosition(), new Position(3,3));
		endRound(1);
		epsilonGame.moveUnit(attackPosition, new Position(4,2));
		endRound(1);
		epsilonGame.moveUnit(redArcher.getPosition(), new Position(3,1));
		epsilonGame.moveUnit(redSettler.getPosition(), attackPosition);
		endRound(1);
	}

	//helper method for endOfTurn
	public void endRound(int numRounds){
		int numTurns = numRounds*2;
		for(int i=0; i<numTurns;i++){
			epsilonGame.endOfTurn();
		}
	}

	//test stub for a developer's action
	class FakeGame implements Game{
		private Game epsilonGame;
		public int d1;
		public int d2;
		public int attackingStrength;
		public int defendingStrength;

		public FakeGame(GameImpl game){
			epsilonGame = game;
		}
		@Override
		public Tile getTileAt(Position p) {
			return epsilonGame.getTileAt(p);
		}

		@Override
		public Unit getUnitAt(Position p) {
			return epsilonGame.getUnitAt(p);
		}

		@Override
		public City getCityAt(Position p) {
			return epsilonGame.getCityAt(p);
		}

		@Override
		public Player getPlayerInTurn() {
			return epsilonGame.getPlayerInTurn();
		}

		@Override
		public Player getWinner() {
			return epsilonGame.getPlayerInTurn();
		}

		@Override
		public int getAge() {
			return epsilonGame.getAge();
		}

		@Override
		public boolean moveUnit(Position from, Position to) {
			return epsilonGame.moveUnit(from, to);
		}

		@Override
		public void endOfTurn() {
			epsilonGame.endOfTurn();
		}

		@Override
		public void changeWorkForceFocusInCityAt(Position p, String balance) {
			epsilonGame.changeWorkForceFocusInCityAt(p, balance);
		}

		@Override
		public void changeProductionInCityAt(Position p, String unitType) {
			epsilonGame.changeProductionInCityAt(p, unitType);

		}

		@Override
		public void performUnitActionAt(Position p) {
			epsilonGame.performUnitActionAt(p);
		}
		
		public int updateAttackStrength(UnitImpl attackingUnit){
			Tile tile = getTileAt(attackingUnit.getPosition());
			Position there = attackingUnit.getPosition();
			attackingStrength = attackingUnit.getAttackingStrength();
			if(tile.getTypeString().equals(GameConstants.FOREST)||tile.getTypeString().equals(GameConstants.HILLS)){
				attackingStrength*=2;
			}
			else if(getCityAt(there)!=null){
				if(getCityAt(there).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength*=3;
				}
			}

			if(getUnitAt(new Position(there.getRow(), there.getColumn()+1))!=null){
				Position p = new Position(there.getRow(), (there.getColumn()+1));
				if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength++;
				}
			}

			if(getUnitAt(new Position(there.getRow(), there.getColumn()-1))!=null){
				Position p = new Position(there.getRow(), (there.getColumn()-1));
				if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength++;
				}
			}

			for(int i=0;i<3;i++){
				if(getUnitAt(new Position(there.getRow()+1, there.getColumn()+1-i))!=null){
					Position p = new Position(there.getRow()+1, (there.getColumn()+1-i));
					if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
						attackingStrength++;
					}
				}
			}

			for(int i=0;i<3;i++){
				if(getUnitAt(new Position(there.getRow()-1, there.getColumn()+1-i))!=null){
					Position p = new Position(there.getRow()-1, (there.getColumn()+1-i));
					if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
						attackingStrength++;
					}
				}
			}
			
			int previousStrength = attackingStrength;
			attackingStrength = 0;
			return previousStrength;
		}
		
		public int updateDefenseStrength(UnitImpl defendingUnit){
			Tile tile = getTileAt(defendingUnit.getPosition());
			Position there = defendingUnit.getPosition();
			defendingStrength = defendingUnit.getDefensiveStrength();
			if(tile.getTypeString().equals(GameConstants.FOREST)||tile.getTypeString().equals(GameConstants.HILLS)){
				defendingStrength*=2;
			}
			else if(getCityAt(there)!=null){
				if(!getCityAt(there).getOwner().equals(defendingUnit.getOwner())){
					defendingStrength*=3;
				}
			}

			if(getUnitAt(new Position(there.getRow(), there.getColumn()+1))!=null){
				Position p = new Position(there.getRow(), (there.getColumn()+1));
				if(getUnitAt(p).getOwner().equals(defendingUnit.getOwner())){
					defendingStrength++;
				}
			}

			if(getUnitAt(new Position(there.getRow(), there.getColumn()-1))!=null){
				Position p = new Position(there.getRow(), (there.getColumn()-1));
				if(getUnitAt(p).getOwner().equals(defendingUnit.getOwner())){
					defendingStrength++;
				}
			}

			for(int i=0;i<3;i++){
				if(getUnitAt(new Position(there.getRow()+1, there.getColumn()+1-i))!=null){
					Position p = new Position(there.getRow()+1, (there.getColumn()+1-i));
					if(getUnitAt(p).getOwner().equals(defendingUnit.getOwner())){
						defendingStrength++;
					}
				}
			}

			for(int i=0;i<3;i++){
				if(getUnitAt(new Position(there.getRow()-1, there.getColumn()+1-i))!=null){
					Position p = new Position(there.getRow()-1, (there.getColumn()+1-i));
					if(getUnitAt(p).getOwner().equals(defendingUnit.getOwner())){
						defendingStrength++;
					}
				}
			}
			int previousStrength = defendingStrength;
			defendingStrength = defendingUnit.getDefensiveStrength();
			return previousStrength;
		}

		public boolean attackUnit(UnitImpl attackingUnit, UnitImpl defendingUnit, Position there){
			Tile tile = getTileAt(there);
			attackingStrength = attackingUnit.getAttackingStrength();
			defendingStrength = defendingUnit.getDefensiveStrength();
			if(tile.getTypeString().equals(GameConstants.FOREST)||tile.getTypeString().equals(GameConstants.HILLS)){
				attackingStrength*=2;
				defendingStrength*=2;
			}
			else if(getCityAt(there)!=null){
				if(getCityAt(there).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength*=3;
				}
				else{
					defendingStrength*=3;
				}
			}

			if(getUnitAt(new Position(there.getRow(), there.getColumn()+1))!=null){
				Position p = new Position(there.getRow(), there.getColumn()+1);
				if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength++;
				}
				else{
					defendingStrength++;
				}
			}

			if(getUnitAt(new Position(there.getRow(), there.getColumn()-1))!=null){
				Position p = new Position(there.getRow(), there.getColumn()-1);
				if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength++;
				}
				else{
					defendingStrength++;
				}
			}

			for(int i=0;i<3;i++){
				if(getUnitAt(new Position(there.getRow()+1, there.getColumn()+1-i))!=null){
					Position p = new Position(there.getRow()+1, there.getColumn()+1-i);
					if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
						attackingStrength++;
					}
					else{
						defendingStrength++;
					}
				}
			}

			for(int i=0;i<3;i++){
				if(getUnitAt(new Position(there.getRow()-1, there.getColumn()+1-i))!=null){
					Position p = new Position(there.getRow()-1, there.getColumn()+1-i);
					if(getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
						attackingStrength++;
					}
					else{
						defendingStrength++;
					}
				}
			}

			Random rand = new Random();
			d1 = rand.nextInt(6) +1;
			d2 = rand.nextInt(6) +1;
			return attackingStrength*d1 > defendingStrength*d2;
		}
	}
}
