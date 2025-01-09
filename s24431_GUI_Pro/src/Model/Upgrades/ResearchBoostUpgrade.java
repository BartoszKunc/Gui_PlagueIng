package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class ResearchBoostUpgrade extends Upgrade {
    private int researchSpeedIncrease; // Procentowy wzrost szybkości badań

    public ResearchBoostUpgrade() {
        super("Research Boost", "Desc", 15);
        this.researchSpeedIncrease = 5;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double increasedResistance = virus.getResistanceLevel() * (1 + researchSpeedIncrease / 100.0);
        virus.setResistanceLevel(increasedResistance);
    }


    public int getResearchSpeedIncrease() {
        return researchSpeedIncrease;
    }

    public void setResearchSpeedIncrease(int researchSpeedIncrease) {
        this.researchSpeedIncrease = researchSpeedIncrease;
    }
}
