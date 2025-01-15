package AntiPlagueInc.View;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Controller.MainMenuController;
import AntiPlagueInc.Controller.UpgradeController;
import AntiPlagueInc.Model.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {

    // Komponenty GUI
    private JPanel topBarPanel;
    private JLabel timer;
    private static JLabel score;

    private static MapPanel mapPanel;

    private JPanel bottomPanel;
    private JButton upgradeButton;
    private JButton progressButton;

    //do watków
    private static boolean running;



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
        this.setSize(1200,800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        running = true;
    }

    /**
     * Metoda tworząca i rozmieszczająca komponenty (paski, mapę, przyciski).
     */
    private void initComponents() {
        // ============ Górny panel (timer + score) ============
        topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBarPanel.setBackground(Color.GRAY);

        timer = new JLabel("Time: 0");
        score = new JLabel("Points: 0");

        topBarPanel.add(timer);
        topBarPanel.add(score);
        add(topBarPanel, BorderLayout.NORTH);

        // Panel środkowy, MapPanel
        // W MapPanel jest rysowana mapa + nazwy państw, zarażenia
        mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);

        // Panel dolny przyciski
        // Używamy BorderLayout, żeby umieścić przyciski po bokach
        bottomPanel = new JPanel(new BorderLayout());
        upgradeButton = new JButton("Upgrade");
        progressButton = new JButton("Postęp");

        bottomPanel.add(upgradeButton, BorderLayout.WEST);
        bottomPanel.add(progressButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

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
                running = false;
                new MainMenuController(new MainMenuView());
                dispose();
            }
        });
    }

    public void openUpgrades(GameController gameController){
        new UpgradeController(new UpgradeView(), gameController);
    }

    public void addActionListenerToUpgrade(ActionListener actionListener){
        this.upgradeButton.addActionListener(actionListener);
    }

    public void updateCureProgress(double progress){
       SwingUtilities.invokeLater(()->{progressButton.setText("Progress: " + progress+"%");});
    }

    public static void updateUpgradePoints(int points){
        SwingUtilities.invokeLater(()->{score.setText("Points: " + points);});
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

    public static MapPanel getMapPanel() {
        return mapPanel;
    }

    public static JLabel getScore() {
        return score;
    }

    public static boolean isRunning() {
        return running;
    }
}
