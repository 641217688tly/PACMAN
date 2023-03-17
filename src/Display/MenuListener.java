package Display;

import ucd.comp2011j.engine.MenuCommands;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuListener implements MenuCommands, KeyListener {
    private boolean about;//Key menu
    private boolean exit; //Exit key
    private boolean high; //Highest score
    private boolean menu; //Return the menu key M
    private boolean newGame; //start Game


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'A' || e.getKeyChar() == 'a') {
            about = true;
        } else if (e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
            exit = true;
        } else if (e.getKeyChar() == 'H' || e.getKeyChar() == 'h') {
            high = true;
        } else if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
            menu = true;
        } else if (e.getKeyChar() == 'N' || e.getKeyChar() == 'n') {
            newGame = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //null
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //null
    }

    @Override
    public boolean hasPressedNewGame() {
        return newGame;
    }

    @Override
    public boolean hasPressedAboutScreen() {
        return about;
    }

    @Override
    public boolean hasPressedHighScoreScreen() {
        return high;
    }

    @Override
    public boolean hasPressedMenu() {
        return menu;
    }

    @Override
    public boolean hasPressedExit() {
        return exit;
    }

    @Override
    public void resetKeyPresses() {
        menu = false;
        about = false;
        newGame = false;
        high = false;
        exit = false;
    }
}
