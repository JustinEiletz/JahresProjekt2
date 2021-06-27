package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    private void backButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
    }

}
