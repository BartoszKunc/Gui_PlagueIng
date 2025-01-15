package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class StrongCureUpgrade extends Upgrade {
    public StrongCureUpgrade(String name, int price, Cure cure, Virus virus) {
        super(name, price, cure, virus);
    }

    @Override
    public void upgrade() {
        cure.addProgress(15);
        setUsed(true);
        System.out.println("Strong Cure upgrade");
    }


}
