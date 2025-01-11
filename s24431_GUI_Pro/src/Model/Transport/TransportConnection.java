package Model.Transport;

import Model.CountryModel;

public class TransportConnection {
    private CountryModel source;
    private CountryModel destination;
    private TransportType type;
    private boolean open;

    public TransportConnection(CountryModel source, CountryModel destination, TransportType type, boolean open) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.open = open;
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
