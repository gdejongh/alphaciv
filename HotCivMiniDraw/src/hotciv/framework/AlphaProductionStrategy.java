package hotciv.framework;

public class AlphaProductionStrategy implements ProductionStrategy{
	
	public void produce(City city, Position position, Board board){
		city.produce();
	}
	
}