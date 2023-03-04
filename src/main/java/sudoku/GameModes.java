package sudoku;

import java.util.Random;

import static sudoku.GameModes.Modes.*;

public class GameModes {

    public enum Modes {
        TEST,
        NORMAL,
        EDITOR;
    }

    /**The amount of free cells on the field*/
    private static int freeCells;
    private static Random rand = new Random();

    /**
     * Method generates free cells on the field depending on the game mode*/
    public static void chooseMode(Modes MODE) {
        int freeCells = 0;
        if (MODE == TEST) {
            freeCells = 5;
        }
        if (MODE == NORMAL) {
            freeCells = rand.nextInt(25 - 10) + 10;
        }
        setFreeCells(freeCells);
    }

    public static void setFreeCells(int x) {
        freeCells = x;
    }

    public int getFreeCells() {
        return freeCells;
    }

}
