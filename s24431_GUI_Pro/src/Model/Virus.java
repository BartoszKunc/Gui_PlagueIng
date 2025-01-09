package Model;

public class Virus {

    private double infectionRate; // Szybkość rozprzestrzeniania się (współczynnik zaraźliwości)
    private double mortalityRate; // Śmiertelność (w procentach)
    private double resistanceLevel; // Poziom odporności na leczenie
    private int mutationLevel; // Poziom mutacji wirusa
    private boolean active; // Czy wirus jest aktywny w danym momencie gry

    public Virus( double infectionRate, double mortalityRate, double resistanceLevel) {
        this.infectionRate = infectionRate;
        this.mortalityRate = mortalityRate;
        this.resistanceLevel = resistanceLevel;
        this.mutationLevel = 0; // Początkowy poziom mutacji
        this.active = true; // Wirus startuje jako aktywny
    }

    // Gettery i settery
    public double getInfectionRate() {
        return infectionRate;
    }

    public void setInfectionRate(double infectionRate) {
        this.infectionRate = infectionRate;
    }

    public double getMortalityRate() {
        return mortalityRate;
    }

    public void setMortalityRate(double mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public double getResistanceLevel() {
        return resistanceLevel;
    }

    public void setResistanceLevel(double resistanceLevel) {
        this.resistanceLevel = resistanceLevel;
    }

    public int getMutationLevel() {
        return mutationLevel;
    }

    public void mutate() {
        this.mutationLevel++;
        this.infectionRate *= 1.1; // Wzrost szybkości rozprzestrzeniania się
        this.resistanceLevel *= 1.2; // Wzrost odporności na leczenie
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false; // Wyłączenie wirusa, np. po eradykacji
    }

    // Metoda do obliczenia potencjalnej liczby nowych zakażeń
    public int calculateNewInfections(int population, int infected) {
        if (!active) return 0;
        double potentialInfections = infected * infectionRate * (1 - ((double) infected / population));
        return Math.min((int) potentialInfections, population - infected);
    }


}
