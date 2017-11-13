package at.fhv.team05.presentation.detailView;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CategoryDTO;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.detailView.buttons.ReserveButtonPresenter;
import at.fhv.team05.presentation.detailView.buttons.ReserveButtonView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class DetailPresenter extends Presenter {
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


    public void initView() {
        Map<String, Object> attributeMap = medium.getAttributeMap();
        lblTitle.setText((String) attributeMap.get("title"));
        labelB.setText("Publisher: ");
        label2.setText((String) attributeMap.get("publisher"));
        labelC.setText("Release Date: ");
        Timestamp releaseDate = (Timestamp) attributeMap.get("releaseDate");
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
        try {
            initTable(ClientRun.controller.getCopiesByMedium(medium));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initReserveButton() {
        ReserveButtonView reserveButtonView = new ReserveButtonView();
        ReserveButtonPresenter presenter = (ReserveButtonPresenter) reserveButtonView.getPresenter();
        presenter.setMedium(medium);
        presenter.setParent(parent);
        buttonContainer.getChildren().setAll(reserveButtonView.getView());
    }

    private void initTable(List<CopyDTO> mediums) {
        ObservableList<CopyDTO> resultData = FXCollections.observableArrayList();
        resultData.addAll(mediums);
        tblColCopyNumber.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
        tblColAvailability.setCellValueFactory(new PropertyValueFactory<>("copyStatus"));
        tblColLocation.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCategory().toString()));
        tblViewCopies.setItems(resultData);
    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
