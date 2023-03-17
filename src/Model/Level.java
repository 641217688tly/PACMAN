package Model;

/*
Function:
1. Different levels adjust the parameters of ghosts in different levels
2. Be responsible for determining whether the level has been completed
3. Reset the level when the player dies

How to achieve this?
Create a level object with a variable that records the level of the current level
For the ghost parameters, use different if conditional to judge the level of the current level and then set different parameters for them
 */
public class Level {
    private int currentLevel = 1;

    public Level() {
    }

    public boolean isLevelOver() {
        if (PACMAN_Game.getCoinArrayList().size() == 0 && PACMAN_Game.getEnergyBallArrayList().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setLevelDifficulty(PACMAN_Game game) {
        for (int i = 1; i < 15; i++) {
            if (i == currentLevel) {
                if (currentLevel >= 1) {
                    game.ghost1.setActive();
                }
                if (currentLevel >= 2) {
                    game.ghost2.setActive();
                    game.ghost2.addResurrectionTime(15-i);
                }
                if (currentLevel >= 3) {
                    game.ghost3.setActive();
                    game.ghost3.addResurrectionTime(30-i);
                }
                if (currentLevel >= 4) {
                    game.ghost4.setActive();
                    game.ghost4.addResurrectionTime(45-i);
                }
            }
        }

    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void addLevel() {
        currentLevel = currentLevel + 1;
    }

}
