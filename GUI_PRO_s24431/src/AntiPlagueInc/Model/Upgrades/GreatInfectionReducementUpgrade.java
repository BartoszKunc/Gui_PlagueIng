package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.*;

public class GreatInfectionReducementUpgrade extends Upgrade{
    public GreatInfectionReducementUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        virus.changeInfectionRate(-0.1);
        setUsed(true);
        System.out.println("Great Infection Reducement Upgrade");
    }
}
