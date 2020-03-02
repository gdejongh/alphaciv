package alphaciv;

import java.util.Random;

public class EpsilonAttackStrategy implements AttackStrategy {

	@Override
	public boolean attackUnit(UnitImpl attackingUnit, UnitImpl defendingUnit, Position there, GameImpl game) {
		Tile tile = game.getTileAt(there);
		int attackingStrength = attackingUnit.getAttackingStrength();
		int defendingStrength = defendingUnit.getDefensiveStrength();
		if(tile.getTypeString().equals(GameConstants.FOREST)||tile.getTypeString().equals(GameConstants.HILLS)){
			attackingStrength*=2;
			defendingStrength*=2;
		}
		else if(game.getCityAt(there)!=null){
			if(game.getCityAt(there).getOwner().equals(attackingUnit.getOwner())){
				attackingStrength*=3;
			}
			else{
				defendingStrength*=3;
			}
		}

		Position p = new Position(there.r, there.c+1);
		if(game.getUnitAt(p)!=null){
			if(game.getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
				attackingStrength++;
			}
			else{
				defendingStrength++;
			}
		}
		
		p = new Position(there.r, there.c-1);
		if(game.getUnitAt(p)!=null){
			if(game.getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
				attackingStrength++;
			}
			else{
				defendingStrength++;
			}
		}
		
		for(int i=0;i<3;i++){
			p = new Position(there.r+1, there.c+1-i);
			if(game.getUnitAt(p)!=null){
				if(game.getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength++;
				}
				else{
					defendingStrength++;
				}
			}
			
		}
		
		for(int i=0;i<3;i++){
			p = new Position(there.r-1, there.c+1-i);
			if(game.getUnitAt(p)!=null){
				if(game.getUnitAt(p).getOwner().equals(attackingUnit.getOwner())){
					attackingStrength++;
				}
				else{
					defendingStrength++;
				}
			}
			
		}
		
		Random rand = new Random();
		int d1 = rand.nextInt(6) +1;
		System.out.println(d1);
		int d2 = rand.nextInt(6) +1;
		System.out.println(d2);
		return attackingStrength*d1 > defendingStrength*d2;
	}

}
