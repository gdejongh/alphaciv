package alphaciv;

import java.util.ArrayList;

public interface ConstructionStrategy {
	public ArrayList<CityImpl> addCities();

	public ArrayList<Tile> addBoard();

	public ArrayList<UnitImpl> addUnits();
}
