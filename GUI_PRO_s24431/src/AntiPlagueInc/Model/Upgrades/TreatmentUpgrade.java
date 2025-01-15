package  AntiPlagueInc.Model.Upgrades;


import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class TreatmentUpgrade extends Upgrade {

    public TreatmentUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.01);
        setUsed(true);
        System.out.println("Treatement upgrade");
    }
}
