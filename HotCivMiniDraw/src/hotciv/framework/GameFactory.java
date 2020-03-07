package hotciv.framework;

public interface GameFactory{
	
	public ActionStrategy getActionStrategy();
	public AttackStrategy getAttackStrategy();
	public BoardFactory getBoardFactory();
	public WinningStrategy getWinningStrategy();
	public AgingStrategy getAgingStrategy();
	public ProductionStrategy getProductionStrategy();
}