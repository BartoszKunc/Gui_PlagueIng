import Controller.MainMenuController;
import View.MainMenuView;

public class Main {
    public static void main(String[] args) {
        MainMenuController mmc = new MainMenuController(new MainMenuView());
    }
}