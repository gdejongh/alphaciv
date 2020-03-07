/** 
 * implemented by Michael Kiley, Gabe Dejongh, and Fred Ottensmeyer
*/


package alphaciv.domain;

import java.util.*;
public class GameImpl implements Game {
	
	private static final int NUMBER_OF_PLAYERS = 2;
	private static final int STARTING_AGE = -4000;
	private static final int AGE_PER_TURN = 100;
	
	private int age;
	private boolean lastTurnOfRound = false;
	private Board theBoard;
	private GameFactory factory;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private WinningStrategy winningStrategy;
	private AgingStrategy agingStrategy;
	private ActionStrategy actionStrategy;
	private BoardFactory boardFactory;
	private AttackStrategy attackStrategy;
	private ProductionStrategy productionStrategy;
	private HashMap<Player, Integer> winMap;
	
	public GameImpl(){
		this(new AlphaCivFactory());
	}
	
	public GameImpl(BoardFactory factory){
		this(new AlphaCivFactory());
		boardFactory = factory;
		theBoard = boardFactory.createBoard();
	}
	
	public GameImpl(GameFactory factory){
		this.factory = factory;
		age = STARTING_AGE;
		
		winMap = new HashMap<Player, Integer>();
		winMap.put(Player.RED, 0);
		winMap.put(Player.BLUE, 0);
		
		players = new ArrayList<Player>();
		players.add(Player.RED);
		players.add(Player.BLUE);
		
		boardFactory = factory.getBoardFactory();
		theBoard = boardFactory.createBoard();
		
		currentPlayer = Player.RED;
		
		winningStrategy = factory.getWinningStrategy();
		agingStrategy = factory.getAgingStrategy();
		actionStrategy = factory.getActionStrategy();
		attackStrategy = factory.getAttackStrategy();
		productionStrategy = factory.getProductionStrategy();
	}

	public GameFactory getGameFactory(){
		return factory;
	}
	
	
	public void setAttackStrategy(AttackStrategy strat){
		attackStrategy = strat;
	}
	
	
	public void setWinningStrategy(WinningStrategy strat){
		winningStrategy = strat;
	}
	
	public void setAgingStrategy(AgingStrategy strat){
		agingStrategy = strat;
	}
	
	public int getBoardSize(){
		return theBoard.getSize();
	}
	
	public Tile getTileAt(Position p) { 
		return theBoard.getTileAt(p);
	}
	
	public Unit getUnitAt( Position p ) { 
		return theBoard.getUnitAt(p); 
	}
	
	public City getCityAt( Position p ) { 
		return theBoard.getCityAt(p);
	}
	
	public Player getPlayerInTurn() { 
		return currentPlayer; 
	}
	
	public Player getWinner() { 
		return winningStrategy.getWinner(theBoard, age, null);
	}
	
	public int getAge() { 
		return age; 
	}
	
	private void placeUnit(Position destination, Unit unit){
		theBoard.placeUnit(destination, unit);
	}
	
	
	private String getTerrainAt(Position p){
		return theBoard.getTerrainAt(p);
	}
	
	private void removeUnitAt(Position p){
		theBoard.removeUnitAt(p);
	}
	
	public boolean moveUnit( Position from, Position to ) {
	  Unit unitToMove = getUnitAt(from);
	  Unit unitAtDestination = getUnitAt(to);
	  if(isLegalMove(unitToMove, from, to)){
			if(attackStrategy.attack(theBoard.getTileAt(from), theBoard.getTileAt(to), theBoard)){
			  	placeUnit(to, unitToMove);
		  		removeUnitAt(from);
		  		unitToMove.decrementMoveCount();
				int successes = winMap.get(unitToMove.getOwner()) + 1;
				winMap.put(unitToMove.getOwner(), successes);
		  	} else {
				removeUnitAt(to);
			}
		  
		  return true;
	  } else {
		  return false;
	  }
	}
	
	private boolean isLegalMove(Unit unitToMove, Position from, Position to){
		if(unitToMove.getMoveCount() > 0 && unitToMove.getOwner() == currentPlayer 
				&& getTerrainAt(to) != GameConstants.MOUNTAINS 
				&& (getUnitAt(to) == null || getUnitAt(to).getOwner() != currentPlayer)){
			return theBoard.getSurroundingPositions(from).contains(to);
		} else {
			return false;
		}
	}
	
	public void endOfTurn() {
		if(islastTurnOfRound()){
			
			for(int row = 0; row < GameConstants.WORLDSIZE; row++){
				for(int column = 0; column < GameConstants.WORLDSIZE; column++){
					Position p = new Position(row, column);
					
					City city = getCityAt(p);
					produceAndPlaceUnits(city, p);
					
					Unit unit = getUnitAt(p);
					if(unit != null){
						unit.setMoveCount(1);
					}
				}
			}
			
			ageWorld();
			
			currentPlayer = players.get(0);
		} else {
			currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
		}
		
	}
	
	private boolean islastTurnOfRound(){
		return currentPlayer == players.get(players.size() - 1);
	}
	
	private void produceAndPlaceUnits(City city, Position position){
		if(city != null){
			//city.produce();
			productionStrategy.produce(city, position, theBoard);
			
			String unitToProduce = city.getProduction();
			int unitPrice = getPriceOf(unitToProduce);
			
			if(city.getProductionCount() >= unitPrice){
				Position p = findNextOpenTile(position);
				if(p != null){
					Unit newUnit = new UnitImpl(city.getOwner(), unitToProduce);
					placeUnit(p, newUnit);
					city.spendProduction(unitPrice);
				}
			}
			
		}
	}
	
	private int getPriceOf(String unitType){
		if(unitType.equals(GameConstants.ARCHER)){
			return GameConstants.ARCHER_PRICE;
		} else if(unitType.equals(GameConstants.LEGION)){
			return GameConstants.LEGION_PRICE;
		}else if(unitType.equals(GameConstants.SETTLER)){
			return GameConstants.SETTLER_PRICE;
		} else {
			return 0;
		}
		
	}
	
	private Position findNextOpenTile(Position p){
		ArrayList<Position> positionsToCheck = new ArrayList<Position>();
		positionsToCheck.add(p);
		positionsToCheck.addAll(theBoard.getSurroundingPositions(p));
		
		for (Position position : positionsToCheck) {
			Tile tile = getTileAt(position);
			if(tile != null) {
				if(tile.getUnit() == null) {
					return position;
				}
			}
		}
		return null;
	}
	
	public void changeWorkForceFocusInCityAt( Position p, String balance ) {
		  City city = theBoard.getCityAt(p);
		  city.setWorkForceFocus(balance);
	}
	
	public void changeProductionInCityAt( Position p, String unitType ) {
		  City theCity = getCityAt(p);
		  theCity.changeProductionInCity(unitType);
	}
	
	public void performUnitActionAt( Position p ) {
		Tile tile = getTileAt(p);  
		theBoard.setTile(p, actionStrategy.performActionAt(tile));
	}
	  
	private void ageWorld(){
		age = agingStrategy.ageWorld(age);
	}
	
	public Board getBoard(){
		return theBoard;
	}
	public void setBoard(Board pBoard){
		theBoard = pBoard;
	}
	
}
