package alphaciv.domain;

public class AlphaCivFactory implements GameFactory{
	
	public ActionStrategy getActionStrategy(){
		return new NoActionStrategy();
	}
	public AttackStrategy getAttackStrategy(){
		return new AlphaAttackStrategy();
	}
	public BoardFactory getBoardFactory(){
		return new AlphaBoardFactory();
	}
	public WinningStrategy getWinningStrategy(){
		return new AlphaWinningStrategy();
	}
	public AgingStrategy getAgingStrategy(){
		return new AlphaAgingStrategy();
	}
	public ProductionStrategy getProductionStrategy(){
		return new AlphaProductionStrategy();
	}
}