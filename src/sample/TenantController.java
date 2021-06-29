package sample;

import daos.TenantDao;
import entity.Tenant;
import entity.TenantTableView;
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

public class TenantController extends BaseController<TenantController> implements Initializable {

    private final TenantDao tenantDao = new TenantDao();

    @FXML
    private TableView<TenantTableView> tenantTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TenantTableView tTV = new TenantTableView();
        TableColumn<TenantTableView, String> id = new TableColumn<>(tTV.getTenantId());
        TableColumn<TenantTableView, String> foreName = new TableColumn<>(tTV.getTenantForename());
        TableColumn<TenantTableView, String> surName = new TableColumn<>(tTV.getTenantSurName());
        TableColumn<TenantTableView, String> phoneNumber = new TableColumn<>(tTV.getTenantPhoneNumber());
        tenantTV.getColumns().addAll(id, foreName, surName, phoneNumber);
        id.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantId()));
        id.setMinWidth(150);

        foreName.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantForename()));
        foreName.setMinWidth(240);

        surName.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantSurName()));
        surName.setMinWidth(290);

        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantPhoneNumber()));
        phoneNumber.setMinWidth(360);

        updateTenantTableView();
    }

    @Override
    protected Class<TenantController> getClassType() { return TenantController.class; }

    @FXML
    private void linkRental() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
    }

    private void updateTenantTableView() {
        tenantTV.getItems().clear();
        List<Tenant> tenants = tenantDao.findAll();

        for (Tenant tenant : tenants) {
            TenantTableView tTV = new TenantTableView();
            tTV.setId(tenant.getId());
            tTV.setForeName(tenant.getForeName());
            tTV.setSurName(tenant.getSurName());
            tTV.setPhoneNumber(tenant.getPhoneNumber());
            tenantTV.getItems().add(tTV);
        }
        addEditTenant = null;
    }

    private static Tenant addEditTenant = null;

    public static Tenant GetAddEditTenant() {
        return addEditTenant;
    }

    public static void SetAddEditTenant(Tenant tenant) {
        addEditTenant = tenant;
    }

    private void openAddEditDialog(boolean edit) {
        try
        {
            if(edit && addEditTenant == null) { return; }
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlfiles/manageTable.fxml"));
            Stage editTenantStage = new Stage();
            editTenantStage.initModality(Modality.WINDOW_MODAL);
            editTenantStage.initOwner(ViewManager.getInstanceVM().getStage());
            editTenantStage.setTitle("Add/Edit Tenant");
            editTenantStage.setScene(new Scene(root));
            editTenantStage.showAndWait();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private Tenant getSelectedTenant() {
        if (tenantTV.getSelectionModel().getSelectedItem() != null) {
            TenantTableView item = tenantTV.getSelectionModel().getSelectedItem();
            TenantDao tenantDao = new TenantDao();
            return tenantDao.findById(item.getId());
        }
        return null;
    }

    @FXML
    private void manageButtonClick() {
        addEditTenant = getSelectedTenant();
        openAddEditDialog(true);
        if(addEditTenant != null) {
            TenantDao tenantDao = new TenantDao();
            tenantDao.update(addEditTenant);
            updateTenantTableView();
        }
    }

    @FXML
    private void addButtonClick() {
        addEditTenant = null;
        openAddEditDialog(false);
        if(addEditTenant != null) {
            TenantDao tenantDao = new TenantDao();
            tenantDao.create(addEditTenant);
            updateTenantTableView();
        }
    }

    @FXML
    private void deleteButtonClick() {
        Tenant delete = getSelectedTenant();
        if(delete != null) {
            TenantDao tenantDao = new TenantDao();
            tenantDao.delete(delete);
            updateTenantTableView();
        }
    }
}
