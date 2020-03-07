package alphaciv.domain;

public class NoActionStrategy implements ActionStrategy{
	
	public Tile performActionAt(Tile pTile){
		return pTile;
	}
	
}