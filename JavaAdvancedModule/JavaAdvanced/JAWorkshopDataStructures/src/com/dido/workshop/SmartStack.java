package com.dido.workshop;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SmartStack {

    public static void main(String[] args) {


        SmartStack smartStack = new SmartStack();
        for (int i = 0; i < 50; i++) {
            smartStack.push(i);
        }

        smartStack.pop();
        smartStack.pop();
        System.out.println(smartStack.peek());

        // print all
        System.out.println("Print all elements");
        smartStack.forEach(System.out::println);
    }

    private static Integer INITIAL_CAPACITY=4;
    private Integer size;
    private int[] data;
    private Integer capacity;

    public SmartStack() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }

    // •	void push(int element) – Adds the given element to the stack
    //•	int pop() – Removes the last added element
    //•	int peek() – Returns the last element in the stack without removing it
    //•	void forEach(Consumer<Integer> consumer) – Goes through each of the elements in the stack

    public int getSize() {
        return this.size;
    }

    public void forEach(Consumer<Integer> consumer) {

        for (int i = 0; i < size; i++) {
            consumer.accept(data[i]);
        }
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty!");
        }
        return data[size-1];
    }

    public int pop() {

        if (size == 0) {
            throw new NoSuchElementException("Stack is empty!");
        }

        int element = data[size-1];
        data[size-1] = 0;
        size--;
        shrink();

        return element;
    }

    private void copy() {
        int[] copyData = data;

        data = new int[capacity];
        for (int i = 0; i < size; i++) {
            data[i] = copyData[i];
        }
    }

    private void shrink() {
        if (size < capacity / 2) {
            capacity /=2;
            copy();
        }
    }

    public void push(int element) {
        resize();
        size++;
        data[size-1] = element;
    }

    private void resize(){
        if (size == capacity) {
            capacity *= 2;
            copy();
        }
    }

}
