package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class NewMedicineUpgrade extends Upgrade {
    public NewMedicineUpgrade(String name, int price, Cure cure, Virus virus) {
        super(name, price, cure, virus);
    }

    @Override
    public void upgrade() {
        virus.changeMortalityRate(-0.02);
        setUsed(true);
        System.out.println("New Medicine Upgrade");
    }
}
