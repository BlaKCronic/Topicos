package org.example.topicos;

import javafx.application.Application;
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
    private Menu menCompetencia1, menuCompetencia2;
    private Menu menSpotify;
    private MenuItem mitCalc, mitloteria, mitMines, mitPista;
    private MenuItem mitListaVenta, mitListaClientes, mitListaGenero, mitListaArtista, mitListaAlbum, mitListaCancion;
    // Items dentro del submenú Spotify

    public void CrearUI() {
        // Crear items del menú
        mitCalc = new MenuItem("Calculadora");
        mitCalc.setOnAction(actionEvent -> new Calculadora());

        mitloteria = new MenuItem("Loteria");
        mitloteria.setOnAction(actionEvent -> new Loteria());

        mitMines = new MenuItem("Busca minas");
        mitMines.setOnAction(actionEvent -> new MinesWeeper());

        mitPista = new MenuItem("Pista Hilos");
        mitPista.setOnAction(actionEvent -> new Pista());

        // Submenú Spotify con sus items
        mitListaVenta = new MenuItem("Lista de Ventas");
        mitListaVenta.setOnAction(actionEvent -> new ListaVenta());

        mitListaClientes = new MenuItem("Lista de Clientes");
        mitListaClientes.setOnAction(actionEvent -> new ListaClientes());

        mitListaGenero = new MenuItem("Lista de Generos");
        mitListaGenero.setOnAction(actionEvent -> new ListaGeneros());

        mitListaArtista = new MenuItem("Lista Artistas");
        mitListaArtista.setOnAction(actionEvent -> new ListaArtistas());

        mitListaAlbum = new MenuItem("Lista de Albums");
        mitListaAlbum.setOnAction(actionEvent -> new ListaAlbum());

        mitListaCancion = new MenuItem("Lista de Canciones");
        mitListaCancion.setOnAction(actionEvent -> new ListaCancion());

        menSpotify = new Menu("Spotify"); // Submenú
        menSpotify.getItems().addAll(mitListaVenta, mitListaClientes, mitListaGenero, mitListaArtista, mitListaAlbum, mitListaCancion);

        // Crear menú principal Competencia 1
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalc, mitloteria,mitMines, menSpotify);

        // Crear Competencia 2
        menuCompetencia2 = new Menu("Competencia 2");
        menuCompetencia2.getItems().addAll(mitPista);

        // Crear barra de menús
        menBar = new MenuBar(menCompetencia1, menuCompetencia2);

        // Configurar layout
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
        stage.setTitle("Mi Aplicación");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        // Establecer la conexión
        Conexion.crearConexion();
    }

    public static void main(String... args) {
        launch();
    }
}
