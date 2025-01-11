package Model.Virus;

import Controller.ScoreboardController;
import Model.CountryModel;
import Model.Cure.Cure;
import Model.Cure.CureSettings;
import Model.Transport.TransportConnection;
import Model.Transport.TransportModel;
import View.GameView;

import javax.swing.*;
import java.util.List;

public class VirusSpreadThread extends Thread {

    private Virus virus;                     // obiekt wirusa z infectionRate, mortalityRate
    private List<CountryModel> countries;    // lista krajów
    private TransportModel transportModel;    // połączenia transportowe
    private Cure cure;
    private boolean running;                 // flaga do zatrzymywania wątku
    private long spreadInterval;             // co ile ms ma być aktualizacja (np. 1 sekunda)
    private int score = 0;

    public VirusSpreadThread(Virus virus, List<CountryModel> countries, TransportModel transportModel) {
        this.virus = virus;
        this.countries = countries;
        this.transportModel = transportModel;
        this.spreadInterval = 1000;  // np. 1000ms = 1 sekunda
        this.running = true;
        this.cure = CureSettings.createCure(VirusSettings.getDifficulty());
    }

    @Override
    public void run() {
        while (running && virus.isActive()) {
            System.out.println("Start spread");
            try {
                adjustSpreadInterval(); // Dostosuj tempo rozprzestrzeniania na podstawie stanu globalnego
                spreadWithinCountries();
                spreadBetweenCountries();
                applyMortality();

                //cure
//                progressCalc();
//                randomHeal();


                checkEndGameConditions();
                if (!running) break;

                SwingUtilities.invokeLater(() -> GameView.getMapPanel().repaint());
                Thread.sleep(spreadInterval); // Dynamiczne tempo przerwy między turami
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

//    private void progressCalc(){
//        cure.addProgress();
//        healIfCure();
//        SwingUtilities.invokeLater(() -> GameView.updateCureProgress(cure.getProgress()));
//    }

    private void randomHeal() {
        for (CountryModel country : countries) {
            if ((double) country.getInfected() / country.getInitialPopulation() > 0.05) { // Jeśli infekcja > 5%
                int healAmount = (int) (country.getInfected() * 0.02); // Wylecz 2% zainfekowanych
                country.recover(healAmount);
                System.out.println("Wyleczono " + healAmount + " w kraju: " + country.getName());
            }
        }
    }

    private void healIfCure(){
        if(cure.isCompleted()){
            for(CountryModel country : countries){
                country.massRecover();
            }
        }
    }

    private void adjustSpreadInterval() {
        int totalPopulation = countries.stream().mapToInt(CountryModel::getPopulation).sum();
        int totalInfected = countries.stream().mapToInt(CountryModel::getInfected).sum();

        double infectionRate = (double) totalInfected / totalPopulation;

        if (infectionRate < 0.25) {
            spreadInterval = 2000; // Wolniejsze rozprzestrzenianie
        } else if (infectionRate < 0.75) {
            spreadInterval = 500;  // Szybsze rozprzestrzenianie
        } else {
            spreadInterval = 1500; // Wolniejsze rozprzestrzenianie
        }

        System.out.println("Adjusting spread interval: " + spreadInterval + " ms (infection rate: " + infectionRate + ")");
    }



    private void spreadWithinCountries() {

            for (CountryModel c : countries) {
                if (c.getInfected() > 0) {
                    int newInfections = virus.calculateNewInfections(c.getPopulation(), c.getInfected());
                    newInfections = Math.max(1, newInfections);


                    int total = c.getInfected() + newInfections;

                    if (total > c.getPopulation()) {
                        total = c.getPopulation();

                    }

                    c.setInfected(total);

                }
            }

    }


    private void spreadBetweenCountries() {
        for (CountryModel sourceCountry : countries) {
            if (sourceCountry.getInfected() <= 0) {
                continue; // Brak zakażonych, nie ma kto przenosić
            }

            // Przeglądamy wszystkie połączenia
            for (TransportConnection conn : transportModel.getAllConnections()) {
                if (!conn.isOpen() || Math.random() < 0.1) {
                    continue; // Zamknięte połączenie lub 10% szans na pominięcie
                }

                CountryModel src = conn.getSource();
                CountryModel dst = conn.getDestination();

                // Rozprzestrzenienie z losową szansą
                if (Math.random() > 0.2) { // 80% szans na rozprzestrzenienie
                    if (src.equals(sourceCountry)) {
                        infectCountry(sourceCountry, dst);
                    } else if (dst.equals(sourceCountry)) {
                        infectCountry(sourceCountry, src);
                    }
                }
            }
        }
    }


    private void infectCountry(CountryModel infectedCountry, CountryModel targetCountry) {
        if (targetCountry.getInfected() >= targetCountry.getPopulation()) {
            return; // Kraj docelowy jest już w pełni zakażony
        }

        // Procent zakażonych w kraju źródłowym przenosi się do kraju docelowego
        int newInfections = virus.calculateNewInfections(
                targetCountry.getPopulation(),
                targetCountry.getInfected()
        );

        // Skuteczność transportu z losowym współczynnikiem
        double baseTransferRate = 0.1; // np. 10% przenoszone przez transport
        double randomFactor = 0.5 + Math.random(); // Od 0.5 do 1.5
        int infectionsFromTransport = (int) (infectedCountry.getInfected() * baseTransferRate * randomFactor);

        // Dodaj szansę na większe rozprzestrzenienie w zależności od transportu
        double chanceFactor = Math.random(); // Losowa szansa
        if (chanceFactor > 0.75) {
            infectionsFromTransport *= 2; // Podwójna infekcja z losową szansą
        }

        // Ostateczna liczba infekcji
        int infectedNow = targetCountry.getInfected() + infectionsFromTransport;

        // Ograniczamy do populacji kraju docelowego
        if (infectedNow > targetCountry.getPopulation()) {
            infectedNow = targetCountry.getPopulation();
        }

        targetCountry.setInfected(infectedNow);

        System.out.println("Zarażono " + infectionsFromTransport
                + " w kraju: " + targetCountry.getName()
                + " (łącznie: " + infectedNow + ")");
    }


    private void applyMortality() {
        for (CountryModel c : countries) {
            if (c.getInfected() > 0) {
                // Oblicz procent pozostałej populacji w kraju
                double remainingPopulationRate = (double) c.getPopulation() / c.getInitialPopulation();

                // Dostosuj współczynnik śmiertelności w zależności od populacji kraju
                double dynamicMortalityRate = virus.getMortalityRate();
                if (remainingPopulationRate < 0.2) {
                    dynamicMortalityRate *= 2; // Podwójna śmiertelność, gdy kraj ma <20% populacji
                }

                // Oblicz liczbę zgonów
                int deaths = (int) Math.ceil(c.getInfected() * dynamicMortalityRate);

                // Zmniejsz liczbę zakażonych
                int remainingInfected = c.getInfected() - deaths;
                if (remainingInfected < 0) {
                    remainingInfected = 0; // Nie możemy mieć ujemnej liczby zakażonych
                }

                // Zmniejsz ogólną populację kraju
                int newPopulation = c.getPopulation() - deaths;
                if (newPopulation < 0) {
                    newPopulation = 0; // Nie możemy mieć ujemnej populacji
                }

                // Aktualizuj wartości w modelu kraju
                c.setInfected(remainingInfected);
                c.setPopulation(newPopulation);

                System.out.println("Kraj: " + c.getName() + " - Zmarli: " + deaths
                        + " - Populacja: " + newPopulation + " - Zarażeni: " + remainingInfected);
            }
        }
    }




    private void checkEndGameConditions() {
        boolean allDead = true;   // Flaga, czy wszyscy są martwi
        boolean noInfected = true; // Flaga, czy nie ma żadnych zainfekowanych

        for (CountryModel c : countries) {
            if (c.getPopulation() > 0) {
                allDead = false; // Jeśli ktoś jest żywy, ustawiamy na false
            }
            if (c.getInfected() > 0) {
                noInfected = false; // Jeśli ktoś jest zainfekowany, ustawiamy na false
            }
        }

        // Warunek 1: wszyscy zmarli
        if (allDead) {
            System.out.println("Gra zakończona: nie ma żywych osób na mapie.");
            virus.deactivate();
            running = false;
            endGame();
        }
        // Warunek 2: brak zainfekowanych
        else if (noInfected) {
            System.out.println("Gra zakończona: brak zainfekowanych na mapie.");
            virus.deactivate();
            running = false;
            endGame();
        }
    }


    public void stopSpread() {
        running = false;
    }

    private void endGame(){
        ScoreboardController.saveScore(score);
    }

    private void calculateScore() {


        int totalPopulation = 0;
        int deadCount = 0;
        int allRecovered = 0;
        for (CountryModel c : countries) {
            totalPopulation += c.getPopulation();
            deadCount += c.getInitialPopulation();
            allRecovered += c.getRecovered();
        }

        deadCount-=totalPopulation;

        // Punkty za wyleczonych
        score += (allRecovered);

        //Punkty zabrane za zmarłych
        score -= (deadCount);

    }


    public boolean isRunning() {
        return running;
    }

    public long getSpreadInterval() {
        return spreadInterval;
    }

    public void setSpreadInterval(long spreadInterval) {
        this.spreadInterval = spreadInterval;
    }

    public Cure getCure() {
        return cure;
    }
}
