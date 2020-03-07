package hotciv.framework;

public interface AttackStrategy {
	
	public boolean attack(Tile attackerTile, Tile defenderTile, Board board);
	
}