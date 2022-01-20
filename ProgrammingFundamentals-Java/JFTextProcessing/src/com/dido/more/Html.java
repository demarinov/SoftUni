package com.dido.more;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Html {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String title = sc.nextLine();
        String content = sc.nextLine();
        String input = sc.nextLine();

        List<String> comments = new ArrayList<>();
        while(!input.equals("end of comments")) {
            comments.add(input);
            input = sc.nextLine();
        }

        // create class Article with relevant fields - title, content, list of comments
        // populate the Article object with each read field from input
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setComments(comments);
        // print the Article object into html format as given
        // - each text element with 4 spaces from the left.
        article.printArticle();
    }


    static class Article {
        private String title;
        private String content;
        private List<String> comments;

        public Article() {

        }

        public Article(String title, String content, List<String> comments) {
            this.title = title;
            this.content = content;
            this.comments = comments;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getComments() {
            return comments;
        }

        public void setComments(List<String> comments) {
            this.comments = comments;
        }

        public void printArticle() {

            printTitle();
            printContent();
            printComments();
        }

        public void printComments() {
            for (int i = 0; i < comments.size(); i++) {

                String comment  = comments.get(i);
                System.out.println("<div>");
                System.out.printf("\t%s%n",comment);
                System.out.println("</div>");

            }

        }

        public void printTitle() {
            System.out.println("<h1>");
            System.out.printf("\t%s%n",title);
            System.out.println("</h1>");
        }

        public void printContent() {
            System.out.println("<article>");
            System.out.printf("\t%s%n",content);
            System.out.println("</article>");
        }

        @Override
        public String toString() {
            return "Article{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", comments=" + comments +
                    '}';
        }
    }
}
