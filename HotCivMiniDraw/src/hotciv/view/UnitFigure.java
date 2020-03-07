package hotciv.view;

import java.awt.*;

import minidraw.standard.ImageFigure;

import hotciv.framework.*;

public class UnitFigure extends ImageFigure {
  private Unit unit;
  private Point position;

  public UnitFigure(Unit u, Point p) {
    super(u.getTypeString(), p); 
    position = p;
    unit = u;
  }
  public void draw(Graphics g) {
    g.setColor(GfxConstants.getColorForPlayer(unit.getOwner()));
    super.draw(g);

  }
}