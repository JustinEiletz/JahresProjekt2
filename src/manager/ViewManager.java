package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewManager {

    private static ViewManager viewManager = null;
    private Scene loginScene = null;
    private Stage primaryStage;

    private ViewManager() {
        try {
            Pane paneLogin = FXMLLoader.load(getClass().getResource("/fxmlfiles/login.fxml"));
            this.loginScene = new Scene(paneLogin);
        } catch (Exception exc) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage());
            alert.showAndWait();
        }
    }

    public static ViewManager getInstanceVM() {
        return Objects.requireNonNullElseGet(ViewManager.viewManager, () -> ViewManager.viewManager = new ViewManager());
    }

    public void activateScene( final Scene scene ) {
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void setStage( final Stage primaryStage ) { this.primaryStage = primaryStage; }
    public Stage getStage() { return this.primaryStage; }

    public Scene getLoginScene() { return loginScene; }

    public Scene getDocumentManagementScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneDocument = FXMLLoader.load(getClass().getResource("/fxmlfiles/documentManagement.fxml"));
            return new Scene(paneDocument);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getRentalScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneRental = FXMLLoader.load(getClass().getResource("/fxmlfiles/rental.fxml"));
            return new Scene(paneRental);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getEmployeeScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneEmployee = FXMLLoader.load(getClass().getResource("/fxmlfiles/employee.fxml"));
            return new Scene(paneEmployee);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getTenantScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneTenant = FXMLLoader.load(getClass().getResource("/fxmlfiles/tenant.fxml"));
            return new Scene(paneTenant);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getChatScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneTenant = FXMLLoader.load(getClass().getResource("/fxmlfiles/chat.fxml"));
            return new Scene(paneTenant);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getAdminEmployeeScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneEmployee = FXMLLoader.load(getClass().getResource("/fxmlfiles/employeeAdministration.fxml"));
            return new Scene(paneEmployee);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getAdminChoiceScene() {
        try {
            // this needs to be created after a user has logged in
            Pane paneAdmin = FXMLLoader.load(getClass().getResource("/fxmlfiles/employeeAdministration.fxml"));
            return new Scene(paneAdmin);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getCalendarScene() {
        try {
            // this needs to be created after a user has logged in
            // to load the users documents
            Pane paneCalendar = FXMLLoader.load(getClass().getResource("/fxmlfiles/calender.fxml"));
            return new Scene(paneCalendar);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getDashboardScene() {
        try {
            // this needs to be created after a user has logged in
            // to load the users documents
            Pane dashboard = FXMLLoader.load(getClass().getResource("/fxmlfiles/dashboard.fxml"));
            return new Scene(dashboard);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }

    public Scene getUserAdministrationScene() {
        try {
            // this needs to be created after a user has logged in
            // to load the users documents
            Pane paneUserAdmin = FXMLLoader.load(getClass().getResource("/fxmlfiles/userAdministration.fxml"));
            return new Scene(paneUserAdmin);
        } catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }
}
