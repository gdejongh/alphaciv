package alphaciv.domain;

public class EpsilonAttackStrategy implements AttackStrategy {
	private Dice dice;

	public EpsilonAttackStrategy(Dice dice){
		this.dice = dice;
	}
	
	public boolean attack(Tile attackerTile, Tile defenderTile, Board board){
		Unit attacker = attackerTile.getUnit();
		Unit defender = defenderTile.getUnit();
		if(defender == null){
			return true;
		}
		int attackingStrength = attacker.getAttackingStrength();
		for(Position p : board.getSurroundingPositions(attackerTile.getPosition())){
			Unit u = board.getUnitAt(p);
			if(u != null && u.getOwner() == attacker.getOwner()){
				attackingStrength += u.getAttackingStrength();
			}
		}
		attackingStrength = attackingStrength * dice.roll();
		
		int defendingStrength = defender.getDefensiveStrength();
		for(Position p : board.getSurroundingPositions(defenderTile.getPosition())){
			Unit u = board.getUnitAt(p);
			if(u != null && u.getOwner() == defender.getOwner()){
				defendingStrength += u.getDefensiveStrength();
			}
		}
		defendingStrength = defendingStrength * dice.roll();
		
		if(attackingStrength <= defendingStrength){
			return false;	
		}
		return true;
		
	}
	
	
}