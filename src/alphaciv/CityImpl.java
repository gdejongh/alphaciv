package alphaciv;

public class CityImpl implements City {

	private Position position;
	private Player owner;
	private int size =1;
	private int production = 0;
	private int food = 0;
	private String productionFocus;
	private String workFocus;
	
	public CityImpl(Position po, Player p, String prod){
		position = po;
		owner = p;
		productionFocus = prod;
	}
	
	public void setOwner(Player newOwner){
		owner = newOwner;
	}
	
	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	public void setSize(int size){
		this.size = size;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	public void setProductionAmount(int p){
		production = p;
	}
	
	public int getProductionAmount(){
		return production;
	}
	
	@Override
	public String getProduction() {
		return productionFocus;
	}

	public void setWorkforceFocus(String balance){
		workFocus = balance;
	}
	
	@Override
	public String getWorkforceFocus() {
		return workFocus;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position p){
		position = p;
	}

	public void setProductionFocus(String unitType) {
		productionFocus = unitType;
		
	}
	
	public int getFood(){
		return food;
	}
	
	public void setFood(int food){
		this.food = food;
	}
}
