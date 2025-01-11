package View;

import Controller.MainMenuController;
import Model.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Główne okno gry, zawiera:
 *  - Pasek górny z licznikiem czasu i wyniku,
 *  - MapPanel (mapę z narysowanymi państwami),
 *  - Pasek dolny z przyciskami Upgrade i Postęp,
 *  - Skrót klawiszowy Ctrl+Shift+Q do powrotu do menu głównego.
 */
public class GameView extends JFrame {

    // --- Komponenty GUI ---
    private JPanel topBarPanel;
    private JLabel timer;
    private JLabel score;

    private JPanel bottomPanel;
    private JButton upgradeButton;
    private JButton progressButton;

    private Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();

    public GameView() {
        init();
        initComponents();
    }

    /**
     * Metoda inicjalizująca okno (JFrame).
     */
    private void init() {
        this.setTitle("KoronaVirus - Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenDim);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Metoda tworząca i rozmieszczająca komponenty (paski, mapę, przyciski).
     */
    private void initComponents() {
        // ============ Górny panel (timer + score) ============
        topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBarPanel.setBackground(Color.GRAY);

        timer = new JLabel("Time: 0");
        score = new JLabel("Score: 0");

        topBarPanel.add(timer);
        topBarPanel.add(score);
        add(topBarPanel, BorderLayout.NORTH);

        // ============ Panel środkowy: nasz MapPanel ============
        // W MapPanel jest rysowana mapa + nazwy państw, zarażenia itp.
        MapPanel mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);

        // ============ Panel dolny (przyciski) ============
        // Używamy BorderLayout, żeby umieścić przyciski po bokach
        bottomPanel = new JPanel(new BorderLayout());
        upgradeButton = new JButton("Upgrade");
        progressButton = new JButton("Postęp");

        bottomPanel.add(upgradeButton, BorderLayout.WEST);
        bottomPanel.add(progressButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Możesz ewentualnie dodać ActionListenery do przycisków:
        // upgradeButton.addActionListener(...);
        // progressButton.addActionListener(...);

        // ============ Skrót klawiszowy Ctrl+Shift+Q ============
        addShortCutListener();
    }

    /**
     * Metoda dodaje obsługę skrótu klawiszowego Ctrl+Shift+Q,
     * który cofa do MainMenuView (poprzez MainMenuController).
     */
    public void addShortCutListener(){
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("control shift Q"), "goBack");

        getRootPane().getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuController(new MainMenuView());
                dispose();
            }
        });
    }

    // ====== Gettery / settery / metody pomocnicze (opcjonalnie) ======

    public JLabel getTimerLabel() {
        return timer;
    }

    public JLabel getScoreLabel() {
        return score;
    }

    public JButton getUpgradeButton() {
        return upgradeButton;
    }

    public JButton getProgressButton() {
        return progressButton;
    }

    public JLabel getTimer() {
        return timer;
    }
}
