package sudoku;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App {

    private final int FIELD_SIZE = 9;
    private Action action = new Action();
    private BoxLayout startLayout, levelsLayout;
    private JFrame frame;
    private JPanel startScreen, modesScreen, game, editor;
    private JTextField name, modeChoice;
    private Font headerFont, font, fontGame;
    private JTextField[][] grid;
    private int[][] userAnswer, editorField;
    private UserSolution us = new UserSolution();
    private AutoSolution as = new AutoSolution();
    private JButton play, exit, test, normal, backStart, backLevels, answer, next, backLevEd, edit, auto;

    public App() {
        frame = new JFrame("Судоку");
        startScreen = new JPanel();
        modesScreen = new JPanel();
        editor = new JPanel();
        game = new JPanel();
        grid = new JTextField[FIELD_SIZE][FIELD_SIZE];
        editorField = new int[FIELD_SIZE][FIELD_SIZE];
        userAnswer = new int[FIELD_SIZE][FIELD_SIZE];
        startLayout = new BoxLayout(startScreen, BoxLayout.Y_AXIS);
        levelsLayout = new BoxLayout(modesScreen, BoxLayout.Y_AXIS);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane();
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(550, 650);
    }

    public void setText() {
        headerFont = new Font("Century Gothic", Font.BOLD, 80);
        font = new Font("Century Cothic",Font.PLAIN , 60);
        fontGame = new Font("Century Cothic",Font.PLAIN , 30);

        name = new JTextField("СУДОКУ");
        name.setFont(headerFont);
        name.setEditable(false);
        name.setBackground(Color.WHITE);
        name.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        modeChoice = new JTextField("РЕЖИМ ИГРЫ");
        modeChoice.setFont(headerFont);
        modeChoice.setEditable(false);
        modeChoice.setBackground(Color.WHITE);
        modeChoice.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    //создание стартовой панели
    public void setStartScreen() {
        setStartScreenButtons();
        JPanel helper1 = new JPanel();
        JPanel helper2 = new JPanel();
        JPanel helper3 = new JPanel();
        startScreen.setLayout(startLayout);
        startScreen.setBackground(Color.WHITE);
        helper1.setBackground(Color.WHITE);
        helper2.setBackground(Color.WHITE);
        helper3.setBackground(Color.WHITE);

        startScreen.add(Box.createRigidArea(new Dimension(0, 60)));
        helper1.add(name);
        startScreen.add(helper1);
        startScreen.add(Box.createRigidArea(new Dimension(0, 60)));
        helper2.add(play);
        startScreen.add(helper2);
        startScreen.add(Box.createRigidArea(new Dimension(0, 15)));
        helper3.add(exit);
        startScreen.add(helper3);
        startScreen.add(Box.createRigidArea(new Dimension(0, 200)));
        frame.add(startScreen);
        startScreen.revalidate();
        startScreen.repaint();
    }

    //создание панели стартового экрана
    public void setStartScreenButtons() {
        play = new JButton("ИГРАТЬ");
        play.setFont(font);
        play.setBackground(Color.decode("#D2F8F3"));
        play.addActionListener(action);
        exit = new JButton("ВЫЙТИ");
        exit.setFont(font);
        exit.setBackground(Color.decode("#D2F8F3"));
        exit.addActionListener(action);
    }

    //создание панели выбора режимов
    public void setLevels() {
        setLevelsButtons();
        modesScreen.setBackground(Color.WHITE);
        modesScreen.setLayout(levelsLayout);
        JPanel helper1 = new JPanel();
        JPanel helper2 = new JPanel();
        JPanel helper3 = new JPanel();
        JPanel helper4 = new JPanel();
        JPanel helper5 = new JPanel();
        JPanel helper6 = new JPanel();
        helper1.setBackground(Color.WHITE);
        helper2.setBackground(Color.WHITE);
        helper3.setBackground(Color.WHITE);
        helper4.setBackground(Color.WHITE);
        helper5.setBackground(Color.WHITE);
        helper6.setBackground(Color.WHITE);

        helper1.add(modeChoice);
        modesScreen.add(helper1);
        helper2.add(test);
        modesScreen.add(helper2);
        modesScreen.add(Box.createRigidArea(new Dimension(0, 3)));
        helper3.add(normal);
        modesScreen.add(helper3);
        modesScreen.add(Box.createRigidArea(new Dimension(0, 3)));
        helper4.add(edit);
        modesScreen.add(helper4);
        modesScreen.add(Box.createRigidArea(new Dimension(0, 2)));
        helper5.add(backStart);
        modesScreen.add(helper5);
        modesScreen.add(Box.createRigidArea(new Dimension(0, 2)));
        modesScreen.revalidate();
        modesScreen.repaint();
    }

    //создание кнопок панели выбора режима
    public void setLevelsButtons() {
        test = new JButton("ТЕСТОВЫЙ");
        normal = new JButton("ОБЫЧНЫЙ");
        edit = new JButton("РЕДАКТОР");
        backStart = new JButton("НАЗАД");

        test.setBackground(Color.decode("#D2F8F3"));
        normal.setBackground(Color.decode("#D2F8F3"));
        edit.setBackground(Color.decode("#D2F8F3"));
        backStart.setBackground(Color.decode("#D2F8F3"));

        test.setFont(font);
        normal.setFont(font);
        edit.setFont(font);
        backStart.setFont(font);

        test.addActionListener(action);
        normal.addActionListener(action);
        edit.addActionListener(action);
        backStart.addActionListener(action);
    }

    //создание панели игры
    public void setGame(int[][] field) {
        setGameButtons();
        game.setBackground(Color.WHITE);
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == 0) {
                    grid[i][j] = new JTextField("");
                    PlainDocument doc = (PlainDocument) grid[i][j].getDocument();
                    doc.setDocumentFilter(new DigitFilter()); //чтобы вводились только цифры
                    int finalI = i;
                    int finalJ = j;
                    grid[i][j].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            if (grid[finalI][finalJ].getText().length() == 1) {
                                e.consume(); //чтобы нельзя было ввести больше одного числа
                            }
                        }
                    });
                } else {
                    grid[i][j] = new JTextField(String.valueOf(field[i][j]));
                    grid[i][j].setEditable(false);
                    grid[i][j].setBackground(Color.decode("#D2F8F1"));
                }
                grid[i][j].setFont(font);
                grid[i][j].setPreferredSize(new Dimension(45, 45));
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                game.add(grid[i][j], gbc);
                gbc.gridx++;
            }
            gbc.gridx = 1;
            gbc.gridy++;
        }

        gbc.gridwidth = 12;
        gbc.gridy = 12;
        gbc.gridx = 0;
        game.add(answer, gbc);
        gbc.gridy = 13;
        game.add(auto, gbc);
        gbc.gridy = 14;
        game.add(backLevels, gbc);
        game.revalidate();
        game.repaint();
    }

    //создание кнопок панели игры
    public void setGameButtons() {
        backLevels = new JButton("НАЗАД");
        answer = new JButton("ПРОВЕРКА");
        auto = new JButton("РЕШЕНИЕ");
        backLevels.setBackground(Color.decode("#D2F8F3"));
        answer.setBackground(Color.decode("#D2F8F3"));
        auto.setBackground(Color.decode("#D2F8F3"));
        auto.setFont(fontGame);
        backLevels.setFont(fontGame);
        answer.setFont(fontGame);
        auto.addActionListener(action);
        backLevels.addActionListener(action);
        answer.addActionListener(action);
    }

    //создание панели редактора
    public void setEditor() {
        setEditorButtons();
        editor.setBackground(Color.WHITE);
        editor.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                grid[i][j] = new JTextField("");
                PlainDocument doc = (PlainDocument) grid[i][j].getDocument();
                doc.setDocumentFilter(new DigitFilter());//чтобы вводились только цифры
                int finalI = i;
                int finalJ = j;
                grid[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (grid[finalI][finalJ].getText().length() == 1) {
                            e.consume();//чтобы нельзя было ввести больше одного числа
                        }
                    }
                });
                grid[i][j].setFont(font);
                grid[i][j].setPreferredSize(new Dimension(45, 45));
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                editor.add(grid[i][j], gbc);
                gbc.gridx++;
            }
            gbc.gridx = 1;
            gbc.gridy++;
        }

        gbc.gridwidth = 12;
        gbc.gridy = 12;
        gbc.gridx = 0;
        editor.add(next, gbc);
        gbc.gridy = 13;
        editor.add(backLevEd, gbc);

        editor.revalidate();
        editor.repaint();
    }

    //создание кнопок редактора
    public void setEditorButtons() {
        backLevEd = new JButton("НАЗАД");
        next = new JButton("ДАЛЕЕ");
        backLevEd.setBackground(Color.decode("#D2F8F3"));
        next.setBackground(Color.decode("#D2F8F3"));

        backLevEd.setFont(fontGame);
        next.setFont(fontGame);

        backLevEd.addActionListener(action);
        next.addActionListener(action);
    }

    public void startSudoku() {
        setText();
        setStartScreen();
        setLevels();
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == play) {
                frame.remove(startScreen);
                frame.add(modesScreen);
                modesScreen.revalidate();
                modesScreen.repaint();
            }
            if (e.getSource() == exit) {
                System.exit(0);
            }
            if (e.getSource() == backStart) {
                frame.remove(modesScreen);
                frame.add(startScreen);
                startScreen.revalidate();
                startScreen.repaint();
            }
            if (e.getSource() == backLevEd) {
                editor.remove(backLevEd);
                editor.remove(next);
                for (int i = 0; i < FIELD_SIZE; i++) {
                    for (int j = 0; j < FIELD_SIZE; j++) {
                        editor.remove(grid[i][j]);
                    }
                }
                frame.remove(editor);
                frame.add(modesScreen);
                modesScreen.revalidate();
                modesScreen.repaint();
            }
            if (e.getSource() == backLevels) {
                game.remove(backLevels);
                game.remove(answer);
                game.remove(auto);
                for (int i = 0; i < FIELD_SIZE; i++) {
                    for (int j = 0; j < FIELD_SIZE; j++) {
                        game.remove(grid[i][j]);
                    }
                }
                frame.remove(game);
                frame.add(modesScreen);
                modesScreen.revalidate();
                modesScreen.repaint();
            }
            if (e.getSource() == next) {
                for (int i = 0; i < FIELD_SIZE; i++) {
                    for (int j = 0; j < FIELD_SIZE; j++) {
                        if (grid[i][j].getText().equals("")) {
                            editorField[i][j] = 0;
                        } else {
                            editorField[i][j] = Integer.parseInt(grid[i][j].getText());
                        }
                    }
                }
                //проверка: правильно ли пользователь составил судоку в режиме редактора
                if (new Solver().isFieldValid(editorField)) {
                    editor.remove(backLevEd);
                    editor.remove(next);
                    for (int i = 0; i < FIELD_SIZE; i++) {
                        for (int j = 0; j < FIELD_SIZE; j++) {
                            editor.remove(grid[i][j]);
                        }
                    }
                    frame.remove(editor);
                    us.setField(editorField);
                    setGame(editorField);
                    frame.add(game);
                    game.revalidate();
                    game.repaint();
                } else {
                    JFrame error = new JFrame("Ошибка");
                    Font fontE = new Font("Century Cothic",Font.BOLD , 20);
                    error.setBackground(Color.white);
                    JTextField text = new JTextField("Судоку составлена неверно");
                    error.setSize(350, 100);
                    error.add(text);
                    text.setBackground(Color.white);
                    text.setEditable(false);
                    text.setFont(fontE);
                    error.setVisible(true);
                }
            }
            if (e.getSource() == test) {
                Generator generator = new Generator(GameModes.Modes.TEST);
                generator.generateSudoku();
                us.setField(generator.getField());
                frame.remove(modesScreen);
                setGame(generator.getField());
                frame.add(game);
                game.repaint();
                game.revalidate();
            }
            if (e.getSource() == normal) {
                Generator generator = new Generator(GameModes.Modes.NORMAL);
                generator.generateSudoku();
                us.setField(generator.getField());
                frame.remove(modesScreen);
                setGame(generator.getField());
                frame.add(game);
                game.repaint();
                game.revalidate();
            }
            if (e.getSource() == edit) {
                setEditor();
                frame.remove(modesScreen);
                frame.add(editor);
                editor.repaint();
                editor.revalidate();
            }
            //проверка ответа пользователя
            if (e.getSource() == answer) {
                for (int i = 0; i < FIELD_SIZE; i++) {
                    for (int j = 0; j < FIELD_SIZE; j++) {
                        if (grid[i][j].getText().equals("")) {
                            userAnswer[i][j] = 0;
                        } else {
                            userAnswer[i][j] = Integer.parseInt(grid[i][j].getText());
                        }
                    }
                }
                us.setAnswer(userAnswer);
                us.checkAnswer();
            }
            //автоматическое решение
            if (e.getSource() == auto) {
                int[][] gridInt = new int[FIELD_SIZE][FIELD_SIZE];
                for (int i = 0; i < FIELD_SIZE; i++) {
                    for (int j = 0; j < FIELD_SIZE; j++) {
                        if (grid[i][j].getText().equals("")) {
                            gridInt[i][j] = 0;
                        } else {
                            gridInt[i][j] = Integer.parseInt(grid[i][j].getText());
                        }
                    }
                }
                as.setField(gridInt);
                int[][]rightField = as.autoAnswer();

                game.remove(backLevels);
                game.remove(answer);
                game.remove(auto);
                for (int i = 0; i < FIELD_SIZE; i++) {
                    for (int j = 0; j < FIELD_SIZE; j++) {
                        game.remove(grid[i][j]);
                    }
                }
                frame.remove(game);
                setGame(rightField);
                frame.add(game);
                game.revalidate();
                game.repaint();
            }
        }
    }

}
