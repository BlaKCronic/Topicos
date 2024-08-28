module org.example.topicos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.topicos to javafx.fxml;
    exports org.example.topicos;
}