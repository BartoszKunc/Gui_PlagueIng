package AntiPlagueInc.Model.Transport;

import AntiPlagueInc.Model.MapPanel;

public class TransportThread implements Runnable {
    private int xCurrent;
    private int yCurrent;
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private MapPanel panel;
    private TransportType transportType;

    public TransportThread(int xStart, int yStart, int xEnd, int yEnd, MapPanel panel, TransportType transportType) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.xCurrent = xStart;
        this.yCurrent = yStart;
        this.panel = panel;
        this.transportType = transportType;

    }

    @Override
    public void run() {
        int steps = 40;
        double xStep = (xEnd - xStart) / (double) steps;
        double yStep = (yEnd - yStart) / (double) steps;

        for (int i = 0; i <= steps; i++) {
            xCurrent = (int) (xStart + i * xStep);
            yCurrent = (int) (yStart + i * yStep);
            panel.repaint();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        panel.removeTransport(this);
        panel.repaint();
    }

    public int getXCurrent() {
        return xCurrent;
    }

    public int getYCurrent() {
        return yCurrent;
    }

    public boolean isRotatedRight() {
        return xStart > xEnd;
    }

    public TransportType getTransportType() {
        return transportType;
    }
}
