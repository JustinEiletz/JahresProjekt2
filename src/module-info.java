module testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    requires java.persistence;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;

    requires java.xml.bind;
    requires java.naming;

    requires mysql.connector.java;

    exports sample;
    opens sample to javafx.fxml;
    opens entity to org.hibernate.orm.core;
}