package at.fhv.team05.presentation.search;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.dtos.IDvd;
import at.fhv.team05.rmiinterfaces.BookRMI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
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
    private TextField txtFieldAsin;

    @FXML
    private TextField txtFieldGenreDvd;

    @FXML
    private TableView<IDvd> tableViewDvdSearch;

    @FXML
    private TableColumn<IDvd, String> tblColTitleDvd;

    @FXML
    private TableColumn<IDvd, String> tblColDirector;

    @FXML
    private TableColumn<IDvd, String> tblColAsin;

    @FXML
    private TableColumn<IDvd, String> tblColGenreDvd;
    @FXML
    private TableColumn<IDvd, Date> tblColReleaseDate;


    @FXML
    public void onSearchBtnPressedBook(ActionEvent event) {
        List<IBook> books = new LinkedList<>();
        try {
            BookRMI searchForBook = (BookRMI) Naming.lookup("rmi://localhost/ApplicationController");
            books.addAll(searchForBook.searchForBook(getBookTitle(), getAuthor(), getIsbn()));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        resultTableBook(books);


    }

    @FXML
    public void onSearchBtnPressedDvd(ActionEvent event) {
        if (getDvdTitle() != null) {
            System.out.println(getDvdTitle());
        }
        if (getDirector() != null) {
            System.out.println(getDirector());
        }
        if (getAsin() != null) {
            System.out.println(getAsin());
        }
        if (getDvdGenre() != null) {
            System.out.println(getDvdGenre());
        }
    }

    //Book
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


    //DVD
    private String getDvdTitle() {
        return txtFieldTitleDvd.getText();
    }

    private String getDvdGenre() {
        return txtFieldGenreDvd.getText();
    }

    private String getAsin() {
        return txtFieldAsin.getText();
    }

    private String getDirector() {
        return txtFieldDirector.getText();
    }

    private void resultTableBook(List<IBook> bookList) {

        ObservableList<IBook> resultData = FXCollections.observableArrayList();
        resultData.addAll(bookList);
        tblColTitleBook.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tblColIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableViewBookSearch.setItems(resultData);


    }

    private void resultTableDvd(List<IDvd> dvdList) {
        ObservableList<IDvd> resultData = FXCollections.observableArrayList();
        resultData.addAll(dvdList);
        tblColTitleDvd.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        tblColAsin.setCellValueFactory(new PropertyValueFactory<>("asin"));
        tblColReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableViewDvdSearch.setItems(resultData);
    }

}
