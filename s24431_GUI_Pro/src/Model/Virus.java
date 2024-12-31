package Model;

public class Virus {
    private double infectionRate;
    private int oneTickInfectionAmount;
    private final String name = "Koronavirus";
    private CountryModel countryModel;

    public Virus(DifficultyEnum difficulty, CountryModel countryModel) {
        this.countryModel = countryModel;

        if(difficulty == DifficultyEnum.EASY){
           this.infectionRate = 0.7;
           this.oneTickInfectionAmount = 3;
        }

        if(difficulty == DifficultyEnum.NORMAL){
            this.infectionRate = 1;
            this.oneTickInfectionAmount = 5;
        }

        if(difficulty == DifficultyEnum.HARD){
            this.infectionRate = 1.4;
            this.oneTickInfectionAmount = 10;
        }
    }

    public void infect() {
        countryModel.setInfected(countryModel.getInfected() + (int)(oneTickInfectionAmount*infectionRate));


    }


}
