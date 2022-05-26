package sudoku;

import java.util.Random;
import static sudoku.DifficultyLevels.Levels.*;

public class DifficultyLevels {

    enum Levels {
        VERY_EASY,
        EASY,
        MEDIUM,
        HARD;
    }

    private static int freeCells;

    //выбор уровня сложности
    public static void chooseLevel(Levels LEVEL) {
        int min = 0; //min и max свободных клеток
        int max = 0;
        if (LEVEL == VERY_EASY) {
            min = 3;
            max = 6;
        }
        if (LEVEL == EASY) {
            min = 21;
            max = 40;
        }
        if (LEVEL == MEDIUM) {
            min = 41;
            max = 50;
        }
        if (LEVEL == HARD) {
            min = 51;
            max = 61;
        }
        setFreeCells(min, max);
    }

    //количество свободных клеток на поле
    private static void setFreeCells(int min, int max) {
        freeCells = new Random(System.currentTimeMillis()).nextInt(max + 1 - min) + min;
    }

    public int getFreeCells() {
        return freeCells;
    }

}
