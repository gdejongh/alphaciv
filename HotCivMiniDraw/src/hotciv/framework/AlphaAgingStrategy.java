package hotciv.framework;

public class AlphaAgingStrategy implements AgingStrategy {
	public int ageWorld(int currentAge){
		return currentAge + 100;
	}
}
