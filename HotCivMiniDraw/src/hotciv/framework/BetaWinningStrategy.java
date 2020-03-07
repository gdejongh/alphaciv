package hotciv.framework;
import java.util.*;

public class BetaWinningStrategy implements WinningStrategy {
	public Player getWinner(Board pBoard, int age, HashMap<Player, Integer> wins) {
		boolean blueHasCity = false;
		boolean redHasCity = false;
		for (int row = 0; row < pBoard.getSize(); row++) {
			for (int column = 0; column < pBoard.getSize(); column++) {
				Tile tile = pBoard.getTileAt(new Position(row, column));
				City city = tile.getCity();
				if (city != null) {
					if (city.getOwner() == Player.RED) {
						redHasCity = true;
					}
					if (city.getOwner() == Player.BLUE) {
						blueHasCity = true;
					}
					if (blueHasCity && redHasCity) {
						return null;
					}
				}
			}
		}
		return blueHasCity ? Player.BLUE : Player.RED;
	}
}
