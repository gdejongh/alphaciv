package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Test the TextFigure to display age in
 * the status panel.
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class PlayGame {
  
  public static void main(String[] args) {

    //Game game = new StubGame1();
	Game game = new GameImpl(new GameImpl());
    DrawingEditor editor = 
      new MiniDrawApplication( "Click the turn shield to end your turn",  
                               new HotCivFactory3(game) );
    editor.open();
    TextFigure tf = new TextFigure("4000 BC", 
                                   new Point(GfxConstants.AGE_TEXT_X,
                                             GfxConstants.AGE_TEXT_Y) );
    editor.drawing().add(tf);
	  
	String currentPlayerStr = game.getPlayerInTurn() == Player.RED ? "red" : "blue";
	TurnFigure turnFig = new TurnFigure(currentPlayerStr);
    editor.drawing().add(turnFig);
	  
	game.addObserver(new TurnObserver(turnFig, tf));
	  
	Position p;  
	for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        int xpos = GfxConstants.getXFromColumn(c);
        int ypos = GfxConstants.getYFromRow(r);
       
		City city = game.getCityAt(p);
		if(city != null){
			CityFigure cf = new CityFigure(city, new Point(xpos, ypos));
			editor.drawing().add(cf);
		}
		  
		Unit unit = game.getUnitAt(p);
		if(unit != null){
			UnitFigure uf = new UnitFigure(unit, new Point(xpos, ypos));
			editor.drawing().add(uf);
		}
      }
    }
	  
	
	  
    editor.setTool( new EndTurnTool(game, editor) );

  }
}

class ChangeAgeTool extends NullTool {
  private TextFigure textFigure;
  public ChangeAgeTool(TextFigure tf) {
    textFigure = tf;
  }
  int count = 0;
  public void mouseDown(MouseEvent e, int x, int y) {
    count++;
    textFigure.setText( ""+(4000-count*100)+" BC" );
	
  }
}

//class EndTurnTool extends SelectionTool {
//  private Game game;
//  public EndTurnTool(Game g, DrawingEditor e) {
//	super(e);
//    game = g;
//  }
//  int count = 0;
//  public void mouseDown(MouseEvent e, int x, int y) {
//    count++;
//	super.mouseDown(e, x, y);
//	if(draggedFigure instanceof TurnFigure){
//    	game.endOfTurn();
//	}
//	
//  }
//}

class EndTurnTool extends AbstractTool implements Tool {
  private Game game;
  private Figure clickedFigure;
  private DrawingEditor editor;
  public EndTurnTool(Game g, DrawingEditor e) {
	super(e);
    game = g;
  }
  int count = 0;
  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    count++;
	Drawing model = editor().drawing();

    model.lock();

    clickedFigure = model.findFigure(e.getX(), e.getY());

  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y){
	if(clickedFigure instanceof TurnFigure){
    	game.endOfTurn();
	}
	editor().drawing().unlock();
    clickedFigure = null;
  }
}

class HotCivFactory2 implements Factory {
  private Game game;
  public HotCivFactory2(Game g) { game = g; }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "hotciv-background");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}

class TurnObserver implements GameObserver{
	
	private TurnFigure turnFigure;
	private TextFigure ageText;
	
	public TurnObserver(TurnFigure turnFigure, TextFigure ageText){
		this.turnFigure = turnFigure;
		this.ageText = ageText;
	}
	/** invoked every time some change occurs on a position
   * in the world - a unit disappears or appears, a
   * city appears, a city changes player color, or any
   * other event that requires the GUI to redraw the
   * graphics on a particular position.
   * @param pos the position in the world that has changed state
   */
  public void worldChangedAt(Position pos){
	  
  }

  /** invoked just after the game's end of turn is called
   * to signal the new "player in turn" and world age state.
   * @param nextPlayer the next player that may move units etc.
   * @param age the present age of the world
   */
  public void turnEnds(Player nextPlayer, int age){
	  turnFigure.setPlayerInTurn(nextPlayer);
	  String ageString = "";
	  if(age < 0){
		  ageString = Integer.toString(age * -1) + " BC";
	  } else {
		  ageString = Integer.toString(age) + " AD";
	  }
	  ageText.setText(ageString);
  }
    
  /** invoked whenever the user changes focus to another
   * tile (for inspecting the tile's unit and city
   * properties.)
   * @param position the position of the tile that is
   * now inspected/has focus.
   */
  public void tileFocusChangedAt(Position position){
	  
  }
}