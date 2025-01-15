package AntiPlagueInc.Model.Transport;

import javax.swing.*;
import java.awt.*;

public class AirplaneThread implements Runnable {
    private int xStart, yStart; // Punkt początkowy
    private int xEnd, yEnd;     // Punkt końcowy
    private int xCurrent, yCurrent; // Obecna pozycja samolotu
    private JPanel panel;       // Panel, na którym rysujemy animację
    private boolean running;    // Czy wątek jest aktywny

    public AirplaneThread(int xStart, int yStart, int xEnd, int yEnd, JPanel panel) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.panel = panel;
        this.xCurrent = xStart;
        this.yCurrent = yStart;
        this.running = true;
    }

    @Override
    public void run() {
        try {
            int steps = 100; // Liczba kroków animacji
            double dx = (xEnd - xStart) / (double) steps; // Przesunięcie w osi X na jeden krok
            double dy = (yEnd - yStart) / (double) steps; // Przesunięcie w osi Y na jeden krok

            for (int i = 0; i <= steps && running; i++) {
                xCurrent = (int) (xStart + i * dx);
                yCurrent = (int) (yStart + i * dy);

                panel.repaint(); // Odśwież panel, aby narysować nową pozycję
                Thread.sleep(50); // Opóźnienie między klatkami
            }

            running = false; // Zatrzymanie wątku po zakończeniu animacji
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    public int getXCurrent() {
        return xCurrent;
    }

    public int getYCurrent() {
        return yCurrent;
    }
}
