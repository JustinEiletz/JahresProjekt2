package sample;

import daos.RentalDao;
import daos.TenantDao;
import entity.Rental;
import entity.RentalTableView;
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
    protected Class<TenantController> getClassType() { return TenantController.class; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTenantTableView();
    }

    @FXML
    private void linkRental() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
    }

    private void updateTenantTableView() {
        tenantTV.getItems().clear();
        List<Tenant> tenants = tenantDao.findAll();
        TenantTableView tTV = new TenantTableView();
        TableColumn<TenantTableView, String> id = new TableColumn<>(tTV.getTenantId());
        TableColumn<TenantTableView, String> foreName = new TableColumn<>(tTV.getTenantForename());
        TableColumn<TenantTableView, String> surName = new TableColumn<>(tTV.getTenantSurName());
        TableColumn<TenantTableView, String> phoneNumber = new TableColumn<>(tTV.getTenantPhoneNumber());
        tenantTV.getColumns().addAll(id, foreName, surName, phoneNumber);

        id.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantId()));
        id.setMinWidth(90);

        foreName.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantForename()));
        foreName.setMinWidth(160);

        surName.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantSurName()));
        surName.setMinWidth(240);

        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(tTV.getTenantPhoneNumber()));
        phoneNumber.setMinWidth(360);

        for (Tenant tenant : tenants) {
            tTV.setId(tenant.getId());
            tTV.setForeName(tenant.getForeName());
            tTV.setSurName(tenant.getSurName());
            tTV.setPhoneNumber(tenant.getPhoneNumber());
            tenantTV.getItems().add(tTV);
        }
        addEditTenant = null;
    }

    private static Tenant addEditTenant = null;
    private Stage editRentalStage = null;

    public static Tenant GetAddEditTenant() {
        return addEditTenant;
    }

    public static void SetAddEditTenant(Tenant tenant) {
        addEditTenant = tenant;
    }

    @FXML
    private void linkTenant() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getTenantScene());
    }

    private void openAddEditDialog(boolean edit)
    {
        try
        {
            if(edit && addEditTenant == null) { return; }
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlfiles/manageTable.fxml"));
            editRentalStage = new Stage();
            editRentalStage.initModality(Modality.WINDOW_MODAL);
            editRentalStage.initOwner(ViewManager.getInstanceVM().getStage());
            editRentalStage.setTitle("Add/Edit Rental");
            editRentalStage.setScene(new Scene(root));
            editRentalStage.showAndWait();
            updateTenantTableView();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private Tenant getSelectedTenant()
    {
        if(tenantTV.getSelectionModel().getSelectedItem() != null)
        {
            TenantTableView item = tenantTV.getSelectionModel().getSelectedItem();
            TenantDao tenantDao = new TenantDao();
            return tenantDao.findById(item.getId());
        }
        return null;
    }

    @FXML
    private void manageButtonClick()
    {
        addEditTenant = getSelectedTenant();
        openAddEditDialog(true);
    }

    @FXML
    private void addButtonClick() {
        addEditTenant = null;
        openAddEditDialog(false);
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
