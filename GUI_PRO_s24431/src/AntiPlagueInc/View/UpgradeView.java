package AntiPlagueInc.View;

import AntiPlagueInc.Model.Upgrades.Upgrade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UpgradeView extends JFrame {
    private JPanel upgradesPanel;
    private Map<Upgrade, JButton> upgradeButtons; // Mapowanie upgrade -> JButton
    private Map<Upgrade, JLabel> upgradeLabels;  // Mapowanie upgrade -> JLabel

    public UpgradeView() {
        init();
        initComponents();
    }

    public void init() {
        this.setTitle("Upgrades");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1400, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    public void initComponents() {
        upgradesPanel = new JPanel();
        upgradesPanel.setLayout(new GridLayout(9, 2, 10, 10));
        upgradeButtons = new HashMap<>();
        upgradeLabels = new HashMap<>();

        this.add(upgradesPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> this.setVisible(false));
        this.add(closeButton, BorderLayout.SOUTH);
    }

    public void createUpgradePanel(ArrayList<Upgrade> upgrades, ActionListener purchaseListener) {
        upgradesPanel.removeAll();
        upgradeButtons.clear();
        upgradeLabels.clear();

        for (Upgrade upgrade : upgrades) {
            JLabel upgradeLabel = new JLabel(upgrade.getName() + ", Cost: " + upgrade.getPrice() + " points, "+upgrade.getDescription());
            JButton upgradeButton = new JButton("Buy");



            upgradeButton.addActionListener(e -> purchaseListener.actionPerformed(new ActionEvent(upgrade, ActionEvent.ACTION_PERFORMED, null)));


            upgradesPanel.add(upgradeLabel);
            upgradesPanel.add(upgradeButton);

            // Przechowywanie referencji do przycisku i etykiety
            upgradeButtons.put(upgrade, upgradeButton);
            upgradeLabels.put(upgrade, upgradeLabel);
        }

        revalidate();
        repaint();
    }


}
