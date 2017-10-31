package at.fhv.team05.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DVD")
public class Dvd {
    private int id;
    private String title;
    private String asin;
    private Date releaseDate;
    private int categoryId;
    private String publisher;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "asin", nullable = false, length = 20)
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Basic
    @Column(name = "releaseDate", nullable = false)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "categoryId", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "publisher", nullable = false, length = 50)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dvd dvdEntity = (Dvd) o;

        if (id != dvdEntity.id) {
            return false;
        }
        if (categoryId != dvdEntity.categoryId) {
            return false;
        }
        if (title != null ? !title.equals(dvdEntity.title) : dvdEntity.title != null) {
            return false;
        }
        if (asin != null ? !asin.equals(dvdEntity.asin) : dvdEntity.asin != null) {
            return false;
        }
        if (releaseDate != null ? !releaseDate.equals(dvdEntity.releaseDate) : dvdEntity.releaseDate != null) {
            return false;
        }
        if (publisher != null ? !publisher.equals(dvdEntity.publisher) : dvdEntity.publisher != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (asin != null ? asin.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + categoryId;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        return result;
    }
}
