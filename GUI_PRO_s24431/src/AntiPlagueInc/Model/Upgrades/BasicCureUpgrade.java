package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class BasicCureUpgrade extends Upgrade {
    public BasicCureUpgrade(String name, int price, Cure cure, Virus virus) {
        super(name, price, cure, virus);
    }

    @Override
    public void upgrade() {
        cure.addProgress(5);
        setUsed(true);
        System.out.println("Basic Cure upgrade");
    }
}
