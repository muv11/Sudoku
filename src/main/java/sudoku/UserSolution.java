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
        this.answer = answer; //получаем ответ пользователя
    }

    public void setField(int[][] field) {
        this.field = field; //получаем нерешенное судоку
    }

    public void checkAnswer() {
        solver.solveSudoku(field); //получаем решение судоку
        Font font = new Font("Century Cothic",Font.BOLD , 20);
        if (isArrSame()) {//если решение компьютера и пользователя сходятся, то победа
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

    //проверка: сходятся ли решенное судоку и ответ пользователя
    public boolean isArrSame() {
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

}
