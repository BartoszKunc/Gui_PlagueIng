package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class InformationCampaignUpgrade extends Upgrade {
    private double awarenessIncrease; // Zwiększenie świadomości społecznej w procentach

    public InformationCampaignUpgrade() {
        super("Information Campaign", "description", 20);
        this.awarenessIncrease = 5;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double reducedInfectionRate = virus.getInfectionRate() * (1 - awarenessIncrease / 100);
        virus.setInfectionRate(reducedInfectionRate);
    }


    public double getAwarenessIncrease() {
        return awarenessIncrease;
    }

    public void setAwarenessIncrease(double awarenessIncrease) {
        this.awarenessIncrease = awarenessIncrease;
    }
}
