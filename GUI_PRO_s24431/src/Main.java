import AntiPlagueInc.Controller.MainMenuController;
import AntiPlagueInc.View.MainMenuView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(()-> new MainMenuController(new MainMenuView()));

    }
}