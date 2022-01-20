package com.dido.lab;

public class Shapes {

    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(1,2);
        rectangle.calculatePerimeter();
        rectangle.calculateArea();
        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getArea());

        Circle circle =new Circle(4);
        circle.calculatePerimeter();
        circle.calculateArea();
        System.out.println(circle.getPerimeter());
        System.out.println(circle.getArea());
    }

    static class Circle extends Shape {

        private double radius;



        public Circle(double radius) {
            setRadius(radius);
        }

        public double getRadius() {
            return radius;
        }

        private void setRadius(double radius) {
            this.radius = radius;
        }

        @Override
        public void calculatePerimeter() {
             setPerimeter(Math.PI * radius * 2);
        }

        @Override
        public void calculateArea() {
            setArea(Math.PI * radius * radius);
        }
    }

    static class Rectangle extends Shape {

        private double height;
        private double width;

        public Rectangle(double height, double width) {
            setHeight(height);
            setWidth(width);
        }

        public double getHeight() {
            return height;
        }

        private void setHeight(double height) {
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        private void setWidth(double width) {
            this.width = width;
        }

        @Override
        public void calculatePerimeter() {
            this.setPerimeter((getWidth() + getHeight()) * 2);
        }

        @Override
        public void calculateArea() {
            this.setArea(getWidth() * getHeight());
        }
    }

    static abstract class Shape {

        private double perimeter;
        private double area;

        public abstract void calculatePerimeter();
        public abstract void calculateArea();

        public double getPerimeter() {
            return perimeter;
        }

        protected void setPerimeter(double perimeter) {
            this.perimeter = perimeter;
        }

        public double getArea() {
            return area;
        }

        protected void setArea(double area) {
            this.area = area;
        }
    }
}
