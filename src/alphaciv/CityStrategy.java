package alphaciv;

public interface CityStrategy {
	public void setUpCity(CityImpl city, GameImpl gameImpl);

	public int setProdIncrease();
}
