module testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires hibernate.core;
    requires hibernate.jpa;
    exports sample;
    opens sample to javafx.fxml;
}