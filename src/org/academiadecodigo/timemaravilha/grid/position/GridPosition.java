package org.academiadecodigo.timemaravilha.grid.position;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.timemaravilha.grid.Direction;

public interface GridPosition {


    public int getCol();
    public int getRow();
    public int getDimX();
    public int getDimY();

    public boolean onBorder();

    public void loadNextFrame(String name);
    public void flip();

    public void setDimension(int dimX, int dimY);

    public void move(Direction dir, int units);

    public void setColor(Color color);

    public void show();
    public void hide();

    public boolean equals(GridPosition other);

}
