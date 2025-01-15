package AntiPlagueInc.View;

import AntiPlagueInc.Controller.MainMenuController;
import AntiPlagueInc.Model.LeaderBoardModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeaderboardView extends JFrame {
    private JList leaderboardList;
    private JButton backButton;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public LeaderboardView() {
        init();
        initComponents();
    }

    public void init(){
        this.setTitle("KoronaVirus - Leaderboard");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    public void initComponents(){
        leaderboardList = new JList();
        leaderboardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //ladwonie danych z pliku
        LeaderBoardModel.loadFromFile();
        ArrayList<LeaderBoardModel> list = new ArrayList<LeaderBoardModel>();
        for (LeaderBoardModel model: LeaderBoardModel.getExtension()){
            list.add(model);
        }
        //sortowanie po najwiekszym wyniku
        list.sort((o1,o2)->Integer.compare(o2.getScore(),o1.getScore()));

        DefaultListModel dlm = new DefaultListModel();
        for (LeaderBoardModel model: list){
            dlm.addElement("Nick: "+model.getName()+" | Score: "+model.getScore());
        }

        //wyswietlnie danych
        leaderboardList.setModel(dlm);
        JScrollPane jScrollPane = new JScrollPane(leaderboardList);
        backButton = new JButton("Back");

        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(backButton, BorderLayout.SOUTH);

    }


    public void addBackButtonActionListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }

    public void goBack(){
        new MainMenuController(new MainMenuView());
        this.dispose();
    }

}
