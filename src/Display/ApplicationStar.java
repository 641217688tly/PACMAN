//Student Name: LiYan Tao  UCD Number: 21207500
package Display;

import Model.PACMAN_Game;
import ucd.comp2011j.engine.GameManager;
import ucd.comp2011j.engine.ScoreKeeper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ApplicationStar {
    public static void main(String[] args) throws IOException {
        JFrame mainWindow = new JFrame();
        mainWindow.setVisible(true);
        Insets insets = mainWindow.getInsets();
        mainWindow.setSize(PACMAN_Game.SCREEN_WIDTH + insets.left + insets.right, PACMAN_Game.SCREEN_HEIGHT + insets.top + insets.bottom);
        mainWindow.setTitle("PAC-MAN(Name:LiYanTao  UCD number:21207500)");
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);

        PlayerListener playerListener = new PlayerListener();
        mainWindow.addKeyListener(playerListener);
        MenuListener menuListener = new MenuListener();
        mainWindow.addKeyListener(menuListener);
        PACMAN_Game game = new PACMAN_Game(playerListener);
        GameScreen gameScreen = new GameScreen(game);
        MenuScreen menuScreen = new MenuScreen();
        ScoreKeeper scoreKeeper = new ScoreKeeper("scores.txt");
        GameManager mmm = new GameManager(game, mainWindow, menuListener, menuScreen, new AboutScreen(), new ScoreScreen(scoreKeeper), gameScreen, scoreKeeper);
        mainWindow.setVisible(true);
        mmm.run();
    }

}
