package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class SupplyChainUpgrade extends Upgrade {
    private double supplyEfficiencyIncrease; // Procentowy wzrost efektywności dostaw

    public SupplyChainUpgrade() {
        super("Supply Chain Upgrade", "Desc", 15);
        this.supplyEfficiencyIncrease = 10;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double reducedInfectionRate = virus.getInfectionRate() * (1 - supplyEfficiencyIncrease / 100);
        virus.setInfectionRate(reducedInfectionRate);
    }


    public double getSupplyEfficiencyIncrease() {
        return supplyEfficiencyIncrease;
    }

    public void setSupplyEfficiencyIncrease(double supplyEfficiencyIncrease) {
        this.supplyEfficiencyIncrease = supplyEfficiencyIncrease;
    }
}
