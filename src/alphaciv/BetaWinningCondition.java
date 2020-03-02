package alphaciv;

import java.util.ArrayList;

public class BetaWinningCondition implements WinningCondition {

	@Override
	public Player getWinner(ArrayList<CityImpl> cities, int age, int numberOfRounds, int redAttacks, int blueAttacks) {
		Player player = cities.get(0).getOwner();
		for (CityImpl city : cities){
			if(city.getOwner() != player) {
				return null;	
			}
		}
		return player;
	}

}
