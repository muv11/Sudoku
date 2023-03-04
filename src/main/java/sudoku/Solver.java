package sudoku;

/**
 * Class implements sudoku's recursive solving algorithm */
public class Solver {

    private final int FIELD_SIZE = 9;
    private int[][] solvedSudoku;
    private int[][] solvedSudokuReverse;

    public Solver() {
        solvedSudoku = new int[FIELD_SIZE][FIELD_SIZE];
        solvedSudokuReverse = new int[FIELD_SIZE][FIELD_SIZE];
    }

    /**
     * Method checks whether the value is in row
     * @return false if there is no such value */
    public boolean isValueInRow(int[][] field, int row, int value) {
        for (int j = 0; j < FIELD_SIZE; j++) {
            if(field[row][j] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method checks whether the value is in column
     * @return false if there is no such value */
    public boolean isValueInColumn(int[][] field, int column, int value) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            if(field[i][column] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method checks whether the value is in block (3*3)
     * @return false if there is no such value */
    public boolean isValueInBlock(int[][] field, int row, int column, int value) {
        int blockFirstRow = row - row % 3;
        int blockFirstColumn = column - column % 3;
        for (int i = blockFirstRow; i < blockFirstRow + 3; i++) {
            for (int j = blockFirstColumn; j < blockFirstColumn + 3; j++) {
                if(field[i][j] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method checks whether the value is valid
     * @return true if the value is valid */
    public boolean isValueValid(int[][] field, int row, int column, int value) {
        return !isValueInRow(field, row, value) && !isValueInColumn(field, column, value)
                && !isValueInBlock(field, row, column, value);
    }

    /**
     * Method checks whether the sudoku field is valid
     * (no duplicate numbers in rows or columns)
     * @param field unsolved sudoku field
     * @return true if field is valid */
    public boolean isFieldValid(int[][] field) {
        int count = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                for (int k = 0; k < FIELD_SIZE; k++) {
                    if (field[i][j] != 0) {
                        if (field[i][j] == field[i][k]) {
                            count++;
                        }
                    }
                }
                if (count > 1) {
                    return false;
                }
                count = 0;
            }
        }

        count = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                for (int k = 0; k < FIELD_SIZE; k++) {
                    if (field[j][i] != 0) {
                        if (field[j][i] == field[k][i]) {
                            count++;
                        }
                    }
                }
                if (count > 1) {
                    return false;
                }
                count = 0;
            }
        }
        return true;
    }

    /**
     * Sudoku is solved by the brute force method and recursion
     * @param field unsolved sudoku field
     * @return true if sudoku is solved, false if sudoku has no solutions */
    public boolean solveSudoku(int[][] field) {
        solvedSudoku = field;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(solvedSudoku[i][j] == 0) {
                    for (int number = 1; number < 10; number++) {
                        if(isValueValid(solvedSudoku, i, j, number)) {
                            solvedSudoku[i][j] = number;
                            if(solveSudoku(solvedSudoku)) {
                                return true;
                            } else {
                                solvedSudoku[i][j] = 0;
                            }
                        }
                    }
                   return false;
                }
            }
        }
        return true;
    }

    /**
     * Sudoku is solved by the brute force method and recursion,
     * but the numbers are substituted in reverse order
     * @param field unsolved sudoku field
     * @return true if sudoku is solved, false if sudoku has no solutions */
    public boolean solveSudokuReverse(int[][] field) {
        solvedSudokuReverse = field;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(solvedSudokuReverse[i][j] == 0) {
                    for (int number = 9; number > 0; number--) {
                        if(isValueValid(solvedSudokuReverse, i, j, number)) {
                            solvedSudokuReverse[i][j] = number;
                            if(solveSudokuReverse(solvedSudokuReverse)) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method checks whether there is one solution of sudoku, else it's incorrect
     * @return true if there is one solution, false if there is none or >1 */
    public boolean isOneSolution(int[][] field) {
        if (!solveSudoku(field)) {
            return false;
        }
        solveSudokuReverse(field);
        return solvedSudoku == solvedSudokuReverse;
    }

    public int[][] getSolution() {
        return solvedSudoku;
    }

}
