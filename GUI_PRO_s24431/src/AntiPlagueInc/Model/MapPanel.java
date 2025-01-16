package AntiPlagueInc.Model;

import AntiPlagueInc.Model.Transport.TransportConnection;
import AntiPlagueInc.Model.Transport.TransportModel;
import AntiPlagueInc.Model.Transport.TransportThread;
import AntiPlagueInc.Model.Transport.TransportType;
import AntiPlagueInc.Model.Upgrades.Upgrade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private Image mapImage;
    private List<CountryModel> countries;
    private List<TransportThread> transportThreads;
    private static TransportModel transportModel;
    private List<JButton> countryButtons;
    private Image airplaneImage;
    private Image carImage;
    private Image shipImage;
    private Image airplaneImageRight;
    private Image carImageRight;
    private Image shipImageRight;

    public MapPanel() {
        // Wczytanie grafiki mapy
        ImageIcon icon = new ImageIcon("src/resources/png/wordlMap.png");
        mapImage = icon.getImage();

        //wczytywanie ikon transportu
        airplaneImage = new ImageIcon("src/resources/png/planeNoBackgroundAlpha.png").getImage();
        carImage = new ImageIcon("src/resources/png/carInfected.png").getImage();
        shipImage = new ImageIcon("src/resources/png/shipInfected.png").getImage();
        airplaneImageRight = new ImageIcon("src/resources/png/planeNoBackgroundAlphaRight.png").getImage();
        carImageRight = new ImageIcon("src/resources/png/carInfectedRight.png").getImage();
        shipImageRight = new ImageIcon("src/resources/png/shipInfectedRight.png").getImage();

        transportThreads = new ArrayList<>();
        countryButtons = new ArrayList<>();

        // Inicjalizacja krajów i transportu
        initCountries();
        transportModel = new TransportModel();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));

        // Dodanie przycisków dla krajów
        addCountryButtons();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateCountryButtonPositions();
            }
        });
    }

    private void initCountries() {
        CountryModel.resetCountries();
        Upgrade.cleanUpgrades();
        countries = new ArrayList<>();
        countries.add(new CountryModel("Chiny", 2000000, 1400, 550));
        countries.add(new CountryModel("Rosja", 190000, 1140, 350));
        countries.add(new CountryModel("Ukraina", 70000, 1015, 460));
        countries.add(new CountryModel("Polska", 38000, 945, 425));
        countries.add(new CountryModel("Meksyk", 1000000, 310, 620));
        countries.add(new CountryModel("Kazachstan", 80000, 1210, 450));
        countries.add(new CountryModel("Argentyna", 30000, 515, 940));
        countries.add(new CountryModel("Canada", 160000, 250, 350));
        countries.add(new CountryModel("USA", 192000, 250, 500));
        countries.add(new CountryModel("Brazylia", 600000, 530, 780));
    }

    private void addCountryButtons() {
        for (CountryModel country : countries) {
            JButton button = new JButton();
            button.setText(country.getName() + ": " + country.getInfected() + "/" + country.getPopulation());
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFocusable(false);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addActionListener(e -> showCountryInfo(country));

            this.add(button);
            countryButtons.add(button);
        }
        updateCountryButtonPositions();
    }

    private void updateCountryButtonPositions() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        for (int i = 0; i < countries.size(); i++) {
            CountryModel country = countries.get(i);
            JButton button = countryButtons.get(i);


            int scaledX = (int) (country.getX() / (double) mapImage.getWidth(null) * panelWidth);
            int scaledY = (int) (country.getY() / (double) mapImage.getHeight(null) * panelHeight);

            button.setBounds(scaledX, scaledY, 160, 20);
        }

        revalidate();
        repaint();
    }

    private void showCountryInfo(CountryModel country) {
        StringBuilder stats = new StringBuilder(String.format(
                "Country: %s\nPopulation: %d\nInfected: %d\nTotal Recovered: %d\nDead: %d\n",
                country.getName(),
                country.getPopulation(),
                country.getInfected(),
                country.getRecovered(),
                country.getDead()
        ));

        if(hasConnection(country, TransportType.AIRPLANE)){
            stats.append("\nAirport is: ").append(countryHasOpenPort(country, TransportType.AIRPLANE)? "OPEN" : "CLOSED");
        }
        if(hasConnection(country, TransportType.SHIP)){
            stats.append("\nShip port is: ").append(countryHasOpenPort(country, TransportType.SHIP)? "OPEN" : "CLOSED");
        }
        if(hasConnection(country, TransportType.CAR)){
            stats.append("\nCar transport is: ").append(countryHasOpenPort(country, TransportType.CAR)? "OPEN" : "CLOSED");
        }

        JOptionPane.showMessageDialog(
                this,
                stats,
                "Country Info",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void updateCountryButtonTexts() {
        for (int i = 0; i < countries.size(); i++) {
            CountryModel country = countries.get(i);
            JButton button = countryButtons.get(i);

            button.setText(country.getName() + ": " + country.getInfected() + "/" + country.getPopulation());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rysowanie tła
        g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);

        //rysowanie obrazkow podczas infekcji innego panstwa
        synchronized (transportThreads) {
            for (TransportThread transport : transportThreads) {
                drawTransport(g, transport);
            }
        }
    }

    private boolean hasConnection(CountryModel country, TransportType type) {
        for (TransportConnection connection : transportModel.getAllConnections()) {
            if ((connection.getSource().equals(country) || connection.getDestination().equals(country))
                    && connection.getType() == type) {
                return true;
            }
        }
        return false;
    }

    private boolean countryHasOpenPort(CountryModel country, TransportType type) {
        for (TransportConnection connection : transportModel.getAllConnections()) {
            if ((connection.getSource().equals(country) || connection.getDestination().equals(country))
                    && connection.getType() == type) {

                boolean sourceOpen = connection.getSource().equals(country) && connection.isOpen();
                boolean destinationOpen = connection.getDestination().equals(country) && connection.isOpen();

                if (sourceOpen || destinationOpen) {
                    return true;
                }
            }
        }
        return false;
    }



    private void drawTransport(Graphics g, TransportThread transport) {
        Graphics2D g2d = (Graphics2D) g;

        Image transportImage = switch (transport.getTransportType()) {
            case AIRPLANE -> transport.isRotatedRight() ? airplaneImage : airplaneImageRight;
            case CAR -> transport.isRotatedRight() ? carImage : carImageRight;
            case SHIP -> transport.isRotatedRight() ? shipImage : shipImageRight;
        };

        // Pozycja transportu
        int centerX = transport.getXCurrent();
        int centerY = transport.getYCurrent();

        g2d.drawImage(transportImage, centerX, centerY, 25, 25, null);

    }

    public void addTransportThread(int xStart, int yStart, int xEnd, int yEnd, TransportType transportType) {
        TransportThread transportThread = new TransportThread(xStart, yStart, xEnd, yEnd, this, transportType);
        synchronized (transportThreads) {
            transportThreads.add(transportThread);
        }
        new Thread(transportThread).start();
    }

    public void removeTransport(TransportThread transport) {
        synchronized (transportThreads) {
            transportThreads.remove(transport);
        }
        repaint();
    }

    public Point getButtonPosition(CountryModel country) {
        for (JButton button : countryButtons) {
            if (button.getText().startsWith(country.getName())) {
                Rectangle bounds = button.getBounds();
                // Środek przycisku
                return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
            }
        }
        return null;
    }

    public List<CountryModel> getCountries() {
        return countries;
    }
}
