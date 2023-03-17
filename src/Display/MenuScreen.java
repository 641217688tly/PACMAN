package Display;

import Model.PACMAN_Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class MenuScreen extends JPanel {
    private BufferedImage image1 = ImageIO.read(new FileInputStream("images/cover.png"));

    public MenuScreen() throws IOException {
    }

    private void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setColor(Color.YELLOW);
        g2d.drawString(text, x, y);
    }

    private void drawCover(Graphics2D gc) {
        gc.drawImage(image1, 0, 130, 512, 789, null);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PACMAN_Game.SCREEN_WIDTH, PACMAN_Game.SCREEN_HEIGHT);
        drawString(g, "Welcome to PAC-MAN!!!!", new Rectangle(PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 32 - 130,
                PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 3), 36);
        drawString(g, "To play a new game press N", new Rectangle(PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 6 - 200,
                PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 3), 18);
        drawString(g, "To see the controls press A", new Rectangle(PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 6 - 180,
                PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 3), 18);
        drawString(g, "To see the High scores press H", new Rectangle(PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 6 - 160,
                PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 3), 18);
        drawString(g, "To exit press X", new Rectangle(PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 6 - 140,
                PACMAN_Game.SCREEN_WIDTH / 3, PACMAN_Game.SCREEN_HEIGHT / 3), 18);
        drawCover(g2);
    }
}
