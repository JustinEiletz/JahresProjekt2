package sample;

import daos.TenantDao;
import entity.Tenant;
import entity.TenantTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public void initialize(URL url, ResourceBundle resourceBundle) { setupTenantTableView(); }

    @FXML
    private void linkRental() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
    }

    private void setupTenantTableView() {
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
    }
}
