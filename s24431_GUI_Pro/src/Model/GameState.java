package Model;

public class GameState {
    private CountryModel countryModel;
    private static Virus virus;
    private TimeManager timeManager;


    public GameState() {
        this.virus = virus;
    }

    public static Virus getVirus() {
        return virus;
    }
}
