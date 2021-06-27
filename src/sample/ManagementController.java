package sample;

import daos.RentalDao;
import entity.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {




    private final RentalDao rentalDao = new RentalDao();

    @FXML
    private TableView<Rental> rentalTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Rental, String> colId = (TableColumn<Rental, String>) rentalTV.getColumns().get(0);
        TableColumn<Rental, String> colAddress = (TableColumn<Rental, String>) rentalTV.getColumns().get(1);
        TableColumn<Rental, String> colLivingSpace = (TableColumn<Rental, String>) rentalTV.getColumns().get(2);
        List<Rental> rentals = rentalDao.findAll();
        if (rentals.size() > 0) {
                colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
                colLivingSpace.setCellValueFactory(new PropertyValueFactory<>("LivingSpace"));
        }
        rentalTV.getColumns().set(0, colId);
        rentalTV.getColumns().set(1, colAddress);
        rentalTV.getColumns().set(2, colLivingSpace);
    }
}
