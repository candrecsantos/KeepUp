package org.academiadecodigo.timemaravilha.grid.position;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.timemaravilha.grid.Direction;

public interface GridPosition {


    public int getCol();
    public int getRow();

    public void move(Direction dir, int units);

    public void setColor(Color color);

    public void show();
    public void hide();

}