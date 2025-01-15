package AntiPlagueInc.Model.VirusPackage;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.CountryModel;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.Transport.TransportConnection;
import AntiPlagueInc.Model.Transport.TransportModel;
import AntiPlagueInc.Model.Transport.TransportType;
import AntiPlagueInc.View.GameView;

import java.util.List;

public class VirusThread implements Runnable {
    private Virus virus;
    private CountryModel countryModel;
    private TransportModel transportModel;
    private Cure cure;
    private boolean running;

    public VirusThread(Virus virus, CountryModel countryModel, TransportModel transportModel, Cure cure) {
        this.countryModel = countryModel;
        this.virus = virus;
        this.transportModel = transportModel;
        this.cure = cure;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                synchronized (countryModel) {

                    if (cure.isCompleted()) {
                        //gdy szczepionka gotowa lecz nadal nie dziala
                        heal(cure.getHealing());
                    } else {
                        // zarazanie wewnątrz kraju
                        infectCountry();
                        //zarazanie miedzy krajami
                        List<TransportConnection> connections = transportModel.getAllConnections();
                        for (TransportConnection connection : connections) {
                            if (connection.isOpen() && roll())
                                infectConnectedCountry(connection);
                        }
                    }

                        //zabijanie
                        kill();

                        //uzdrowiena ludzi
                        randomHeal();

                        //sprawdzanie portów
                        transportModel.evaluateAllConnections();

                        if(!GameView.isRunning())
                            running = false;

                    Thread.sleep(1000);
                }

            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getClass().getName() + " stopped");
    }

    public void randomHeal(){
        int randomHealAmount = 0;
        for (CountryModel cm: CountryModel.getExtensionCountryies()){

            //zabezpieczenie przed natychmiastową wygraną dlatego jest 10
            if(cm.getInfected()>10 && roll(0.8)){
                randomHealAmount = (int) (cm.getInfected()/10)+1;

                if(randomHealAmount>10)
                    randomHealAmount = 10;

                if(randomHealAmount>cm.getInfected())
                    randomHealAmount = cm.getInfected();

                System.out.println(cm.getName()+" random healed "+randomHealAmount);
                cm.heal(randomHealAmount);
                GameController.addPoints(randomHealAmount);
            }

        }
    }

    public void infectCountry() {
        for(CountryModel cm: CountryModel.getExtensionCountryies()){

                if (cm.getInfected() > 0  && roll(0.4)) {
                    int newInfection = virus.calculateNewInfections(cm.getPopulation(), cm.getInfected());
                    if (newInfection > cm.getPopulation() || cm.getInfected() + newInfection > cm.getPopulation()) {
                        cm.setInfected(cm.getPopulation());
                    } else {
                        cm.infect(newInfection);
                    }

                }

        }
    }

    public void kill(){
        for(CountryModel cm: CountryModel.getExtensionCountryies()){

            if(cm.getPopulation()>0 && cm.getInfected() > 0 && roll(0.5)) {
                double percentOfInfectedInCountry = (double) cm.getInfected() / cm.getPopulation() * 100;
                if (percentOfInfectedInCountry > 15) {
                    int calculateDeaths = virus.calculateNewDeaths(cm.getInfected())+10;
                    cm.die(calculateDeaths);
                }
            }
        }
    }


    public void infectConnectedCountry(TransportConnection transportConnection){
        CountryModel source = transportConnection.getSource();
        CountryModel destination = transportConnection.getDestination();

        if(source.getInfected()>0 && !cure.isCompleted() && transportConnection.isOpen()){
            int transportInfections = virus.calculateInfections(source, transportConnection.getType());
            destination.infect(transportInfections);

        }

    }

    public void heal(int healing){
        for(CountryModel cm: CountryModel.getExtensionCountryies()){
            if(cm.getPopulation()>0 && cure.isCompleted()){
                int heals = (int)(cm.getInfected()/healing)+10;
                cm.heal(heals);

            }
        }
    }



    public boolean roll(){
        double getsInfected = Math.random();
        if(getsInfected>0.75){
            return true;
        }
        return false;

    }

    public boolean roll(double amount){
        double getsInfected = Math.random();
        if(getsInfected>amount){
            return true;
        }
        return false;

    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
