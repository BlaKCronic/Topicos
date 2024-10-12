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
import org.example.topicos.Vistas.*;
import org.example.topicos.models.Conexion;

import java.io.IOException;

public class HelloApplication extends Application {
    private BorderPane bdpPrincipal;
    private MenuBar menBar;
    private Menu menCompetencia1, menuCompetencia2, menSalir;
    private MenuItem mitCalc, mitloteria, mitSpotify, mitMines;

    public void CrearUI() {
        mitCalc = new MenuItem("Calculadora");
        mitCalc.setOnAction(actionEvent -> new Calculadora());
        mitloteria = new MenuItem("Loteria");
        mitloteria.setOnAction(actionEvent -> new Loteria());
        mitSpotify = new MenuItem("Spotify");
        mitSpotify.setOnAction(actionEvent -> new ListaClientes());
        mitSpotify.setOnAction(actionEvent -> new ListaVenta());
        mitMines = new MenuItem("Busca minas");
        mitMines.setOnAction(actionEvent -> new MinesWeeper());
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalc, mitloteria, mitSpotify, mitMines);
        menuCompetencia2 = new Menu("Competencia 2");
        menuCompetencia2.getItems().addAll();
        menBar = new MenuBar(menCompetencia1, menuCompetencia2);

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
