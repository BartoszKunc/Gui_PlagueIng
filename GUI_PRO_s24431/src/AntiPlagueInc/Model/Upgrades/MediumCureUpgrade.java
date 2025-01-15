package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class MediumCureUpgrade extends Upgrade {
    public MediumCureUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        cure.addProgress(10);
        setUsed(true);
        System.out.println("Medium Cure upgrade");
    }
}
