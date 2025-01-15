package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class LockdownUpgrade extends Upgrade {
    public LockdownUpgrade(String name, String desc, int price, Cure cure, Virus virus) {
        super(name, desc, price, cure, virus);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.02);
        setUsed(true);
        System.out.println("LockDown Upgrade");
    }
}
