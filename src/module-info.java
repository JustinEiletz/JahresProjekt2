module testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.persistence;
    exports sample;
    opens sample to javafx.fxml;
}