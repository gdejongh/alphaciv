package alphaciv.domain;

public interface AttackStrategy {
	
	public boolean attack(Tile attackerTile, Tile defenderTile, Board board);
	
}