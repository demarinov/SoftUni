public class PlayerFactory implements Factory{

    // imagine db fields
    private int i = 200;
    private int i2 = 1;

    @Override
    public GameObject produce() {

        return new Player(i, i2);
    }
}
