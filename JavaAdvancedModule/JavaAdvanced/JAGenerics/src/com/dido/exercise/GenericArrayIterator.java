package com.dido.exercise;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Scanner;

public class GenericArrayIterator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GenericArrayV2<String> smartArray = new GenericArrayV2<>(String.class);

        //•	END - stops the reading of commands


        String input = sc.nextLine();

        while(!"END".equals(input)) {

            String[] commandData = input.split("\\s");

            switch(commandData[0]) {

                case "Add":
                    // •	Add <element> - Adds the given element to the end of the list
                    String element = commandData[1];
                    smartArray.add(element);
                    break;
                case "Remove":
                    //•	Remove <index> - Removes the element at the given index
                    int idx = Integer.parseInt(commandData[1]);
                    smartArray.remove(idx);
                    break;
                case "Contains":
                    //•	Contains <element> - Prints if the list contains the
                    // given element (true or false)
                    element = commandData[1];
                    System.out.println(smartArray.contains(element));
                    break;
                case "Swap":
                    //•	Swap <index> <index> - Swaps the elements at the given indexes
                    int idxOne = Integer.parseInt(commandData[1]);
                    int idxTwo = Integer.parseInt(commandData[2]);

                    smartArray.swap(idxOne, idxTwo);
                    break;
                case "Greater":
                    //•	Greater <element> - Counts the elements that are greater than
                    // the given element and prints their count
                    element = commandData[1];
                    System.out.println(smartArray.countGreaterThan(element));
                    break;
                case "Max":
                    //•	Max - Prints the maximum element in the list
                    System.out.println(smartArray.getMax());
                    break;
                case "Min":
                    //•	Min - Prints the minimum element in the list
                    System.out.println(smartArray.getMin());
                    break;
                case "Print":
                    //•	Print - Prints all elements in the list, each on a separate line
                    smartArray.forEach(System.out::println);
                    for (String el : smartArray) {

                    }
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }
    }
}

class GenericArrayV2<T extends Comparable> implements Comparable<T>, Iterable<T> {

    private static final Integer INITIAL_CAPACITY = 21;
    private Integer size;
    private T[] data;
    private Integer capacity;
    private Class<T> typeClass;

    public GenericArrayV2(Class<T> cl) {
        data = (T[]) Array.newInstance(cl, INITIAL_CAPACITY);
        typeClass = cl;
        size = 0;
        capacity = INITIAL_CAPACITY;
    }




    public T getMin() {

        T min = data[0];

        for (T element : data) {

            if (element == null) {
                return min;
            }

            if (element.compareTo(min) < 0) {

                min = element;
            }

        }

        return min;
    }

    public T getMax() {

        T max = data[0];
        for (T element : data) {

            if (element == null) {
                return max;
            }

            if (element.compareTo(max) > 0) {

                max = element;
            }

        }

        return max;

    }

    public int countGreaterThan(T element) {

        int count = 0;

        for (T elementData : data) {

            if (elementData == null) {
                return count;
            }

            if (elementData.compareTo(element) > 0) {
                count++;
            }
        }

        return count;
    }

    public void swap(int idxOne, int idxTwo) {
        if (idxOne < 0 || idxOne >= size
                || idxTwo < 0 || idxTwo >= size) {
            throw new IndexOutOfBoundsException("Wrong index entered!");
        }

        T temp = data[idxOne];
        data[idxOne] = data[idxTwo];
        data[idxTwo] = temp;

    }

    // •	void add(int element) - Adds the given element to the end of the list
    //•	int get(int index) - Returns the element at the specified position in this list
    //•	int remove(int index) - Removes the element at the given index
    //•	bool contains(int element) - Checks if the list contains the given element returns (True or False)
    //•	void add(int firstIndex, int secondIndex) - Adds element at the specific index,
    // the element at this index gets shift to the right alongside with any following elements,
    // increasing size
    //•	void forEach(Consumer<Integer> consumer) - Goes through each one of the elements in the list

//    @Override
//    public void forEach(Consumer<T> consumer) {
//
//        for (int i = 0; i < size; i++) {
//            consumer.accept(data[i]);
//        }
//    }

    public void add(int firstIndex, T element) {

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

    public boolean contains(T element) {

        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index entered!");
        }

        T removedElement = data[index];
        data[index] = null;
        // shift left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[data.length - 1] = null;
        size--;

        // resize if too big
        if (size < (capacity / 2)) {
            resizeDown();
        }

        return removedElement;
    }

    public T get(int index) {

        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Wrong index entered!");
        }

        return data[index];
    }

    public void add(T element) {
        resize();
        data[size] = element;
        size++;
    }

    private void resizeDown() {

        capacity /= 2;
        T[] copyData = data;
        data = (T[]) Array.newInstance(typeClass, capacity);
        // or until size
        for (int i = 0; i < size; i++) {
            data[i] = copyData[i];
        }
    }

    private void resize() {
        if (size == capacity) {
            capacity *= 2;
            T[] copyData = data;
            data =(T[]) Array.newInstance(typeClass, capacity);
            for (int i = 0; i < size; i++) {
                data[i] = copyData[i];
            }
        }
    }

    @Override
    public int compareTo(T o) {
        return this.compareTo(o);
    }

    @Override
    public Iterator<T> iterator() {

        GenericIterator iterator = new GenericIterator(this);

        return iterator;
    }

    class GenericIterator implements Iterator<T> {

        GenericArrayV2<T> arrayList;
        int next;

        public GenericIterator(GenericArrayV2<T> arrayList) {

            this.arrayList = arrayList;
            this.next = 0;
        }

        @Override
        public boolean hasNext() {
            return arrayList.get(next) != null && arrayList.size != 0;
        }

        @Override
        public T next() {
            T element = arrayList.get(next);
            next++;
            return element;
        }
    }
}
