package app.services;

import app.models.Book;
import app.repositories.AuthorRepository;
import app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book entity) {
        bookRepository.save(entity);
    }

    @Override
    public List<String> getTitlesOfAllBooksReleasedAfter(Date date) {

        return bookRepository.getBooksByReleaseDateAfter(date).stream().map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksTitleReleaseDateAndCopiesByAuthorNames(final String firstName, final String lastName) {
        return this.bookRepository
                .getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .stream()
                .map(book -> String.format("%s - %s - %d",
                        book.getTitle(), book.getReleaseDate().toString(), book.getCopies()))
                .collect(Collectors.toList());
    }
}
