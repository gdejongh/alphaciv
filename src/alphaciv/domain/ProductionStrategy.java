package alphaciv.domain;

public interface ProductionStrategy{
	
	public void produce(City city, Position position, Board board);
	
}