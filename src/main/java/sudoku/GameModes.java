package sudoku;

import java.util.Random;

import static sudoku.GameModes.Modes.*;

public class GameModes {

    public enum Modes { //виды режимов
        TEST,
        NORMAL,
        EDITOR;
    }

    private static int freeCells;
    private static Random rand = new Random();

    //генерация количества пустых клеток в зависимости от режима
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

    //количество свободных клеток на поле
    private static void setFreeCells(int x) {
        freeCells = x;
    }

    public int getFreeCells() {
        return freeCells;
    }

}
