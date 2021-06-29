package sample;

import javafx.fxml.FXML;
import manager.ApplicationManager;
import manager.ViewManager;

public abstract class BaseController<T> {

    protected abstract Class<T> getClassType();

    @FXML
    private void backButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
    }

    @FXML
    private void chatButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getChatScene());
    }

    @FXML
    private void logoutButtonClick() {
        ApplicationManager app = ApplicationManager.getInstance();
        app.setCurrentUser(null);
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getLoginScene());
    }

    @FXML
    private void linkTenant() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getTenantScene());
    }

    @FXML
    private void linkRental() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getRentalScene());
    }

}
