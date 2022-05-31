package sudoku;

public class AutoSolution {

    private final int FIELD_SIZE = 9;
    private int[][] field;
    private int[][] copyField;
    private Solver solver;

    public AutoSolution() {
        field = new int[FIELD_SIZE][FIELD_SIZE];
        copyField = new int[FIELD_SIZE][FIELD_SIZE];
        solver = new Solver();
    }

    public void setField(int[][] field) {
        this.field = field;
    }
//вернуть что-то такое что при неправильной таблице в редакторе вызывало бы сообщение об этом
    public int[][] autoAnswer() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                copyField[i][j] = field[i][j];
            }
        }
        if (!solver.isOneSolution(field)) {
            System.out.println("False field");
        }
        solver.solveSudoku(copyField);
        return solver.getSolution();
    }

}
