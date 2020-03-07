package hotciv.framework;
import java.util.List;
import java.nio.file.*; 
import java.io.File;
import java.nio.charset.StandardCharsets;

public class DeltaBoardFactory implements BoardFactory{
	
	private static final String MOUNTAIN = "M";
	private static final String PLAIN = "P";
	private static final String FOREST = "T";
	private static final String HILL = "H";
	private static final String OCEAN = "O";
	
	public Board createBoard(){
		
		Board board = new Board(GameConstants.WORLDSIZE, GameConstants.WORLDSIZE);
		
		try{
			//Thanks to https://stackoverflow.com/a/14169729 for the one-liner to read a txt file!
			List<String> lines = Files.readAllLines(Paths.get("DeltaBoard.txt"), StandardCharsets.UTF_8);

			for(int row = 0; row < lines.size(); row++){
				String[] tileTerrainTypes = lines.get(row).split(" ");
				for(int col = 0; col < tileTerrainTypes.length; col++){
					String type = tileTerrainTypes[col];
					switch (type.trim()){
						case PLAIN:
							board.placeNewTile(new Position(row, col), GameConstants.PLAINS);
							break;
						case MOUNTAIN:
							board.placeNewTile(new Position(row, col), GameConstants.MOUNTAINS);
							break;
						case OCEAN:
							board.placeNewTile(new Position(row, col), GameConstants.OCEANS);
							break;
						case HILL:
							board.placeNewTile(new Position(row, col), GameConstants.HILLS);
							break;
						case FOREST:
							board.placeNewTile(new Position(row, col), GameConstants.FOREST);
							break;
						default:
							break;
					}
				}
			}
		
			board.placeNewCity(new Position(8,12), Player.RED);
			board.placeNewCity(new Position(4,5), Player.BLUE);
		
			return board;
			
		}catch(Exception ex){
			System.out.println("Failed creating DeltaBoard, here's the error message");
			System.out.println(ex.toString());
			return null;
		}
	}
	
	
	
	
	
	
}