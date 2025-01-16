package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;

public class MortalityDecreaseUpgrade extends Upgrade{
    public MortalityDecreaseUpgrade(String name, String desc, int price, GameController gameController) {
        super(name, desc, price, gameController);
    }

    @Override
    public void upgrade() {
        virus.changeMortalityRate(-0.05);
        setUsed(true);
        System.out.println("Mortality Decreased");
    }
}
