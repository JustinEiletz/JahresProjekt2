package sample;

import entity.Chat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends BaseController<Chat> implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    private void linkAdministration() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getAdminChoiceScene());
    }

    @FXML
    private void linkCalendar() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getCalendarScene());
    }

    @FXML
    private void linkEmployee() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getEmployeeScene());
    }

    @FXML
    private void linkManagement() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
    }

    @FXML
    private void linkDocumentManagement() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDocumentManagementScene());
    }

    @Override
    protected Class<Chat> getClassType() {
        return null;
    }
}
