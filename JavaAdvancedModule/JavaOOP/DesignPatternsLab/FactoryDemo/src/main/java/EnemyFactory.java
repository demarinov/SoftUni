public class EnemyFactory implements Factory {

    private int i=100;
    private int i2= 20;

    @Override
    public GameObject produce() {

        return new Enemy(i, i2);
    }
}
