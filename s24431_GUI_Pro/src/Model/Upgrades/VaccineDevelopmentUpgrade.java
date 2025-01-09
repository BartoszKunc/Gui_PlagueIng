package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class VaccineDevelopmentUpgrade extends Upgrade {
    private int developmentReduction; // Skrócenie czasu opracowania szczepionki (w dniach)

    public VaccineDevelopmentUpgrade() {
        super("Vaccine Dev Upgrade", "Desc", 10);
        this.developmentReduction = 10;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double reducedInfectionRate = virus.getInfectionRate() * (1 - developmentReduction / 100.0);
        virus.setInfectionRate(reducedInfectionRate);
    }


    public int getDevelopmentReduction() {
        return developmentReduction;
    }

    public void setDevelopmentReduction(int developmentReduction) {
        this.developmentReduction = developmentReduction;
    }
}
