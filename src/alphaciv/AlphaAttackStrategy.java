package alphaciv;

public class AlphaAttackStrategy implements AttackStrategy {

	@Override
	public boolean attackUnit(UnitImpl attackingUnit, UnitImpl defendingUnit, Position there, GameImpl gameImpl) {
		return true;
	}

}
