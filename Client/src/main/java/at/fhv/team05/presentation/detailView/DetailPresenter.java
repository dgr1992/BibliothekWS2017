package at.fhv.team05.presentation.detailView;
import at.fhv.team05.dtos.IMediumDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DetailPresenter implements Initializable{
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
    private AnchorPane mediumContainer;

    @FXML
    private TableView<?> tblViewCopies;

    @FXML
    private TableColumn<?, ?> tblColCopyNumber;

    @FXML
    private TableColumn<?, ?> tblColAvailability;

    @FXML
    private TableColumn<?, ?> tblColLocation;

    @FXML
    private AnchorPane buttonContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Map<String, Object> attributeMap = medium.getAttributeMap();
//        lblTitle.setText((String) attributeMap.get("title"));

    }

    public void initReserveButton() {
      ReserveButtonView reserveButtonView = new ReserveButtonView();
      buttonContainer.getChildren().setAll(reserveButtonView.getView());
      ReserveButtonPresenter presenter = (ReserveButtonPresenter) reserveButtonView.getPresenter();
      presenter.setMedium(medium);
    }


    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
