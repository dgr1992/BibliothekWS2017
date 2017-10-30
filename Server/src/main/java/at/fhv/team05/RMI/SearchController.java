package at.fhv.team05.RMI;

import at.fhv.team05.domain.Book;
import at.fhv.team05.dtos.IBook;
import at.fhv.team05.persistence.BookRepository;
import at.fhv.team05.rmiinterfaces.SearchForBook;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.LinkedList;

public class SearchController extends UnicastRemoteObject implements SearchForBook {
    private BookRepository _bookRepository = null;
    private HashSet<Book> _bookSet;
    private LinkedList<IBook> _foundBooks;

    private static SearchController instance;

    public SearchController() throws RemoteException {

        super();

        _bookRepository = new BookRepository();
        _bookSet = new HashSet<>();

        _bookSet.addAll(_bookRepository.list());
    }

    public static SearchController getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SearchController();
        }
        return instance;
    }

    @Override
    public LinkedList<IBook> searchForBook(String title, String author, String ISBN) throws RemoteException {

        _foundBooks = new LinkedList<>();

        for (Book book : _bookSet) {
            if (book.getTitle().equalsIgnoreCase(title) || book.getAuthor().equalsIgnoreCase(author) || book.getIsbn().equalsIgnoreCase(ISBN)) {

                _foundBooks.add((IBook) book);
            }
        }

        return _foundBooks;
    }
}

