package alphaciv.domain;
import java.util.*;
public class RandomDice implements Dice {
	
	Random rand = new Random();
	
	public int roll(){
		return rand.nextInt(6) + 1;
	}
	
}