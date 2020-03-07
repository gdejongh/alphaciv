package alphaciv.domain;

import java.util.*;

public interface WinningStrategy {
	
	public Player getWinner(Board pBoard, int age, HashMap<Player, Integer> wins);
	
}
