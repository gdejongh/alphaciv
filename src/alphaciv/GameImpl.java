package alphaciv;

import java.util.ArrayList;
import java.util.Random;

/** Skeleton implementation of HotCiv.

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

public class GameImpl implements Game {

	private int productionIncrease;
	private static final int ARCHERCOST = 10;
	private static final int SETTLERCOST = 30;
	private static final int LEGIONCOST = 15;
	private static final int DEFAULTDEFENCESTRENGTH = 3;
	private UnitImpl redArcher = new UnitImpl(new Position(2,0), GameConstants.ARCHER, Player.RED);
	private UnitImpl redSettler = new UnitImpl(new Position(4,3), GameConstants.SETTLER, Player.RED);
	private ArrayList<CityImpl> cities = new ArrayList<CityImpl>();
	private ArrayList<UnitImpl> units = new ArrayList<UnitImpl>();
	private ArrayList<Tile> board  = new ArrayList<Tile>();
	private int age = -4000;
	private Player inTurn = Player.RED;
	private boolean endOfRound = false;
	private WinningCondition winCondition;
	private Aging aging;
	private ConstructionStrategy constructionStrategy;
	private int blueAttacks=0;
	private int redAttacks = 0;
	private int numberOfRounds = 0;
	private CityStrategy citySetup;
	private AttackStrategy attackStrategy;


	public GameImpl(WinningCondition wCondition, Aging wAging, ConstructionStrategy constructStrat, CityStrategy cityStrat, AttackStrategy attackStrat){
		constructionStrategy = constructStrat;
		board = constructionStrategy.addBoard();
		cities = constructionStrategy.addCities();
		units = constructionStrategy.addUnits();

		redArcher.setDefensiveStrength(DEFAULTDEFENCESTRENGTH);
		redSettler.setDefensiveStrength(DEFAULTDEFENCESTRENGTH);

		winCondition = wCondition;
		aging = wAging;
		citySetup = cityStrat;
		productionIncrease = citySetup.setProdIncrease();
		attackStrategy = attackStrat;
	}

	public Tile getTileAt( Position p ) { 
		for(Tile tile: board){
			if(tile.getPosition().getRow() == p.getRow() && tile.getPosition().getColumn() == p.getColumn()){
				return tile;
			}
		}
		return new TileImpl(p, GameConstants.PLAINS); 
	}


	public Unit getUnitAt( Position p ) {		
		for(UnitImpl unit: units){
			if(unit.getPosition().getRow() == p.getRow() && unit.getPosition().getColumn() == p.getColumn()){
				return unit;
			}
		}
		return null; 
	}

	public City getCityAt( Position p ) {
		for(CityImpl city: cities){
			if(city.getPosition().getRow() == p.getRow() && city.getPosition().getColumn() == p.getColumn()){
				return city;
			}
		}
		return null; 
	}

	public Player getPlayerInTurn() { 
		return inTurn; 
	}

	public Player getWinner() { 
		return winCondition.getWinner(cities, age, numberOfRounds, redAttacks, blueAttacks);
	}

	public int getAge() { 
		return age; 
	}

	public boolean moveUnit( Position from, Position to ) {
		if(from==(null) || getUnitAt(from) == null || getTileAt(to).getTypeString() == GameConstants.MOUNTAINS || 
				getTileAt(to).getTypeString() == GameConstants.OCEANS || getUnitAt(from).getMoveCount() == 0 || to.getRow() > 15 || to.getColumn() > 15
				|| to.getColumn() < 0 || to.getRow() < 0 || to==(null) || (from.getColumn()-to.getColumn()>1 && from.getColumn()-to.getColumn()<-1)
				|| (from.getRow()-to.getRow()>1 && from.getRow()-to.getRow()>1)){
			return false;
		} 
		UnitImpl currentUnit = (UnitImpl) getUnitAt(from);
		CityImpl city = (CityImpl) getCityAt(to);
		if(city != null){
			city.setOwner(currentUnit.getOwner());
		}
		if (getUnitAt(to) != null){
			if(getUnitAt(to).getOwner() == currentUnit.getOwner()){
				return false;
			} else {
				if(attackUnit(currentUnit, (UnitImpl)getUnitAt(to), to)){
					if(currentUnit.getOwner()==Player.RED){
						redAttacks++;
					}
					else{
						blueAttacks++;
					}
					units.remove(getUnitAt(to));
					currentUnit.setPosition(to);
				}
				else{
					units.remove(getUnitAt(from));
				}
			}
		}
		else{
			currentUnit.setPosition(to);
			currentUnit.setMoveCount(0);
		}
		return true;
	}

	public boolean attackUnit(UnitImpl attackingUnit, UnitImpl defendingUnit, Position there){
		return attackStrategy.attackUnit(attackingUnit, defendingUnit, there, this);
	}

	public void endOfTurn() {
		if(inTurn == Player.RED){
			inTurn = Player.BLUE;
		} else {
			inTurn = Player.RED;
		}
		endOfRound();
	}

	public void endOfRound(){
		if (endOfRound){
			numberOfRounds++;
			for(UnitImpl unit: units){
				unit.setMoveCount(1);
			}
			age = aging.newAge(age);

			for(CityImpl city: cities){
				city.setProductionAmount(city.getProductionAmount() + productionIncrease);
				city.setFood(city.getFood()+productionIncrease);
				switch(city.getProduction()){
				case(GameConstants.ARCHER):
					if(city.getProductionAmount()>=ARCHERCOST){
						city.setProductionAmount(city.getProductionAmount()-ARCHERCOST);
						placeUnit(city);
					}
				break;
				case(GameConstants.LEGION):
					if(city.getProductionAmount()>=LEGIONCOST){
						city.setProductionAmount(city.getProductionAmount()-LEGIONCOST);
						placeUnit(city);
					}
				break;
				case(GameConstants.SETTLER):
					if(city.getProductionAmount()>=SETTLERCOST){
						city.setProductionAmount(city.getProductionAmount()-SETTLERCOST);
						placeUnit(city);
					}
				break;

				}
				citySetup.setUpCity(city, this);
			}
			endOfRound = false;
		} else {
			endOfRound = true;
		}
	}

	//put in list, then add list to the current position of the city
	public void placeUnit(CityImpl city){
		if(getUnitAt(city.getPosition())==null){
			UnitImpl newUnit = new UnitImpl(city.getPosition(),city.getProduction(), city.getOwner());
			units.add(newUnit);
			return;
		}
		if(getUnitAt(new Position(city.getPosition().getRow()-1, city.getPosition().getColumn()))==null){
			UnitImpl newUnit = new UnitImpl(new Position(city.getPosition().getRow()-1, city.getPosition().getColumn()),city.getProduction(), city.getOwner());
			units.add(newUnit);
			return;
		}
		if(getUnitAt(new Position(city.getPosition().getRow()-1, city.getPosition().getColumn()+1))==null){
			UnitImpl newUnit = new UnitImpl(new Position(city.getPosition().getRow()-1, city.getPosition().getColumn()+1),city.getProduction(), city.getOwner());
			units.add(newUnit);
			return;
		}
		for(int i=1; i<4;i++){
			if(getUnitAt(new Position(city.getPosition().getRow()-1+i, city.getPosition().getColumn()+1))==null){
				UnitImpl newUnit = new UnitImpl(new Position(city.getPosition().getRow()-1+i, city.getPosition().getColumn()+1),city.getProduction(), city.getOwner());
				units.add(newUnit);
				return;
			}
		}
		for(int i=1; i<4;i++){
			if(getUnitAt(new Position(city.getPosition().getRow()+1, city.getPosition().getColumn()+1-i))==null){
				UnitImpl newUnit = new UnitImpl(new Position(city.getPosition().getRow()+1, city.getPosition().getColumn()+1-i),city.getProduction(), city.getOwner());
				units.add(newUnit);
				return;
			}
		}
		for(int i=1; i<4;i++){
			if(getUnitAt(new Position(city.getPosition().getRow()+1-i, city.getPosition().getColumn()-1))==null){
				UnitImpl newUnit = new UnitImpl(new Position(city.getPosition().getRow()+1-i, city.getPosition().getColumn()-1),city.getProduction(), city.getOwner());
				units.add(newUnit);
				return;
			}
		}

	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {
		CityImpl city = (CityImpl) getCityAt(p);
		city.setWorkforceFocus(balance);
	}

	public void changeProductionInCityAt( Position p, String unitType ) {
		CityImpl city = (CityImpl) getCityAt(p);
		city.setProductionFocus(unitType);
		city.setProductionAmount(0);
	}

	public void performUnitActionAt( Position p ) {
		UnitImpl currentUnit = (UnitImpl) getUnitAt(p);
		if(currentUnit.getTypeString().equals(GameConstants.ARCHER)){
			if(currentUnit.getDefensiveStrength()==2*DEFAULTDEFENCESTRENGTH){
				currentUnit.setDefensiveStrength(currentUnit.getDefensiveStrength()/2);
			}
			else{currentUnit.setDefensiveStrength(currentUnit.getDefensiveStrength()*2);}
			currentUnit.setMoveCount(0);
		}
		if(currentUnit.getTypeString().equals(GameConstants.SETTLER)){
			CityImpl newCity = new CityImpl(p, currentUnit.getOwner(), "");
			cities.add(newCity);
			units.remove(currentUnit);
		}

	}

	public int getRedAttacks(){
		return redAttacks;
	}
}
