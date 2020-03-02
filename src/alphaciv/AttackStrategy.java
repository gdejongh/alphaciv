package alphaciv;

public interface AttackStrategy {
	public boolean attackUnit(UnitImpl attackingUnit, UnitImpl defendingUnit, Position there, GameImpl gameImpl);
}
