package  AntiPlagueInc.Model;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.Cure.CureSettings;

import java.util.List;

import static java.lang.Math.round;


public class TimeCureManager implements Runnable {
    private int seconds;
    private int minutes;
    private List<CountryModel> countries;
    private GameController gameController;
    private Cure cure;
    private static int totalTime;

    private boolean gameRunning; // Wskazuje, czy gra trwa

    public TimeCureManager(GameController gameController, Cure cure) {
        this.seconds = 0;
        this.minutes = 0;
        this.totalTime = 0;
        this.countries = CountryModel.getExtensionCountryies();
        this.gameController = gameController;
        this.cure = cure;
        this.gameRunning = true;
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

    public void stop() {
        gameRunning = false;
    }

    @Override
    public void run() {
        while (gameRunning) {
            try {
                Thread.sleep(1000);
                incrementTime();

                // Aktualizacja wyświetlania czasu
                gameController.passGameView().getTimer().setText("Time: " + formatTime());

                //cure
                cure.addProgress();
                gameController.passGameView().getProgressButton().setText("Progress: " + Math.round(cure.getProgress())+"%");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void incrementTime() {
        seconds++;
        totalTime ++;
        if (seconds >= 60) {
            minutes++;
            seconds = 0;
        }
    }

    private String formatTime() {
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public static int getTotalTime(){
        return totalTime;
    }
}
