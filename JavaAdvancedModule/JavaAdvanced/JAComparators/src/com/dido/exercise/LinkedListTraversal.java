package com.dido.exercise;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LinkedListTraversal {

    SmartLinkedList list;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        LinkedListTraversal linkedListTraversal = new LinkedListTraversal();

        for (int i = 0; i < n; i++) {

            String[] commandData = sc.nextLine().split("\\s");

            switch (commandData[0]) {

                case "Add":
                    Integer num = Integer.parseInt(commandData[1]);

                    linkedListTraversal.getList().addLast(num);
                    break;
                case "Remove":
                    num = Integer.parseInt(commandData[1]);
                    linkedListTraversal.getList().remove(num);
                    break;
            }
        }

        System.out.println(linkedListTraversal.getList().getSize());
        linkedListTraversal.getList().forEach(s -> System.out.printf("%d ",s));

    }

    public LinkedListTraversal() {
        list = new SmartLinkedList();
    }

    public SmartLinkedList getList() {
        return list;
    }

    public void setList(SmartLinkedList list) {
        this.list = list;
    }

    private final class ListIterator implements Iterator<Integer> {

        private int next = 0;

        @Override
        public boolean hasNext() {

            if (next <= list.getSize()-1) {
                return true;
            }

            return false;
        }

        @Override
        public Integer next() {

            Integer element = list.get(next++);
            return element;
        }
    }

    class SmartLinkedList implements Iterable<Integer> {


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

        public int getSize() {

            return size;
        }

        public int get(int index) {

            if (index < 0 || index >= size) {

                throw new IndexOutOfBoundsException("Wrong index!");
            }

            ListNode node = head;

            int count = 0;
            int result = 0;
            while (node != null) {

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

            int count = 0;
            while (node != null) {

                array[count++] = node.getValue();
                node = node.next;
            }

            return array;
        }

//    public void forEach(Consumer<? super SmartLinkedList> consumer) {
//
//        ListNode node = head;
//        while(node != null) {
//            consumer.accept(node.getValue());
//            node = node.next;
//        }
//    }

        public boolean remove(int element) {
            ListNode node = head;
            boolean found = false;

            while (node != null) {
                int nodeValue = node.getValue();

                if (nodeValue == element) {
                    found = true;
                    break;
                }
                node = node.next;
            }


            if (found) {


                if (tail == node) {
                    removeLast();
                    return true;
                }

                if (head == node) {
                    removeFirst();
                    return true;
                }

                int value = node.getValue();
                ListNode previousNode = node.previous;
                ListNode nextNode = node.next;
                nextNode.previous = previousNode;
                previousNode.next = nextNode;
                node.next = null;
                node.previous = null;
                size--;

                return true;
            }

            return false;
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

        @Override
        public Iterator<Integer> iterator() {
            return new ListIterator();
        }

        class ListNode {

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
}



