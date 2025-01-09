import Controller.GameController;
import Controller.MainMenuController;
import Model.TimeManager;
import View.GameView;
import View.MainMenuView;

public class Main {
    public static void main(String[] args) {
        //tu powinien byc swingutilites.invokelater

        //MainMenuController mmc = new MainMenuController(new MainMenuView());
        TimeManager tm1 = new TimeManager(new GameController(new GameView()));
        Thread t1 = new Thread(tm1);
        t1.start();
    }
}