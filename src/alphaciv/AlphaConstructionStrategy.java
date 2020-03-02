package alphaciv;

import java.util.ArrayList;

public class AlphaConstructionStrategy implements ConstructionStrategy {
	private Tile oceans;
	private Tile hills;
	private Tile mountains;
	private CityImpl redCity = new CityImpl(new Position(1,1), Player.RED, GameConstants.ARCHER);
	private CityImpl blueCity = new CityImpl(new Position(4,1), Player.BLUE, GameConstants.LEGION);
	private UnitImpl redArcher = new UnitImpl(new Position(2,0), GameConstants.ARCHER, Player.RED);
	private UnitImpl redSettler = new UnitImpl(new Position(4,3), GameConstants.SETTLER, Player.RED);
	private UnitImpl blueLegion = new UnitImpl(new Position(3,2), GameConstants.LEGION, Player.BLUE);
	
	@Override
	public ArrayList<CityImpl> addCities() {
		ArrayList<CityImpl> cities = new ArrayList<CityImpl>();
		cities.add(redCity);
		cities.add(blueCity);
		return cities;
	}

	@Override
	public ArrayList<Tile> addBoard() {
		oceans = new TileImpl(new Position (1,0), GameConstants.OCEANS);
		hills = new TileImpl(new Position (0, 1), GameConstants.HILLS);
		mountains = new TileImpl(new Position (2,2), GameConstants.MOUNTAINS);
		ArrayList<Tile> board = new ArrayList<Tile>();
		board.add(oceans);
		board.add(hills);
		board.add(mountains);
		return board;
	}

	@Override
	public ArrayList<UnitImpl> addUnits() {
		ArrayList<UnitImpl> units = new ArrayList<UnitImpl>();
		units.add(blueLegion);
		units.add(redArcher);
		units.add(redSettler);
		return units;
	}

}
