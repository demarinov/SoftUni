package strategy;

public class DrawRectangleStrategy implements DrawStrategy{
    @Override
    public void draw() {
        System.out.println("Rectangle is drawn");
    }
}
