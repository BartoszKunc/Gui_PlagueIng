package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class PublicSanitationUpgrade extends Upgrade {
    private double sanitationBoost; // Procentowy wzrost higieny publicznej

    public PublicSanitationUpgrade() {
        super("Public Sanitation", "description", 5);
        this.sanitationBoost = 5;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double reducedInfectionRate = virus.getInfectionRate() * (1 - sanitationBoost / 100);
        virus.setInfectionRate(reducedInfectionRate);
    }


    public double getSanitationBoost() {
        return sanitationBoost;
    }

    public void setSanitationBoost(double sanitationBoost) {
        this.sanitationBoost = sanitationBoost;
    }
}
