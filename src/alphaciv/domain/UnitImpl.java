package alphaciv.domain;

public class UnitImpl implements Unit{
	
	private Player owner;
	private String unitType;
	private int moveCount;
	private int defensiveMultiplier;
	private int moveCountMultiplier;
	
	public UnitImpl(Player pOwner, String pUnitType) {
		owner = pOwner;
		unitType = pUnitType;
		moveCount = 1;
		defensiveMultiplier = 1;
		moveCountMultiplier = 1;
	}

	@Override
	public String getTypeString() {
		// TODO Auto-generated method stub
		return unitType;
	}

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	@Override
	public int getMoveCount() {
		// TODO Auto-generated method stub
		return moveCountMultiplier * moveCount;
	}

	@Override
	public int getDefensiveStrength() {
		// TODO Auto-generated method stub
		if(unitType.equals(GameConstants.ARCHER)) { return GameConstants.ARCHER_DEFENSIVE_STRENGTH * defensiveMultiplier;}
		if(unitType.equals(GameConstants.SETTLER)) {return GameConstants.SETTLER_DEFENSIVE_STRENGTH;}
		if(unitType.equals(GameConstants.LEGION)) {return GameConstants.LEGION_DEFENSIVE_STRENGTH;}
		else {
			return 0;
		}
	}

	@Override
	public int getAttackingStrength() {
		if(unitType.equals(GameConstants.ARCHER)) { return GameConstants.ARCHER_ATTACKING_STRENGTH;}
		if(unitType.equals(GameConstants.SETTLER)) {return GameConstants.SETTLER_ATTACKING_STRENGTH;}
		if(unitType.equals(GameConstants.LEGION)) {return GameConstants.LEGION_ATTACKING_STRENGTH;}
		else {
			return 0;
		}
	}
	
	@Override
	public void setMoveCount(int newMoveCount){
		moveCount = newMoveCount;
	}
	
	@Override
	public void decrementMoveCount(){
		moveCount = moveCount - 1;
	}
	
	@Override
	public void setDefensiveMultiplier(int m){
		defensiveMultiplier = m;
	}
	
	@Override
	public void setMoveCountMultiplier(int m){
		moveCountMultiplier = m;
	}
	

}
