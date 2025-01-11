package Model.Upgrades;

import Model.Virus.VirusSpreadThread;

public class TreatmentUpgrade extends Upgrade {
    private VirusSpreadThread virusThread;

    public TreatmentUpgrade(VirusSpreadThread virusThread) {
        super("Faster Treatment", 50);
        this.virusThread = virusThread;
    }

    @Override
    public void upgrade() {
        long currentInterval = virusThread.getSpreadInterval();
        long newInterval = (long) (currentInterval * 0.9); // Zmniejszamy czas o 10%
        virusThread.setSpreadInterval(newInterval);
        System.out.println("Treatment speed increased. Spread interval: " + newInterval + " ms");
    }
}
