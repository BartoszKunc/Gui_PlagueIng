package Model.Cure;

import Model.DifficultyEnum;

public class CureSettings {
    public static Cure createCure(DifficultyEnum difficulty){
        Cure cure = null;

        if(difficulty == DifficultyEnum.EASY){
            cure = new Cure(0.2);
        }else if(difficulty == DifficultyEnum.NORMAL){
            cure = new Cure(0.1);
        }else{
            cure = new Cure(0.05);
        }
        return cure;
    }
}
