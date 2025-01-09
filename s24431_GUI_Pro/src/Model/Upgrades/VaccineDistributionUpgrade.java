package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class VaccineDistributionUpgrade extends Upgrade {
    private int distributionSpeedIncrease; // Procentowy wzrost szybkości dystrybucji szczepionek

    public VaccineDistributionUpgrade() {
        super("Vaccine Distribution Upgrade", "description", 15);
        this.distributionSpeedIncrease = 15;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double increasedResistance = virus.getResistanceLevel() * (1 + distributionSpeedIncrease / 100.0);
        virus.setResistanceLevel(increasedResistance);
    }


    public int getDistributionSpeedIncrease() {
        return distributionSpeedIncrease;
    }

    public void setDistributionSpeedIncrease(int distributionSpeedIncrease) {
        this.distributionSpeedIncrease = distributionSpeedIncrease;
    }
}
