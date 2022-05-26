package sudoku;

import javax.swing.*;
import java.awt.*;

public class App {

    private GridLayout layout;
    private JFrame frame;
    private JPanel startScreen;
    private JPanel levels;
    private JPanel game;
    private JTextField name;
    Font headerFont;
    Font font;

    private JButton play;
    private JButton exit;

    public App() {
        frame = new JFrame("Судоку");
        layout = new GridLayout();
        startScreen = new JPanel();
        levels = new JPanel();
        game = new JPanel();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(550, 650);
        frame.setLayout(layout);
    }

    public void setText() {
        headerFont = new Font("Century Gothic", Font.BOLD, 80);
        font = new Font("Century Cothic",Font.PLAIN , 60);
        name = new JTextField("СУДОКУ");
        name.setFont(headerFont);
        name.setEditable(false);
        name.setBackground(Color.WHITE);
    }

    public void setStartScreen() {
        startScreen.setBackground(Color.WHITE);
        startScreen.add(name);
        frame.add(startScreen);
        setStartScreenButtons();
        startScreen.add(play);
        startScreen.add(exit);
    }

    private void setStartScreenButtons() {
        play = new JButton("ИГРАТЬ");
        play.setFont(font);
        play.setBackground(Color.decode("#D2F8F3"));
        exit = new JButton("ВЫЙТИ");
        exit.setFont(font);
        exit.setBackground(Color.decode("#D2F8F3"));
    }

}
