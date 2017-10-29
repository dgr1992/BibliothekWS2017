package at.fhv.team05.presentation.mainView;

import at.fhv.team05.presentation.navigation.LoginNavigationPresenter;
import at.fhv.team05.presentation.navigation.LoginNavigationView;
import at.fhv.team05.presentation.navigation.NavigationView;
import at.fhv.team05.presentation.search.SearchView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michelle on 29.10.2017.
 */
public class MainViewPresenter implements Initializable {

    @FXML
    protected AnchorPane navigationBarContainer;

    @FXML
    protected AnchorPane contentContainer;


    public void initialize(URL location, ResourceBundle resources) {
        changeNavigationBarToLoggedOut();
        openSearchView();
    }

    public void openSearchView(){
        SearchView searchView = new SearchView();
        contentContainer.getChildren().setAll(searchView.getView());
    }

    protected void changeNavigationBarToLoggedIn(){
        NavigationView navigationView = new NavigationView();
        navigationBarContainer.getChildren().setAll(navigationView.getView());
    }

    protected void changeNavigationBarToLoggedOut() {
        LoginNavigationView navigationView = new LoginNavigationView();
        navigationBarContainer.getChildren().setAll(navigationView.getView());
    }
}
