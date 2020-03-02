package alphaciv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DeltaConstructionStrategy implements ConstructionStrategy {
	ArrayList<CityImpl> cities;
	ArrayList<Tile> board;
	ArrayList<UnitImpl> units;
	
	public DeltaConstructionStrategy(ArrayList<CityImpl> paramCities, ArrayList<UnitImpl> paramUnits, ArrayList<Tile> tiles) {
		cities = paramCities;
		board = tiles;
		units = paramUnits;
	}
	
//	public DeltaConstructionStrategy(File textFile) throws IOException{
//		InputStream file = getClass().getResourceAsStream("deltaCiv.txt");
//		try {
//			FileReader fRead = new FileReader(textFile);
//			BufferedReader input = new BufferedReader(fRead);
//			
//			String nextLine = input.readLine();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
	@Override
	public ArrayList<CityImpl> addCities() {
		return cities;
	}

	@Override
	public ArrayList<Tile> addBoard() {
		return board;
	}

	@Override
	public ArrayList<UnitImpl> addUnits() {
		return units;
	}

}
