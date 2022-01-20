package strategy;

public class DrawCircleStrategy implements DrawStrategy{
    @Override
    public void draw() {
        System.out.println("Circle is drawn.");
    }
}
