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
        this.gameController= gameController;
        this.view = view;
        upgrades = new ArrayList<>();

        //dodanie upgradów
        initUpgrades();

        view.createUpgradePanel(upgrades, this::purchaseUpgrade);
    }


    /**
     * Inicjalizacja pakietów ulepszeń
     */
    public void initUpgrades(){
        upgrades.add(new MortalityDecreaseUpgrade("Mortality Decrese Upgrade","Decrease mortality rate", 400, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new TreatmentUpgrade("Treatement Upgrade","Decreace infection rate", 500, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new BasicCureUpgrade("Basic Cure Upgrade","Advance progress", 500, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new NewMedicineUpgrade("New Medicine Upgrade","Decreace infection rate", 500, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new LockdownUpgrade("LockDown Upgrade","Decreace infection rate", 500, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new MaskDeliveryUpgrade("Mask Delivery Upgrade","Decreace infection rate", 750, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new GreatInfectionReducementUpgrade("Great Infection Reducement Upgrade","Decreace infection rate", 1000, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new MediumCureUpgrade("Medium Cure Upgrade","Advance progress", 1000, gameController.getCure(), gameController.getVirus()));
        upgrades.add(new StrongCureUpgrade("Strong Cure Upgrade","Advance progress", 1250, gameController.getCure(), gameController.getVirus()));

    }

    /**
     * Funkcja obsługująca możliwość zakupu wraz z zakupem
     *
     */
    public void purchaseUpgrade(ActionEvent e) {
        // Pobranie obiektu Upgrade z ActionEvent
        Upgrade upgrade = (Upgrade) e.getSource();

        if (gameController.getPoints() >= upgrade.getPrice()) {
            gameController.addPoints(-upgrade.getPrice());
            upgrade.upgrade();
            upgrade.setUsed(true);

        } else {
            JOptionPane.showMessageDialog(
                    view,
                    "Not enough points to purchase " + upgrade.getName() + "!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    public GameController getGameController() {
        return gameController;
    }
}
