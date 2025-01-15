package  AntiPlagueInc.Model.Cure;

import AntiPlagueInc.Model.DifficultyEnum;

public class CureSettings {
    public static Cure createCure(DifficultyEnum difficulty){
        Cure cure = null;

        if(difficulty == DifficultyEnum.EASY){
            cure = new Cure(0.6);
        }else if(difficulty == DifficultyEnum.NORMAL){
            cure = new Cure(0.4);
        }else{
            cure = new Cure(0.2);
        }
        return cure;
    }
}
