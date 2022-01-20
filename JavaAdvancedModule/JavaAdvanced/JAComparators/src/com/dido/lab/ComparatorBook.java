package com.dido.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorBook implements Comparator<Booklet> {

    public static void main(String[] args) {

        Booklet bookOne = new
                Booklet("Now and forever",2002,"Billy Bob");
        Booklet bookTwo = new
                Booklet("Kiss of mortality",2004,"Bendji Zachs");

        Booklet bookThree = new
                Booklet("Supernatural justice",2006,"Kevin Donovan");

        List<Booklet> books = new ArrayList<>();

        books.add(bookOne);
        books.add(bookTwo);
        books.add(bookThree);

        books.sort(new ComparatorBook());

        for (Booklet book: books) {
            System.out.println(book.getTitle() + ""+book.getYear());
        }
    }



    @Override
    public int compare(Booklet o1, Booklet o2) {

        if (o1.getTitle().compareTo(o2.getTitle()) == 0) {

            return o1.getYear().compareTo(o2.getYear());
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}

class Booklet {

    private String title;
    private Integer year;
    private List<String> authors;

    public Booklet(String title, Integer year, String... authors) {

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
