package alphaciv;

public class AlphaAging implements Aging {

	@Override
	public int newAge(int age) {
		return age+100;
	}

}
