package Model.Cure;

public class Cure {
    private double progress; // Aktualny postęp szczepionki (w %)
    private double researchSpeed; // Tempo badań (w % na turę)
    private boolean completed; // Czy szczepionka została ukończona

    public Cure(double reasearchSpeed) {
        this.progress = 0.0;
        this.researchSpeed = reasearchSpeed;
        this.completed = false;
    }

    public void addProgress(){
        checkProgress();
        if(progress < 100.0)
            this.progress += this.researchSpeed;
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
}
