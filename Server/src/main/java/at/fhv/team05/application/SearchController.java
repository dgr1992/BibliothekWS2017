package at.fhv.team05.application;

import at.fhv.team05.application.rmiinterfaces.SearchForBook;
import at.fhv.team05.domain.Book;

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
