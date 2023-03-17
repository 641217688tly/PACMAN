package Model.Ghosts;

import Model.Ghosts.BFS.BreadthFirstSearch;
import Model.Ghosts.BFS.Node;
import Model.PACMAN_Game;
import Model.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Ghost {
    protected int x;
    protected int y;
    protected int birthplaceX;
    protected int birthplaceY;
    protected Rectangle hitVolume;
    protected boolean alive = true;
    protected int resurrectionTime = 0;
    protected int scores = 200;
    protected boolean isActive;
    protected int speed;
    protected Node nextStep;
    public static final int Ghost_SIZE = 32;

    public Ghost() {
        autoDecreaseInvincibleTime();
    }

    public void ghostEat(Player player,PACMAN_Game game) {
        if (player.getInvincibleTime() == 0) { // The player is not invincible
            if (this.getHitVolume().intersects(player.getHitVolume())) {// The player collides with the ghost
                game.setPause();
                player.setIsAlive();
                player.decreasePlayerLives();
            }
        }
    }

    public void resetDestroyed() {
        alive = true;
        this.x = birthplaceX;
        this.y = birthplaceY;
        hitVolume = new Rectangle(birthplaceX, birthplaceY, Ghost_SIZE, Ghost_SIZE);
    }

    public void refreshNextNode(Player player) {
        Node ghostNode = new Node((x / 32), ((y - 20) / 32));//starNode
        Node playerNode = new Node((player.getX() / 32), ((player.getY() - 20) / 32));//endNode
        ArrayList<Node> nodePath = (new BreadthFirstSearch()).bfsFindWay(ghostNode, playerNode);

        if (nodePath == null || nodePath.size() == 1) {
            if (player.getX() > this.x) {
                nextStep = new Node((player.getX() / 32), ((player.getY() - 20) / 32));
            }
            if (player.getX() < this.x) {
                nextStep = new Node((player.getX() / 32), ((player.getY() - 20) / 32));
            }
            if (player.getY() > this.y) {
                nextStep = new Node((player.getX() / 32), ((player.getY() - 20) / 32));
            }
            if (player.getY() < this.y) {
                nextStep = new Node((player.getX() / 32), ((player.getY() - 20) / 32));
            }
        } else {
            nextStep = nodePath.get(1);
        }

    }

    public void trackingPlayer(Player player) {
        if (isActive){
            if (resurrectionTime == 0) {
                if (player.getInvincibleTime() == 0) { //If the player is not invincible
                    if (((float) x / 32) < nextStep.x) {
                        this.x = this.x + speed;
                    }
                    if (((float) x / 32) > nextStep.x) {
                        this.x = this.x - speed;
                    }
                    if (((float) (y - 20) / 32) < nextStep.y) {
                        this.y = this.y + speed;
                    }
                    if (((float) (y - 20) / 32) > nextStep.y) {
                        this.y = this.y - speed;
                    }
                    hitVolume = new Rectangle(x, y, Ghost_SIZE, Ghost_SIZE);
                }
            }
        }
    }

    public void escapePlayer(Player player) {
        if (isActive){
            if (resurrectionTime == 0) {
                if (player.getInvincibleTime() > 0) {
                    if (this.x < birthplaceX) {
                        x = x + speed;
                        hitVolume = new Rectangle(x, y, Ghost_SIZE, Ghost_SIZE);
                    } else if (this.x > birthplaceX) {
                        x = x - speed;
                        hitVolume = new Rectangle(x, y, Ghost_SIZE, Ghost_SIZE);
                    }else if (this.y < birthplaceY) {
                        y = y + speed;
                        hitVolume = new Rectangle(x, y, Ghost_SIZE, Ghost_SIZE);
                    } else if (this.y > birthplaceY) {
                        y = y - speed;
                        hitVolume = new Rectangle(x, y, Ghost_SIZE, Ghost_SIZE);
                    }
                }
            }
        }
    }

    public void autoDecreaseInvincibleTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(isActive == true){
                    if (resurrectionTime > 0) {
                        resurrectionTime = resurrectionTime - 1;
                    } else {
                        //pass
                    }
                }
            }
        }, 0 * 1000, 1 * 1000);
    }

    public void addResurrectionTime(int i) {
        resurrectionTime = resurrectionTime + i;
    }

    public void setAlive() {
        this.alive = false;
    }

    public void setActive(){
        this.isActive = true;
    }

    //getMethods--------------------------------------------------------------------------------------------------------
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitVolume() {
        return hitVolume;
    }

    public int getScores() {
        return scores;
    }
}
