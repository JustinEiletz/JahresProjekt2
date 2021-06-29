package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeAdministrationController extends BaseController<EmployeeAdministrationController> implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    protected Class<EmployeeAdministrationController> getClassType() { return EmployeeAdministrationController.class; }

    @FXML
    private void linkUserAdministration() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getUserAdministrationScene());
    }
}
