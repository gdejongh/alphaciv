package alphaciv;

public class BetaAging implements Aging {

	@Override
	public int newAge(int age) {
		if( age < -100){
			return age + 100;
		}
		else if(age == -100){
				return -1;				
			}
		else if(age==-1){
				return 1;
			}
		else if(age==1){
				return 50;
		}
		else if (age < 1750 ){
			return age + 50;
		}
		else if (age < 1900){
			return age + 25;
		}
		else if (age < 1970){
			return age + 5;
		}
		else {
			return age+1;
		}
	}

}
