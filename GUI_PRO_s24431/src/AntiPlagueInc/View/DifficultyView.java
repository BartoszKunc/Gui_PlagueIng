package AntiPlagueInc.View;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Controller.MainMenuController;
import AntiPlagueInc.Model.DifficultyEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DifficultyView extends JFrame {
    private DifficultyEnum difficulty;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton backButton;



    public DifficultyView() {
        init();
        initComponents();
    }

    public void init(){
        this.setTitle("KoronaVirus - Difficulty selection");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(4,1));
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents(){
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        backButton = new JButton("Back");

        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
        this.add(backButton);
    }

    public void addEasyButtonActionListener(ActionListener listener){
        easyButton.addActionListener(listener);
    }

    public void setEasy(){
        difficulty = DifficultyEnum.EASY;
        new GameController(new GameView(),difficulty);
        this.dispose();
    }


    public void addMediumButtonActionListener(ActionListener listener){
        mediumButton.addActionListener(listener);
    }

    public void setMedium(){
        difficulty = DifficultyEnum.NORMAL;
        new GameController(new GameView(),difficulty);
        this.dispose();
    }

    public void addHardButtonActionListener(ActionListener listener){
        hardButton.addActionListener(listener);
    }

    public void setHard(){
        difficulty = DifficultyEnum.HARD;
        new GameController(new GameView(),difficulty);
        this.dispose();
    }

    public void addBackButtonActionListener(ActionListener listener){
        backButton.addActionListener(listener);
    }

    public void backToMainMenu(){
        new MainMenuController(new MainMenuView());
        this.dispose();
    }
}
