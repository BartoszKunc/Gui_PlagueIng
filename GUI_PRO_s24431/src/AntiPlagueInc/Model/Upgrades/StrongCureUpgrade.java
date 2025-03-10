package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;

public class StrongCureUpgrade extends Upgrade {

    public StrongCureUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        cure.addProgress(15);
        setUsed(true);
        System.out.println("Strong Cure upgrade");
    }


}
