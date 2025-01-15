package  AntiPlagueInc.Model;

import java.util.*;

public class CountryModel {
    private static List<CountryModel> extensionCountryies = new ArrayList<CountryModel>();;
    private String name;
    private int population;
    private int initialPopulation;
    private int infected;
    private int recovered;
    private int dead;

    private int x;
    private int y;

    public CountryModel(String name, int population,int x, int y) {
        this.name = name;
        this.population = population;
        this.initialPopulation = population;
        this.infected = 0;
        if(name.equals("Chiny"))
            this.infected = 1;

        this.recovered = 0;
        this.dead = 0;

        this.x = x;
        this.y = y;

        extensionCountryies.add(this);
    }

    public CountryModel() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfected() {
        return infected;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDead() {
        return dead;
    }


    public void setInfected(int infected) {
        this.infected = infected;
    }

    public void infect(int infected){
        if(this.infected+infected>this.population) {
            setInfected(this.population);
        }else
            this.infected += infected;
    }

    public void die(int count){
        if(this.population-count<0) {
            this.population = 0;
        }else
            this.population -= count;
    }

    public void heal(int count){
        if(this.infected-count<0) {
            this.infected = 0;
        }else{
            this.infected-=count;
        }
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getInitialPopulation() {
        return initialPopulation;
    }

    public void setInitialPopulation(int initialPopulation) {
        this.initialPopulation = initialPopulation;
    }

    public static List<CountryModel> getExtensionCountryies() {
        return extensionCountryies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", infected=" + infected +
                ", recovered=" + recovered +
                ", dead=" + dead +
                '}';
    }
}
