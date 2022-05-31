package sudoku;

import javax.swing.*;
import java.awt.*;

public class UserSolution {

    private final int FIELD_SIZE = 9;
    private int[][] answer;
    private int[][] field;
    private Solver solver;

    public UserSolution() {
        answer = new int[FIELD_SIZE][FIELD_SIZE];
        field = new int[FIELD_SIZE][FIELD_SIZE];
        solver = new Solver();
    }

    public void setAnswer(int[][] answer) {
        this.answer = answer;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public void checkAnswer() {
        solver.solveSudoku(field);
        Font font = new Font("Century Cothic",Font.BOLD , 20);
        if (isArrSame()) {
            JFrame win = new JFrame("Победа");
            win.setBackground(Color.white);
            JTextField text = new JTextField("Поздравляю! Вы победили!");
            win.setSize(300, 100);
            win.add(text);
            text.setBackground(Color.white);
            text.setEditable(false);
            text.setFont(font);
            win.setVisible(true);
        } else {
            JFrame failure = new JFrame("Поражение");
            JTextField text = new JTextField("Решение неверное");
            failure.setSize(300, 100);
            failure.add(text);
            text.setBackground(Color.white);
            text.setEditable(false);
            text.setFont(font);
            failure.setVisible(true);
        }
    }

    private boolean isArrSame() {
        int count = 0;
        for(int i=0; i<FIELD_SIZE; i++) {
            for(int j=0; j<FIELD_SIZE; j++) {
                if (field[i][j] != answer[i][j]) {
                    count++;
                }
            }
        }
        if (count != 0) {
            return false;
        }
        return true;
    }

    public void showAnswer() {
        for(int i=0; i<FIELD_SIZE; i++) {
            for(int j=0; j<FIELD_SIZE; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        for(int i=0; i<FIELD_SIZE; i++) {
            for(int j=0; j<FIELD_SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}
