package alphaciv;

public class AlphaCityStrategy implements CityStrategy {

	@Override
	public void setUpCity(CityImpl city, GameImpl game) {
		// do nothing additional
	}

	@Override
	public int setProdIncrease() {
		return 6;
	}

}
