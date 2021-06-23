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

    public void setStage( final Stage primaryStage ) {
        this.primaryStage = primaryStage;
    }

    public Scene getLoginScene() { return loginScene; }

    public Scene getDocumentManagementScene() {
        try
        {
            // this needs to be created after a user has logged in
            // to load the users documents
            Pane paneDocument = FXMLLoader.load(getClass().getResource("/fxmlfiles/document_management.fxml"));
            return new Scene(paneDocument);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return loginScene;
        }
    }
}
