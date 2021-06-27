package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministrationController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    private void settingUser() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getUserAdministrationScene());
    }

    @FXML
    private void settingEmployee() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getAdminEmployeeScene());
    }
}
