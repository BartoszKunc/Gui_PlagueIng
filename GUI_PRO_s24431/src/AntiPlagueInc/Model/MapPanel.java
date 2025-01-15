package AntiPlagueInc.Model;

import AntiPlagueInc.Model.Transport.AirplaneThread;
import AntiPlagueInc.Model.Transport.TransportConnection;
import AntiPlagueInc.Model.Transport.TransportModel;
import AntiPlagueInc.Model.Transport.TransportType;
import AntiPlagueInc.Model.Upgrades.Upgrade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private Image mapImage;
    private List<CountryModel> countries;

    private static TransportModel transportModel;

    public MapPanel() {
        // Wczytanie grafiki mapy
        ImageIcon icon = new ImageIcon("src/resources/png/wordlMap.png");
        mapImage = icon.getImage();


        //init krajów i transportu
        initCountries();
        transportModel = new TransportModel();

        setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));
    }

    private void initCountries() {
        CountryModel.resetCountries();
        Upgrade.cleanUpgrades();
        countries = new ArrayList<>();
        countries.add(new CountryModel("Chiny", 2000000,1400, 550));
        countries.add(new CountryModel("Rosja", 190000,1140, 350));
        countries.add(new CountryModel("Ukraina", 70000,1015, 460));
        countries.add(new CountryModel("Polska", 38000,945, 436));
        countries.add(new CountryModel("Meksyk", 1000000,310, 620));
        countries.add(new CountryModel("Kazachstan", 80000,1990, 450));
        countries.add(new CountryModel("Argentyna", 30000,515, 940));
        countries.add(new CountryModel("Canada", 160000,250, 350));
        countries.add(new CountryModel("USA", 192000,250, 500));
        countries.add(new CountryModel("Brazylia", 600000,530, 780));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rysowanie tła
        g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        // Rysowanie portów
        drawPorts(g);

        // nazwy krajow + dane
        for (CountryModel c : countries) {

            int scaledX = (int) (c.getX() / (double) mapImage.getWidth(null) * getWidth());
            int scaledY = (int) (c.getY() / (double) mapImage.getHeight(null) * getHeight());

            // Rysowanie nazwy (zakażeń, populacji itp.)
            g.setColor(Color.red);
            g.drawString(c.getName() + ": " + c.getInfected() + "/" + c.getPopulation(), scaledX, scaledY);

        }

    }


    /**
     * Metody pomocnicze do wyswietlenia otwartych transportów
     */
    private void drawPorts(Graphics g) {
        if (transportModel == null) return;

        java.util.List<TransportConnection> connections = transportModel.getAllConnections();
        Graphics2D g2 = (Graphics2D) g;

        Image airplaneOpen = new ImageIcon("src/resources/png/planeNoBackGround.png").getImage();
        Image airplaneClosed = new ImageIcon("src/resources/png/planeNoBackGroundClosed.png").getImage();
        Image shipOpen = new ImageIcon("src/resources/png/anchorNoBackGround.png").getImage();
        Image shipClosed = new ImageIcon("src/resources/png/anchorNoBackGroundClosed.png").getImage();
        Image carOpen = new ImageIcon("src/resources/png/carIcon.png").getImage();
        Image carClosed = new ImageIcon("src/resources/png/carIconClosed.png").getImage();

        for (CountryModel country : CountryModel.getExtensionCountryies()) {
            int x = (int) (country.getX() / (double) mapImage.getWidth(null) * getWidth());
            int y = (int) (country.getY() / (double) mapImage.getHeight(null) * getHeight());


            // startowa pozycja dla ikonek portów
            int iconX = x;
            int iconY = y+5;

            // Sprawdzanie połączeń i rysowanie odpowiednich ikon
            if (hasConnection(country, TransportType.AIRPLANE)) {
                boolean airOpenI = countryHasOpenPort(country, TransportType.AIRPLANE);
                g2.drawImage(airOpenI ? airplaneOpen : airplaneClosed, iconX, iconY, 16, 16, null);
                //przesunięcie w prawo dla następnej ikonki
                iconX += 20;
            }

            if (hasConnection(country, TransportType.SHIP)) {
                boolean shipOpenI = countryHasOpenPort(country, TransportType.SHIP);
                g2.drawImage(shipOpenI ? shipOpen : shipClosed, iconX, iconY, 16, 16, null);
                //przesunięcie w prawo dla następnej ikon
                iconX += 20;
            }

            if (hasConnection(country, TransportType.CAR)) {
                boolean carOpenI = countryHasOpenPort(country, TransportType.CAR);
                g2.drawImage(carOpenI ? carOpen : carClosed, iconX, iconY, 16, 16, null);
            }
        }
    }

    private boolean hasConnection(CountryModel country, TransportType type) {
        // Sprawdzanie typów połączen
        for (TransportConnection connection : transportModel.getAllConnections()) {
            if ((connection.getSource().equals(country) || connection.getDestination().equals(country))
                    && connection.getType() == type) {
                return true;
            }
        }
        return false;
    }

    private boolean countryHasOpenPort(CountryModel country, TransportType type) {
        // Sprawdzanie stanu otwarcia portu
        for (TransportConnection connection : transportModel.getAllConnections()) {
            if ((connection.getSource().equals(country) || connection.getDestination().equals(country))
                    && connection.getType() == type) {
                return connection.isOpen();
            }
        }
        return false;
    }

}
