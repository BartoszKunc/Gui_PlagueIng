package AntiPlagueInc.Model.VirusPackage;

import AntiPlagueInc.Model.DifficultyEnum;

public class VirusSettings {
    public VirusSettings() {
    }

    public static Virus createVirus(DifficultyEnum difficultyEnum) {
        Virus virus = null;
        if(difficultyEnum == DifficultyEnum.EASY) {
        virus = new Virus(0.2, 0.1);
        }else if(difficultyEnum == DifficultyEnum.NORMAL) {
        virus = new Virus(0.25, 0.2);
        }else{
        virus = new Virus(0.3, 0.3);
        }

        return virus;
    }

}
