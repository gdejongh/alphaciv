package alphaciv.domain;

public class GammaActionStrategy implements ActionStrategy{
	
	public Tile performActionAt(Tile pTile){
		Unit unit = pTile.getUnit();
		if(unit == null){return pTile;}
		Tile tile = new TileImpl(pTile.getTypeString(), pTile.getPosition());
		if(unit.getTypeString() == GameConstants.SETTLER){
			City city = new CityImpl(unit.getOwner());
			tile.addCity(city);
		} else if (unit.getTypeString() == GameConstants.ARCHER){
			if(unit.getDefensiveStrength() == GameConstants.ARCHER_DEFENSIVE_STRENGTH){
				unit.setDefensiveMultiplier(2);
				unit.setMoveCountMultiplier(0);
			} else {
				unit.setDefensiveMultiplier(1);
				unit.setMoveCountMultiplier(1);
			}
			tile.addUnit(unit);
		}
		return tile;
	}
	
}