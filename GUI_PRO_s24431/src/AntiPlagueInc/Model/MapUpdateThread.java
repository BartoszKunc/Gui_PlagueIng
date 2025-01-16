package  AntiPlagueInc.Model;

import AntiPlagueInc.View.GameView;

public class MapUpdateThread implements Runnable{
    private GameView gameView;
    private boolean running;
    public MapUpdateThread(GameView gameView){
        this.gameView = gameView;
        this.running = true;
    }

    @Override
    public void run() {
        while(running){
            try{
                Thread.sleep(100);
                gameView.repaint();
                gameView.getMap().updateCountryButtonTexts();
                if(!GameView.isRunning())
                    running = false;

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(this.getClass().getName() + " stopped");
    }


    public void setRunning(boolean running) {
        this.running = running;
    }
}
