package AntiPlagueInc.Controller;

import AntiPlagueInc.View.MainMenuView;

public class MainMenuController {
    private MainMenuView mainMenuView;

    public MainMenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
        this.mainMenuView.addStartButtonActionListener(e->startControll());
        this.mainMenuView.addExitButtonActionListener(e->exitControll());
        this.mainMenuView.addLeaderBoardButtonActionListener(e->leaderBoardControll());
    }

    public void startControll(){
        mainMenuView.startGame();

    }

    public void exitControll(){
        mainMenuView.exitMM();
    }

    public void leaderBoardControll(){
        mainMenuView.startLeaderboard();

    }
}
