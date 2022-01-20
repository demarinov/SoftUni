import strategy.DrawStrategy;

public class Enemy implements GameObject{

    public Enemy(int i, int i1) {

    }

    @Override
    public void update() {
        System.out.println("Enemy is updated.");
    }

    @Override
    public void draw(DrawStrategy drawStrategy) {
        System.out.print("Enemy with ");
        drawStrategy.draw();
    }
}
