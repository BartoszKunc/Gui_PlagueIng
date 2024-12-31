package Controller;

import View.GameView;



public class GameController {
    private GameView gameView;

    public GameController( GameView gameView) {
        this.gameView = gameView;

        this.gameView.addShortCutListener();
    }

    //gameview


    //reszta
    public GameView passGameView(){
        return gameView;
    }

}
