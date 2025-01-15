package  AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.Virus;

import java.util.ArrayList;


public abstract class Upgrade {
    private String name;
    private int price;
    private boolean used;
    protected Cure cure;
    protected Virus virus;

    public Upgrade(String name, int price, Cure cure, Virus virus) {
        this.name = name;
        this.price = price;
        this.cure = cure;
        this.virus = virus;
        this.used = false;

    }

    public abstract void upgrade();

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }


}
