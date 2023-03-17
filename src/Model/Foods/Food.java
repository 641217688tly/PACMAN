package Model.Foods;

import java.awt.*;

public abstract class Food {
/*
Common features:
Properties:
1. Bring your own score
2. There are collision boxes
3. Coordinate position

Function:
1. Can be eaten (the principle is to receive a stringMap, and then delete the point on the stringMap)
2. Getting eaten (return a point)
 */
    protected int x;
    protected int y;
    protected Rectangle hitVolume;
    protected int score;

    public abstract void beEaten();

    public int getScore(){
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitVolume() {
        return hitVolume;
    }
}
