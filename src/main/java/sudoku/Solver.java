package sudoku;

public class Solver {

    private final int FIELD_SIZE = 9;
    private int[][] solvedSudoku;
    private int[][] solvedSudokuReverse;

    public Solver() {
        solvedSudoku = new int[FIELD_SIZE][FIELD_SIZE];
        solvedSudokuReverse = new int[FIELD_SIZE][FIELD_SIZE];
    }

    public boolean isValueInRow(int[][] field, int row, int value) {
        for (int j = 0; j < FIELD_SIZE; j++) {
            if(field[row][j] == value) {
                return true; //такое значение уже есть в строке
            }
        }
        return false; //такого значения еще нет
    }

    public boolean isValueInColumn(int[][] field, int column, int value) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            if(field[i][column] == value) {
                return true; //такое значение уже есть в столбце
            }
        }
        return false; //такого значения еще нет
    }

    public boolean isValueInBlock(int[][] field, int row, int column, int value) {
        int blockFirstRow = row - row % 3; //значения для нахождения расположения
        int blockFirstColumn = column - column % 3; //первого элемента блока
        for (int i = blockFirstRow; i < blockFirstRow + 3; i++) {
            for (int j = blockFirstColumn; j < blockFirstColumn + 3; j++) {
                if(field[i][j] == value) {
                    return true; //такое значение уже есть в блоке
                }
            }
        }
        return false; //такого значения еще нет
    }

    public boolean isValueValid(int[][] field, int row, int column, int value) {
        return !isValueInRow(field, row, value) && !isValueInColumn(field, column, value)
                && !isValueInBlock(field, row, column, value);
        //true, если такое значение иожно использовать; false, если такое значение нельзя использовать
    }

    public boolean solveSudoku(int[][] field) {
        solvedSudoku = field;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(solvedSudoku[i][j] == 0) {
                    for (int number = 1; number < 10; number++) {
                        if(isValueValid(solvedSudoku, i, j, number)) {
                            solvedSudoku[i][j] = number;
                            if(solveSudoku(solvedSudoku)) {
                                //solvedSudoku[i][j] = 0;
                                return true; //решена
                            } else {
                                solvedSudoku[i][j] = 0;
                            }
                        }
                    }
                   return false; //нет решений
                }
            }
        }
        return true; //не нуждается в решении
    }

    public boolean solveSudokuReverse(int[][] field) {
        solvedSudokuReverse = field;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(solvedSudokuReverse[i][j] == 0) {
                    for (int number = 9; number > 0; number--) {
                        if(isValueValid(solvedSudokuReverse, i, j, number)) {
                            solvedSudokuReverse[i][j] = number;
                            if(solveSudokuReverse(solvedSudokuReverse)) {
                                //solvedSudokuReverse[i][j] = 0;
                                return true; //решена
                            }
                        }
                    }
                    return false; //нет решений
                }
            }
        }
        return true; //не нуждается в решении
    }

    public boolean isOneSolution(int[][] field) {
        if (!solveSudoku(field)) {
            return false; //нельзя решить
        }
        solveSudokuReverse(field);
        return solvedSudoku == solvedSudokuReverse; //true одно решение
    }

    /*public boolean solveSudoku(int[][] field) {
        solvedSudoku = field;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(solvedSudoku[i][j] == 0) {
                    for (int number = 1; number < 10; number++) {
                        if(isValueValid(solvedSudoku, i, j, number)) {
                            solvedSudoku[i][j] = number;
                            if(solveSudoku(solvedSudoku)) {
                                return true; //решена
                            } else {
                                solvedSudoku[i][j] = 0;
                            }
                        }
                    }
                    return false; //нет решений
                }
            }
        }
        return true; //не нуждается в решении
    }
*/
    public int[][] getSolution() {
        return solvedSudoku;
    }

}
