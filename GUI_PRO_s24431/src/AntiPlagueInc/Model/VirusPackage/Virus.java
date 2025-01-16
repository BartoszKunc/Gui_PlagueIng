package AntiPlagueInc.Model.VirusPackage;

import AntiPlagueInc.Model.CountryModel;
import AntiPlagueInc.Model.Transport.TransportType;

public class Virus {

    // 0.01 - 1
    private double infectionRate;
    // Śmiertelność %
    private double mortalityRate;


    public Virus( double infectionRate, double mortalityRate) {
        this.infectionRate = infectionRate;
        this.mortalityRate = mortalityRate;

    }

    //metody

    public int calculateNewInfections( int infected) {
        int potentialInfections = (int) Math.ceil(infected * infectionRate);
        int newInfections = 1 + potentialInfections;
        return newInfections;
    }

    public int calculateInfections(CountryModel source, TransportType type){
        double infectionRate = 0;

        //procent zarazonych w jednym pojezdzie
        switch(type){
            case CAR -> infectionRate = 0.02;
            case AIRPLANE -> infectionRate = 0.01;
            case SHIP -> infectionRate = 0.02;
        }

        int infections = (int)(source.getInfected()*infectionRate)+1;

        //zabezpieczenie przed nadmiernym zarazaniem przez transport
        if(infections > 50 && type == TransportType.CAR)
            return 4;

        if(infections > 50)
            return 50;

        return infections;
    }

    public int calculateNewDeaths(int infections){
        return (int) Math.ceil(mortalityRate * infections)/5;
    }

    // Gettery i settery

    public void changeInfectionRate(double infectionRate) {
        this.infectionRate += infectionRate;
    }

    public void changeMortalityRate(double mortalityRate) {
        this.mortalityRate += mortalityRate;
    }

}
