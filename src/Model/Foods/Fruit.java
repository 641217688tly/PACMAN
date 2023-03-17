package Model.Foods;

import Model.PACMAN_Game;
import Model.Player;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fruit extends Food {
    private boolean isEatable = true;

    public Fruit(int x, int y) {
        this.x = x;
        this.y = y;
        this.hitVolume = new Rectangle(x, y, Player.PLAYER_SIZE - 2, Player.PLAYER_SIZE - 2);
        this.score = 500;
        setEatable();
    }

    @Override
    public void beEaten() {
        for (int i = 0; i < PACMAN_Game.getFruitArrayList().size(); i++) {
            if (this == PACMAN_Game.getFruitArrayList().get(i)) {
                PACMAN_Game.getFruitArrayList().remove(i);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(PACMAN_Game.getStringMap().get((this.getY() - 20) / 32));
        stringBuilder.setCharAt(getX() / 32, 'O');
        PACMAN_Game.getStringMap().set((this.getY() - 20) / 32, stringBuilder.toString());
    }

    public void setEatable() {//This method ensures that the fruit disappears and appears on the screen
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                isEatable = !isEatable;
            }
        }, 2 * 1000, 5 * 1000);
    }

    public boolean isEatable() {
        return isEatable;
    }

}
