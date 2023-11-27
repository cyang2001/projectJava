module com.isep.eleve.javaproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.isep.eleve.javaproject to javafx.fxml;
    exports com.isep.eleve.javaproject;
}
