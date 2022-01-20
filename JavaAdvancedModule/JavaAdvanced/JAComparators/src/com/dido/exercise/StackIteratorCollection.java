package com.dido.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StackIteratorCollection implements Iterable<Integer> {

    private List<Integer> dataList;
    private Iterator customIterator;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input  = sc.nextLine();

        StackIteratorCollection stackIteratorCollection = new StackIteratorCollection();
        while(!"END".equals(input)) {

            String[] commandData = input.split("\\s+");

            switch(commandData[0]) {

                case "Push":


                    for (int i = 1; i < commandData.length; i++) {
                        Integer element = Integer.parseInt(commandData[i].replaceAll(",",""));
                        stackIteratorCollection.push(element);
                    }


                    break;
                case "Pop":
                    Integer element = stackIteratorCollection.pop();

                    if (element == null) {

                        System.out.println("No elements");
                        break;
                    }

                    break;
            }

            input = sc.nextLine();
        }

        for (int i = 0; i < 2; i++) {
            for (Integer element : stackIteratorCollection) {

                System.out.println(element);
            }
        }



    }

    public StackIteratorCollection() {

        dataList = new ArrayList<>();
    }

    public List<Integer> getDataList() {
        return dataList;
    }

    public void setDataList(List<Integer> dataList) {
        this.dataList = dataList;
    }

    public Iterator getCustomIterator() {
        return customIterator;
    }

    public void setCustomIterator(Iterator customIterator) {
        this.customIterator = customIterator;
    }

    public void push(Integer element) {
        dataList.add(element);
    }

    public Integer pop() {
        if (!dataList.isEmpty()) {
            return dataList.remove(dataList.size() - 1);
        }

        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator();
    }

    private final class CustomIterator implements Iterator<Integer> {

        Integer index = dataList.size()-1;

        @Override
        public boolean hasNext() {
            if (index >= 0) {
                return true;
            }

            return false;
        }

        @Override
        public Integer next() {
            Integer element = dataList.get(index--);
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
