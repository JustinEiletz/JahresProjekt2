package sample;

import daos.RentalDao;
import entity.Rental;
import entity.RentalTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import manager.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RentalController extends BaseController<Rental> implements Initializable {

    private final RentalDao rentalDao = new RentalDao();

    @FXML
    private TableView<RentalTableView> rentalTV;

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
        List<Rental> rentals = rentalDao.findAll();
        RentalTableView rTV = new RentalTableView();
        TableColumn<RentalTableView, String> id = new TableColumn<>(rTV.getRentalId());
        TableColumn<RentalTableView, String> desc = new TableColumn<>(rTV.getRentalDesc());
        TableColumn<RentalTableView, String> typ = new TableColumn<>(rTV.getRentalTyp());
        TableColumn<RentalTableView, String> notice = new TableColumn<>(rTV.getRentalNotice());
        rentalTV.getColumns().addAll(id, typ, desc, notice);

        id.setCellValueFactory(new PropertyValueFactory<>(rTV.getRentalId()));
        id.setMinWidth(150);

        typ.setCellValueFactory(new PropertyValueFactory<>(rTV.getRentalTyp()));
        typ.setMinWidth(240);

        desc.setCellValueFactory(new PropertyValueFactory<>(rTV.getRentalDesc()));
        desc.setMinWidth(290);

        notice.setCellValueFactory(new PropertyValueFactory<>(rTV.getRentalNotice()));
        notice.setMinWidth(360);

        for (Rental rental : rentals) {
            rTV.setId(rental.getId());
            rTV.setDesc(rental.getObjectDesc());
            rTV.setTyp(rental.getObjectTyp());
            rTV.setNotice(rental.getNotice());
            rentalTV.getItems().add(rTV);
        }
    }
}
