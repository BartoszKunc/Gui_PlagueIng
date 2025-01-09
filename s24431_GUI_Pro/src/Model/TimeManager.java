package Model;

import Controller.GameController;

import java.util.List;


public class TimeManager implements Runnable {
    private int seconds;
    private int minutes;
    private List<CountryModel> countries;
    private GameController gameController;

    private boolean gameRunning; // Wskazuje, czy gra trwa

    public TimeManager(GameController gameController) {
        this.seconds = 0;
        this.minutes = 0;
        this.countries = CountryModel.getExtensionCountryies();
        this.gameController = gameController;
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
                Thread.sleep(1000); // Opóźnienie 1 sekundy
                incrementTime(); // Zwiększenie licznika czasu

                // Aktualizacja wyświetlania czasu w widoku
                gameController.passGameView().getTimer().setText("Time: " + formatTime());

                // Sprawdzenie, czy gra nadal powinna trwać
                gameRunning = isGameRunning();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Obsługa przerwania wątku
                break;
            }
        }
    }

    private void incrementTime() {
        seconds++;
        if (seconds >= 60) {
            minutes++;
            seconds = 0;
        }
    }

    private String formatTime() {
        // Formatowanie czasu jako mm:ss
        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isGameRunning() {
        int totalPopulation = 0;
        int totalInfected = 0;

        for (CountryModel country : countries) {
            totalPopulation += country.getPopulation();
            totalInfected += country.getInfected();

            if(country.getName().equals("Chiny")){
                System.out.println(country.getInfected());
            }
        }

        System.out.println(totalPopulation+" "+totalInfected);

        // Jeśli brak żywych i zainfekowanych, koniec gry - bad ending
        if (totalPopulation == 0 && totalInfected == 0) {
            return false;
        }

        // Jeśli brak zainfekowanych, koniec gry - good ending
        if (totalInfected == 0) {
            return false;
        }

        return true; // Gra trwa
    }
}
