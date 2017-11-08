package at.fhv.team05.presentation.search;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class SearchPresenter implements Initializable{
    private MainViewPresenter parent;

    @FXML
    private TextField txtFiledTitleBook;

    @FXML
    private TextField txtFiledAuthor;

    @FXML
    private TextField txtFiledIsbn;

    @FXML
    private TextField txtFiledGenreBook;

    @FXML
    private TableView<BookDTO> tableViewBookSearch;

    @FXML
    private TableColumn<BookDTO, String> tblColTitleBook;

    @FXML
    private TableColumn<BookDTO, String> tblColAuthor;

    @FXML
    private TableColumn<BookDTO, String> tblColIsbn;

    @FXML
    private TableColumn<BookDTO, String> tblColGenreBook;

    @FXML
    private TextField txtFieldTitleDvd;

    @FXML
    private TextField txtFieldDirector;

    @FXML
    private TextField txtFieldAsin;

    @FXML
    private TextField txtFieldGenreDvd;

    @FXML
    private TableView<DvdDTO> tableViewDvdSearch;

    @FXML
    private TableColumn<DvdDTO, String> tblColTitleDvd;

    @FXML
    private TableColumn<DvdDTO, String> tblColDirector;

    @FXML
    private TableColumn<DvdDTO, String> tblColAsin;

    @FXML
    private TableColumn<DvdDTO, String> tblColGenreDvd;

    @FXML
    private TableColumn<DvdDTO, Date> tblColReleaseDate;



    @FXML
    public void onSearchBtnPressedBook(ActionEvent event) {
        List<BookDTO> books = new LinkedList<>();
        try {
            BookDTO book = new BookDTO(getBookTitle(), getAuthor(), getIsbn());
            books.addAll(ClientRun.controller.searchForBook(book));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        resultTableBook(books);


    }

    @FXML
    public void onSearchBtnPressedDvd(ActionEvent event) {
        List<DvdDTO> dvds = new LinkedList<>();
        try {
            DvdDTO dvd = new DvdDTO(getDvdTitle(), getDirector(), getAsin());
            dvds.addAll(ClientRun.controller.searchForDvd(dvd));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        resultTableDvd(dvds);
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

    private void resultTableBook(List<BookDTO> bookList) {

        ObservableList<BookDTO> resultData = FXCollections.observableArrayList();
        resultData.addAll(bookList);
        tblColTitleBook.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tblColIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableViewBookSearch.setItems(resultData);


    }

    private void resultTableDvd(List<DvdDTO> dvdList) {
        ObservableList<DvdDTO> resultData = FXCollections.observableArrayList();
        resultData.addAll(dvdList);
        tblColTitleDvd.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        tblColAsin.setCellValueFactory(new PropertyValueFactory<>("asin"));
        tblColReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableViewDvdSearch.setItems(resultData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoubleClick(tableViewDvdSearch);
        setDoubleClick(tableViewBookSearch);

    }

    private void setDoubleClick(TableView table) {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                parent.openDetailView((IMediumDTO) table.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }
}
