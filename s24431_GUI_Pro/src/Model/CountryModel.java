package Model;

import java.util.*;

public class CountryModel {
    private static List<CountryModel> extensionCountryies = new ArrayList<CountryModel>();;
    private String name;
    private int population;
    private int initialPopulation;
    private int infected;
    private int recovered;
    private int dead;

    private boolean isAirOpen;
    private boolean isCarOpen;
    private boolean isShipOpen;
    private double airThreshold;
    private double carThreshold;
    private double shipThreshold;

    private int x;
    private int y;

    public CountryModel(String name, int population, boolean isShipOpen,int x, int y) {
        this.name = name;
        this.population = population;
        this.initialPopulation = population;
        this.infected = 0;
        if(name.equals("Chiny"))
            this.infected = 1;

        this.recovered = 0;
        this.dead = 0;

        this.isAirOpen = true;
        this.isCarOpen = true;
        this.isShipOpen = isShipOpen;

        this.airThreshold = 0.1 + (0.3 - 0.1) * Math.random();
        this.carThreshold = 0.2 + (0.4 - 0.2) * Math.random();
        this.shipThreshold = 0;
        if(isShipOpen)
            this.shipThreshold = 0.15 + (0.35 - 0.15) * Math.random();

        this.x = x;
        this.y = y;

        extensionCountryies.add(this);
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

    public double getAirThreshold() {
        return airThreshold;
    }

    public double getCarThreshold() {
        return carThreshold;
    }

    public double getShipThreshold() {
        return shipThreshold;
    }

    public void setInfected(int infected) {
        this.infected = infected;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Wylecz część populacji z zarażonych.
     */
    public void recover(int number) {
        if (number > infected) {
            number = infected;
        }
        infected -= number;
        recovered += number;
    }

    public void massRecover(){
        int recoverable = (int) (infected * 0.1);
        recover(recoverable);
    }

    /**
     * Odnotuj zgony (opcjonalne).
     */
    public void die(int number) {
        if (number > infected) {
            number = infected;
        }
        infected -= number;
        dead += number;
        population -= number; // jeśli uznajemy, że populacja się zmniejsza
    }

    public boolean isAirOpen() {
        return isAirOpen;
    }

    public boolean isCarOpen() {
        return isCarOpen;
    }

    public boolean isShipOpen() {
        return isShipOpen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setAirOpen(boolean airOpen) {
        isAirOpen = airOpen;
    }

    public void setCarOpen(boolean carOpen) {
        isCarOpen = carOpen;
    }

    public void setShipOpen(boolean shipOpen) {
        isShipOpen = shipOpen;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
