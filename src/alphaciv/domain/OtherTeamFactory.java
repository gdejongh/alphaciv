package alphaciv.domain;

public class OtherTeamFactory implements GameFactory{
	
	public ActionStrategy getActionStrategy(){
		return new NoActionStrategy();
	}
	public AttackStrategy getAttackStrategy(){
		return new AlphaAttackStrategy();
	}
	public BoardFactory getBoardFactory(){
		return new DeltaBoardFactory();
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