package at.fhv.team05.Application.medium;

import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.medium.Book;
import at.fhv.team05.dtos.BookDTO;

public class BookController extends MediumController<Book, BookDTO> {

    private static BookController mInstance;

    private BookController(Class<Book> medium) {
        super(medium);
    }

    public static BookController getInstance() {
        if (mInstance == null) {
            mInstance = new BookController(Book.class);
        }
        return mInstance;
    }

    @Override
    protected boolean compareInput(Book book, BookDTO bookDTO) {
        return StringUtilities.containsIgnoreCase(book.getTitle(), bookDTO.getTitle())
                && StringUtilities.containsIgnoreCase(book.getAuthor(), bookDTO.getAuthor())
                && StringUtilities.containsIgnoreCase(book.getIsbn(), bookDTO.getIsbn());
    }

    @Override
    protected BookDTO createDTO(Book book) {
        return new BookDTO(book);
    }

}

