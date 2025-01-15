package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class MediumCureUpgrade extends Upgrade {
    public MediumCureUpgrade(String name, int price, Cure cure, Virus virus) {
        super(name, price, cure, virus);
    }

    @Override
    public void upgrade() {
        cure.addProgress(10);
        setUsed(true);
        System.out.println("Medium Cure upgrade");
    }
}
