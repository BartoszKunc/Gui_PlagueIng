package AntiPlagueInc.Controller;

import AntiPlagueInc.Model.Upgrades.*;
import AntiPlagueInc.View.UpgradeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class UpgradeController {
    private UpgradeView view;
    private GameController gameController;
    private ArrayList<Upgrade> upgrades;

    public UpgradeController(UpgradeView view, GameController gameController) {
        this.gameController = gameController;
        this.view = view;

        // Inicjalizacja ulepszeń
        Upgrade.initUpgrades(gameController);
        this.upgrades = Upgrade.getUpgrades();

        // Tworzenie panelu ulepszeń
        view.createUpgradePanel(upgrades, this::purchaseUpgrade);
    }

    public void purchaseUpgrade(ActionEvent e) {
        Upgrade upgrade = (Upgrade) e.getSource();

        if (gameController.getPoints() >= upgrade.getPrice() && !upgrade.isUsed()) {
            gameController.addPoints(-upgrade.getPrice());
            upgrade.upgrade();
            upgrade.setUsed(true);

            JOptionPane.showMessageDialog(
                    view,
                    "Successfully purchased: " + upgrade.getName(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            view.disableUpgrade(upgrade);
        } else {
            JOptionPane.showMessageDialog(
                    view,
                    "Not enough points to purchase " + upgrade.getName() + "!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public UpgradeView getView() {
        return view;
    }
}
