package alphaciv.domain;
import java.util.*;

public class EpsilonWinningStrategy implements WinningStrategy {

	public Player getWinner(Board pBoard, int age, HashMap<Player, Integer> wins){
		for(Player p: wins.keySet()){
			if(wins.get(p) >= 3){
				return p;
			}
		}
		return null;
	}
}