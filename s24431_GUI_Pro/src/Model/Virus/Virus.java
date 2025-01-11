package Model.Virus;

public class Virus {

    private double infectionRate; // Szybkość rozprzestrzeniania się (współczynnik zaraźliwości)
    private double mortalityRate; // Śmiertelność (w procentach)
    private int mutationLevel; // Poziom mutacji wirusa
    private boolean active; // Czy wirus jest aktywny w danym momencie gry

    public Virus( double infectionRate, double mortalityRate) {
        this.infectionRate = infectionRate;
        this.mortalityRate = mortalityRate;
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



    public int getMutationLevel() {
        return mutationLevel;
    }

    public void mutate() {
        this.mutationLevel++;
        this.infectionRate *= 1.1; // Wzrost szybkości rozprzestrzeniania się
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false; // Wyłączenie wirusa, np. po eradykacji
    }

    public int calculateNewInfections(int population, int infected) {
        if (!active) return 0;

        // Minimalny przyrost zakażeń, jeśli są jeszcze zdrowi
        double potentialInfections = infected * infectionRate;
        int result = Math.min((int) Math.ceil(potentialInfections), population - infected);

        // Upewniamy się, że jeśli są zdrowi, dodajemy przynajmniej 1 nową infekcję
        if (result == 0 && infected < population) {
            result = 1;
        }

        return result;
    }




}
