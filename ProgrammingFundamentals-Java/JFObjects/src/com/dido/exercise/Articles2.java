package com.dido.exercise;

import java.util.*;

public class Articles2{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);



        int n = Integer.parseInt(sc.nextLine());
        List<Article2> articles = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            List<String> articleData = Arrays.asList(sc.nextLine().split(", "));

            Article2 article = new Article2(articleData.get(0),articleData.get(1),articleData.get(2));
            articles.add(article);
        }

        String orderCommandType = sc.nextLine();

        //Collections.sort(articles, (o1,o2) -> o1.title.compareTo(o2.title));

        switch (orderCommandType.toLowerCase()) {

            case "title":
                Collections.sort(articles, new Comparator<Article2>() {
                    @Override
                    public int compare(Article2 o1, Article2 o2) {
                        return o1.title.compareTo(o2.title);
                    }
                });
                break;
            case "content":
                Collections.sort(articles, new Comparator<Article2>() {
                    @Override
                    public int compare(Article2 o1, Article2 o2) {
                        return o1.content.compareTo(o2.content);
                    }
                });
                break;
            case "author":
                Collections.sort(articles, new Comparator<Article2>() {
                    @Override
                    public int compare(Article2 o1, Article2 o2) {
                        return o1.author.compareTo(o2.author);
                    }
                });
                break;
        }

        for (int i = 0; i < articles.size(); i++) {
            System.out.println(articles.get(i).toString());
        }

    }

}


class Article2 {

    String content;
    String title;
    String author;

    public Article2(String title, String content, String author) {

        this.author = author;
        this.content = content;
        this.title = title;
    }

    public void edit(String content) {
        this.content = content;
    }

    public void changeAuthor(String author) {

        this.author = author;
    }

    public void rename(String title) {
        this.title = title;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article2 article2 = (Article2) o;
        return Objects.equals(content, article2.content) &&
                Objects.equals(title, article2.title) &&
                Objects.equals(author, article2.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, title, author);
    }

    @Override
    public String toString() {

        return this.title+" - "+this.content+": "+this.author;
    }
}
