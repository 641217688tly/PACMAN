//Student Name: LiYan Tao  UCD Number: 21207500
package Model;

import Display.PlayerListener;
import Model.Foods.Coin;
import Model.Foods.EnergyBall;
import Model.Foods.Fruit;
import Model.Ghosts.*;
import ucd.comp2011j.engine.Game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class PACMAN_Game implements Game {
    public static final int SCREEN_WIDTH = 512; //The screen width is 16 x 32
    public static final int SCREEN_HEIGHT = 789; //Screen height 24 * 32 + 20(score column)

    private static ArrayList<String> stringMap; //{Line1,Line2,... } text map
    private static ArrayList<Rectangle> screenBounds; //Store all the Rectangles that can be passed through the palace
    private static ArrayList<Coin> coinArrayList;
    private static ArrayList<Fruit> fruitArrayList;
    private static ArrayList<EnergyBall> energyBallArrayList;

    private boolean pause = true;
    private PlayerListener listener;
    private Player player;
    public Ghost1_Blinky ghost1;
    public Ghost2_Pinky ghost2;
    public Ghost3_Lnky ghost3;
    public Ghost4_Clyde ghost4;
    private Level level;

    public PACMAN_Game(PlayerListener listener) {
        this.listener = listener;
        startNewGame();
    }

    public static void initStringMap() {
        ArrayList<String> stringMap = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("map"))) {
            String semString;
            while ((semString = br.readLine()) != null) {
                stringMap.add(semString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        PACMAN_Game.stringMap = stringMap;
    }

    public static void setScreenBounds() {
        ArrayList<Rectangle> semRectangleList = new ArrayList<>();
        ArrayList<Coin> semCoinArrayList = new ArrayList<>();
        ArrayList<EnergyBall> semEnergyBallArrayList = new ArrayList<>();
        ArrayList<Fruit> semFruitArrayList = new ArrayList<>();
        for (int i = 0; i < 24; i++) { //y
            for (int j = 0; j < 16; j++) { //x
                if (stringMap.get(i).charAt(j) == 'W') {
                    //pass
                } else if (stringMap.get(i).charAt(j) == '.') {
                    semRectangleList.add(new Rectangle(j * 32, i * 32 + 20, 32, 32));

                    semCoinArrayList.add(new Coin(j * 32, i * 32 + 20));//coin
                } else if (stringMap.get(i).charAt(j) == '*') {
                    semRectangleList.add(new Rectangle(j * 32, i * 32 + 20, 32, 32));

                    semEnergyBallArrayList.add(new EnergyBall(j * 32, i * 32 + 20));//energy ball
                } else if (stringMap.get(i).charAt(j) == 'F') {
                    semRectangleList.add(new Rectangle(j * 32, i * 32 + 20, 32, 32));

                    semFruitArrayList.add(new Fruit(j * 32, i * 32 + 20));//fruit
                } else if (stringMap.get(i).charAt(j) == 'P') {
                    semRectangleList.add(new Rectangle(j * 32, i * 32 + 20, 32, 32));
                }
            }
        }
        PACMAN_Game.screenBounds = semRectangleList;
        PACMAN_Game.coinArrayList = semCoinArrayList;
        PACMAN_Game.energyBallArrayList = semEnergyBallArrayList;
        PACMAN_Game.fruitArrayList = semFruitArrayList;
    }

    private void movePlayer() {
        if (listener.isPressingLeft()) {
            player.move(-2, 0);
        } else if (listener.isPressingRight()) {
            player.move(+2, 0);
        }
        if (listener.isPressingUp()) {
            player.move(0, -2);
        } else if (listener.isPressingDown()) {
            player.move(0, +2);
        }
    }

    @Override
    public void startNewGame() {
        PACMAN_Game.initStringMap();
        PACMAN_Game.setScreenBounds();
        player = new Player();
        level = new Level();
        ghost1 = new Ghost1_Blinky();
        ghost2 = new Ghost2_Pinky();
        ghost3 = new Ghost3_Lnky();
        ghost4 = new Ghost4_Clyde();
        level.setLevelDifficulty(this);
    }

    @Override
    public void moveToNextLevel() {
        level.addLevel();
        level.setLevelDifficulty(this);
        initStringMap();
        setScreenBounds();
        ghost1.resetDestroyed();
        ghost2.resetDestroyed();
        ghost3.resetDestroyed();
        ghost4.resetDestroyed();
        player.resetDestroyed();
        setPause();
    }

    @Override
    public void resetDestroyedPlayer() {
        player.resetDestroyed();
    }

    public void setPause() {
        pause = true;
    }

    @Override
    public void checkForPause() {
        if (listener.hasPressedPause()) {
            pause = !pause;
            listener.resetPause();
        }
    }

    @Override
    public void updateGame() {
        if (!isPaused()) {
            movePlayer();
            player.playerEat(this);
            player.increasePlayerLives(); //Gain a life when a player scores more than 10,000 points

            ghost1.refreshNextNode(this.player);
            ghost1.trackingPlayer(this.player);
            ghost1.ghostEat(this.player, this);
            ghost1.escapePlayer(this.player);

            ghost2.refreshNextNode(this.player);
            ghost2.trackingPlayer(this.player);
            ghost2.escapePlayer(this.player);
            ghost2.ghostEat(this.player, this);

            ghost3.refreshNextNode(this.player);
            ghost3.trackingPlayer(this.player);
            ghost3.escapePlayer(this.player);
            ghost3.ghostEat(this.player, this);

            ghost4.refreshNextNode(this.player);
            ghost4.trackingPlayer(this.player);
            ghost4.escapePlayer(this.player);
            ghost4.ghostEat(this.player, this);
        }
    }

    //getMethods--------------------------------------------------------------------------------------------------------
    @Override
    public boolean isGameOver() {
        if (player.getPlayerLives() == 0 || level.getCurrentLevel() > 14) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    @Override
    public boolean isLevelFinished() { //Is the level over
        return level.isLevelOver();
    }

    @Override
    public boolean isPaused() {
        return pause;
    }

    @Override
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    @Override
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    @Override
    public int getPlayerScore() {
        return player.getPlayerScore();
    }

    @Override
    public int getLives() {
        return player.getPlayerLives();
    }

    public Player getPlayer() {
        return player;
    }

    public static ArrayList<String> getStringMap() {
        return PACMAN_Game.stringMap;
    }

    public static ArrayList<Rectangle> getScreenBoundsList() {
        return PACMAN_Game.screenBounds;
    }

    public static ArrayList<Coin> getCoinArrayList() {
        return PACMAN_Game.coinArrayList;
    }

    public static ArrayList<Fruit> getFruitArrayList() {
        return PACMAN_Game.fruitArrayList;
    }

    public static ArrayList<EnergyBall> getEnergyBallArrayList() {
        return PACMAN_Game.energyBallArrayList;
    }

}
