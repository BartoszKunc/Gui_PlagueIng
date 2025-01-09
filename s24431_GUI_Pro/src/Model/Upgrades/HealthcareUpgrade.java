package Model.Upgrades;

import Model.Virus;
import Model.GameState;

public class HealthcareUpgrade extends Upgrade {
    private double healingBoost; // Procentowy wzrost efektywności leczenia

    public HealthcareUpgrade() {
        super("Healtcare Upgrade", "", 30);
        this.healingBoost = 10;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus(); // Zakładamy, że GameState przechowuje instancję wirusa
        double reducedMortality = virus.getMortalityRate() * (1 - healingBoost / 100);
        virus.setMortalityRate(reducedMortality);
    }


    public double getHealingBoost() {
        return healingBoost;
    }

    public void setHealingBoost(double healingBoost) {
        this.healingBoost = healingBoost;
    }
}
