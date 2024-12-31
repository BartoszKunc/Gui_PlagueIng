package View;


import Controller.DifficultyController;
import Controller.LeaderboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    private JButton startButton;
    private JButton exitButton;
    private JButton leaderBoardButton;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MainMenuView(){
        init();
        initComponents();
    }

    public void init(){
        this.setTitle("KoronaVirus Game - Main Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(new GridLayout(3,1));
        this.setLocation((int)screenSize.getWidth()/4,(int)screenSize.getHeight()/4);
    }

    public void initComponents(){
        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        leaderBoardButton = new JButton("Leaderboard");

        this.add(startButton);
        this.add(leaderBoardButton);
        this.add(exitButton);

        //wymusza ladownie przyciskow po dodaniu
        this.revalidate();
    }

    public void addStartButtonActionListener(ActionListener al){
        startButton.addActionListener(al);
    }

    public void startGame(){
        new DifficultyController(new DifficultyView());
        this.dispose();
    }

    public void addLeaderBoardButtonActionListener(ActionListener al){
        leaderBoardButton.addActionListener(al);
    }

    public void startLeaderboard(){
        new LeaderboardController(new LeaderboardView());
        this.dispose();
    }

    public void addExitButtonActionListener(ActionListener al){
        exitButton.addActionListener(al);
    }

    public void exitMM(){
        System.exit(0);
    }

}
