package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class GreatInfectionReducementUpgrade extends Upgrade{
    public GreatInfectionReducementUpgrade(String name, String desc, int price, Cure cure, Virus virus) {
        super(name, desc, price, cure, virus);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.1);
        setUsed(true);
        System.out.println("Great Infection Reducement Upgrade");
    }
}
