package com.dido.lab;

public class GenericScale {

    public static void main(String[] args) {

        Scale<String> stringScale = new Scale<>("a","c");
        System.out.println(stringScale.getHeavier());

        Scale<Integer> intScale = new Scale<>(1,2);
        System.out.println(intScale.getHeavier());
    }

    static class Scale<T extends Comparable<T>> {

        private T left;
        private T right;

        public Scale(T left, T right) {

            this.left = left;
            this.right = right;
        }

        public T getHeavier() {
            if (this.left.compareTo(this.right) == 0) {
                return null;
            }

            if (this.left.compareTo(this.right) < 0) {
                return this.right;
            }

            return this.left;
        }
    }
}
