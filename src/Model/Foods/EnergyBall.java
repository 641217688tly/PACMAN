package Model.Foods;

import Model.PACMAN_Game;
import Model.Player;

import java.awt.*;

public class EnergyBall extends Food {

    public EnergyBall(int x, int y) {
        this.x = x;
        this.y = y;
        this.hitVolume = new Rectangle(x, y, 24, 24);
        this.score = 50;
    }

    @Override
    public void beEaten() {
        for (int i = 0; i < PACMAN_Game.getEnergyBallArrayList().size(); i++) {
            if (this == PACMAN_Game.getEnergyBallArrayList().get(i)) {
                PACMAN_Game.getEnergyBallArrayList().remove(i);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(PACMAN_Game.getStringMap().get((this.getY() - 20) / 32));
        stringBuilder.setCharAt(getX() / 32, 'O');
        PACMAN_Game.getStringMap().set((this.getY() - 20) / 32, stringBuilder.toString());
    }

    public void addInvincibleTime(Player player) {
        player.increaseInvincibleTime(5);
    }
}
