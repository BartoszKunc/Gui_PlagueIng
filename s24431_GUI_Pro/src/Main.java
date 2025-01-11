import Controller.GameController;
import Controller.MainMenuController;
import Model.*;
import View.GameView;
import View.MainMenuView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //tu powinien byc swingutilites.invokelater

        SwingUtilities.invokeLater(()-> new MainMenuController(new MainMenuView()));
        //MainMenuController mmc = new MainMenuController(new MainMenuView());




        
        //test wirus diff
//        Virus virus = VirusSettings.createVirus(DifficultyEnum.EASY);
//        Virus virus = VirusSettings.createVirus(DifficultyEnum.NORMAL);
        Virus virus = VirusSettings.createVirus(DifficultyEnum.HARD);
        System.out.println(virus.getInfectionRate());
        //test timera
//        TimeManager tm1 = new TimeManager(new GameController(new GameView()));
//        Thread t1 = new Thread(tm1);
//        t1.start();
    }
}