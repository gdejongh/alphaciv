package alphaciv;

public class UnitImpl implements Unit{
	
	private Position position;
	private String typeString;
	private Player owner;
	private int move = 1;
	private int defenseStrength;
	private int attackStrength;
	private boolean hasMoved = false;
	
	public UnitImpl(Position p, String s, Player pl){
		position = p;
		typeString = s;
		owner = pl;
		switch(s){
		case(GameConstants.LEGION):
			attackStrength = 4;
			defenseStrength = 2;
			break;
		case(GameConstants.ARCHER):
			attackStrength = 2;
			defenseStrength = 3;
			break;
		case(GameConstants.SETTLER):
			attackStrength = 0;
			defenseStrength = 3;
			break;
		
		}
	}

	public void setTypeString(){
		
	}
	
	@Override
	public String getTypeString() {
		// TODO Auto-generated method stub
		return typeString;
	}

	public void setOwner(){
		
	}
	
	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	public void setMoveCount(int c){
		move = c;
	}
	@Override
	public int getMoveCount() {
		// TODO Auto-generated method stub
		return move;
	}
	
	
	public void setDefensiveStrength(int s){
		defenseStrength = s;
	}

	@Override
	public int getDefensiveStrength() {
		// TODO Auto-generated method stub
		return defenseStrength;
	}
	
	public void setAttackStrength(){
		
	}

	@Override
	public int getAttackingStrength() {
		return attackStrength;
	}
	
	public void setPosition(Position p){
		position = p;
	}
	
	public Position getPosition(){
		return position;
	}

}
