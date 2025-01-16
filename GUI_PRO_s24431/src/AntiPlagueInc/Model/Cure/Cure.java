package AntiPlagueInc.Model.Cure;

public class Cure {
    // Aktualny postęp szczepionki (w %)
    private double progress;
    // Tempo badań %
    private double researchSpeed;
    private boolean completed;
    private int healing;

    public Cure(double reasearchSpeed) {
        this.progress = 0.0;
        this.researchSpeed = reasearchSpeed;
        this.healing = 10;
        this.completed = false;
    }

    public void addProgress(){
        checkProgress();
        if(progress < 100.0)
            this.progress += this.researchSpeed;
    }

    public void addProgress(double amount){
        checkProgress();
        if(progress < 100.0)
            this.progress += amount;
    }

    public void checkProgress(){
        if(progress >= 100){
            progress = 100.0;
            completed = true;
        }
    }

    public double getProgress() {
        return progress;
    }
    public void addProgressSpeed(double amount){
        this.researchSpeed += amount;
    }

    public boolean isCompleted() {
        return completed;
    }


    public int getHealing() {
        return healing;
    }

}
