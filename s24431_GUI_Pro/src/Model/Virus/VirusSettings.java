package Model.Virus;

import Model.DifficultyEnum;

public class VirusSettings {
    private static DifficultyEnum difficulty;
    public VirusSettings() {
    }

    public static Virus createVirus(DifficultyEnum difficultyEnum) {
        difficulty = difficultyEnum;
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

    public static DifficultyEnum getDifficulty(){
        return difficulty;
    }
}
