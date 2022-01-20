package com.dido.workshop;

import java.util.function.Consumer;

public class SmartArray {

    private static final Integer INITIAL_CAPACITY = 21;
    private Integer size;
    private int[] data;
    private Integer capacity;

    public SmartArray() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }


    public static void main(String[] args) {

        SmartArray smartArray = new SmartArray();
        for (int i = 0; i < 100; i++) {
            smartArray.add(i);
        }

        smartArray.remove(2);
        smartArray.add(2, 100);
        smartArray.remove(0);
        System.out.println(smartArray.contains(100) ? "Yes" : "No");
        smartArray.forEach(System.out::println);
    }

    // •	void add(int element) - Adds the given element to the end of the list
    //•	int get(int index) - Returns the element at the specified position in this list
    //•	int remove(int index) - Removes the element at the given index
    //•	bool contains(int element) - Checks if the list contains the given element returns (True or False)
    //•	void add(int firstIndex, int secondIndex) - Adds element at the specific index,
    // the element at this index gets shift to the right alongside with any following elements,
    // increasing size
    //•	void forEach(Consumer<Integer> consumer) - Goes through each one of the elements in the list

    public void forEach(Consumer<Integer> consumer) {

        for (int i = 0; i < size; i++) {
            consumer.accept(data[i]);
        }
    }

    public void add(int firstIndex, int element) {

        if (firstIndex < 0 || firstIndex >= size) {
            throw new IndexOutOfBoundsException("Wrong index entered!");
        }

        resize();


        // shift right
        for (int i = size; i > firstIndex; i--) {

            data[i] = data[i - 1];
        }

        data[firstIndex] = element;
        size++;
    }

    public boolean contains(int element) {

        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                return true;
            }
        }

        return false;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index entered!");
        }

        int removedElement = data[index];
        data[index] = 0;
        // shift left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[data.length - 1] = 0;
        size--;

        // resize if too big
        if (size < (capacity / 2)) {
            resizeDown();
        }

        return removedElement;
    }

    public int get(int index) {

        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Wrong index entered!");
        }

        return data[index];
    }

    public void add(int element) {
        resize();
        data[size] = element;
        size++;
    }

    private void resizeDown() {

        capacity /= 2;
        int[] copyData = data;
        data = new int[capacity];
        // or until size
        for (int i = 0; i < size; i++) {
            data[i] = copyData[i];
        }
    }

    private void resize() {
        if (size == capacity) {
            capacity *= 2;
            int[] copyData = data;
            data = new int[capacity];
            for (int i = 0; i < size; i++) {
                data[i] = copyData[i];
            }
        }
    }
}
