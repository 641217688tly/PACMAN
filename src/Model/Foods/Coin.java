package Model.Foods;

import Model.PACMAN_Game;

import java.awt.*;

public class Coin extends Food {

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        this.hitVolume = new Rectangle(x, y, 10, 10);
        this.score = 10;
    }

    @Override
    public void beEaten() {
        for (int i = 0; i < PACMAN_Game.getCoinArrayList().size(); i++) {
            if (this == PACMAN_Game.getCoinArrayList().get(i)) {
                PACMAN_Game.getCoinArrayList().remove(i);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(PACMAN_Game.getStringMap().get((this.getY() - 20) / 32));
        stringBuilder.setCharAt(getX() / 32, 'O');
        PACMAN_Game.getStringMap().set((this.getY() - 20) / 32, stringBuilder.toString());
    }
}
