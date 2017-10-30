package at.fhv.team05.application;

import at.fhv.team05.application.rmiinterfaces.SearchForBook;
import at.fhv.team05.domain.Book;

public class SearchController implements SearchForBook {

    public Book searchForBook(String title, String author, String ISBN, String Genre) {
        return null;
    }
}
