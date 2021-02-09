package org.academiadecodigo.timemaravilha.entities;

import org.academiadecodigo.timemaravilha.grid.Direction;
import org.academiadecodigo.timemaravilha.grid.position.GridPosition;

public class Mask extends DespawnableEntity{
    public Mask (GridPosition position, int dimensionX, int dimensionY) {
        super(position,dimensionX,dimensionY);
    }

    @Override
    public void move() {
        if (!isDead()) {
            Direction[] directions = Direction.values();
            int random = (int) (Math.random() * directions.length);
            Direction direction = directions[random];
            getPosition().move(direction, 1);
        }
    }

    @Override
    public boolean collidedWith(Entity other) {
        return getCollisionBox().collidedWith(other.getCollisionBox());

    }

    public void collide(Entity other){
        if(other instanceof Player){
            despawn();
            System.out.println("Mask collided with Player");

        } else if (other instanceof Covidinhos){
            despawn();
            System.out.println("Mask Collided with Covidinhos");

        }else{
            System.out.println("Mask Collided with another Mask");
        }
    }
}