package hotciv.view;

import java.awt.*;

import minidraw.standard.ImageFigure;

import hotciv.framework.*;

public class TurnFigure extends ImageFigure {
  private Player currentPlayer;
  private Point position = new Point();

  public TurnFigure(String playerString) {
    super(playerString + "shield", new Point(GfxConstants.TURN_SHIELD_X,
                                             GfxConstants.TURN_SHIELD_Y)); 
 
    currentPlayer = playerString == "red" ? Player.RED : Player.BLUE;
  }
  public void draw(Graphics g) {
    super.draw(g);
  }
  public void setPlayerInTurn(Player newPlayer) {
    willChange();
    currentPlayer = newPlayer;
	String playerString = currentPlayer == Player.RED ? "red" : "blue";
	set(playerString + "shield", new Point(GfxConstants.TURN_SHIELD_X,
                                             GfxConstants.TURN_SHIELD_Y));
    changed();
  }
}
