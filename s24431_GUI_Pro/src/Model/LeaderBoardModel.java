package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoardModel implements Serializable{
    private String name;
    private int score;
    private static List<LeaderBoardModel> extension = new ArrayList<LeaderBoardModel>();
    private static final long serialVersionUID = 1L;

    public LeaderBoardModel( String name, int score) {
        this.name = name;
        this.score = score;

        extension.add(this);
    }


    public static void saveToFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("LeaderBoard.ser"));
            for (LeaderBoardModel m : extension) {
                oos.writeObject(m);
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e+" nie udalo sie zapisac obiektow do pliku");
        }
    }

    public static void loadFromFile(){
        extension.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("LeaderBoard.ser"))) {
            while (true) {
                try {
                    LeaderBoardModel lb = (LeaderBoardModel) ois.readObject();
                    extension.add(lb);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static List<LeaderBoardModel> getExtension(){
        return Collections.unmodifiableList(extension);
    }

    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        return name+" "+score;
    }
}
