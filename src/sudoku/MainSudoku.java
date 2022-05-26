package sudoku;

import java.util.Arrays;

public class MainSudoku {

    static int[][] field = {
            {9, 4, 0, 8, 7, 0, 0, 1, 0},
            {0, 6, 8, 0, 1, 0, 3, 0, 9},
            {0, 0, 5, 0, 3, 0, 4, 0, 7},
            {0, 0, 0, 0, 2, 1, 7, 9, 8},
            {8, 5, 0, 9, 0, 0, 0, 3, 0},
            {2, 9, 0, 0, 8, 3, 5, 0, 0},
            {3, 0, 0, 2, 0, 6, 9, 0, 1},
            {0, 7, 9, 1, 0, 0, 0, 0, 3},
            {6, 0, 2, 3, 0, 7, 0, 5, 0}
    };

    public static void main(String[] args) {

        /*Generator object = new Generator();
        object.createBaseField();
        object.showField();
        System.out.print("\n");
        object.mixField(20);
        object.showField();*/

        /*Solver solver = new Solver();
        System.out.println(solver.isOneSolution(field));
        System.out.println(Arrays.deepToString(solver.getSolution()));*/

        DifficultyLevels l = new DifficultyLevels();
        DifficultyLevels.chooseLevel(DifficultyLevels.Levels.EASY);
        System.out.println(l.getFreeCells());

    }
}
