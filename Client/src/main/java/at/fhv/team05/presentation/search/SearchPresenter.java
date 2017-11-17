package at.fhv.team05.presentation.search;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class SearchPresenter extends Presenter{
    @FXML
    private Label lblViewTitle;

    @FXML
    private TextField txtFiledTitleBook;

    @FXML
    private TextField txtFiledAuthor;

    @FXML
    private TextField txtFiledIsbn;

    @FXML
    private TableView<BookDTO> tableViewBookSearch;

    @FXML
    private TableColumn<BookDTO, String> tblColTitleBook;

    @FXML
    private TableColumn<BookDTO, String> tblColAuthor;

    @FXML
    private TableColumn<BookDTO, String> tblColIsbn;

    @FXML
    private TableColumn<BookDTO, Date> tblColReleaseDateBook;


    @FXML
    private TextField txtFieldTitleDvd;

    @FXML
    private TextField txtFieldDirector;

    @FXML
    private TextField txtFieldAsin;


    @FXML
    private TableView<DvdDTO> tableViewDvdSearch;

    @FXML
    private TableColumn<DvdDTO, String> tblColTitleDvd;

    @FXML
    private TableColumn<DvdDTO, String> tblColDirector;

    @FXML
    private TableColumn<DvdDTO, String> tblColAsin;

    @FXML
    private TableColumn<DvdDTO, Date> tblColReleaseDate;


    @FXML
    public void onSearchBtnPressedBook(ActionEvent event) {
        List<BookDTO> books = new LinkedList<>();
        try {
            BookDTO book = new BookDTO(getBookTitle(), getAuthor(), getIsbn(), "Book");
            ResultListDTO<BookDTO> resultBooks = ClientRun.controller.searchForBook(book);
            if (resultBooks.getException() == null) {
                books.addAll(resultBooks.getListDTO());
                resultTableBook(books);
            } else {
                errorAlert(resultBooks.getException().getMessage());
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void onSearchBtnPressedDvd(ActionEvent event) {
        List<DvdDTO> dvds = new LinkedList<>();
        try {
            DvdDTO dvd = new DvdDTO(getDvdTitle(), getDirector(), getAsin(), "Dvd");
            ResultListDTO<DvdDTO> resultDvd = ClientRun.controller.searchForDvd(dvd);
            if (resultDvd.getException() == null) {
                dvds.addAll(resultDvd.getListDTO());
                resultTableDvd(dvds);
            } else {
                errorAlert(resultDvd.getException().getMessage());
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //Book
    private String getAuthor() {
        return txtFiledAuthor.getText();
    }

    private String getBookTitle() {
        return txtFiledTitleBook.getText();
    }

    private String getIsbn() {
        return txtFiledIsbn.getText();
    }


    //DVD
    private String getDvdTitle() {
        return txtFieldTitleDvd.getText();
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
        tblColReleaseDateBook.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
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


    public void doubleClickReservation() {
        setDoubleClickReservation(tableViewBookSearch);
        setDoubleClickReservation(tableViewDvdSearch);
    }

    public void doubleClickDefault() {
        setDoubleClickDefault(tableViewBookSearch);
        setDoubleClickDefault(tableViewDvdSearch);
    }

    public void setDoubleClickReservationOverview() {
        setDoubleClickReservationOverview(tableViewBookSearch);
        setDoubleClickReservationOverview(tableViewDvdSearch);
    }

    private void setDoubleClickReservation(TableView table) {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                parent.openDetailView((IMediumDTO) table.getSelectionModel().getSelectedItem(), true);
            }
        });
    }

    private void setDoubleClickDefault(TableView table) {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                parent.openDetailView((IMediumDTO) table.getSelectionModel().getSelectedItem(), false);
            }
        });
    }

    private void setDoubleClickReservationOverview(TableView table) {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                parent.openReservationOverview((IMediumDTO) table.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void setLblViewTitle(String viewTitle) {
        lblViewTitle.setText(viewTitle);
    }
}
