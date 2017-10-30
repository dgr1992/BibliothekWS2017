package at.fhv.team05.presentation.search;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.rmiinterfaces.SearchForBook;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.LinkedList;
import java.util.List;


public class SearchPresenter {
    @FXML
    private TextField txtFiledTitleBook;

    @FXML
    private TextField txtFiledAuthor;

    @FXML
    private TextField txtFiledIsbn;

    @FXML
    private TextField txtFiledGenreBook;

    @FXML
    private TableView<IBook> tableViewBookSearch;

    @FXML
    private TableColumn<IBook, String> tblColTitleBook;

    @FXML
    private TableColumn<IBook, String> tblColAuthor;

    @FXML
    private TableColumn<IBook, String> tblColIsbn;

    @FXML
    private TableColumn<IBook, String> tblColGenreBook;


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
        List<IBook> books = new LinkedList<>();
        try {
            SearchForBook searchForBook = (SearchForBook) Naming.lookup("rmi://10.0.51.95/SearchController");
            books = searchForBook.searchForBook(getBookTitle(), getAuthor(), getIsbn());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        resultTable(books);


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

    private void resultTable(List<IBook> bookList) {

        ObservableList<IBook> resultData = FXCollections.observableArrayList();
        resultData.addAll(bookList);
        // tblColTitleBook.setCellValueFactory(new PropertyValueFactory<IBook,String>("title"));
        tblColAuthor.setCellValueFactory(new PropertyValueFactory<IBook, String>("author"));
        tblColIsbn.setCellValueFactory(new PropertyValueFactory<IBook, String>("isbn"));

        tblColTitleBook.setCellValueFactory(param -> {
            if (param.getValue().getTitle() != null) {
                return new SimpleStringProperty(param.getValue().getTitle());
            }
            return new SimpleStringProperty("");
        });
        tableViewBookSearch.setItems(resultData);


    }

}
