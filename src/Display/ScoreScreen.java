package Display;

import Model.PACMAN_Game;
import ucd.comp2011j.engine.Score;
import ucd.comp2011j.engine.ScoreKeeper;

import javax.swing.*;
import java.awt.*;

public class ScoreScreen extends JPanel {
    private ScoreKeeper scoreKeeper;

    public ScoreScreen(ScoreKeeper sc) {
        this.scoreKeeper = sc;
    }

    private void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setColor(Color.GREEN);
        g2d.drawString(text, x, y);
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, PACMAN_Game.SCREEN_WIDTH, PACMAN_Game.SCREEN_HEIGHT);
        drawString(g, "PAC-MAN Hall of Fame", new Rectangle(0, 0, PACMAN_Game.SCREEN_WIDTH, PACMAN_Game.SCREEN_HEIGHT / 8),
                36);
        g.setColor(Color.GREEN);
        Score[] scores = scoreKeeper.getScores();
        g.setFont(new Font("Arial", Font.BOLD, 24));
        for (int i = 0; i < scores.length; i++) {
            Score score = scores[i];
            g.drawString(score.getName(), 2 * PACMAN_Game.SCREEN_WIDTH / 6, 96 + i * 32);
            g.drawString("" + score.getScore(), 4 * PACMAN_Game.SCREEN_WIDTH / 6, 96 + i * 32);
        }
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, PACMAN_Game.SCREEN_WIDTH, 96), 24);
    }

}
