package at.fhv.team05.application;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.rmiinterfaces.SearchForBook;

import java.util.LinkedList;

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

    public LinkedList<IBook> searchForBook(String title, String author, String ISBN) {
        return new LinkedList<IBook>();
    }
}
