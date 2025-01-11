package Controller;

import Model.*;
import Model.Transport.TransportModel;
import Model.Virus.Virus;
import Model.Virus.VirusSettings;
import Model.Virus.VirusSpreadThread;
import View.GameView;

import java.util.List;


public class GameController {
    private GameView gameView;
    private Virus virus;
    private List<CountryModel> countries;
    private TransportModel transport;

    public GameController(GameView gameView, DifficultyEnum difficulty) {
        this.gameView = gameView;
        this.gameView.addShortCutListener();
        this.countries = CountryModel.getExtensionCountryies();
        // Ustawienie listy krajów w MapPanel
        this.gameView.getMapPanel().setCountries(countries);
        this.transport = MapPanel.getTransportModel();
        this.virus = VirusSettings.createVirus(difficulty);
        VirusSpreadThread vT = new VirusSpreadThread(virus, countries, transport);
        vT.start();

        TimeManager tm = new TimeManager(this);
        Thread t = new Thread(tm);
        t.start();
        //jakos wylaczac terzeba
        if(!tm.isGameRunning()){
            gameView.dispose();
        }
    }

    //gameview


    //reszta
    public GameView passGameView(){
        return gameView;
    }

}
