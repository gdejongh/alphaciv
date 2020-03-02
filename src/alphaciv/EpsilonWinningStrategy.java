package alphaciv;

import java.util.ArrayList;

public class EpsilonWinningStrategy implements WinningCondition {

	@Override
	public Player getWinner(ArrayList<CityImpl> cities, int age, int numberOfRounds, int redAttacks, int blueAttacks) {
		if(redAttacks>2) {
			return Player.RED;
		}
		if(blueAttacks>2) {
			return Player.BLUE;
		}
		else return null;
	}
}


