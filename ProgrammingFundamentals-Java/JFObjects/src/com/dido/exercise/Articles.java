package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Articles{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> articleData = Arrays.asList(sc.nextLine().split(", "));

        Article article = new Article(articleData.get(0),articleData.get(1),articleData.get(2));

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            String command = sc.nextLine();
            String[] subCommands = command.split(": ");

            if (subCommands[0].contains("Edit")) {
                String content = subCommands[1];
                article.edit(content);
            } else if (subCommands[0].contains("ChangeAuthor")) {
                String author = subCommands[1];
                article.changeAuthor(author);
            } else if (subCommands[0].contains("Rename")) {
                String title = subCommands[1];
                article.rename(title);
            }
        }

        System.out.println(article.toString());

    }

}


class Article {

    String content;
    String title;
    String author;

    public Article(String title, String content, String author) {

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
    public String toString() {

        return this.title+" - "+this.content+": "+this.author;
    }
}
