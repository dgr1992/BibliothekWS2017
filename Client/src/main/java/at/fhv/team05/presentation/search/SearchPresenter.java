package at.fhv.team05.presentation.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Flo on 29.10.17.
 */
public class SearchPresenter {


    @FXML
    private TabPane tabPaneSearch;

    @FXML
    private Tab tabBook;

    @FXML
    private TextField txtFiledTitleBook;

    @FXML
    private TextField txtFiledAuthor;

    @FXML
    private TextField txtFiledIsbn;

    @FXML
    private TextField txtFiledGenreBook;

    @FXML
    private TableView<?> tableViewBookSearch;

    @FXML
    private TableColumn<?, ?> tblColTitleBook;

    @FXML
    private TableColumn<?, ?> tblColAuthor;

    @FXML
    private TableColumn<?, ?> tblColIsbn;

    @FXML
    private TableColumn<?, ?> tblColGenreBook;

    @FXML
    private Tab tabDvd;

    @FXML
    private TextField txtFieldTitleDvd;

    @FXML
    private TextField txtFieldDirector;

    @FXML
    private TextField txtFieldActor;

    @FXML
    private TextField txtFieldGenreDvd;

    @FXML
    private TableView<?> tableViewDvdSearch;

    @FXML
    private TableColumn<?, ?> tblColTitleDvd;

    @FXML
    private TableColumn<?, ?> tblColDirector;

    @FXML
    private TableColumn<?, ?> tblColActor;

    @FXML
    private TableColumn<?, ?> tblColGenreDvd;

    @FXML
    public void onSearchBtnPressedBook(ActionEvent event) {
        if (getBookTitle() != null) {
            System.out.println(getBookTitle());
        }
        if (getAuthor() != null) {
            System.out.println(getAuthor());
        }
        if (getIsbn() != null) {
            System.out.println(getIsbn());
        }
        if (getBookGenre() != null) {
            System.out.println(getBookGenre());
        }
    }

    @FXML
    public void onSearchBtnPressedDvd(ActionEvent event) {
        if (getDvdTitle() != null) {
            System.out.println(getDvdTitle());
        }
        if (getDirector() != null) {
            System.out.println(getDirector());
        }
        if (getActor() != null) {
            System.out.println(getActor());
        }
        if (getDvdGenre() != null) {
            System.out.println(getDvdGenre());
        }
    }

    private String getAuthor() {
        return txtFiledAuthor.getText();
    }

    private String getBookTitle() {
        return txtFiledTitleBook.getText();
    }

    private String getBookGenre() {
        return txtFiledGenreBook.getText();
    }
    private String getIsbn() {
        return txtFiledIsbn.getText();
    }

    private String getDvdTitle() {
        return txtFieldTitleDvd.getText();
    }
    private String getDvdGenre() {
        return txtFieldGenreDvd.getText();
    }
    private String getActor() {
        return txtFieldActor.getText();
    }
    private String getDirector() {
        return txtFieldDirector.getText();
    }
}
