package at.fhv.team05.application;

import at.fhv.team05.domain.Book;
import at.fhv.team05.rmiinterfaces.SearchForBook;

public class SearchController implements SearchForBook {


    private static SearchController instance;

    private SearchController() {
    }

    public static SearchController getInstance() {
        if (instance == null) {
            instance = new SearchController();
        }
        return instance;
    }

    public Book searchForBook(String title, String author, String ISBN, String Genre) {

        return null;
    }

}
