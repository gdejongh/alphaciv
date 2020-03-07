package hotciv.framework;

public class TileImpl implements Tile{
	
	private String type;
	private Position position;
	private City city;
	private Unit unit;
	
	public TileImpl(String pType, Position pPosition){
		  type = pType;
		  position = pPosition;
		  city = null;
		  unit = null;
	}
	
	public Position getPosition(){
		return position;
	}
	  
	public String getTypeString(){
		return type;
	}
	
	public boolean addCity(City cityToAdd){
		if(city == null){
			city = cityToAdd;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addUnit(Unit unitToAdd){
		unit = unitToAdd;
		return true;
	}
	
	public void removeUnit(){
		unit = null;
	}
	
	public City getCity(){
		return city;
	}
	
	public Unit getUnit(){
		return unit;
	}
	
}
