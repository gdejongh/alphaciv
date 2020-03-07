package alphaciv.domain;

public class CityImpl implements City{
	
	private Player owner;
	private int population;
	private String production;
	private int resourcesProduced;
	private int foodCount;
	private String workForceFocus;
	
	public CityImpl(Player pOwner) {
		owner = pOwner;
		population = GameConstants.STARTING_POPULATION_SIZE;
		production = GameConstants.ARCHER;
		resourcesProduced = 0;
	}

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	@Override
	public void setOwner(Player player) {
		// TODO Auto-generated method stub
		owner = player;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return population;
	}

	@Override
	public String getProduction() {
		// TODO Auto-generated method stub
		return production;
	}

	@Override
	public String getWorkforceFocus() {
		// TODO Auto-generated method stub
		return workForceFocus;
	}
	
	@Override
	public void changeProductionInCity(String unitToProduce){
		production = unitToProduce;
	}
	
	@Override
	public int getProductionCount(){
		return resourcesProduced;
	}
	
	@Override
	public void setProductionCount(int newCount){
		resourcesProduced = newCount;
	}
	
	@Override
	public void spendProduction(int price){
		resourcesProduced -= price;
	}
	 
	@Override
	public void produce(){
		resourcesProduced += GameConstants.PRODUCTION_PER_ROUND;
	}
	
	@Override
	public void setWorkForceFocus(String focus){
		workForceFocus = focus;
	}
	
	@Override
	public int getFoodCount(){
		return foodCount;
	}

	@Override
	public void setFoodCount(int count){
		foodCount = count;
	}

}
