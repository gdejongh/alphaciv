package alphaciv;

import java.util.ArrayList;

public class AlphaWinningCondition implements WinningCondition {

	/**
	 * If age is -3000, Red wins
	 */
	public Player getWinner(ArrayList<CityImpl> cities, int age, int numberOfRounds, int redAttacks, int blueAttacks) {
		if(age == -3000){
			return Player.RED;
		} else {
			return null;
		}
	}

}
