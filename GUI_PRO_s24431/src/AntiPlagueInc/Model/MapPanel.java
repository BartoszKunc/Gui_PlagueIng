package AntiPlagueInc.Model;

import AntiPlagueInc.Model.Transport.TransportConnection;
import AntiPlagueInc.Model.Transport.TransportModel;
import AntiPlagueInc.Model.Transport.TransportType;

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
        ImageIcon icon = new ImageIcon("GUI_PRO_s24431/src/resources/png/wordlMap.png");
        mapImage = icon.getImage();

        //init krajów i transportu
        initCountries();
        transportModel = new TransportModel();

        setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));
    }

    private void initCountries() {
        countries = new ArrayList<>();
        countries.add(new CountryModel("Chiny", 2000000,1400, 550));
        countries.add(new CountryModel("Rosja", 190000,1140, 350));
        countries.add(new CountryModel("Ukraina", 70000,1010, 460));
        countries.add(new CountryModel("Polska", 38000,970, 435));
        countries.add(new CountryModel("Meksyk", 1000000,305, 620));
        countries.add(new CountryModel("Kazachstan", 80000,1200, 450));
        countries.add(new CountryModel("Argentyna", 30000,525, 940));
        countries.add(new CountryModel("Canada", 160000,340, 350));
        countries.add(new CountryModel("USA", 192000,340, 500));
        countries.add(new CountryModel("Brazylia", 600000,590, 820));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rysowanie tła
        g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        // Rysujemy linie/połączenia
        drawConnections(g);

        // nazwy krajow + dane
        for (CountryModel c : countries) {

            int scaledX = (int) (c.getX() / (double) mapImage.getWidth(null) * getWidth());
            int scaledY = (int) (c.getY() / (double) mapImage.getHeight(null) * getHeight());

            // Rysowanie nazwy (zakażeń, populacji itp.)
            g.setColor(Color.magenta);
            g.drawString(c.getName() + ": " + c.getInfected() + "/" + c.getPopulation(), scaledX, scaledY);

            // Sprawdzamy, czy w tym kraju jest port (SHIP) lub lotnisko (AIRPLANE)
            boolean hasPort = false;
            boolean hasAirport = false;

            // Przeszukujemy wszystkie połączenia
            for (TransportConnection conn : transportModel.getAllConnections()) {
                // Jeśli kraj jest źródłem LUB celem w tym połączeniu:
                if (conn.getSource().equals(c) || conn.getDestination().equals(c)) {
                    // Połączenie jest otwarte i typu SHIP -> port
                    if (conn.isOpen() && conn.getType() == TransportType.SHIP) {
                        hasPort = true;
                    }
                    // Połączenie jest otwarte i typu AIRPLANE -> lotnisko
                    if (conn.isOpen() && conn.getType() == TransportType.AIRPLANE) {
                        hasAirport = true;
                    }
                }
            }
        }
    }


    /**
     * Metoda pomocnicza do narysowania wszystkich połączeń.
     */
    private void drawConnections(Graphics g) {
        if (transportModel == null) return;
        java.util.List<TransportConnection> connections = transportModel.getAllConnections();

        Graphics2D g2 = (Graphics2D) g;
        for (TransportConnection conn : connections) {
            CountryModel src = conn.getSource();
            CountryModel dst = conn.getDestination();

            int x1 = (int) (src.getX() / (double) mapImage.getWidth(null) * getWidth());
            int y1 = (int) (src.getY() / (double) mapImage.getHeight(null) * getHeight());
            int x2 = (int) (dst.getX() / (double) mapImage.getWidth(null) * getWidth());
            int y2 = (int) (dst.getY() / (double) mapImage.getHeight(null) * getHeight());

            // kolor i grubość linii w zależności od typu transportu i stanu
            if (!conn.isOpen()) {
                g2.setColor(Color.GRAY);
                float[] dashPattern = { 5, 5 };
                g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));

            } else {
                // Połączenie otwarte
                switch (conn.getType()) {
                    case AIRPLANE:
                        g2.setColor(Color.BLACK);
                        break;
                    case SHIP:
                        g2.setColor(Color.BLUE);
                        break;
                    case CAR:
                        g2.setColor(Color.GREEN);
                        break;
                    default:
                        g2.setColor(Color.GRAY);
                        break;
                }
                g2.setStroke(new BasicStroke(2f));
            }


            g2.drawLine(x1, y1, x2, y2);
        }
    }

    public static TransportModel getTransportModel() {
        return transportModel;
    }

    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
    }
}
