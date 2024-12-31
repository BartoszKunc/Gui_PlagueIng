package Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {
    private Image mapImage;
    private ArrayList<CountryModel> countries = new ArrayList<>();

    public MapPanel() {
        ImageIcon icon = new ImageIcon("s24431_GUI_Pro/src/png/wordlMap.png");
        mapImage = icon.getImage();

        countries.add(new CountryModel("Chiny",2000000,true,1400, 550));
        countries.add(new CountryModel("Rosja",190000,true,1140, 350));
        countries.add(new CountryModel("Ukraina",70000,true,1010, 460));
        countries.add(new CountryModel("Polska",38000,true,970, 435));
        countries.add(new CountryModel("Meksyk",1000000,true,305, 620));
        countries.add(new CountryModel("Kazachstan",80000,false,1200, 450));
        countries.add(new CountryModel("Argentyna",30000,true,525, 940));
        countries.add(new CountryModel("Kanada",160000,true,340, 350));
        countries.add(new CountryModel("USA",192000,true,340, 500));
        countries.add(new CountryModel("Brazylia", 600000,true,590, 820));


        setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 3. Rysujemy mapę w tle (dopasowaną do aktualnych rozmiarów panelu)
        g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);

        // 4. Dla każdego kraju obliczamy skalowane współrzędne (jeśli mapa się zmienia)
        for (CountryModel c : countries) {
            // Skalowanie pozycji (opcjonalnie, jeśli panel się rozciąga)
            int scaledX = (int) (c.getX() / (double) mapImage.getWidth(null) * getWidth());
            int scaledY = (int) (c.getY() / (double) mapImage.getHeight(null) * getHeight());

            // Rysujemy napis (np. nazwa + liczba zakażeń)
            g.setColor(Color.RED);
            g.drawString(c.getName() + ": " + c.getInfected() + "/" + c.getPopulation(), scaledX, scaledY);
        }
    }
}

