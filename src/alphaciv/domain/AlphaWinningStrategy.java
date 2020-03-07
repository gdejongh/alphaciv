package alphaciv.domain;
import java.util.*;

public class AlphaWinningStrategy implements WinningStrategy {

	public Player getWinner(Board pBoard, int age, HashMap<Player, Integer> wins){
		if(age == -3000){
			return Player.RED;
		} else{
			return null;
			
		}
	}
}
