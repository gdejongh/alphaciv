package alphaciv;

import java.util.ArrayList;

public class ZetaWinningCondition implements WinningCondition {

	@Override
	public Player getWinner(ArrayList<CityImpl> cities, int age, int numberOfRounds, int redAttacks, int blueAttacks) {
		if(numberOfRounds<21) {
			Player player = cities.get(0).getOwner();
			for (CityImpl city : cities){
				if(city.getOwner() != player) {
					return null;	
				}
			}
			return player;
		}
		else {
			if(redAttacks>2) {
				return Player.RED;
			}
			if(blueAttacks>2) {
				return Player.BLUE;
			}
			else return null;
		}
	}

}
