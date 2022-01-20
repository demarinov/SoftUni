package com.dido.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book {

    public static void main(String[] args) {

        NewBook bookOne = new NewBook("Now and forever",2002,"Billy Bob");
        NewBook bookTwo = new NewBook("Kiss of mortality",2004,"Bendji Zachs");
        NewBook bookThree = new NewBook("Supernatural justice",2006,"Kevin Donovan");

        List<NewBook> books = new ArrayList<>();
        books.add(bookOne);
        books.add(bookTwo);
        books.add(bookThree);
    }

    private String title;
    private Integer year;
    private List<String> authors;

    public Book(String title, Integer year, String... authors) {

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
