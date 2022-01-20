package app.service.impl;

import app.model.entity.*;
import app.repository.BookRepository;
import app.service.AuthorService;
import app.service.BookService;
import app.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Paths.get(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllBooksByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllBooksByEditionTypeAndCopiesLessThan(EditionType editionType, Integer maxCopies) {
        return bookRepository.findAllBooksByEditionTypeAndCopiesLessThan(editionType, maxCopies);
    }

    @Override
    public List<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal minorPrice, BigDecimal majorPrice) {
        return bookRepository.findBooksByPriceLessThanOrPriceGreaterThan(minorPrice, majorPrice);
    }

    @Override
    public List<Book> findBooksNotReleasedInYear(Integer year) {
        return bookRepository.findBooksNotReleasedInYear(year);
    }

    @Override
    public List<Book> findBooksByDateBefore(LocalDate date) {
        return bookRepository.findBooksByReleaseDateBefore(date);
    }

    @Override
    public List<Book> findBooksByTitleContainingIgnoreCase(String contained) {
        return bookRepository.findBooksByTitleContainingIgnoreCase(contained);
    }

    @Override
    public List<Book> findBooksWrittenByAuthor(String starting) {
        String pattern = String.format("%s%%",starting);
        return bookRepository.findBooksWrittenByAuthor(pattern);
    }

    @Override
    public List<Book> findBooksByTitleCountGreaterThan(Integer num) {
        return bookRepository.findBooksByTitleCountGreaterThan(num);
    }

    @Override
    public List<BooksByAuthor> findTotalBookCopiesByAuthor() {
        return bookRepository.findTotalBookCopiesByAuthor();
    }

    @Override
    public List<ReducedBook> findReducedBookByTitle(String title) {
        return bookRepository.findReducedBookByTitle(title);
    }

    @Override
    public List<Book> findBooksByDateAfter(LocalDate date) {
        return bookRepository.findBooksByReleaseDateAfter(date);
    }

    @Transactional
    @Override
    public void increaseBookCopies(List<Book> books, Integer copiesIncrease) {
        for (Book book : books) {
            book.setCopies(book.getCopies()+copiesIncrease);
        }

        bookRepository.saveAll(books);
    }

    @Override
    public List<Book> findBooksByCopiesLessThan(Integer num) {
        return bookRepository.findBooksByCopiesLessThan(num);
    }

    @Transactional
    @Override
    public void deleteBooks(List<Book> books) {

        bookRepository.deleteAll(books);
    }

    @Override
    public int getBookCountByAuthor(String firstName, String lastName) {
        return bookRepository.getBookCountByAuthor(firstName, lastName);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
