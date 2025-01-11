package Model;

import Model.Transport.TransportConnection;
import Model.Transport.TransportModel;
import Model.Transport.TransportType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private Image mapImage;
    private Image anchorIcon;
    private Image planeIcon;
    // Lista krajów, które chcemy wyświetlać na mapie
    private List<CountryModel> countries;

    // Referencja do obiektu TransportModel (lista połączeń)
    private static TransportModel transportModel;

    public MapPanel() {
        // Wczytanie grafiki mapy
        ImageIcon icon = new ImageIcon("s24431_GUI_Pro/src/png/wordlMap.png");
        mapImage = icon.getImage();

        //wczytanie grafik dla portów
        anchorIcon = new ImageIcon("s24431_GUI_Pro/src/png/kotwica.png").getImage();
        planeIcon = new ImageIcon("s24431_GUI_Pro/src/png/samolot.png").getImage();

        // Inicjalizujemy listę krajów (można również robić to w innym miejscu)
        countries = new ArrayList<>();
        countries.add(new CountryModel("Chiny", 2000000, true, 1400, 550));
        countries.add(new CountryModel("Rosja", 190000, true, 1140, 350));
        countries.add(new CountryModel("Ukraina", 70000, true, 1010, 460));
        countries.add(new CountryModel("Polska", 38000, true, 970, 435));
        countries.add(new CountryModel("Meksyk", 1000000, true, 305, 620));
        countries.add(new CountryModel("Kazachstan", 80000, false, 1200, 450));
        countries.add(new CountryModel("Argentyna", 30000, true, 525, 940));
        countries.add(new CountryModel("Canada", 160000, true, 340, 350));
        countries.add(new CountryModel("USA", 192000, true, 340, 500));
        countries.add(new CountryModel("Brazylia", 600000, true, 590, 820));


        // Opcjonalne: Inicjalizujemy TransportModel i jego połączenia
        transportModel = new TransportModel();

        // Ustawiamy preferowany rozmiar panelu na rozmiar obrazka
        setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("Rysowanie mapy...");
        // Rysujemy tło (mapę)
        g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);

        // Rysujemy linie/połączenia
        drawConnections(g);


        // Dla każdego kraju rysujemy nazwę + ewentualnie ikonki portu/lotniska
        for (CountryModel c : countries) {

            int scaledX = (int) (c.getX() / (double) mapImage.getWidth(null) * getWidth());
            int scaledY = (int) (c.getY() / (double) mapImage.getHeight(null) * getHeight());

            // Rysowanie nazwy (zakażeń, populacji itp.)
            g.setColor(Color.RED);
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

            // Rysujemy ikony
            // Możesz zmienić offset, aby ikonki się nie nakładały na tekst
            int iconOffsetY = 15;  // np. ikony 15px poniżej nazwy kraju
            int iconSize = 16;     // rozmiar rysowanych ikon (w px)

            if (hasPort) {
                g.drawImage(anchorIcon, scaledX, scaledY + iconOffsetY, iconSize, iconSize, this);
                iconOffsetY += iconSize + 2; // żeby kolejna ikonka była niżej
            }
            if (hasAirport) {
                g.drawImage(planeIcon, scaledX, scaledY + iconOffsetY, iconSize, iconSize, this);
                iconOffsetY += iconSize + 2;
            }
            System.out.println("Rysowanie kraju: " + c.getName() + " - Zarażeni: " + c.getInfected() + "/" + c.getPopulation());
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

            // Obliczamy skalowane współrzędne (x1, y1) i (x2, y2)
            int x1 = (int) (src.getX() / (double) mapImage.getWidth(null) * getWidth());
            int y1 = (int) (src.getY() / (double) mapImage.getHeight(null) * getHeight());
            int x2 = (int) (dst.getX() / (double) mapImage.getWidth(null) * getWidth());
            int y2 = (int) (dst.getY() / (double) mapImage.getHeight(null) * getHeight());

            // Ustawiamy kolor i grubość linii w zależności od typu transportu i stanu (otwarte/zamknięte)
            if (!conn.isOpen()) {
                // Połączenie zamknięte - możemy rysować np. szarą, przerywaną linię
                g2.setColor(Color.GRAY);
                float[] dashPattern = { 5, 5 };
                g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
            } else {
                // Połączenie otwarte
                switch (conn.getType()) {
                    case AIRPLANE:
                        g2.setColor(Color.RED);
                        break;
                    case SHIP:
                        g2.setColor(Color.BLUE);
                        break;
                    case CAR:
                        g2.setColor(Color.GREEN);
                        break;
                    default:
                        g2.setColor(Color.BLACK);
                        break;
                }
                g2.setStroke(new BasicStroke(2f));
            }

            // Rysujemy linię
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * Możesz dodać setter, jeśli chcesz wstrzyknąć TransportModel z zewnątrz.
     */
    public void setTransportModel(TransportModel transportModel) {
        this.transportModel = transportModel;
        repaint();
    }

    public static TransportModel getTransportModel() {
        return transportModel;
    }

    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
    }
}
