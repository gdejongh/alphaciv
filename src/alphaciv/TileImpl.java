package alphaciv;

public class TileImpl implements Tile{

	private Position position;
	private String stringType;
	
	public TileImpl(Position p, String s){
		setPosition(p);
		setTypeString(s);
	}
	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setPosition(Position p){
		position = p;
		
	}

	@Override
	public String getTypeString() {
		// TODO Auto-generated method stub
		return stringType;
	}
	
	public void setTypeString(String s){
		stringType = s;
	}

}
