package sudoku;

public class Solver {

    private final int FIELD_SIZE = new Generator().getSizeField();

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
            for (int j = blockFirstColumn; j < blockFirstColumn; j++) {
                if(field[i][j] == value) {
                    return true; //такое значение уже есть в блоке
                }
            }
        }
        return false; //такого значения еще нет
    }

    public boolean isValueValid(int[][] field, int row, int column, int value) {
        if(isValueInRow(field, row, value) || isValueInColumn(field, column, value)
            || isValueInBlock(field, row, column, value)) {
            return false; //значение уже имеется, потому недопустимо
        }
        return true; //значения нет, можно использовать
    }

    public boolean solveSudoku(int[][] field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(field[i][j] == 0) {
                    for (int number = 1; number < 10; number++) {
                        if(isValueValid(field, i, j, number)) {
                            field[i][j] = number;
                            if(solveSudoku(field)) {
                                return true;
                            } else {
                                field[i][j] = 0;
                            }
                        }
                    }
                   return false; //нет решений
                }
            }
        }
        return true;
    }
    
}
