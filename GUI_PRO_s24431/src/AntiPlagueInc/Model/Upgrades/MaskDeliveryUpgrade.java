package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class MaskDeliveryUpgrade extends Upgrade {

    public MaskDeliveryUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.02);
        setUsed(true);
        System.out.println("Treatement upgrade");
    }
}
