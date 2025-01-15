package AntiPlagueInc.Model.VirusPackage;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.CountryModel;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.Transport.TransportConnection;
import AntiPlagueInc.Model.Transport.TransportModel;
import AntiPlagueInc.Model.Transport.TransportType;

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
                        //zabijanie
                        kill();
                        // zarazanie wewnątrz kraju
                        infectCountry();
                        //zarazanie miedzy krajami
                        List<TransportConnection> connections = transportModel.getAllConnections();
                        for (TransportConnection connection : connections) {
                            if (connection.isOpen() && roll())
                                infectConnectedCountry(connection);
                        }

                        //uzdrowiena ludzi
                        randomHeal();

                        //sprawdzanie portów
                        transportModel.evaluateAllConnections();

                        Thread.sleep(1000);
                    }
                }

            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void randomHeal(){
        int randomHealAmount = 0;
        for (CountryModel cm: CountryModel.getExtensionCountryies()){

            if(cm.getInfected()>0 && roll()){
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

                if (cm.getInfected() > 0 && !cure.isCompleted() && roll(0.35)) {
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

            if(cm.getPopulation()>0 && !cure.isCompleted() && roll(0.5)) {
                double percentOfInfectedInCountry = (double) cm.getInfected() / cm.getPopulation() * 100;
                if (percentOfInfectedInCountry > 25) {
                    int calculateDeaths = virus.calculateNewDeaths(cm.getInfected());
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
                int heals = (int)(cm.getPopulation()/healing);
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
