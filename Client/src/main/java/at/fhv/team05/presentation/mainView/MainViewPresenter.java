package at.fhv.team05.presentation.mainView;

import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.customer.CustomerDetailPresenter;
import at.fhv.team05.presentation.customer.CustomerDetailView;
import at.fhv.team05.presentation.customer.CustomerPresenter;
import at.fhv.team05.presentation.customer.CustomerView;
import at.fhv.team05.presentation.customer.buttons.CustomerButtonType;
import at.fhv.team05.presentation.detailView.DetailPresenter;
import at.fhv.team05.presentation.detailView.DetailView;
import at.fhv.team05.presentation.login.LoginPresenter;
import at.fhv.team05.presentation.login.LoginView;
import at.fhv.team05.presentation.message.CreateMessagePresenter;
import at.fhv.team05.presentation.message.CreateMessageView;
import at.fhv.team05.presentation.message.ViewMessagePresenter;
import at.fhv.team05.presentation.message.ViewMessageView;
import at.fhv.team05.presentation.navigation.LoginNavigationPresenter;
import at.fhv.team05.presentation.navigation.LoginNavigationView;
import at.fhv.team05.presentation.navigation.NavigationPresenter;
import at.fhv.team05.presentation.navigation.NavigationView;
import at.fhv.team05.presentation.rental.RentalOverviewPresenter;
import at.fhv.team05.presentation.rental.RentalOverviewView;
import at.fhv.team05.presentation.rental.RentalPresenter;
import at.fhv.team05.presentation.rental.RentalView;
import at.fhv.team05.presentation.reservation.ReservationPresenter;
import at.fhv.team05.presentation.reservation.ReservationView;
import at.fhv.team05.presentation.search.SearchPresenter;
import at.fhv.team05.presentation.search.SearchView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewPresenter extends Presenter {

    @FXML
    protected StackPane navigationBarContainer;

    @FXML
    protected StackPane contentContainer;


    public void initialize() {
        navigationBarContainer.setStyle("-fx-background-color: #d4daf6");
        changeNavigationBarToLoggedOut();
        openSearchView();
    }

    /**
    * Changes to the full navigation bar with every action possible for the logged in user.
     */
    public void changeNavigationBarToLoggedIn() {
        NavigationView navigationView = new NavigationView();
        NavigationPresenter presenter = (NavigationPresenter) navigationView.getPresenter();
        presenter.setParent(this);
        navigationBarContainer.getChildren().setAll(navigationView.getView());
    }

    /**
    * Changes to the navigation bar which is visible for logged out users or user without an account.
    * "Search" and "Login" menu items are available here.
     */
    public void changeNavigationBarToLoggedOut() {
        LoginNavigationView navigationView = new LoginNavigationView();
        LoginNavigationPresenter presenter = (LoginNavigationPresenter) navigationView.getPresenter();
        presenter.setParent(this);
        navigationBarContainer.getChildren().setAll(navigationView.getView());
    }

    /**
    * Opens the search view.
    */
    public void openSearchView() {
        SearchView searchView = new SearchView();
        SearchPresenter presenter = (SearchPresenter) searchView.getPresenter();
        presenter.setLblViewTitle("Search Medium");
        presenter.setParent(this);
        presenter.doubleClickDefault();
        contentContainer.getChildren().setAll(searchView.getView());
    }

    /**
    * Opens the rental view.
     */
    public void openRentalView() {
        RentalView rentalView = new RentalView();
        RentalPresenter presenter = (RentalPresenter) rentalView.getPresenter();
        presenter.setParent(this);
        contentContainer.getChildren().setAll(rentalView.getView());
    }

    /**
    * Opens the customer view.
    * @param buttonType Enum for choosing which button type should be displayed: OK, RESERVATION, NONE
     * @param copy Only needed for rental --> if the OK button should be displayed.
     * @param medium Only needed for reservation --> if the RESERVATION button should be displayed.
     */
    public void openCustomerView(CopyDTO copy, IMediumDTO medium, CustomerButtonType buttonType) {
        CustomerView customerView = new CustomerView();
        CustomerPresenter presenter = (CustomerPresenter) customerView.getPresenter();
        presenter.setParent(this);
        switch (buttonType) {
            case OK:
                presenter.initOkButton(copy);
                presenter.setViewTitle("Select Customer");
                break;
            case RESERVATION:
                presenter.initReservationButton(medium);
                presenter.setViewTitle("Select Customer");
                break;
            case NONE:
                presenter.setViewTitle("Customer Overview");
                presenter.setDetailView(true);
                break;
            default:
                presenter.setDetailView(true);
        }
        contentContainer.getChildren().setAll(customerView.getView());
    }

    /**
     * Opens the customer detail view.
     * Displays personal information and all rented copies.
     * @param customer The customer for whom the detailed information should be displayed.
     */
    public void openCustomerDetailView(CustomerDTO customer) {
        CustomerDetailView customerDetailView = new CustomerDetailView();
        CustomerDetailPresenter presenter = (CustomerDetailPresenter) customerDetailView.getPresenter();
        presenter.setCustomer(customer);
        presenter.setCustomerDetails();
        contentContainer.getChildren().setAll(customerDetailView.getView());
    }

    /**
     * Opens rental overview.
     * Displays information about the rental and allows to confirm the rental.
     * @param customer The customer which wants to rent the copy.
     * @param copy The copy which is rented.
     */
    public void openRentalOverview(CustomerDTO customer, CopyDTO copy) {
        RentalOverviewView rentalOverview = new RentalOverviewView();
        RentalOverviewPresenter presenter = (RentalOverviewPresenter) rentalOverview.getPresenter();
        presenter.setCustomer(customer);
        presenter.setCopy(copy);
        presenter.initView();
        contentContainer.getChildren().setAll(rentalOverview.getView());
    }

    /**
    * Opens the reservation view.
     * @param isOverview Choose if the search is for reserving a medium or to view all reservations of a chosen medium.
     */
    public void openReservationView(boolean isOverview) {
        SearchView searchView = new SearchView();
        SearchPresenter presenter = (SearchPresenter) searchView.getPresenter();
        presenter.setParent(this);
        if (isOverview) {
            presenter.setDoubleClickReservationOverview();
            presenter.setLblViewTitle("Reservation Overview");
        } else {
            presenter.doubleClickReservation();
            presenter.setLblViewTitle("Reserve Medium");
        }
        contentContainer.getChildren().setAll(searchView.getView());
    }

    /**
     * Opens the detail view of a medium.
     * @param medium The chosen medium
     * @param reserveButtonEnabled If the reservation button should be enabled or not
     */
    public void openDetailView(IMediumDTO medium, boolean reserveButtonEnabled) {
        DetailView detailView = new DetailView();
        DetailPresenter presenter = (DetailPresenter) detailView.getPresenter();
        presenter.setMedium(medium);
        presenter.setParent(this);
        if (reserveButtonEnabled) {
            presenter.initReserveButton();
        }
        presenter.initView();
        contentContainer.getChildren().setAll(detailView.getView());
    }

    /**
     * Opens the reservation overview.
     * @param medium The medium for which the reservations should be displayed.
     */
    public void openReservationOverview(IMediumDTO medium) {
        ReservationView reservationView = new ReservationView();
        ReservationPresenter presenter = (ReservationPresenter) reservationView.getPresenter();
        presenter.setParent(this);
        presenter.setMedium(medium);
        presenter.initView();
        contentContainer.getChildren().setAll(reservationView.getView());

    }

    /**
     * Opens the login view.
     */
    public void openLoginView() {
        LoginView loginView = new LoginView();
        LoginPresenter presenter = (LoginPresenter) loginView.getPresenter();
        presenter.setParent(this);
        contentContainer.getChildren().setAll(loginView.getView());
    }


    public void openCreateMessageView() {
        CreateMessageView messageView = new CreateMessageView();
        CreateMessagePresenter presenter = (CreateMessagePresenter) messageView.getPresenter();
        presenter.setParent(this);
        contentContainer.getChildren().setAll(messageView.getView());
    }

    public void openViewMessageView() {
        ViewMessageView viewMessageView = new ViewMessageView();
        ViewMessagePresenter presenter = (ViewMessagePresenter) viewMessageView.getPresenter();
        presenter.setParent(this);
        contentContainer.getChildren().setAll(viewMessageView.getView());

    }

}
