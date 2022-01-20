package app.services;

import app.models.Author;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface AuthorService {
    void save(Author author);
    List<String> getAuthorsWithBooksReleasedBeforeDate(final Date date);
}
