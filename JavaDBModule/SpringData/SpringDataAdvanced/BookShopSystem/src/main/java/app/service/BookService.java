package app.service;

import app.model.entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllBooksByEditionTypeAndCopiesLessThan(EditionType editionType, Integer maxCopies);

    List<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal minorPrice, BigDecimal majorPrice);

    List<Book> findBooksNotReleasedInYear(Integer year);

    List<Book> findBooksByDateBefore(LocalDate date);

    List<Book> findBooksByTitleContainingIgnoreCase(String contained);

    List<Book> findBooksWrittenByAuthor(String starting);

    List<Book> findBooksByTitleCountGreaterThan(Integer num);

    List<BooksByAuthor> findTotalBookCopiesByAuthor();

    List<ReducedBook> findReducedBookByTitle(String title);

    List<Book> findBooksByDateAfter(LocalDate date);

    void increaseBookCopies(List<Book> books, Integer copiesIncrease);

    List<Book> findBooksByCopiesLessThan(Integer num);

    void deleteBooks(List<Book> books);

    int getBookCountByAuthor(String firstName, String lastName);
}
