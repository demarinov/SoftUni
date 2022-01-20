package com.dido.workshop;


import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SmartLinkedList {

    public static void main(String[] args) {

        SmartLinkedList smartLinkedList = new SmartLinkedList();
        smartLinkedList.addFirst(1);
        smartLinkedList.addFirst(2);
        smartLinkedList.addFirst(3);
        smartLinkedList.addLast(10);
        smartLinkedList.addLast(11);
        smartLinkedList.addLast(12);

        smartLinkedList.removeFirst();
        smartLinkedList.removeLast();

        // print all elements
        smartLinkedList.forEach(System.out::println);

        int[] array = smartLinkedList.toArray();
        System.out.println("Print array");
        for (int i = 0; i < array.length; i++) {

            System.out.println(array[i]);
        }

        System.out.println("get value at index");
        System.out.println(smartLinkedList.get(2));
    }

    private ListNode head;
    private ListNode tail;
    private Integer size;

    public SmartLinkedList() {
        size = 0;
        head = tail = null;
    }

    // •	void addFirst(int element) – adds an element at the beginning of the collection
    //•	void addLast(int element) – adds an element at the end of the collection
    //•	int get(int index) - Returns the element at the specified position in this list
    //•	int removeFirst() – removes the element at the beginning of the collection
    //•	int removeLast() – removes the element at the end of the collection
    //•	void forEach() – goes through the collection and executes a given action
    //•	int[] toArray() – returns the collection as an array

    public int get(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException("Wrong index!");
        }

        ListNode node = head;

        int count = 0;
        int result = 0;
        while(node!=null) {

            if (count == index) {
                result = node.getValue();
                break;
            }

            count++;
            node = node.next;
        }

        return result;
    }

    public int[] toArray() {

        int[] array = new int[size];

        ListNode node = head;

        int count=0;
        while(node!=null) {

            array[count++] = node.getValue();
            node = node.next;
        }

        return array;
    }

    public void forEach(Consumer<Integer> consumer) {

        ListNode node = head;
        while(node != null) {
            consumer.accept(node.getValue());
            node = node.next;
        }
    }

    public int removeLast() {

        if (tail == null) {
            throw new NoSuchElementException("List is empty!");
        }

        int value = tail.getValue();
        ListNode previousNode = tail.previous;
        tail = previousNode;
        if (tail == null) {
            head = null;
        } else {
            previousNode.next = null;
        }
        size--;

        return value;
    }

    public int removeFirst() {

        if (head == null) {

            throw new NoSuchElementException("List is empty!");
        }

        int value = head.getValue();
        ListNode nextNode = head.next;
        head = nextNode;

        if (head == null) {

            tail = null;
        } else {

            nextNode.previous = null;

        }
        size--;

        return value;
    }

    public void addLast(int element) {

        if (tail == null) {
            tail = new ListNode(element);
            head = tail;
        } else {
            ListNode newNode = new ListNode(element);
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void addFirst(int element) {

        if (head == null) {
            head = new ListNode(element);
            tail = head;
        } else {

            ListNode newNode = new ListNode(element);
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }

        size++;
    }

    static class ListNode {

        private int value;
        private ListNode previous;
        private ListNode next;

        public ListNode(int item) {
            next = previous = null;
            value = item;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getPrevious() {
            return previous;
        }

        public void setPrevious(ListNode previous) {
            this.previous = previous;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
}
