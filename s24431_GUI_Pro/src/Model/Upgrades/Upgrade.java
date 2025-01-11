package Model.Upgrades;

public abstract class Upgrade {
    protected String name;
    protected int price;

    public Upgrade(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public abstract void upgrade();

}
