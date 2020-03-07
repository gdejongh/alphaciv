package alphaciv.domain;

public class AlphaBoardFactory implements BoardFactory{
	public Board createBoard(){
		
		Board board = new Board(GameConstants.WORLDSIZE, GameConstants.WORLDSIZE);
		
		board.placeNewTile(new Position(1, 0), GameConstants.OCEANS);
		board.placeNewTile(new Position(0, 1), GameConstants.HILLS);
		board.placeNewTile(new Position(1, 1), GameConstants.MOUNTAINS);
		
		board.placeNewCity(new Position(1,1), Player.RED);
		board.placeNewCity(new Position(4,1), Player.BLUE);
		
		board.placeUnit(new Position(2,0), new UnitImpl(Player.RED, GameConstants.ARCHER));
		board.placeUnit(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
		board.placeUnit(new Position(4,3), new UnitImpl(Player.RED, GameConstants.SETTLER));
		
		return board;
	}
	
	
	
	
	
	
}