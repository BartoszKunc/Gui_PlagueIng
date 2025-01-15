package AntiPlagueInc.Model.Cure;

public class Cure {
    private double progress; // Aktualny postęp szczepionki (w %)
    private double researchSpeed; // Tempo badań (w % na turę)
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

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getResearchSpeed() {
        return researchSpeed;
    }

    public void setResearchSpeed(double researchSpeed) {
        this.researchSpeed = researchSpeed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }
}
