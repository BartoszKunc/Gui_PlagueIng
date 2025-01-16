package  AntiPlagueInc.Model.Transport;

import AntiPlagueInc.Model.CountryModel;

public class TransportConnection {
    private CountryModel source;
    private CountryModel destination;
    private TransportType type;
    private boolean open;
    private double threshold;

    public TransportConnection(CountryModel source, CountryModel destination, TransportType type, boolean open) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.open = open;
        this.threshold = 0.2 + (0.4 - 0.2) * Math.random();
    }

    /**
     * Funkcja blokująca i odblokująca transport
     */
    public void evaluateTransportStatus() {
        double infectionRateSource = (double) source.getInfected() / source.getPopulation();

        if (infectionRateSource >= threshold || source.getPopulation() == 0) {
            setOpen(false);
            System.out.println("Connection closed: " + source.getName() + " -> " + destination.getName() + " (Type: " + type + ")");
        } else if (infectionRateSource < threshold && source.getPopulation() > 0) {
            setOpen(true);
            System.out.println("Connection open: " + source.getName() + " -> " + destination.getName() + " (Type: " + type + ")");
        }
    }

    public CountryModel getSource() {
        return source;
    }

    public CountryModel getDestination() {
        return destination;
    }

    public TransportType getType() {
        return type;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
