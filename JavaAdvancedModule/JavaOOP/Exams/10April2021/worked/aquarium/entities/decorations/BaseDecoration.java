package aquarium.entities.decorations;

public abstract class BaseDecoration implements Decoration {

    private int comfort;
    private double price;
    private String type;

    protected BaseDecoration(int comfort, double price) {
        this.comfort = comfort;
        this.price = price;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public int getComfort() {
        return comfort;
    }

    @Override
    public double getPrice() {
        return price;
    }


}
