package hotciv.framework;
import java.util.*;

public class Board{
	private Tile[][] board;
	
	public Board(int rows, int columns){
		board = new Tile[rows][columns];
		setToAllPlains(rows, columns);
	}
	
	public Board(int rows, int columns, TileImpl[] specialTiles){
		board = new Tile[rows][columns];
		setToAllPlains(rows, columns);
		for (TileImpl tile : specialTiles){
			Position p = tile.getPosition();
			setTile(p, tile);
		}
	}
	
	private void setToAllPlains(int rows, int columns){
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				Position p = new Position(row, column);
				Tile tile = new TileImpl(GameConstants.PLAINS, p);
				setTile(p, tile);
			}
		}
	}
	
	public void placeNewTile(Position destination, String type){
		board[destination.getRow()][destination.getColumn()] = new TileImpl(type, destination);
	}
	
	public void placeNewCity(Position destination, Player owner){
		board[destination.getRow()][destination.getColumn()].addCity(new CityImpl(owner));
	}
	
	public int getSize(){
		return board.length;
	}
	
	public Tile getTileAt(Position p){
		int row = p.getRow();
		int column = p.getColumn();
		
		return board[row][column];
	}
	
	public Unit getUnitAt(Position p){
		return board[p.getRow()][p.getColumn()].getUnit(); 
	}
	
	public City getCityAt(Position p){
		return board[p.getRow()][p.getColumn()].getCity(); 
	}
	
	public void addUnit(Position destination, Unit unit){
		getTileAt(destination).addUnit(unit);
	}
	
	public String getTerrainAt(Position p){
		return board[p.getRow()][p.getColumn()].getTypeString();
	}
	
	public void removeUnitAt(Position p){
		board[p.getRow()][p.getColumn()].removeUnit();
	}
	
	public void setTile(Position p, Tile tile){
		board[p.getRow()][p.getColumn()] = tile;
	}
	
	public void placeUnit(Position destination, Unit unit){
		addUnit(destination, unit);
		City cityAtDestination = getCityAt(destination);
		if(cityAtDestination != null){
			cityAtDestination.setOwner(unit.getOwner());
		}
		
	}
	
	public ArrayList<Position> getSurroundingPositions(Position p){
		 int row = p.getRow();
		 int col = p.getColumn();
		 ArrayList<Position> surroundingPositions = new ArrayList<Position>();
			
		 surroundingPositions.add(new Position(row-1, col));
		 surroundingPositions.add(new Position(row-1, col+1));
		 surroundingPositions.add(new Position(row, col+1));
		 surroundingPositions.add(new Position(row+1, col+1));
		 surroundingPositions.add(new Position(row+1, col));
		 surroundingPositions.add(new Position(row+1,col-1));
		 surroundingPositions.add(new Position(row, col-1));
		 surroundingPositions.add(new Position(row -1, col-1));
		 return surroundingPositions;
	}
	
	
}