package Model;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private int x;
    private int y;
    private int birthplaceX;
    private int birthplaceY;
    private Rectangle hitVolume; //Collision volume (needs to be updated constantly with the latest collision volume/location)
    private boolean alive = true;
    private boolean isUp;
    private boolean isDown;
    private boolean isLeft;
    private boolean isRight = true;
    private int playerScore = 0;
    private int playerLives = 3;
    private int invincibleTime = 0;
    private int chanceOfIncreaseHealth = 1; //The player has only one chance to increase health
    public static final int PLAYER_SIZE = 32;


    public Player() {
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
                    this.x = j * 32;
                    this.y = i * 32 + 20;
                    this.birthplaceX = j * 32;
                    this.birthplaceY = i * 32 + 20;
                }
            }
        }
        hitVolume = new Rectangle(x, y, PLAYER_SIZE, PLAYER_SIZE);
        autoDecreaseInvincibleTime();
    }

    public void resetDestroyed() { //rebirth
        if (playerLives >= 0) {
            alive = true;
            this.x = birthplaceX;
            this.y = birthplaceY;
            hitVolume = new Rectangle(birthplaceX, birthplaceY, PLAYER_SIZE, PLAYER_SIZE);
        }
    }

    public void playerEat(PACMAN_Game game) {
        for (int i = 0; i < PACMAN_Game.getCoinArrayList().size(); i++) {
            if (this.getHitVolume().intersects(PACMAN_Game.getCoinArrayList().get(i).getHitVolume())) { //First determine if the player has collided with all coins
                playerScore = playerScore + PACMAN_Game.getCoinArrayList().get(i).getScore();
                PACMAN_Game.getCoinArrayList().get(i).beEaten();
                break;
            }
        }
        for (int i = 0; i < PACMAN_Game.getEnergyBallArrayList().size(); i++) {
            if (this.getHitVolume().intersects(PACMAN_Game.getEnergyBallArrayList().get(i).getHitVolume())) {
                PACMAN_Game.getEnergyBallArrayList().get(i).addInvincibleTime(this);//Increases the player's invincible time
                playerScore = playerScore + PACMAN_Game.getEnergyBallArrayList().get(i).getScore();
                PACMAN_Game.getEnergyBallArrayList().get(i).beEaten();
                break;
            }
        }
        for (int i = 0; i < PACMAN_Game.getFruitArrayList().size(); i++) {
            if (PACMAN_Game.getFruitArrayList().get(i).isEatable()) {
                StringBuilder stringBuilder = new StringBuilder(PACMAN_Game.getStringMap().get((PACMAN_Game.getFruitArrayList().get(i).getY() - 20) / 32));
                stringBuilder.setCharAt(PACMAN_Game.getFruitArrayList().get(i).getX() / 32, 'F');
                PACMAN_Game.getStringMap().set((PACMAN_Game.getFruitArrayList().get(i).getY() - 20) / 32, stringBuilder.toString());
                if (this.getHitVolume().intersects(PACMAN_Game.getFruitArrayList().get(i).getHitVolume())) {
                    playerScore = playerScore + PACMAN_Game.getFruitArrayList().get(i).getScore();
                    PACMAN_Game.getFruitArrayList().get(i).beEaten();
                    break;
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder(PACMAN_Game.getStringMap().get((PACMAN_Game.getFruitArrayList().get(i).getY() - 20) / 32));
                stringBuilder.setCharAt(PACMAN_Game.getFruitArrayList().get(i).getX() / 32, 'O');
                PACMAN_Game.getStringMap().set((PACMAN_Game.getFruitArrayList().get(i).getY() - 20) / 32, stringBuilder.toString());
            }
        }
        if (invincibleTime > 0) {
            if (game.ghost1.getHitVolume().intersects(this.hitVolume)) {
                game.ghost1.setAlive(); //Changed the ghost's state to death
                game.ghost1.addResurrectionTime(5); //Increased ghost resurrection time
                game.ghost1.resetDestroyed();//Let the ghost be eaten and reborn
                playerScore = playerScore + game.ghost1.getScores();
            }
        }
        if (invincibleTime > 0) {
            if (game.ghost2.getHitVolume().intersects(this.hitVolume)) {
                game.ghost2.setAlive();
                game.ghost2.addResurrectionTime(5);
                game.ghost2.resetDestroyed();
                playerScore = playerScore + game.ghost2.getScores();
            }
        }
        if (invincibleTime > 0) {
            if (game.ghost3.getHitVolume().intersects(this.hitVolume)) {
                game.ghost3.setAlive();
                game.ghost3.addResurrectionTime(5);
                game.ghost3.resetDestroyed();
                playerScore = playerScore + game.ghost3.getScores();
            }
        }
        if (invincibleTime > 0) {
            if (game.ghost4.getHitVolume().intersects(this.hitVolume)) {
                game.ghost4.setAlive();
                game.ghost4.addResurrectionTime(5);
                game.ghost4.resetDestroyed();
                playerScore = playerScore + game.ghost4.getScores();
            }
        }
    }

    public void move(int x1, int y1) {
        Rectangle newHitVolume = new Rectangle(x + x1, y + y1, PLAYER_SIZE, PLAYER_SIZE);
        if (x1 > 0) { //right
            isRight = true;
            isLeft = false;
            isDown = false;
            isUp = false;
            if ((x + x1 + 32) % 32 == 0) {
                hitVolume = newHitVolume;
                this.x = x + x1;
                this.y = y + y1;
            } else {
                Rectangle checkedRectangle = new Rectangle(((x + x1) / 32) * 32 + 32, y + y1, PLAYER_SIZE, PLAYER_SIZE);
                if (PACMAN_Game.getScreenBoundsList().contains(checkedRectangle)) {
                    hitVolume = newHitVolume;
                    this.x = x + x1;
                    this.y = y + y1;
                }
            }
        } else if (x1 < 0) {//left
            isRight = false;
            isLeft = true;
            isDown = false;
            isUp = false;
            if ((x + x1) % 32 == 0) {
                hitVolume = newHitVolume;
                this.x = x + x1;
                this.y = y + y1;
            } else {
                Rectangle checkedRectangle = new Rectangle(((x + x1) / 32) * 32, y + y1, PLAYER_SIZE, PLAYER_SIZE);
                if (PACMAN_Game.getScreenBoundsList().contains(checkedRectangle)) {
                    hitVolume = newHitVolume;
                    this.x = x + x1;
                    this.y = y + y1;
                }
            }
        } else if (y1 < 0) {//up
            isRight = false;
            isLeft = false;
            isDown = false;
            isUp = true;
            if ((y1 + y - 20) % 32 == 0) {
                hitVolume = newHitVolume;
                this.x = x + x1;
                this.y = y + y1;
            } else {
                Rectangle checkedRectangle = new Rectangle(x + x1, ((y + y1 - 20) / 32) * 32 + 20, PLAYER_SIZE, PLAYER_SIZE);
                if (PACMAN_Game.getScreenBoundsList().contains(checkedRectangle)) {
                    hitVolume = newHitVolume;
                    this.x = x + x1;
                    this.y = y + y1;
                }
            }
        } else if (y1 > 0) {//down
            isRight = false;
            isLeft = false;
            isDown = true;
            isUp = false;
            if (((y1 + y + 32) - 20) % 32 == 0) {
                hitVolume = newHitVolume;
                this.x = x + x1;
                this.y = y + y1;
            } else {
                Rectangle checkedRectangle = new Rectangle(x + x1, ((y + y1 - 20) / 32) * 32 + 20 + 32, PLAYER_SIZE, PLAYER_SIZE);
                if (PACMAN_Game.getScreenBoundsList().contains(checkedRectangle)) {
                    hitVolume = newHitVolume;
                    this.x = x + x1;
                    this.y = y + y1;
                }
            }
        }
    }

    public void increaseInvincibleTime(int time) {
        invincibleTime = invincibleTime + time;
    }

    public void autoDecreaseInvincibleTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (invincibleTime > 0) {
                    invincibleTime = invincibleTime - 1;
                } else {
                    //pass
                }
            }
        }, 0 * 1000, 1 * 1000);
    }

    public void increasePlayerLives() {
        if (this.playerScore >= 10000 && chanceOfIncreaseHealth == 1) {
            chanceOfIncreaseHealth = chanceOfIncreaseHealth - 1;
            playerLives = playerLives + 1;
        }
    }

    public void decreasePlayerLives() {
        playerLives = playerLives - 1;
    }

    public void setIsAlive() {
        alive = false;
    }

    //getMethods--------------------------------------------------------------------------------------------------------
    public int getX() { //gian x
        return x;
    }

    public int getY() { //gain y
        return y;
    }

    public int getInvincibleTime() {
        return invincibleTime;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Rectangle getHitVolume() {
        return hitVolume;
    }

    public boolean isAlive() { //Determine survival
        return alive;
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean isDown() {
        return isDown;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isRight() {
        return isRight;
    }

}
