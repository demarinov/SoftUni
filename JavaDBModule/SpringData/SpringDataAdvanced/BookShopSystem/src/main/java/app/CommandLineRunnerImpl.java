package app;

import app.model.entity.*;
import app.service.AuthorService;
import app.service.BookService;
import app.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
//        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        // 1.	Books Titles by Age Restriction
//        printBookTitlesByAgeRestriction();

        // 2.	Golden Books
//        printGoldenBooksLessThan5000();

        // 3.	Books by Price
//        printBooksByPriceBelowAbove();

        // 4.	Not Released Books
//        printBooksNotReleased();
        
        // 5.	Books Released Before Date
//        printBooksReleasedBeforeDate();

        // 6.	Authors Search
//        printAuthorsEndingWith();

        // 7.	Books Search
//        printBooksContaining();

        // 8.	Book Titles Search
//        printBooksWrittenByAuthors();

        // 9.	Count Books
//        printCountBooksGreaterThan();

        // 10.	Total Book Copies
//        printTotalBookCopiesByAuthor();

        // 11.	Reduced Book
//        printReducedBook();

        // 12.	* Increase Book Copies
//        printIncreaseOfBookCopies();

        // 13.	* Remove Books
//        printDeletedBooks();

        // 14.	* Stored Procedure
        printTotalNumberOfBooks();
    }

    private void printTotalNumberOfBooks() {

        Scanner sc = new Scanner(System.in);

        String[] name = sc.nextLine().split(" ");
        int bookCountByAuthor = bookService.getBookCountByAuthor(name[0], name[1]);

        System.out.println(bookCountByAuthor);
    }

    private void printDeletedBooks() {

        Integer num = 1000;

        List<Book> books = bookService.findBooksByCopiesLessThan(num);

        if (books != null) {
            bookService.deleteBooks(books);
        }
    }

    private void printIncreaseOfBookCopies() throws ParseException {

        Scanner sc = new Scanner(System.in);

        String dateStr = sc.nextLine();
        Integer copiesIncrease = Integer.parseInt(sc.nextLine());

        LocalDate date = new DateFormatter("dd MMM yyyy").parse(dateStr, Locale.getDefault())
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<Book> books = bookService.findBooksByDateAfter(date);

        if (books != null) {
            int booksAdded = books.size() * copiesIncrease;

            bookService.increaseBookCopies(books, copiesIncrease);

            System.out.println(booksAdded);
        }
    }

    private void printReducedBook() {

        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();

        List<ReducedBook> books = bookService.findReducedBookByTitle(title);

        books.forEach(b -> System.out.printf("%s %s %s %s%n",
                b.getTitle(), b.getEditionType(), b.getAgeRestriction(), b.getPrice()));
    }

    private void printTotalBookCopiesByAuthor() {

        List<BooksByAuthor> books = bookService.findTotalBookCopiesByAuthor();

        books.forEach(b -> System.out.printf("%s %s - %d%n", b.getFirstName(),
                b.getLastName(), b.getTotalCount()));
    }

    private void printCountBooksGreaterThan() {

        Scanner sc = new Scanner(System.in);
        Integer num = Integer.parseInt(sc.nextLine());

        List<Book> books = bookService.findBooksByTitleCountGreaterThan(num);

        if (books != null) {
            System.out.println(books.size());
        }
    }

    private void printBooksWrittenByAuthors() {

        Scanner sc = new Scanner(System.in);

        String starting = sc.nextLine();

        List<Book> books = bookService.findBooksWrittenByAuthor(starting);

        books.forEach(b -> System.out.printf("%s (%s %s)%n",b.getTitle(),
                b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    private void printBooksContaining() {

        Scanner sc = new Scanner(System.in);

        String contained = sc.nextLine();

        List<Book> books = bookService.findBooksByTitleContainingIgnoreCase(contained);

        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%s (%s %s)",books.get(i).getTitle());
        }
    }

    private void printAuthorsEndingWith() {

        Scanner sc = new Scanner(System.in);

        String ending = sc.nextLine();

        List<Author> authors = authorService.findAuthorsByFirstNameEndingWith(ending);

        authors.forEach(a -> System.out.printf("%s %s%n",a.getFirstName(), a.getLastName()));

    }

    private void printBooksReleasedBeforeDate() throws ParseException {

        Scanner sc = new Scanner(System.in);

        String dateStr = sc.nextLine();

        LocalDate date = new DateFormatter("dd-MM-yyyy").parse(dateStr, Locale.getDefault())
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Book> books = bookService.findBooksByDateBefore(date);

        books.forEach(b -> System.out.printf("%s %s %s%n", b.getTitle(),
                b.getEditionType(), b.getPrice()));
    }

    private void printBooksNotReleased() {
        Scanner sc = new Scanner(System.in);

        Integer year = Integer.parseInt(sc.nextLine());

        List<Book> books = bookService.findBooksNotReleasedInYear(year);

        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void printBooksByPriceBelowAbove() {

        BigDecimal minorPrice = new BigDecimal("5");
        BigDecimal majorPrice = new BigDecimal("40");

        List<Book> books =  bookService.
                findBooksByPriceLessThanOrPriceGreaterThan(minorPrice, majorPrice);

        books.forEach(b -> System.out.printf("%s - $%s%n", b.getTitle(), b.getPrice()));
    }

    private void printGoldenBooksLessThan5000() {

        EditionType editionType =EditionType.GOLD;
        Integer maxCopies = new Integer("5000");

        List<Book> books = bookService.
                findAllBooksByEditionTypeAndCopiesLessThan(editionType, maxCopies);

        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void printBookTitlesByAgeRestriction() {

        Scanner sc = new Scanner(System.in);

        String ageStr = sc.nextLine();
        AgeRestriction ageRestriction = null;

        switch (ageStr.toLowerCase()) {
            case "minor":
                ageRestriction = AgeRestriction.MINOR;
                break;
            case "teen":
                ageRestriction = AgeRestriction.TEEN;
                break;
            case "adult":
                ageRestriction = AgeRestriction.ADULT;
                break;
        }

        List<Book> books = bookService.findAllBooksByAgeRestriction(ageRestriction);
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
