package at.fhv.team05.presentation.detailView;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.detailView.buttons.ReserveButtonPresenter;
import at.fhv.team05.presentation.detailView.buttons.ReserveButtonView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DetailPresenter extends Presenter {

    @FXML
    private TitledPane titledPaneMedium;

    private IMediumDTO medium;

    @FXML
    private Label lblTitle;

    @FXML
    private Label labelA;

    @FXML
    private Label labelB;

    @FXML
    private Label labelC;

    @FXML
    private Label labelD;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private TableView<CopyDTO> tblViewCopies;

    @FXML
    private TableColumn<CopyDTO, Integer> tblColCopyNumber;

    @FXML
    private TableColumn<CopyDTO, String> tblColAvailability;

    @FXML
    private TableColumn<CopyDTO, String> tblColLocation;

    @FXML
    private AnchorPane buttonContainer;


    /**
    * Method for displaying the detailed information of a medium and copies.
     */
    public void initView() {
        titledPaneMedium.setCollapsible(false);
        Map<String, Object> attributeMap = medium.getAttributeMap();
        lblTitle.setText((String) attributeMap.get("title"));
        labelB.setText("Publisher: ");
        label2.setText((String) attributeMap.get("publisher"));
        labelC.setText("Release Date: ");
        Date releaseDate = (Date) attributeMap.get("releaseDate");
        label3.setText(releaseDate.toString());
        if ("book".equalsIgnoreCase(medium.getType())) {
            labelA.setText("Author: ");
            label1.setText((String) attributeMap.get("prod"));
            labelD.setText("ISBN: ");
            label4.setText((String) attributeMap.get("articleId"));
        } else if ("dvd".equalsIgnoreCase(medium.getType())) {
            labelA.setText("Director: ");
            label1.setText((String) attributeMap.get("prod"));
            labelD.setText("ASIN: ");
            label4.setText((String) attributeMap.get("articleId"));
        }

        //Display copies of given medium.
        try {
            ResultListDTO<CopyDTO> resultCopies = ClientRun.controller.getCopiesByMedium(medium);
            if (resultCopies.getException() == null) {
                initTable(resultCopies.getListDTO());
            } else {
                errorAlert(resultCopies.getException().getMessage());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
    * Shows the reservation button in the detail view to reserve the given medium.
     */
    public void initReserveButton() {
        ReserveButtonView reserveButtonView = new ReserveButtonView();
        ReserveButtonPresenter presenter = (ReserveButtonPresenter) reserveButtonView.getPresenter();
        presenter.setMedium(medium);
        presenter.setParent(parent);
        buttonContainer.getChildren().setAll(reserveButtonView.getView());
    }

    /**
    * Displays the detailed information for each copy of a medium in a table.
     * @param copies All copies that should be displayed in the table.
    */
    private void initTable(List<CopyDTO> copies) {
        ObservableList<CopyDTO> resultData = FXCollections.observableArrayList();
        resultData.addAll(copies);
        tblColCopyNumber.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
        tblColAvailability.setCellValueFactory(new PropertyValueFactory<>("copyStatus"));
        tblColLocation.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCategory().toString()));
        tblViewCopies.setItems(resultData);
    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
