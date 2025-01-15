package  AntiPlagueInc.Model.Upgrades;


import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class TreatmentUpgrade extends Upgrade {

    public TreatmentUpgrade(String name, int price,Cure cure, Virus virus) {
        super(name, price, cure, virus);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.01);
        setUsed(true);
        System.out.println("Treatement upgrade");
    }
}
