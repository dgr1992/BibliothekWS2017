package at.fhv.team05.Application;

import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Book;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.LinkedList;

public class BookController {
    private Repository<Book> _bookRepository = null;
    private HashSet<Book> _bookSet;
    private LinkedList<BookDTO> _foundBooks;

    private static BookController instance;

    private static final Logger log = LogManager.getLogger(BookController.class);


    private BookController() {

        super();

        _bookRepository = RepositoryFactory.getRepositoryInstance(Book.class);
        _bookSet = new HashSet<>();

        _bookSet.addAll(_bookRepository.list());
    }

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    public LinkedList<BookDTO> searchForBook(String title, String author, String ISBN) throws RemoteException {
        _foundBooks = new LinkedList<>();
        try {
            for (Book book : _bookSet) {
                if (StringUtilities.containsIgnoreCase(book.getTitle(), title)
                        && StringUtilities.containsIgnoreCase(book.getAuthor(), author)
                        && StringUtilities.containsIgnoreCase(book.getIsbn(), ISBN)) {
                    _foundBooks.add(new BookDTO(book));
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return _foundBooks;
    }
}

