package sudoku;

public class AutoSolution {

    private final int FIELD_SIZE = 9;
    private int[][] answer;
    private int[][] field;
    private Solver solver;

    public AutoSolution() {
        answer = new int[FIELD_SIZE][FIELD_SIZE];
        field = new int[FIELD_SIZE][FIELD_SIZE];
        solver = new Solver();
    }

    public void setElement(int i, int j, int val) {
        field[i][j] = val;
    }

    public int[][] autoAnswer() {
        solver.solveSudoku(field);

        return solver.getSolution();
    }

}
