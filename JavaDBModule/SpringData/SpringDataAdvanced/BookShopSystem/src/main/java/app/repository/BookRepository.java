package app.repository;

import app.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllBooksByEditionTypeAndCopiesLessThan(EditionType editionType, Integer maxCopies);

    List<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal minorPrice, BigDecimal majorPrice);

    @Query("select b from Book b where year(b.releaseDate) <> :year")
    List<Book> findBooksNotReleasedInYear(@Param(value="year")Integer year);

    List<Book> findBooksByReleaseDateBefore(LocalDate date);

    List<Book> findBooksByTitleContainingIgnoreCase(String contained);

    @Query("select b from Book b join b.author a where a.lastName like :pattern")
    List<Book> findBooksWrittenByAuthor(@Param(value="pattern") String pattern);

    @Query("select b from Book b where length(b.title) > :num")
    List<Book> findBooksByTitleCountGreaterThan(@Param(value="num")Integer num);

    @Query("select new app.model.entity.BooksByAuthor(b.author.firstName, b.author.lastName, " +
            "sum(b.copies)) from Book b " +
            "group by b.author order by sum(b.copies) desc")
    List<BooksByAuthor> findTotalBookCopiesByAuthor();

    @Query("select new app.model.entity.ReducedBook(b.title, b.editionType, b.ageRestriction, b.price) " +
            "from Book b where b.title = :title")
    List<ReducedBook> findReducedBookByTitle(@Param(value="title") String title);

    List<Book> findBooksByReleaseDateAfter(LocalDate date);

    List<Book> findBooksByCopiesLessThan(Integer num);

    @Procedure(value = "GET_TOTAL_AMOUNT_OF_BOOKS")
    int getBookCountByAuthor(String firstName, String lastName);
}
