import strategy.DrawStrategy;

public interface GameObject {

    void update();

    void draw(DrawStrategy drawStrategy);
}
