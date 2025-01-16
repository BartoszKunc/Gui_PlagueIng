package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;

public class NewMedicineUpgrade extends Upgrade {
    public NewMedicineUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        virus.changeMortalityRate(-0.02);
        setUsed(true);
        System.out.println("New Medicine Upgrade");
    }
}
