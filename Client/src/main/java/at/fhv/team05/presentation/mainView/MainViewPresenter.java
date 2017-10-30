package at.fhv.team05.presentation.mainView;

import at.fhv.team05.presentation.navigation.LoginNavigationPresenter;
import at.fhv.team05.presentation.navigation.LoginNavigationView;
import at.fhv.team05.presentation.navigation.NavigationPresenter;
import at.fhv.team05.presentation.navigation.NavigationView;
import at.fhv.team05.presentation.search.SearchView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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

    public void changeNavigationBarToLoggedIn(){
        NavigationView navigationView = new NavigationView();
        navigationBarContainer.getChildren().setAll(navigationView.getView());
        NavigationPresenter presenter = (NavigationPresenter) navigationView.getPresenter();
        presenter.setParent(this);
    }

    public void changeNavigationBarToLoggedOut() {
        LoginNavigationView navigationView = new LoginNavigationView();
        navigationBarContainer.getChildren().setAll(navigationView.getView());
        LoginNavigationPresenter presenter = (LoginNavigationPresenter) navigationView.getPresenter();
        presenter.setParent(this);
    }
}
