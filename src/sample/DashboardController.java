package sample;

import entity.User;
import enums.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import manager.ApplicationManager;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends BaseController<DashboardController> implements Initializable {

    User currentUser = ApplicationManager.getInstance().getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    private void linkAdministration() {
        if (currentUser.getRole().equals(UserRole.ADMIN)) {
            ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getAdministration());
        } else {
            System.out.println("not enough permissions");
        }
    }

    @FXML
    private void linkCalendar() {
        if (currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.USER)) {
            ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getCalendarScene());
        } else {
            System.out.println("not enough permissions");
        }
    }

    @FXML
    private void linkEmployee() {
        if (currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.USER)) {
            ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getEmployeeScene());
        } else {
            System.out.println("not enough permissions");
        }
    }

    @FXML
    private void linkManagement() {
        if (currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.USER)) {
            ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
        } else {
            System.out.println("not enough permissions");
        }
    }

    @FXML
    private void linkDocumentManagement() {
        if (currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.USER)) {
            ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDocumentManagementScene());
        } else {
            System.out.println("not enough permissions");
        }
    }

    @Override
    protected Class<DashboardController> getClassType() {
        return DashboardController.class;
    }
}
