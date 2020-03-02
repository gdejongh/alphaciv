package alphaciv;

import java.util.ArrayList;

public interface WinningCondition {
public Player getWinner(ArrayList<CityImpl> cities, int age, int numberOfRounds, int redAttacks, int blueAttacks);
}
