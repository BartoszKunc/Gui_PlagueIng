package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class MortalityDecreaseUpgrade extends Upgrade{
    public MortalityDecreaseUpgrade(String name, String desc, int price, Cure cure, Virus virus) {
        super(name, desc, price, cure, virus);
    }

    @Override
    public void upgrade() {
        virus.changeMortalityRate(-0.05);
        setUsed(true);
        System.out.println("Mortality Decreased");
    }
}
