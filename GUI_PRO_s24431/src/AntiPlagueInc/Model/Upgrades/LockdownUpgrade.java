package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;

public class LockdownUpgrade extends Upgrade {
    public LockdownUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.02);
        setUsed(true);
        System.out.println("LockDown Upgrade");
    }
}
