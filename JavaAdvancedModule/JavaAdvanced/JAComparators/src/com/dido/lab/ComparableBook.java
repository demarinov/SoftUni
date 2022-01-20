package com.dido.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComparableBook implements Comparable<ComparableBook> {

    public static void main(String[] args) {

        ComparableBook bookOne = new
                ComparableBook("Now and forever",2002,"Billy Bob");
        ComparableBook bookTwo = new
                ComparableBook("Kiss of mortality",2004,"Bendji Zachs");
        ComparableBook bookThree = new
                ComparableBook("Supernatural justice",2006,"Kevin Donovan");


        if (bookOne.compareTo(bookTwo) > 0) {
            System.out.println(String.format("%s is before %s", bookOne, bookTwo));
        } else if (bookOne.compareTo(bookTwo) < 0) {
            System.out.println(String.format("%s is before %s", bookTwo, bookOne));
        } else {
            System.out.println("Books are equal");
        }


    }

    private String title;
    private Integer year;
    private List<String> authors;

    public ComparableBook(String title, Integer year, String... authors) {

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

    @Override
    public int compareTo(ComparableBook o) {

        if (this.title.equals(o.getTitle())) {
            return this.year.compareTo(o.getYear());
        }
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
