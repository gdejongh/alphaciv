package alphaciv.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class thoroughly tests the UnitImpl class.
 * 
 * @author GabeDeJongh
 *
 */
public class TestWorldLayout {

	private GameImpl game;
	
	@Before
	public void setUp() throws Exception {
	    game = new GameImpl();
	}
	
	@Test
	public void shouldHaveOceanAt10() {
		Position p = new Position(1,0);
		assertEquals(game.getTileAt(p).getTypeString(), GameConstants.OCEANS);
	}
	
	@Test
	public void shouldHaveHillAt01() {
		Position p = new Position(0,1);
		assertEquals(game.getTileAt(p).getTypeString(), GameConstants.HILLS);
	}
	
	@Test
	public void shouldHaveMountainAt11() {
		Position p = new Position(1,1);
		assertEquals(game.getTileAt(p).getTypeString(), GameConstants.MOUNTAINS);
	}
	
	@Test
	public void shouldHavePlainsInCorners(){
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,15);
		Position p3 = new Position(15,15);
		Position p4 = new Position(15,0);
		assertEquals(game.getTileAt(p1).getTypeString(), GameConstants.PLAINS);
		assertEquals(game.getTileAt(p2).getTypeString(), GameConstants.PLAINS);
		assertEquals(game.getTileAt(p3).getTypeString(), GameConstants.PLAINS);
		assertEquals(game.getTileAt(p4).getTypeString(), GameConstants.PLAINS);
	}
	
	@Test
	public void boardIs16By16(){
		assertEquals(16, game.getBoardSize());
	}
	
	// Tests for DeltaCiv Layout
	
	@Test
	public void shouldSetUpDeltaBoardCorrectly(){
		DeltaBoardFactory factory = new DeltaBoardFactory();
		Board board = factory.createBoard();
		assertEquals(16,board.getSize());
		assertEquals(Player.RED, board.getCityAt(new Position(8,12)).getOwner());
		assertEquals(Player.BLUE, board.getCityAt(new Position(4,5)).getOwner());
		assertEquals(GameConstants.OCEANS, board.getTileAt(new Position(0,0)).getTypeString());
		assertEquals(GameConstants.MOUNTAINS, board.getTileAt(new Position(0,5)).getTypeString());
		assertEquals(GameConstants.OCEANS, board.getTileAt(new Position(0,0)).getTypeString());
		assertEquals(GameConstants.HILLS, board.getTileAt(new Position(1,3)).getTypeString());
		assertEquals(GameConstants.FOREST, board.getTileAt(new Position(1,9)).getTypeString());
		assertEquals(GameConstants.OCEANS, board.getTileAt(new Position(8,6)).getTypeString());

	}
}
