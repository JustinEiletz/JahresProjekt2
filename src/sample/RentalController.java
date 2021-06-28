package sample;

import daos.RentalDao;
import entity.Rental;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalController extends BaseController<Rental> implements Initializable {

    private final RentalDao rentalDao = new RentalDao();
    private ObservableList<Rental> data;

    @FXML
    private TableView<Rental> rentalTV;

    @Override
    protected Class<Rental> getClassType() { return Rental.class; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupRentalTableView();
    }

    @FXML
    private void linkTenant() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getTenantScene());
    }

    private void setupRentalTableView() {
        rentalTV = new TableView<>();
        rentalTV.getColumns().add(0, new TableColumn<>("e"));
    }
}
