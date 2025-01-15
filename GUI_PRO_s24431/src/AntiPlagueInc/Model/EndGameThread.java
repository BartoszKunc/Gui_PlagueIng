package  AntiPlagueInc.Model;

import AntiPlagueInc.Controller.GameController;

public class EndGameThread implements Runnable {
    private GameController gameController;
    private boolean running;
    public EndGameThread(GameController gameController) {
        this.gameController = gameController;
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            try{
                Thread.sleep(1000);
                if(gameController.chcekEndGame()) {
                    break;
                }
                gameController.passGameView().getScoreLabel().setText("Points: "+ GameController.getPoints());

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
