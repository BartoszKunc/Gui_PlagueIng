package AntiPlagueInc.Controller;


import AntiPlagueInc.Model.*;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.Cure.CureSettings;
import AntiPlagueInc.Model.Transport.TransportModel;
import AntiPlagueInc.Model.VirusPackage.Virus;
import AntiPlagueInc.Model.VirusPackage.VirusSettings;
import AntiPlagueInc.Model.VirusPackage.VirusThread;
import AntiPlagueInc.View.GameView;
import AntiPlagueInc.View.UpgradeView;


public class GameController {
    private GameView gameView;
    private Virus virus;
    private CountryModel countryModel;
    private TransportModel transport;
    private DifficultyEnum difficulty;
    private Cure cure;
    private static int upgradePoints;

    //zmienne do watkow
    private TimeCureManager tm;
    private Thread tct;
    private MapUpdateThread mut;
    private Thread mapUpdateThread;
    private VirusThread vT;
    private Thread virusThread;
    private EndGameThread egt;
    private Thread endGameThread;



    public GameController(GameView gameView, DifficultyEnum difficulty) {
        //Przypisanie
        this.gameView = gameView;
        this.gameView.addShortCutListener();
        this.countryModel = new CountryModel();
        this.difficulty = difficulty;
        this.cure = CureSettings.createCure(difficulty);
        upgradePoints = 0;
        this.virus = VirusSettings.createVirus(difficulty);
        this.transport = new TransportModel();


        // ActionListenery
        this.gameView.addActionListenerToUpgrade(e->enableUpgrades());
        this.gameView.addShortCutListener();

        //wątki
        //wątek czasu i postępu szczepionki
        this.tm = new TimeCureManager(this, cure);
        this.tct = new Thread(tm);
        tct.start();


        //wątek odświeżający view gry
        this.mut = new MapUpdateThread(gameView);
        mapUpdateThread = new Thread(mut);
        mapUpdateThread.start();

        //wątek virusa
        this.vT = new VirusThread(virus,countryModel, transport,cure);
        this.virusThread = new Thread(vT);
        virusThread.start();

        //watek koncowy
        this.egt = new EndGameThread(this);
        this.endGameThread = new Thread(egt);
        endGameThread.start();

    }


    //metody

    /**
     * funkcja sprawdza całkowitą populację oraz zainfekowanych
     * posiada definicje warunków koncowych:
     * Przegrana gdy nie ma już ludzi
     * Wygrana gdy są ludzie i nie ma zainfekowanych
     * zwraca status gry, czy trwa czy się skończyła
     */
    public boolean chcekEndGame(){
        int allPopulation = 0;
        int allInfected = 0;

        for(CountryModel model: CountryModel.getExtensionCountryies()){
            allPopulation += model.getPopulation();
            allInfected += model.getInfected();
        }

        if(allPopulation == 0 && allInfected == 0){
            int score = 1;
            for(CountryModel model: CountryModel.getExtensionCountryies()){
                score+=model.getPopulation();
            }
            endGame(scoreCalc(score));
            return true;
        }
        if(allPopulation > 0 && allInfected == 0){
            int score = 1;
            for(CountryModel model: CountryModel.getExtensionCountryies()){
                score+=model.getPopulation();
            }
            endGame(scoreCalc(score));
            return true;
        }
        return false;
    }


    /**
     * Funkcja do obliczania wyniku koncowego na podstawie ludzi żyjących, trudności i czasu
     * zwraca wynik
     */
    public int scoreCalc(int score){
        int finalScore = 0;
        if(difficulty == DifficultyEnum.EASY){
            finalScore = (int) ((score*0.8)-TimeCureManager.getTotalTime());
        }else if(difficulty == DifficultyEnum.HARD){
            finalScore = (int) ((score*1.2)-TimeCureManager.getTotalTime());
        }else{
            finalScore = (int) (score-TimeCureManager.getTotalTime());
        }
        if(finalScore < 0)
            finalScore = 0;

        System.out.println(TimeCureManager.getTotalTime()+" "+finalScore);

        return finalScore;
    }

    /**
     * Funkcja przekazuje wątkom flagi do zakończenia działania, uruchamia ekran końcowy
     *
     */
    public void endGame(int score){
        EndGameController.saveScore(score);
        gameView.dispose();
        killAllThreads();

    }

    /**
     * Funkcja przekazuje flagi dla wątków
     */
    public void killAllThreads(){
        tm.setGameRunning(false);
        mut.setRunning(false);
        vT.setRunning(false);
        egt.setRunning(false);
    }

    public static void addPoints(int amount){
        upgradePoints+=amount;
    }

    //gameview
    public void enableUpgrades(){
        new UpgradeController(new UpgradeView(), this);
    }

    //getter/settery
    public GameView passGameView(){
        return gameView;
    }

    public static int getPoints(){
        return upgradePoints;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public Cure getCure(){
        return cure;
    }

    public Virus getVirus(){
        return virus;
    }

}
