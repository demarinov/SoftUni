package com.dido.exercise;

import java.util.*;

public class ListyIterator {

    List<String> dataList;
    Integer index;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        String input = sc.nextLine();

        ListyIterator iterator = null;

        while(!"END".equals(input)) {
            String[] data = input.split("\\s+");

            String command = data[0];
            switch (command) {

                case "Create":

                    String[] elements = new String[data.length - 1];

                    for (int i = 1; i < data.length; i++) {
                        elements[i - 1] = data[i];
                    }

                    iterator = create(elements);
                    break;
                case "Move":
                    System.out.println(iterator.move());
                    break;
                case "Print":
                    try {
                        iterator.print();
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    break;
                case "HasNext":
                    System.out.println(iterator.hasNext());
                    break;
                default:
                    break;
            }


            input = sc.nextLine();
        }

    }

    public ListyIterator(String ... data) {

        if (data.length == 0) {
            this.dataList = new ArrayList<>();
        } else {
            this.dataList = new ArrayList<>(Arrays.asList(data));
        }

        index = 0;
    }

    public static ListyIterator create(String... data) {

        ListyIterator iterator = new ListyIterator(data);

        return iterator;
    }

    public void print() {

        if (dataList.isEmpty()) {
            throw new NoSuchElementException("Invalid Operation!");
        }

        System.out.println(dataList.get(index));
    }

    public boolean move() {

        if (hasNext()) {
            index++;
            return true;
        }

        return false;
    }

    public boolean hasNext() {
        if (index < dataList.size()-1) {
            return true;
        }

        return false;
    }

    public String next() {
        return dataList.iterator().next();
    }
}
