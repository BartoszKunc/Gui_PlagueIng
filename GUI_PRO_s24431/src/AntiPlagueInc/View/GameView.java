package AntiPlagueInc.View;

import AntiPlagueInc.Controller.MainMenuController;
import AntiPlagueInc.Model.CountryModel;
import AntiPlagueInc.Model.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {

    // Komponenty
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
     * Metoda tworząca mapę, przyciski
     */
    private void initComponents() {
        // Gorny panel
        topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBarPanel.setBackground(Color.GRAY);

        timer = new JLabel("Time: 0");
        score = new JLabel("Points: 0");

        topBarPanel.add(timer);
        topBarPanel.add(score);
        this.add(topBarPanel, BorderLayout.NORTH);

        // Panel środkowy, MapPanel
        mapPanel = new MapPanel();
        this.add(mapPanel, BorderLayout.CENTER);

        // Panel dolny przyciski
        bottomPanel = new JPanel(new BorderLayout());
        upgradeButton = new JButton("Upgrade");
        progressButton = new JButton("Progress: 0%");

        bottomPanel.add(upgradeButton, BorderLayout.WEST);
        bottomPanel.add(progressButton, BorderLayout.EAST);

        this.add(bottomPanel, BorderLayout.SOUTH);
        progressButton.addActionListener(e -> showWorldProgress());
    }

    /**
     * Metoda dodaje obsługę skrótu klawiszowego Ctrl+Shift+Q
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

    private void showWorldProgress() {
        int totalAlive = 0;
        int totalInfected = 0;
        int totalDead = 0;

        for (CountryModel country : mapPanel.getCountries()) {
            totalAlive += country.getPopulation();
            totalInfected += country.getInfected();
            totalDead += country.getDead();
        }


        String message = String.format(
                "World Progress:\n" +
                        "Total Alive: %d\n" +
                        "Total Infected: %d\n" +
                        "Total Dead: %d",
                totalAlive, totalInfected, totalDead
        );


        JOptionPane.showMessageDialog(
                this,
                message,
                "World Progress",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    public void addActionListenerToUpgrade(ActionListener actionListener){
        this.upgradeButton.addActionListener(actionListener);
    }

    //  gettery

    public JLabel getScoreLabel() {
        return score;
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

    public static boolean isRunning() {
        return running;
    }

    public MapPanel getMap() {
        return mapPanel;
    }
}
