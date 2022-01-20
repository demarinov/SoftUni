package app.model.entities;

import app.model.validators.game_title.GameTitle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="games")
public class Game extends BaseEntity {

    // •	A game has title, trailer (YouTube Video Id), image thumbnail (URL),
    // size, price, description and release date
    @Column(unique = true)
    @GameTitle
    private String title;

    @Column(name="trailer")
    private Integer trailer;

    @Column
    private String url;

    @Column
    private Integer size;

    @Column
    private BigDecimal price;

    @Column
    private String description;

    @Column(name="release_date")
    private Date releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTrailer() {
        return trailer;
    }

    public void setTrailer(Integer trailer) {
        this.trailer = trailer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
