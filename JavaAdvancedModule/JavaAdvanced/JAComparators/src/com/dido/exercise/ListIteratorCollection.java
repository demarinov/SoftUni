package com.dido.exercise;

import java.util.*;

public class ListIteratorCollection implements Iterable<String> {

    List<String> dataList;
    Iterator customIterator;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        String input = sc.nextLine();

        ListIteratorCollection iteratorCollection = null;

        while(!"END".equals(input)) {
            String[] data = input.split("\\s+");

            String command = data[0];
            switch (command) {

                case "Create":

                    String[] elements = new String[data.length - 1];

                    for (int i = 1; i < data.length; i++) {
                        elements[i - 1] = data[i];
                    }

                    iteratorCollection = create(elements);
                    break;
                case "Move":
                    System.out.println(iteratorCollection.move());
                    break;
                case "Print":
                    try {
                        iteratorCollection.print();
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    break;
                case "HasNext":
                    System.out.println(iteratorCollection.hasNext());
                    break;
                case "PrintAll":
                    iteratorCollection.printAll();
                    break;
                default:
                    break;
            }


            input = sc.nextLine();
        }

    }

    public ListIteratorCollection(String ... data) {

        if (data.length == 0) {
            this.dataList = new ArrayList<>();
        } else {
            this.dataList = new ArrayList<>(Arrays.asList(data));
        }
    }

    public void printAll() {

        for (String element : this) {
            System.out.print(element+" ");
        }

        System.out.println();
    }

    public static ListIteratorCollection create(String... data) {

        ListIteratorCollection iteratorCollection = new ListIteratorCollection(data);
        iteratorCollection.customIterator = iteratorCollection.iterator();

        return iteratorCollection;
    }

    public void print() {

        if (dataList.isEmpty()) {
            throw new NoSuchElementException("Invalid Operation!");
        }

        System.out.println(dataList.get(((CustomIterator)this.customIterator).getIndex()));
    }

    public boolean move() {

        if (hasNext()) {
            this.customIterator.next();
            return true;
        }

        return false;
    }

    public boolean hasNext() {

        if (((CustomIterator)this.customIterator).getIndex() == dataList.size()-1) {
            return false;
        }
        return this.customIterator.hasNext();
    }

    @Override
    public Iterator<String> iterator() {
        return new CustomIterator();
    }

    private final class CustomIterator implements Iterator<String> {

        Integer index = 0;

        @Override
        public boolean hasNext() {
            if (index <= dataList.size()-1) {
                return true;
            }

            return false;
        }

        @Override
        public String next() {
            String element = dataList.get(index);
            index++;
            return element;
        }

        public Integer getIndex() {
            return index;
        }

        private void setIndex(Integer index) {
            this.index = index;
        }
    }
}
