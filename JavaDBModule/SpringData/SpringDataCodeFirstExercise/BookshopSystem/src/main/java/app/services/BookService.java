package app.services;

import app.models.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void save(Book entity);

    List<String> getTitlesOfAllBooksReleasedAfter(Date date);

    List<String> getBooksTitleReleaseDateAndCopiesByAuthorNames(final String firstName, final String lastName);
}
