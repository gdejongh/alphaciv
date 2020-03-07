package alphaciv.domain;

public class SemiCivFactory implements GameFactory {

    public ActionStrategy getActionStrategy(){
        return new GammaActionStrategy();
    }
	public AttackStrategy getAttackStrategy(){
        return new EpsilonAttackStrategy(new RandomDice());
    }
	public BoardFactory getBoardFactory(){
        return new DeltaBoardFactory();
    }
	public WinningStrategy getWinningStrategy(){
        return new EpsilonWinningStrategy();
    }
	public AgingStrategy getAgingStrategy(){
        return new BetaAgingStrategy();
    }
	public ProductionStrategy getProductionStrategy(){
        return new EtaProductionStrategy();
    }
}