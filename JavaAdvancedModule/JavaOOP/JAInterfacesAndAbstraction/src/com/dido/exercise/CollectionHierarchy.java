package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollectionHierarchy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] elements = sc.nextLine().split("\\s");

        Addable collectionAdd =new AddCollection();
        AddRemovable collectionAddRemove = new AddRemoveCollection();
        MyList myList = new MyListImpl();
        for (int i = 0; i < elements.length; i++) {

            System.out.printf("%d ",collectionAdd.add(elements[i]));
        }

        System.out.println();

        for (int i = 0; i < elements.length; i++) {

            System.out.printf("%d ",collectionAddRemove.add(elements[i]));
        }

        System.out.println();

        for (int i = 0; i < elements.length; i++) {
            System.out.printf("%d ",myList.add(elements[i]));
        }

        System.out.println();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            String element = collectionAddRemove.remove();
            System.out.printf("%s ",element);
        }

        System.out.println();

        for (int i = 0; i < n; i++) {

            String element = myList.remove();
            System.out.printf("%s ",element);
        }
    }

    interface Addable {
        int add(String element);
    }

    interface AddRemovable extends Addable{
        String remove();
    }

    interface MyList extends AddRemovable{
        int getUsed();
    }

    static abstract class Collection {
        private static final int MAX_SIZE = 100;
        private List<String> items;

        protected Collection() {
            items = new ArrayList<>();
        }

        public List<String> getItems() {
            return items;
        }
    }

    static class MyListImpl extends Collection implements MyList {

        @Override
        public int add(String element) {
            this.getItems().add(0,element);
            return 0;
        }

        @Override
        public String remove() {
            if (this.getItems().isEmpty()) {
                return null;
            }
            return this.getItems().remove(0);
        }

        @Override
        public int getUsed() {
            return this.getItems().size();
        }
    }

    static class AddRemoveCollection extends Collection implements AddRemovable {

        public AddRemoveCollection() {
            super();
        }

        @Override
        public int add(String element) {
            this.getItems().add(0,element);
            return 0;
        }

        @Override
        public String remove() {
            if (this.getItems().isEmpty()) {
                return null;
            }
            return this.getItems().remove(this.getItems().size()-1);
        }


    }

    static class AddCollection extends Collection implements Addable {

        public AddCollection() {
            super();
        }

        @Override
        public int add(String element) {
            this.getItems().add(element);
            return this.getItems().size()-1;
        }
    }
}
