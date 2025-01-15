package AntiPlagueInc.Controller;

import AntiPlagueInc.View.LeaderboardView;

public class LeaderboardController {
    private LeaderboardView leaderboardView;

    public LeaderboardController(LeaderboardView leaderboardView) {
        this.leaderboardView = leaderboardView;

        this.leaderboardView.addBackButtonActionListener(e->goBackToMM());
    }

    public void goBackToMM() {
        leaderboardView.goBack();
    }


}
