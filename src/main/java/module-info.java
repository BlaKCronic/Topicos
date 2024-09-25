module org.example.topicos {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.topicos;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql;

    opens org.example.topicos.models;
    opens org.example.topicos to javafx.fxml;

}