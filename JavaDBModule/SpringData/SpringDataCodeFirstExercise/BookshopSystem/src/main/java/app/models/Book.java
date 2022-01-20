package app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {

    @Id
    @Column(name="book_id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Enumerated
    @Column(name="age_restriction", length = 11)
    private AgeRestriction ageRestriction;

    @Column(name="copies", length = 11)
    private int copies;

    @Column(name="description")
    private String description;

    @Enumerated
    @Column(name = "edition_type", length = 11)
    private EditionType editionType;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="release_date")
    private Date releaseDate;

    @Column(name="title")
    private String title;

    @ManyToMany
    @JoinTable(name="books_categories",
    joinColumns = @JoinColumn(name="book_id", referencedColumnName = "book_id"),
    inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    @ManyToOne(optional = false)
    @JoinColumn(name="author_id", referencedColumnName = "author_id")
    private Author author;

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
