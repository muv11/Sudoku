package sudoku;

import javax.swing.*;

public class UserSolution {

    private final int FIELD_SIZE = 9;
    private int[][] answer;
    private int[][] field;
    private JFrame win;
    private Solver solver;

    public UserSolution() {
        answer = new int[FIELD_SIZE][FIELD_SIZE];
        field = new int[FIELD_SIZE][FIELD_SIZE];
        solver = new Solver();
        //answer = app.getUserAnswer();
    }

    public void getAnswer(int[][] answer) {
        this.answer = answer;
    }

    public void getField(int[][] field) {
        this.field = field;
    }

    public void checkAnswer() {
        solver.solveSudoku(field);
        if (answer == solver.getSolution()) {
            win = new JFrame("Победа");
            JTextField text = new JTextField("Поздравляю! Вы победили!");
            win.setSize(150, 100);
            win.add(text);
            win.setVisible(true);
            System.out.println("Win");
        }
    }

    public void showAnswer() {
        for(int i=0; i<FIELD_SIZE; i++) {
            for(int j=0; j<FIELD_SIZE; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}
