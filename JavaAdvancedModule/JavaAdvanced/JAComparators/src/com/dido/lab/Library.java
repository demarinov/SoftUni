package com.dido.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Library<B> implements Iterable<B> {

    public static void main(String[] args) {

        NewBook bookOne = new NewBook("Now and forever",2002,"Billy Bob");
        NewBook bookTwo = new NewBook("Kiss of mortality",2004,"Bendji Zachs");
        NewBook bookThree = new NewBook("Supernatural justice",2006,"Kevin Donovan");

        Library library = new Library(bookOne, bookTwo, bookThree);

        for (Object book : library) {
            System.out.println(((NewBook)book).getTitle());
        }
    }

    private B[] books;

    public Library(B... books) {
        this.books = books;
    }

    private final class LibIterator implements Iterator<B> {

        private int counter = 0;

        @Override
        public boolean hasNext() {
            return  counter >= 0 && counter < books.length;
        }

        @Override
        public B next() {
            B book = books[counter++];
            return book;
        }
    }

    @Override
    public Iterator<B> iterator() {

        Iterator<B> iterator = new LibIterator();
        return iterator;
    }


}

class NewBook {

    private String title;
    private Integer year;
    private List<String> authors;

    public NewBook(String title, Integer year, String... authors) {

        this.setTitle(title);
        this.setYear(year);
        this.setAuthors(authors);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(String ... authors) {

        if (authors.length == 0) {
            this.authors = new ArrayList<>();
        } else {
            this.authors = new ArrayList<>(Arrays.asList(authors));
        }
    }

}
