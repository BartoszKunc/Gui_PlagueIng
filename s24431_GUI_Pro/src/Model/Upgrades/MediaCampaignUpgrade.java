package Model.Upgrades;

import Model.GameState;
import Model.Virus;

public class MediaCampaignUpgrade extends Upgrade {
    private double mediaInfluence; // Procentowy wzrost wpływu mediów

    public MediaCampaignUpgrade() {
        super("Media CampaignUpgrade", "description", 15);
        this.mediaInfluence = 5;
    }

    @Override
    public void upgrade() {
        Virus virus = GameState.getVirus();
        double reducedInfectionRate = virus.getInfectionRate() * (1 - mediaInfluence / 100);
        virus.setInfectionRate(reducedInfectionRate);
    }


    public double getMediaInfluence() {
        return mediaInfluence;
    }

    public void setMediaInfluence(double mediaInfluence) {
        this.mediaInfluence = mediaInfluence;
    }
}
