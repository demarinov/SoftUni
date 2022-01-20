package prototype;
// Prototype example ...
public class PointMain {

    public static void main(String[] args) {
        Point a = new Point(13, 42);

//        Point b = new Point(a.getX(), a.getY());
////
////        b.setX(73);

        Point b = a.clone();

    }
}
