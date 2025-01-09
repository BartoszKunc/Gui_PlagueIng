package Model;

public class GameState {
    private static Virus virus;

    public GameState(Virus virus) {
        this.virus = virus;
    }

    public static Virus getVirus() {
        return virus;
    }
}
