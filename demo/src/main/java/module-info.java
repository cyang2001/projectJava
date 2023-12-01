module com.isep.eleve.javaproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.isep.eleve.javaproject.view to javafx.fxml;
    opens com.isep.eleve.javaproject.controller to javafx.fxml;
    exports com.isep.eleve.javaproject;
    exports com.isep.eleve.javaproject.controller to javafx.fxml;
}
