package sample;

import daos.RentalDao;
import entity.Rental;
import entity.RentalTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RentalController extends BaseController<RentalController> implements Initializable {

    private final RentalDao rentalDao = new RentalDao();

    @FXML
    private TableView<RentalTableView> rentalTV;

    private static Rental addEditRental;

    public static Rental GetAddEditRental()
    {
        return addEditRental;
    }

    public static void SetAddEditRental(Rental rental) {
        addEditRental = rental;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        updateRentalTableView();
    }

    @Override
    protected Class<RentalController> getClassType() { return RentalController.class; }

    @FXML
    private void linkTenant() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getTenantScene());
    }

    private void updateRentalTableView() {
        rentalTV.getItems().clear();
        List<Rental> rentals = rentalDao.findAll();

        for (Rental rental : rentals) {
            RentalTableView rTV = new RentalTableView();
            rTV.setId(rental.getId());
            rTV.setDesc(rental.getObjectDesc());
            rTV.setTyp(rental.getObjectTyp());
            rTV.setNotice(rental.getNotice());
            rentalTV.getItems().add(rTV);
        }
        addEditRental = null;
    }

    private Rental getSelectedRental() {
        if (rentalTV.getSelectionModel().getSelectedItem() != null) {
            RentalTableView item = rentalTV.getSelectionModel().getSelectedItem();
            RentalDao rentalDao = new RentalDao();
            return rentalDao.findById(item.getId());
        }
        return null;
    }

    private void openAddEditDialog(boolean edit) {
        try {
            if (edit && addEditRental == null) { return; }
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlfiles/rentalManage.fxml"));
            Stage editRentalStage = new Stage();
            editRentalStage.initModality(Modality.WINDOW_MODAL);
            editRentalStage.initOwner(ViewManager.getInstanceVM().getStage());
            editRentalStage.setTitle("Add/Edit Rental");
            editRentalStage.setScene(new Scene(root));
            editRentalStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void addButtonClick() {
        openAddEditDialog(false);
        if (addEditRental != null) {
            RentalDao rentalDao = new RentalDao();
            rentalDao.create(addEditRental);
            updateRentalTableView();
        }
    }

    @FXML
    private void manageButtonClick() {
        addEditRental = getSelectedRental();
        openAddEditDialog(true);
        if (addEditRental != null) {
            RentalDao rentalDao = new RentalDao();
            rentalDao.update(addEditRental);
            updateRentalTableView();
        }
    }

    @FXML
    private void deleteButtonClick() {
        Rental rental = getSelectedRental();
        if (rental != null) {
            RentalDao rentalDao = new RentalDao();
            rentalDao.delete(rental);
            updateRentalTableView();
        }
    }
}
