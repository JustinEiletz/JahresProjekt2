package sample;

import daos.RentalDao;
import entity.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {

    private final RentalDao rentalDao = new RentalDao();

    @FXML
    private TableView<Rental> rentalTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Rental> rentals = new ArrayList<>(rentalDao.findAll());
        if (rentals.size() > 0) {
            rentalTV.setItems((ObservableList) rentals);
        }
    }
}
