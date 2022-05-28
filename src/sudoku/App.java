package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    private final int FIELD_SIZE = 9;
    private Action action = new Action();
    private BoxLayout startLayout, levelsLayout;
    private JFrame frame;
    private JPanel startScreen, levelsScreen, game;
    private JTextField name, levelDiff;
    private Font headerFont, font, fontGame;
    private JTextField[][] grid;

    private JButton play, exit, very_easy, easy, middle, hard, backStart, backLevels, answer;

    public App() {
        frame = new JFrame("Судоку");
        startScreen = new JPanel();
        levelsScreen = new JPanel();
        game = new JPanel();
        grid = new JTextField[FIELD_SIZE][FIELD_SIZE];
        startLayout = new BoxLayout(startScreen, BoxLayout.Y_AXIS);
        levelsLayout = new BoxLayout(levelsScreen, BoxLayout.Y_AXIS);

        frame.setVisible(true);
        //frame.setResizable(false);
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

        levelDiff = new JTextField("СЛОЖНОСТЬ");
        levelDiff.setFont(headerFont);
        levelDiff.setEditable(false);
        levelDiff.setBackground(Color.WHITE);
        levelDiff.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

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

    private void setStartScreenButtons() {
        play = new JButton("ИГРАТЬ");
        play.setFont(font);
        play.setBackground(Color.decode("#D2F8F3"));
        play.addActionListener(action);
        exit = new JButton("ВЫЙТИ");
        exit.setFont(font);
        exit.setBackground(Color.decode("#D2F8F3"));
        exit.addActionListener(action);
    }

    public void setLevels() {
        setLevelsButtons();
        levelsScreen.setBackground(Color.WHITE);
        levelsScreen.setLayout(levelsLayout);
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

        helper1.add(levelDiff);
        levelsScreen.add(helper1);
        helper2.add(very_easy);
        levelsScreen.add(helper2);
        levelsScreen.add(Box.createRigidArea(new Dimension(0, 3)));
        helper3.add(easy);
        levelsScreen.add(helper3);
        levelsScreen.add(Box.createRigidArea(new Dimension(0, 3)));
        helper4.add(middle);
        levelsScreen.add(helper4);
        levelsScreen.add(Box.createRigidArea(new Dimension(0, 3)));
        helper5.add(hard);
        levelsScreen.add(helper5);
        levelsScreen.add(Box.createRigidArea(new Dimension(0, 3)));
        helper6.add(backStart);
        levelsScreen.add(helper6);
        levelsScreen.add(Box.createRigidArea(new Dimension(0, 2)));
        levelsScreen.revalidate();
        levelsScreen.repaint();
    }

    private void setLevelsButtons() {
        very_easy = new JButton("ОЧЕНЬ ЛЕГКАЯ");
        easy = new JButton("ЛЕГКАЯ");
        middle = new JButton("СРЕДНЯЯ");
        hard = new JButton("СЛОЖНАЯ");
        backStart = new JButton("НАЗАД");

        very_easy.setBackground(Color.decode("#D2F8F3"));
        easy.setBackground(Color.decode("#D2F8F3"));
        middle.setBackground(Color.decode("#D2F8F3"));
        hard.setBackground(Color.decode("#D2F8F3"));
        backStart.setBackground(Color.decode("#D2F8F3"));

        very_easy.setFont(font);
        easy.setFont(font);
        middle.setFont(font);
        hard.setFont(font);
        backStart.setFont(font);

        very_easy.addActionListener(action);
        easy.addActionListener(action);
        middle.addActionListener(action);
        hard.addActionListener(action);
        backStart.addActionListener(action);
    }

    public void setGame(Generator generator) {
        setGameButtons();
        game.setBackground(Color.WHITE);
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.SOUTH;

        JPanel gridPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(FIELD_SIZE, FIELD_SIZE, 1, 1);
        gridPanel.setLayout(gridLayout);

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (generator.getFieldEl(i, j) == 0) {
                    grid[i][j] = new JTextField("");
                } else {
                    //grid[i][j].setText(String.valueOf(generator.getFieldEl(i, j)));
                    grid[i][j] = new JTextField(String.valueOf(generator.getFieldEl(i, j)));
                    grid[i][j].setEditable(false);
                }
                grid[i][j].setAlignmentX(10f);
                grid[i][j].setFont(fontGame);
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                gridPanel.add(grid[i][j]);

            }
        }

        game.add(gridPanel);
        gbc.weighty = 0;
        game.add(answer, gbc);
        game.add(backLevels, gbc);
        game.revalidate();
        game.repaint();
    }

    public void setGameButtons() {
        backLevels = new JButton("НАЗАД");
        answer = new JButton("ОТВЕТ");
        backLevels.setBackground(Color.decode("#D2F8F3"));
        answer.setBackground(Color.decode("#D2F8F3"));

        backLevels.setFont(fontGame);
        answer.setFont(fontGame);

        backLevels.addActionListener(action);
        answer.addActionListener(action);
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == play) {
                frame.remove(startScreen);
                frame.add(levelsScreen);
                levelsScreen.revalidate();
                levelsScreen.repaint();
            }
            if (e.getSource() == exit) {
                System.exit(0);
            }
            if (e.getSource() == backStart) {
                frame.remove(levelsScreen);
                frame.add(startScreen);
                startScreen.revalidate();
                startScreen.repaint();
            }
            if (e.getSource() == backLevels) {
                frame.remove(game);
                frame.add(levelsScreen);
                levelsScreen.revalidate();
                levelsScreen.repaint();
            }
            if (e.getSource() == very_easy) {
                Generator generator = new Generator(DifficultyLevels.Levels.VERY_EASY);
                generator.generateSudoku();
                frame.remove(levelsScreen);
                setGame(generator);
                frame.add(game);
                game.revalidate();
                game.repaint();
            }
            if (e.getSource() == easy) {
                Generator generator = new Generator(DifficultyLevels.Levels.EASY);
                generator.generateSudoku();
                frame.remove(levelsScreen);
                setGame(generator);
                frame.add(game);
                game.revalidate();
                game.repaint();
            }
            if (e.getSource() == middle) {
                Generator generator = new Generator(DifficultyLevels.Levels.MEDIUM);
                generator.generateSudoku();
                frame.remove(levelsScreen);
                setGame(generator);
                frame.add(game);
                game.revalidate();
                game.repaint();
            }
            if (e.getSource() == hard) {
                Generator generator = new Generator(DifficultyLevels.Levels.HARD);
                generator.generateSudoku();
                frame.remove(levelsScreen);
                setGame(generator);
                frame.add(game);
                game.revalidate();
                game.repaint();
            }
        }
    }

}
