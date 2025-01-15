package AntiPlagueInc.Controller;

import AntiPlagueInc.View.DifficultyView;

public class DifficultyController {
    private DifficultyView difficultyView;

    public DifficultyController(DifficultyView difficultyView) {
        this.difficultyView = difficultyView;

        this.difficultyView.addBackButtonActionListener(e->backToMM());
        this.difficultyView.addEasyButtonActionListener(e->easy());
        this.difficultyView.addMediumButtonActionListener(e->medium());
        this.difficultyView.addHardButtonActionListener(e->hard());
    }

    //diffView
    public void backToMM(){
        difficultyView.backToMainMenu();
    }

    public void easy(){
        difficultyView.setEasy();
    }

    public void medium(){
        difficultyView.setMedium();
    }

    public void hard(){
        difficultyView.setHard();
    }



}
