import strategy.DrawStrategy;

public class Player implements GameObject{
    public Player(int i, int i1) {

    }

    @Override
    public void update() {
        System.out.println("Player is updated");
    }

    @Override
    public void draw(DrawStrategy  drawStrategy) {
        System.out.print("Player with ");
        drawStrategy.draw();
    }
}
