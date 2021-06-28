package sample;

import entity.Tenant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class TenantController extends BaseController<Tenant> implements Initializable {

    @Override
    protected Class<Tenant> getClassType() { return Tenant.class; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    private void linkRental() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
    }
}
