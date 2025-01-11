package Controller;

import Model.LeaderBoardModel;
import View.GameView;
import View.MainMenuView;

import javax.swing.*;

public class ScoreboardController {

    public static void saveScore(int score){
        String name = JOptionPane.showInputDialog(null, "Enter your name", JOptionPane.QUESTION_MESSAGE);

        if(name == null || name.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nie podano nicku!", "Błąd", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Prosze podać nick");
        }

        LeaderBoardModel lbm = new LeaderBoardModel(name,score);
        LeaderBoardModel.saveToFile();

        for (LeaderBoardModel l : lbm.getExtension()){
            System.out.println(l);
        }

        new MainMenuController(new MainMenuView());
    }

}
