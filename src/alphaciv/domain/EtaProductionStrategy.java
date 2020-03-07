package alphaciv.domain;
import java.util.*;

public class EtaProductionStrategy implements ProductionStrategy{
	
	int production = 1;
	int food = 1;
	HashMap<String, Integer> terrain;
	
	public void produce(City city, Position position, Board board){
		int extraWorkers = city.getSize()-1;
		ArrayList<Position> surroundingPositions = board.getSurroundingPositions(position);
		terrain = new HashMap<String, Integer>();
		for(Position p : surroundingPositions){
			String type = board.getTerrainAt(p);
			if(terrain.get(type) != null){
				terrain.put(type, terrain.get(type) + 1);
			} else {
				terrain.put(type, 1);
			}
		}
		if(city.getWorkforceFocus() == GameConstants.productionFocus){
			extraWorkers = optimizeForProduction(extraWorkers);
			optimizeForFood(extraWorkers);
		} else {
			extraWorkers = optimizeForFood(extraWorkers);
			optimizeForProduction(extraWorkers);
		}
		city.setProductionCount(production);
		city.setFoodCount(food);
	}
	
	private int optimizeForFood(int extraWorkers){
		while(extraWorkers > 0){
			if(terrain.get(GameConstants.PLAINS) > 0){
				food += 3;
				extraWorkers--;
				terrain.put(GameConstants.PLAINS, terrain.get(GameConstants.PLAINS) - 1);
			} else if(terrain.get(GameConstants.OCEANS) > 0){
				food += 1;
				extraWorkers--;
				terrain.put(GameConstants.OCEANS, terrain.get(GameConstants.OCEANS) - 1);
			} else {
				break;
			}
		}
		return extraWorkers;
	}
	
	private int optimizeForProduction(int extraWorkers){
		while(extraWorkers > 0){
			if(terrain.get(GameConstants.FOREST) > 0){
				production += 3;
				extraWorkers--;
				terrain.put(GameConstants.FOREST, terrain.get(GameConstants.FOREST) - 1);
			} else if(terrain.get(GameConstants.HILLS) > 0){
				production += 2;
				extraWorkers--;
				terrain.put(GameConstants.HILLS, terrain.get(GameConstants.HILLS) - 1);
			} else if(terrain.get(GameConstants.MOUNTAINS) > 0){
				production += 1;
				extraWorkers--;
				terrain.put(GameConstants.HILLS, terrain.get(GameConstants.HILLS) - 1);
			} else {
				break;
			}
		}
		return extraWorkers;
	}
	
}