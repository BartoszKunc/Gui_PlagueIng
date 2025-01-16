package AntiPlagueInc.Model.Upgrades;

import AntiPlagueInc.Controller.GameController;
import AntiPlagueInc.Model.Cure.Cure;
import AntiPlagueInc.Model.VirusPackage.Virus;

import java.util.ArrayList;

public abstract class Upgrade {
    private String name;
    private String description;
    private int price;
    private boolean used;
    protected Cure cure;
    protected Virus virus;
    protected static ArrayList<Upgrade> upgrades = new ArrayList<>();
    protected GameController gameController;

    public Upgrade(String name, String desc, int price, GameController gameController) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.cure = gameController.getCure();
        this.virus = gameController.getVirus();
        this.used = false;
        this.gameController = gameController;
    }

    public abstract void upgrade();

    public static void cleanUpgrades(){
        upgrades.clear();
    }

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

    public String getDescription() {
        return description;
    }

    public static ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public static void initUpgrades(GameController gameController) {
        if (upgrades.isEmpty()) {
            upgrades.add(new TreatmentUpgrade("Treatment Upgrade", "Decrease infection rate", 200, gameController));
            upgrades.add(new NewMedicineUpgrade("New Medicine Upgrade", "Decrease infection rate", 200, gameController));
            upgrades.add(new LockdownUpgrade("LockDown Upgrade", "Decrease infection rate", 200, gameController));
            upgrades.add(new MaskDeliveryUpgrade("Mask Delivery Upgrade", "Decrease infection rate", 300, gameController));
            upgrades.add(new MortalityDecreaseUpgrade("Mortality Decrese Upgrade", "Decrease mortality rate", 400, gameController));
            upgrades.add(new BasicCureUpgrade("Basic Cure Upgrade", "Faster progress", 500, gameController));
            upgrades.add(new GreatInfectionReducementUpgrade("Great Infection Reducement Upgrade", "Decrease infection rate", 500, gameController));
            upgrades.add(new MediumCureUpgrade("Medium Cure Upgrade", "Advance progress", 1000, gameController));
            upgrades.add(new StrongCureUpgrade("Strong Cure Upgrade", "Advance progress", 1250, gameController));
        }
    }
}
