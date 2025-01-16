package AntiPlagueInc.Controller;

import AntiPlagueInc.Model.LeaderBoardModel;
import AntiPlagueInc.View.MainMenuView;

import javax.swing.*;

public class EndGameController {

    /**
     *
     * @param score
     * Funkcja służąca do pobrania nick'u gracza oraz zapisania wyniku do pliku
     *
     */
    public static void saveScore(int score, int totalDead, int totalRecoverd){
        boolean isDone = false;
        String name = "";

        while(!isDone){
            name = JOptionPane.showInputDialog(null, "Enter your name","Game Ended",JOptionPane.QUESTION_MESSAGE);
            if(name == null || name.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Nick not given, please type your nick!", "Błąd", JOptionPane.ERROR_MESSAGE);

            }else{
                isDone = true;
            }
        }

        JOptionPane.showMessageDialog(null, "Gameover your score is: "+score+"\nTotal killed: "+totalDead+"\nTotal survivors: "+totalRecoverd, "Game Ended", JOptionPane.INFORMATION_MESSAGE);

        LeaderBoardModel lbm = new LeaderBoardModel(name,score);
        LeaderBoardModel.saveToFile();

        for (LeaderBoardModel l : lbm.getExtension()){
            System.out.println(l);
        }

        new MainMenuController(new MainMenuView());
    }

}
