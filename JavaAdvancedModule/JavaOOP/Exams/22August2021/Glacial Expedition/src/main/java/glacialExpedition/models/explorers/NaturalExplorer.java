package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{

    private static final double INITIAL_ENERGY = 60d;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {

        double energy = this.getEnergy();
        energy -= 7;
        if (energy < 0) {
            energy = 0;
        }

        this.setEnergy(energy);
    }
}
