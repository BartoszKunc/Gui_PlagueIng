import Controller.GameController;
import Controller.MainMenuController;
import Model.*;
import View.GameView;
import View.MainMenuView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(()-> new MainMenuController(new MainMenuView()));

    }
}