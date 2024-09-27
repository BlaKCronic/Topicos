package org.example.topicos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.topicos.Vistas.Calculadora;
import org.example.topicos.Vistas.ListaClientes;
import org.example.topicos.Vistas.Loteria;
import org.example.topicos.models.Conexion;

import java.io.IOException;

public class HelloApplication extends Application {
    private BorderPane bdpPrincipal;
    private MenuBar menBar;
    private Menu menCompetencia1, menuCompetencia2, menSalir;
    private MenuItem mitCalc, mitloteria, mitSpotify;

    public void CrearUI() {
        mitCalc = new MenuItem("Calculadora");
        mitCalc.setOnAction(actionEvent -> new Calculadora());
        mitloteria = new MenuItem("Loteria");
        mitloteria.setOnAction(actionEvent -> new Loteria());
        mitSpotify = new MenuItem("Spotify");
        mitSpotify.setOnAction(actionEvent -> new ListaClientes());
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalc, mitloteria, mitSpotify);
        menBar = new MenuBar(menCompetencia1);
        Label titleLabel = new Label("Mi Aplicación");
        titleLabel.setId("title-label");

        VBox topBox = new VBox(titleLabel, menBar);
        topBox.setId("top-box");

        bdpPrincipal = new BorderPane();
        bdpPrincipal.setTop(topBox);
    }

    @Override
    public void start(Stage stage) throws IOException {
        CrearUI();
        Scene scene = new Scene(bdpPrincipal, 320, 240);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        stage.setTitle("sabes que");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        Conexion.crearConexion();
    }

    public static void main(String... args) {
        launch();
    }
}
