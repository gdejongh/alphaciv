package alphaciv;

public class EtaCityStrategy implements CityStrategy {

	@Override
	public void setUpCity(CityImpl city, GameImpl game) {
		if(city.getProduction().equals(GameConstants.foodFocus)){
			int numberOfPlains = 0;
			int numberOfOceans = 0;
			Position there = city.getPosition();

			if(game.getTileAt(new Position(there.r, there.c+1)).getTypeString().equals(GameConstants.PLAINS)){
				numberOfPlains++;
			}

			if(game.getTileAt(new Position(there.r, there.c-1)).getTypeString().equals(GameConstants.PLAINS)){
				numberOfPlains++;
			}

			for(int i=0;i<3;i++){
				if(game.getTileAt(new Position(there.r+1, there.c+1-i)).getTypeString().equals(GameConstants.PLAINS)){
					numberOfPlains++;
				}
			}

			for(int i=0;i<3;i++){
				if(game.getTileAt(new Position(there.r-1, there.c+1-i)).getTypeString().equals(GameConstants.PLAINS)){
					numberOfPlains++;
				}
			}

			if(numberOfPlains>city.getSize()-1){
				city.setFood((city.getSize()-1)*3 + city.getFood());
			} else{
				if(game.getTileAt(new Position(there.r, there.c+1)).getTypeString().equals(GameConstants.OCEANS)){
					numberOfOceans++;
				}

				if(game.getTileAt(new Position(there.r, there.c-1)).getTypeString().equals(GameConstants.OCEANS)){
					numberOfOceans++;
				}

				for(int i=0;i<3;i++){
					if(game.getTileAt(new Position(there.r+1, there.c+1-i)).getTypeString().equals(GameConstants.OCEANS)){
						numberOfOceans++;
					}
				}

				for(int i=0;i<3;i++){
					if(game.getTileAt(new Position(there.r-1, there.c+1-i)).getTypeString().equals(GameConstants.OCEANS)){
						numberOfOceans++;
					}
				}

				if(numberOfOceans+numberOfPlains< city.getSize()-1){
					city.setFood(numberOfPlains*3+ numberOfOceans + city.getFood());
				} else{
					int howManyOceans = numberOfPlains+numberOfOceans-city.getSize()-1;
					city.setFood(numberOfPlains*3+ howManyOceans + city.getFood());
				}
			}
		} else{
			int numberOfForest = 0;
			int numberOfHills = 0;
			int numberOfMountains = 0;
			Position there = city.getPosition();

			if(game.getTileAt(new Position(there.r, there.c+1)).getTypeString().equals(GameConstants.FOREST)){
				numberOfForest++;
			}

			if(game.getTileAt(new Position(there.r, there.c+1)).getTypeString().equals(GameConstants.FOREST)){
				numberOfForest++;
			}

			if(game.getTileAt(new Position(there.r, there.c-1)).getTypeString().equals(GameConstants.FOREST)){
				numberOfForest++;
			}

			for(int i=0;i<3;i++){
				if(game.getTileAt(new Position(there.r+1, there.c+1-i)).getTypeString().equals(GameConstants.FOREST)){
					numberOfForest++;
				}
			}

			for(int i=0;i<3;i++){
				if(game.getTileAt(new Position(there.r-1, there.c+1-i)).getTypeString().equals(GameConstants.FOREST)){
					numberOfForest++;
				}
			}

			if(numberOfForest>city.getSize()-1){
				city.setProductionAmount(((city.getSize()-1)*3 + city.getProductionAmount()));
			} else{
				if(game.getTileAt(new Position(there.r, there.c+1)).getTypeString().equals(GameConstants.HILLS)){
					numberOfHills++;
				}

				if(game.getTileAt(new Position(there.r, there.c-1)).getTypeString().equals(GameConstants.HILLS)){
					numberOfHills++;
				}

				for(int i=0;i<3;i++){
					if(game.getTileAt(new Position(there.r+1, there.c+1-i)).getTypeString().equals(GameConstants.HILLS)){
						numberOfHills++;
					}
				}

				for(int i=0;i<3;i++){
					if(game.getTileAt(new Position(there.r-1, there.c+1-i)).getTypeString().equals(GameConstants.HILLS)){
						numberOfHills++;
					}
				}

				if(numberOfForest+numberOfHills>city.getSize()-1){
					int howManyHills = numberOfForest+numberOfHills-city.getSize();
					city.setProductionAmount((numberOfForest*3 + howManyHills*2 + city.getProductionAmount()));
				} else{
					if(game.getTileAt(new Position(there.r, there.c+1)).getTypeString().equals(GameConstants.MOUNTAINS)){
						numberOfMountains++;
					}

					if(game.getTileAt(new Position(there.r, there.c-1)).getTypeString().equals(GameConstants.MOUNTAINS)){
						numberOfMountains++;
					}

					for(int i=0;i<3;i++){
						if(game.getTileAt(new Position(there.r+1, there.c+1-i)).getTypeString().equals(GameConstants.MOUNTAINS)){
							numberOfMountains++;
						}
					}

					for(int i=0;i<3;i++){
						if(game.getTileAt(new Position(there.r-1, there.c+1-i)).getTypeString().equals(GameConstants.MOUNTAINS)){
							numberOfMountains++;
						}
					}

					if(numberOfForest+numberOfHills+numberOfMountains>city.getSize()-1){
						int howManyMountains = numberOfForest+numberOfHills+numberOfMountains-city.getSize()-1;
						city.setProductionAmount(numberOfForest*3 + numberOfHills*2+ howManyMountains + city.getProductionAmount());
					}
					else{
						city.setProductionAmount(numberOfForest*3 + numberOfHills*2+ numberOfMountains + city.getProductionAmount());
					}
				}
			}
		}
		if(city.getSize()==9){

		} else{
			int food = city.getFood();
			if(food>5+city.getSize()*3){
				city.setSize(city.getSize()+1);
				city.setFood(0);
			}
		}
	}

	@Override
	public int setProdIncrease() {
		return 1;
	}

}
