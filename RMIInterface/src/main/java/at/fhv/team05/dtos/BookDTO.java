package at.fhv.team05.dtos;

import java.io.Serializable;
import java.util.Date;

public class BookDTO implements Serializable{

    private int id;
    private String title;
    private String isbn;
    private Date releaseDate;
    private CategoryDTO categoryDTO;
    private String publisher;
    private String author;

    public BookDTO(IBook book) {

        id = book.getId();
        title = book.getTitle();
        isbn = book.getIsbn();
        releaseDate = book.getReleaseDate();
        categoryDTO = new CategoryDTO(book.getCategory());
        publisher = book.getPublisher();
        author = book.getAuthor();

    }

    public BookDTO(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }
}
