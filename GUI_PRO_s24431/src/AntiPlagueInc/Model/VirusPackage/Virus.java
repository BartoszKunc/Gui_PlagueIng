package AntiPlagueInc.Model.VirusPackage;

import AntiPlagueInc.Model.CountryModel;
import AntiPlagueInc.Model.Transport.TransportType;

public class Virus {

    private double infectionRate; // 0.01 - 1
    private double mortalityRate; // Śmiertelność %
    private boolean active;

    public Virus( double infectionRate, double mortalityRate) {
        this.infectionRate = infectionRate;
        this.mortalityRate = mortalityRate;
        this.active = true;
    }

    //metody

    public int calculateNewInfections(int population, int infected) {
        if (!active) return 0;

        int potentialInfections = (int) Math.ceil(infected * infectionRate);
        int newInfections = 1 + potentialInfections;

        System.out.println("Nowe infekcje w kraju:"+newInfections);
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
        if(infections > 50)
            return 50;
        return infections;
    }

    public int calculateNewDeaths(int infections){
        return (int) Math.ceil(mortalityRate * infections)/5;
    }

    public int calculateNewHeals(int infections){
        return (int) Math.ceil(mortalityRate * infections * 2);
    }

    // Gettery i settery
    public double getInfectionRate() {
        return infectionRate;
    }

    public void setInfectionRate(double infectionRate) {
        this.infectionRate = infectionRate;
    }

    public void changeInfectionRate(double infectionRate) {
        this.infectionRate += infectionRate;
    }

    public double getMortalityRate() {
        return mortalityRate;
    }

    public void setMortalityRate(double mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public void changeMortalityRate(double mortalityRate) {
        this.mortalityRate += mortalityRate;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }





}
