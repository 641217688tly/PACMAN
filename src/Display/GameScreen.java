package Display;

import Model.Ghosts.Ghost;
import Model.PACMAN_Game;
import Model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GameScreen extends JPanel {
    private PACMAN_Game game;
    private BufferedImage image1 = ImageIO.read(new FileInputStream("images/wall.png"));
    private BufferedImage image2 = ImageIO.read(new FileInputStream("images/coin.png"));
    private BufferedImage image3 = ImageIO.read(new FileInputStream("images/energyBall.png"));
    private BufferedImage image4 = ImageIO.read(new FileInputStream("images/fruit.png"));
    private BufferedImage image5 = ImageIO.read(new FileInputStream("images/PACMAN_Right.png"));
    private BufferedImage image6 = ImageIO.read(new FileInputStream("images/PACMAN_Left.png"));
    private BufferedImage image7 = ImageIO.read(new FileInputStream("images/PACMAN_Up.png"));
    private BufferedImage image8 = ImageIO.read(new FileInputStream("images/PACMAN_Down.png"));
    private BufferedImage image9 = ImageIO.read(new FileInputStream("images/Ghost1.png"));
    private BufferedImage image10 = ImageIO.read(new FileInputStream("images/Ghost2.png"));
    private BufferedImage image11 = ImageIO.read(new FileInputStream("images/Ghost3.png"));
    private BufferedImage image12 = ImageIO.read(new FileInputStream("images/Ghost4.png"));
    private BufferedImage image13 = ImageIO.read(new FileInputStream("images/Ghost5.png"));

    public GameScreen(PACMAN_Game game) throws IOException {
        this.game = game;
    }

    private void drawEntireMap(Graphics2D gc) {
        // Then start creating a drawing screen for each element in the map:
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (PACMAN_Game.getStringMap().get(i).charAt(j) == 'W') {//Wall
                    gc.drawImage(image1, 32 * j, 32 * i + 20, 32, 32, null);
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == '.') { //Coin
                    gc.drawImage(image2, 32 * j + 10, 32 * i + 10 + 20, 10, 10, null);
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == '*') { //Energy Ball
                    gc.drawImage(image3, 32 * j + 4, 32 * i + 4 + 20, 24, 24, null);
                } else if (PACMAN_Game.getStringMap().get(i).charAt(j) == 'F') { //Fruit
                    gc.drawImage(image4, 32 * j, 32 * i + 20, 32, 32, null);
                }
            }
        }
    }

    private void drawPlayer(Graphics2D gc, Player p) {
        int x = p.getX();
        int y = p.getY();
        if (p.isRight()) {
            if (game.getPlayer().getInvincibleTime() == 0) {
                gc.drawImage(image5, x + 2, y + 2, Player.PLAYER_SIZE - 6, Player.PLAYER_SIZE - 6, null); //Let's make it a little bit smaller, but keep the bump box the same size
            } else if (game.getPlayer().getInvincibleTime() > 0) {
                gc.drawImage(image5, x + 2, y + 2, Player.PLAYER_SIZE, Player.PLAYER_SIZE, null); //Let's make it a little bit smaller, but keep the bump box the same size
            }
        } else if (p.isLeft()) {
            if (game.getPlayer().getInvincibleTime() == 0) {
                gc.drawImage(image6, x + 2, y + 2, Player.PLAYER_SIZE - 6, Player.PLAYER_SIZE - 6, null); //Let's make it a little bit smaller, but keep the bump box the same size
            } else if (game.getPlayer().getInvincibleTime() > 0) {
                gc.drawImage(image6, x + 2, y + 2, Player.PLAYER_SIZE, Player.PLAYER_SIZE, null); //Let's make it a little bit smaller, but keep the bump box the same size
            }
        } else if (p.isUp()) {
            if (game.getPlayer().getInvincibleTime() == 0) {
                gc.drawImage(image7, x + 2, y + 2, Player.PLAYER_SIZE - 6, Player.PLAYER_SIZE - 6, null); //Let's make it a little bit smaller, but keep the bump box the same size
            } else if (game.getPlayer().getInvincibleTime() > 0) {
                gc.drawImage(image7, x + 2, y + 2, Player.PLAYER_SIZE, Player.PLAYER_SIZE, null); //Let's make it a little bit smaller, but keep the bump box the same size
            }
        } else if (p.isDown()) {
            if (game.getPlayer().getInvincibleTime() == 0) {
                gc.drawImage(image8, x + 2, y + 2, Player.PLAYER_SIZE - 6, Player.PLAYER_SIZE - 6, null); //Let's make it a little bit smaller, but keep the bump box the same size
            } else if (game.getPlayer().getInvincibleTime() > 0) {
                gc.drawImage(image8, x + 2, y + 2, Player.PLAYER_SIZE, Player.PLAYER_SIZE, null); //Let's make it a little bit smaller, but keep the bump box the same size
            }
        }
    }

    private void drawGhosts(Graphics2D gc, Player player) {
        if (player.getInvincibleTime() > 0) { //When the player is invincible
            gc.drawImage(image13, game.ghost1.getX(), game.ghost1.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        } else {
            gc.drawImage(image9, game.ghost1.getX(), game.ghost1.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        }

        if (player.getInvincibleTime() > 0) { //When the player is invincible
            gc.drawImage(image13, game.ghost2.getX(), game.ghost2.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        } else {
            gc.drawImage(image10, game.ghost2.getX(), game.ghost2.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        }

        if (player.getInvincibleTime() > 0) { //When the player is invincible
            gc.drawImage(image13, game.ghost3.getX(), game.ghost3.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        } else {
            gc.drawImage(image11, game.ghost3.getX(), game.ghost3.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        }

        if (player.getInvincibleTime() > 0) { //When the player is invincible
            gc.drawImage(image13, game.ghost4.getX(), game.ghost4.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        } else {
            gc.drawImage(image12, game.ghost4.getX(), game.ghost4.getY(), Ghost.Ghost_SIZE, Ghost.Ghost_SIZE, null);
        }
    }

    protected void paintComponent(Graphics g) {//Override the paintComponent method to implement jPanel with background
        if (game != null) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, PACMAN_Game.SCREEN_WIDTH, PACMAN_Game.SCREEN_HEIGHT);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("Lives: " + game.getLives(), 0, 15);
            g.drawString("Score: " + game.getPlayerScore(), PACMAN_Game.SCREEN_WIDTH / 4, 15);

            drawEntireMap(g2);
            drawPlayer(g2, game.getPlayer());
            drawGhosts(g2, game.getPlayer());

            if (game.isPaused() && !game.isGameOver()) { //Pause the game
                g.setColor(Color.GREEN);
                g.drawString("Press 'p' to continue!", PACMAN_Game.SCREEN_WIDTH / 2, 15);
            } else if (game.isGameOver()) { //Game over
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.setColor(Color.RED);
                g.drawString("Game over!", PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 4);
            }
        }
    }
}
