package sample;

import daos.RentalDao;
import entity.Rental;
import entity.RentalTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.skin.TableColumnHeader;
import manager.ViewManager;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RentalController extends BaseController<Rental> implements Initializable {

    private final RentalDao rentalDao = new RentalDao();
    private final ObservableList<Rental> data = FXCollections.observableArrayList();

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
        RentalTableView rentalTableView = new RentalTableView();
    }
}
