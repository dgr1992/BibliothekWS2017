package at.fhv.team05.presentation.mainView;

import at.fhv.team05.presentation.customer.CustomerPresenter;
import at.fhv.team05.presentation.customer.CustomerView;
import at.fhv.team05.presentation.navigation.LoginNavigationPresenter;
import at.fhv.team05.presentation.navigation.LoginNavigationView;
import at.fhv.team05.presentation.navigation.NavigationPresenter;
import at.fhv.team05.presentation.navigation.NavigationView;
import at.fhv.team05.presentation.rental.RentalOverviewView;
import at.fhv.team05.presentation.rental.RentalPresenter;
import at.fhv.team05.presentation.rental.RentalView;
import at.fhv.team05.presentation.search.SearchView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewPresenter implements Initializable {

    @FXML
    protected StackPane navigationBarContainer;

    @FXML
    protected StackPane contentContainer;


    public void initialize(URL location, ResourceBundle resources) {
        navigationBarContainer.setStyle("-fx-background-color: #d4daf6");
        changeNavigationBarToLoggedOut();
        openSearchView();
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

    public void openSearchView(){
        SearchView searchView = new SearchView();
        contentContainer.getChildren().setAll(searchView.getView());
    }

    public void openRentalView() {
        RentalView rentalView = new RentalView();
        contentContainer.getChildren().setAll(rentalView.getView());
        RentalPresenter presenter = (RentalPresenter) rentalView.getPresenter();
        presenter.setParent(this);
    }


    public void openCustomerRentalView() {
        CustomerView customerView = new CustomerView();
        contentContainer.getChildren().setAll(customerView.getView());
        CustomerPresenter presenter = (CustomerPresenter) customerView.getPresenter();
        presenter.setParent(this);
    }

    public void openRentalOverview() {
        RentalOverviewView rentalOverview = new RentalOverviewView();
        contentContainer.getChildren().setAll(rentalOverview.getView());
    }
    


}
