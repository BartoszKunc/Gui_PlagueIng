package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;

public class BasicCureUpgrade extends Upgrade {
    public BasicCureUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        cure.addProgressSpeed(0.15);
        setUsed(true);
        System.out.println("Basic Cure upgrade");
    }
}
