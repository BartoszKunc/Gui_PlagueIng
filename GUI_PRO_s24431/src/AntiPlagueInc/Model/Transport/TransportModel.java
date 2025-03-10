package  AntiPlagueInc.Model.Transport;

import AntiPlagueInc.Model.CountryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportModel {

    private static List<TransportConnection> connections;

    public TransportModel() {
        this.connections = new ArrayList<>();
        initTransportConnections();
    }

    // Inicjalizacja połączeń
    public void initTransportConnections() {
        //pobranie listy panstw i ustawienie je w klucz Nazwa, Obiekt
        Map<String, CountryModel> countryMap = new HashMap<>();
        for (CountryModel country : CountryModel.getExtensionCountryies()) {
            countryMap.put(country.getName(), country);

        }
        //Chiny x Rosja
        //Car
        connections.add(new TransportConnection(countryMap.get("Chiny"),countryMap.get("Rosja"),TransportType.CAR, true));
        //Chiny x Brazylia
        //SHIP
        connections.add(new TransportConnection(countryMap.get("Chiny"),countryMap.get("Brazylia"),TransportType.SHIP, true));
        //Chiny x Polska
        //Plane
        connections.add(new TransportConnection(countryMap.get("Chiny"),countryMap.get("Polska"),TransportType.AIRPLANE, true));
        //Rosja x Ukraina
        //Car
        connections.add(new TransportConnection(countryMap.get("Rosja"),countryMap.get("Ukraina"),TransportType.CAR, true));
        //Rosja x USA
        //SHip
        connections.add(new TransportConnection(countryMap.get("Rosja"),countryMap.get("USA"),TransportType.SHIP, true));
        //Ukraina x Polska
        //Car
        connections.add(new TransportConnection(countryMap.get("Ukraina"),countryMap.get("Polska"),TransportType.CAR, true));
        //Ukraina x Kanada
        //airplane
        connections.add(new TransportConnection(countryMap.get("Ukraina"),countryMap.get("Canada"),TransportType.AIRPLANE, true));
        //Kazahstan x Chiny
        //car
        connections.add(new TransportConnection(countryMap.get("Kazachstan"),countryMap.get("Chiny"),TransportType.CAR, true));
        //Kazachstan x Rosja
        //airplane
        connections.add(new TransportConnection(countryMap.get("Rosja"),countryMap.get("Kazachstan"),TransportType.AIRPLANE, true));
        //Polska x Meksyk
        //airplane
        connections.add(new TransportConnection(countryMap.get("Polska"),countryMap.get("Meksyk"),TransportType.AIRPLANE, true));
        //Meksyk x USA
        //Airplane
        connections.add(new TransportConnection(countryMap.get("USA"),countryMap.get("Meksyk"),TransportType.AIRPLANE, true));
        //Meksyk x Polska
        connections.add(new TransportConnection(countryMap.get("Meksyk"),countryMap.get("Polska"),TransportType.SHIP,true));
        //USA x Canada
        //car
        connections.add(new TransportConnection(countryMap.get("USA"),countryMap.get("Canada"),TransportType.CAR, true));
        //Canada x Brazylia
        //airplane
        connections.add(new TransportConnection(countryMap.get("Canada"),countryMap.get("Brazylia"),TransportType.AIRPLANE, true));
        //Brazylia x Argentyna
        //car
        connections.add(new TransportConnection(countryMap.get("Brazylia"),countryMap.get("Argentyna"),TransportType.CAR, true));
        //Argentyna x Rosja
        //ariplane
        connections.add(new TransportConnection(countryMap.get("Argentyna"),countryMap.get("Rosja"),TransportType.AIRPLANE, true));

    }

    public List<TransportConnection> getAllConnections() {
        return connections;
    }

    public void evaluateAllConnections() {
        for (TransportConnection connection : connections) {
            connection.evaluateTransportStatus();
        }
    }

}
