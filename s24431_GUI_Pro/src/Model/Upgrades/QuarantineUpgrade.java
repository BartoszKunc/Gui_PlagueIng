package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class QuarantineUpgrade extends Upgrade {
    private double quarantineEffectiveness; // Procentowy wzrost efektywności kwarantanny

    public QuarantineUpgrade() {
        super("Quarantie Upgrade", "Desc", 15);
        this.quarantineEffectiveness = 5;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double reducedInfectionRate = virus.getInfectionRate() * (1 - quarantineEffectiveness / 100);
        virus.setInfectionRate(reducedInfectionRate);
    }


    public double getQuarantineEffectiveness() {
        return quarantineEffectiveness;
    }

    public void setQuarantineEffectiveness(double quarantineEffectiveness) {
        this.quarantineEffectiveness = quarantineEffectiveness;
    }
}
