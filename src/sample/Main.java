package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.ApplicationManager;
import manager.ViewManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ApplicationManager.getInstance();
        ViewManager.getInstanceVM().setStage(stage);
        stage.setTitle("SchulProjekt");
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getLoginScene());
    }

    public static void main(String[] args) { launch(args); }
}
