package app.components;

import app.models.*;
import app.services.AuthorService;
import app.services.BookService;
import app.services.BookServiceImpl;
import app.services.CategoryService;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy");

    @Autowired
    public ConsoleRunner(BookService bookService, CategoryService categoryService,
                         AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();

        try {
            System.out.println("Titles released after 31/12/2000:");
            printTitlesReleasedAfter(DATE_FORMAT.parse("31/12/2000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Books by George Powell:");
        printBooksTitleReleaseDateAndCountByAuthorName("George", "Powell");

        System.out.println("Authors with books released before 01/01/1990:");
        try {
            printAuthorsWithBooksReleasedBeforeDate(DATE_FORMAT.parse("01/01/1990"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void printAuthorsWithBooksReleasedBeforeDate(Date parse) {
        authorService.getAuthorsWithBooksReleasedBeforeDate(parse).forEach(System.out::println);
    }

    private void printBooksTitleReleaseDateAndCountByAuthorName(String firstName, String lastName) {
        bookService.getBooksTitleReleaseDateAndCopiesByAuthorNames(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printTitlesReleasedAfter(Date parse) {
        bookService.getTitlesOfAllBooksReleasedAfter(parse)
                .forEach(System.out::println);
    }

    private void seedDatabase() throws IOException, ParseException {
        //TODO seed Authors from file authors.txt
//TODO seed categories from file categories.txt

        List<Author> authors = new ArrayList<>();
        String line = "";
        BufferedReader authorsReader = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("authors.txt")));
        while((line = authorsReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            Author author = new Author();
            author.setFirstName(data[0]);
            author.setLastName(data[1]);
            authors.add(author);
            authorService.save(author);
        }

        BufferedReader categoriesReader = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("categories.txt")));
        List<Category> categories = new ArrayList<>();

        while((line = categoriesReader.readLine()) !=null) {
            String category = line.trim();
            if (!category.isEmpty()) {
                Category categoryObject = new Category();
                categoryObject.setName(category);
                categories.add(categoryObject);
                categoryService.save(categoryObject);
            }
        }


//        BufferedReader booksReader = new BufferedReader(new FileReader("books.txt"));
        BufferedReader booksReader = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("books.txt")));
        Random random = new Random();
        while((line = booksReader.readLine()) != null){
            String[] data = line.split("\\s+");


            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            //TODO add random categories for current book
            int categoryIndex = random.nextInt(categories.size());
            Category category = categories.get(categoryIndex);

            book.setCategories( new HashSet() {{add(category);}});

            bookService.save(book);
        }
    }
}
