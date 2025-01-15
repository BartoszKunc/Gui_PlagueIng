package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class BasicCureUpgrade extends Upgrade {
    public BasicCureUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        cure.addProgress(5);
        setUsed(true);
        System.out.println("Basic Cure upgrade");
    }
}
