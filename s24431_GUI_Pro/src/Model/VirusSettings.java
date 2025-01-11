package Model;

public class VirusSettings {
    public VirusSettings() {
    }

    public static Virus createVirus(DifficultyEnum difficultyEnum) {
        Virus virus = null;
        if(difficultyEnum == DifficultyEnum.EASY) {
        virus = new Virus(1.0, 0.01);
        }else if(difficultyEnum == DifficultyEnum.NORMAL) {
        virus = new Virus(1.1, 0.05);
        }else{
        virus = new Virus(1.2, 0.08);
        }

        return virus;
    }
}
