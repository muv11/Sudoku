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
        //true, если такое значение можно использовать
    }

    //готовое поле судоку соответствует правилам
    //(нет повторяющихся элементов в строке, столбце, блоке)
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
        return true; //поле соответствует правилам
    }

    public boolean solveSudoku(int[][] field) {
        solvedSudoku = field;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(solvedSudoku[i][j] == 0) {
                    for (int number = 1; number < 10; number++) {//перебираются все возможные варианты
                        if(isValueValid(solvedSudoku, i, j, number)) {//подходит ли значение
                            solvedSudoku[i][j] = number;
                            if(solveSudoku(solvedSudoku)) {//рекурсия
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
                    for (int number = 9; number > 0; number--) {//значения перебираются в обратном порядке
                        if(isValueValid(solvedSudokuReverse, i, j, number)) {//подходит ли значение
                            solvedSudokuReverse[i][j] = number;
                            if(solveSudokuReverse(solvedSudokuReverse)) {//рекурсия
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

    public int[][] getSolution() {
        return solvedSudoku;
    }

}
