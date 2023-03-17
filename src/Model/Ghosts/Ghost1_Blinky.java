package Model.Ghosts;

import Model.PACMAN_Game;

import java.awt.*;

public class Ghost1_Blinky extends Ghost {

    public Ghost1_Blinky() {
        for (int i = 0; i < 24; i++) { //y
            for (int j = 0; j < 16; j++) { //x
                if (PACMAN_Game.getStringMap().get(i).charAt(j) == 'W') {
                    //pass
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == '.') {
                    //pass
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == '*') {
                    //pass
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == 'F') {
                    //pass
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == 'P') {
                    //pass
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == 'G') {
                    x = j * 32;
                    y = (i * 32 - 32) + 20;
                    birthplaceX = j * 32;
                    birthplaceY = (i * 32 - 32) + 20;
                }
            }
        }
        hitVolume = new Rectangle(x, y, Ghost_SIZE, Ghost_SIZE);
        speed = 1;
        isActive = true;
    }


}
