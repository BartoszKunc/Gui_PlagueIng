package  AntiPlagueInc.Model;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.Cure.CureSettings;
import AntiPlagueInc.View.GameView;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;




public class TimeCureManager implements Runnable {
    private int seconds;
    private int minutes;
    private List<CountryModel> countries;
    private GameController gameController;
    private Cure cure;
    private static int totalTime;
    private LocalDateTime startTime;

    private boolean gameRunning;

    public TimeCureManager(GameController gameController, Cure cure) {
        this.seconds = 0;
        this.minutes = 0;
        this.totalTime = 0;
        this.startTime = LocalDateTime.now();
        this.countries = CountryModel.getExtensionCountryies();
        this.gameController = gameController;
        this.cure = cure;
        this.gameRunning = true;
    }

    @Override
    public void run() {
        while (gameRunning) {
            try {
                Thread.sleep(1000);

                //liczenie czasu
                incrementTime();

                // Aktualizacja wyświetlania czasu
                gameController.passGameView().getTimer().setText("Time: " + formatTime());

                //cure
                cure.addProgress();
                gameController.passGameView().getProgressButton().setText("Progress: " + Math.round(cure.getProgress())+"%");
                if(!GameView.isRunning())
                    gameRunning = false;

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(this.getClass().getName() + " stopped");
    }

    private void incrementTime() {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        totalTime = (int)duration.getSeconds();
        seconds = totalTime%60;
        minutes = totalTime/60;
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
