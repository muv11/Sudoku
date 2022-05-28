package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    private Action action = new Action();
    private FlowLayout startLayout, levelsLayout;
    private JFrame frame;
    private JPanel startScreen, levels, game;
    private JTextField name, levelDiff;
    private Font headerFont, font;

    private JButton play, exit, very_easy, easy, middle, hard, backStart, backLevels, answer;

    public App() {
        frame = new JFrame("Судоку");
        startScreen = new JPanel();
        levels = new JPanel();
        game = new JPanel();
        startLayout = new FlowLayout(FlowLayout.CENTER);
        levelsLayout = new FlowLayout();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(550, 650);


    }

    public void setText() {
        headerFont = new Font("Century Gothic", Font.BOLD, 80);
        font = new Font("Century Cothic",Font.PLAIN , 60);

        name = new JTextField("СУДОКУ");
        name.setFont(headerFont);
        name.setEditable(false);
        name.setBackground(Color.WHITE);

        levelDiff = new JTextField("СЛОЖНОСТЬ");
        levelDiff.setFont(headerFont);
        levelDiff.setEditable(false);
        levelDiff.setBackground(Color.WHITE);
    }

    public void setStartScreen() {
        setStartScreenButtons();
        startScreen.setLayout(startLayout);
        startScreen.setBackground(Color.WHITE);
        startScreen.add(name);
        frame.add(startScreen);
        startScreen.add(play);
        startScreen.add(exit);

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
        levels.setBackground(Color.WHITE);
        levels.add(levelDiff);
        setLevelsButtons();
        levels.add(very_easy);
        levels.add(easy);
        levels.add(middle);
        levels.add(hard);
        levels.add(backStart);
    }

    private void setLevelsButtons() {
        very_easy = new JButton("ОЧЕНЬ ЛЕГКИЙ");
        easy = new JButton("ЛЕГКИЙ");
        middle = new JButton("СРЕДНИЙ");
        hard = new JButton("СЛОЖНЫЙ");
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

        backStart.addActionListener(action);
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == play) {
                frame.remove(startScreen);
                frame.add(levels);
            }
            if (e.getSource() == exit) {
                System.exit(0);
            }
            if (e.getSource() == backStart) {
                frame.remove(levels);
                frame.add(startScreen);
            }
        }
    }

}
