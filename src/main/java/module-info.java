module project.files.final_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens project.files.final_project to javafx.fxml;
    exports project.files.final_project;
    exports project.files.controllers;
    opens project.files.controllers to javafx.fxml;
}