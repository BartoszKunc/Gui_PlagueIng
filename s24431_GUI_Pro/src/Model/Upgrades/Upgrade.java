package Model.Upgrades;

public abstract class Upgrade {
    protected String name;
    protected String description;
    protected int price;

    public Upgrade(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public abstract void upgrade();

}
