package Model;

import Controller.GameController;
import View.GameView;

import java.util.ArrayList;
import java.util.Set;

public class TimeManger implements Runnable{
    private int seconds;
    private int minutes;
    private Set<CountryModel> countries;
    private GameController gameController;

    public TimeManger(GameController gameController) {
        this.seconds = 0;
        this.minutes = 0;
        this.countries = CountryModel.getExtensionCountryies();
        this.gameController = gameController;

    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public void run() {
        Thread tThread = new Thread("TimeThread");
        tThread.start();

        while(isGameRunning()){
            if(seconds >= 60){
                minutes++;
                seconds = 0;
            }

            seconds++;

            gameController.passGameView().getTimer().setText("Time: "+minutes+":"+seconds);

            try {
                tThread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        tThread.interrupt();


    }

    public boolean isGameRunning(){
        boolean running = true;
        int allPopulation = 0;
        int allInfected = 0;
        for (CountryModel country: countries){
            allPopulation += country.getPopulation();
            allInfected += country.getInfected();
        }

        //niema zywych i zainfekowanych to koniec bad ending
        if(allPopulation==0 && allInfected==0){
            running = false;
        }
        //good ending
        if(allInfected==0){
            running = false;
        }

        return running;
    }
}
