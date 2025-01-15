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
        double infectionRate = (double) source.getInfected() / source.getPopulation();
        if (infectionRate >= threshold) {
            setOpen(false);
            System.out.println("Connection: "+source.getName() +" "+destination.getName()+" is now closed");
        }else{
            setOpen(true);
            System.out.println("Connection: "+source.getName() +" "+destination.getName()+" is now open");
        }
    }

    public CountryModel getSource() {
        return source;
    }

    public void setSource(CountryModel source) {
        this.source = source;
    }

    public CountryModel getDestination() {
        return destination;
    }

    public void setDestination(CountryModel destination) {
        this.destination = destination;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
