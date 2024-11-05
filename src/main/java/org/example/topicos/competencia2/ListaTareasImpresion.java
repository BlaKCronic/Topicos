package org.example.topicos.competencia2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ListaTareasImpresion extends Stage {
    private TableView<TareaImpresion> tblTareas;
    private ToolBar tlbMenu;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregarTarea;
    private Button btnSimulador;
    private ProgressBar progressBar;
    private ObservableList<TareaImpresion> tareas;
    private boolean simuladorActivo;
    private Thread simuladorThread;

    public ListaTareasImpresion() {
        CrearUI();
        this.setTitle("Simulador de Impresión");
        this.setScene(escena);
        this.show();
        iniciarSimulador();
    }

    private void CrearUI() {
        tlbMenu = new ToolBar();
        btnAgregarTarea = new Button("Agregar Tarea");
        btnAgregarTarea.setOnAction(actionEvent -> agregarTarea());

        btnSimulador = new Button("Encender Simulador");
        btnSimulador.setOnAction(actionEvent -> toggleSimulador());

        tlbMenu.getItems().addAll(btnAgregarTarea, btnSimulador);

        tblTareas = new TableView<>();
        CrearTabla();

        progressBar = new ProgressBar(0);
        progressBar.setMinWidth(300);

        vBox = new VBox(tlbMenu, tblTareas, progressBar);
        escena = new Scene(vBox, 700, 500);
    }

    private void CrearTabla() {
        TableColumn<TareaImpresion, Integer> colNoArchivo = new TableColumn<>("No. Archivo");
        colNoArchivo.setCellValueFactory(cellData -> cellData.getValue().noArchivoProperty().asObject());

        TableColumn<TareaImpresion, String> colNombreArchivo = new TableColumn<>("Nombre de archivo");
        colNombreArchivo.setCellValueFactory(cellData -> cellData.getValue().nombreArchivoProperty());

        TableColumn<TareaImpresion, Integer> colNumHojas = new TableColumn<>("Número de hojas");
        colNumHojas.setCellValueFactory(cellData -> cellData.getValue().numHojasProperty().asObject());

        TableColumn<TareaImpresion, String> colHoraAcceso = new TableColumn<>("Hora de acceso");
        colHoraAcceso.setCellValueFactory(cellData -> cellData.getValue().horaAccesoProperty());

        tblTareas.getColumns().addAll(colNoArchivo, colNombreArchivo, colNumHojas, colHoraAcceso);
        tareas = FXCollections.observableArrayList();
        tblTareas.setItems(tareas);
    }

    private void agregarTarea() {
        int noArchivo = tareas.size() + 1;
        String nombreArchivo = generarNombreArchivo();
        int numHojas = new Random().nextInt(50) + 1;  // Hojas aleatorias entre 1 y 50
        String horaAcceso = new SimpleDateFormat("HH:mm:ss").format(new Date());

        TareaImpresion nuevaTarea = new TareaImpresion(noArchivo, nombreArchivo, numHojas, horaAcceso);
        tareas.add(nuevaTarea);
    }

    private String generarNombreArchivo() {
        String fechaHora = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "Archivo_" + fechaHora + ".txt";
    }

    private void toggleSimulador() {
        simuladorActivo = !simuladorActivo;
        btnSimulador.setText(simuladorActivo ? "Apagar Simulador" : "Encender Simulador");
    }

    private void iniciarSimulador() {
        simuladorThread = new Thread(() -> {
            while (true) {
                if (simuladorActivo && !tareas.isEmpty()) {
                    TareaImpresion tarea = tareas.get(0);
                    int numHojas = tarea.getNumHojas();

                    for (int i = 1; i <= numHojas; i++) {
                        double progreso = (double) i / numHojas;
                        Platform.runLater(() -> progressBar.setProgress(progreso));
                        try {
                            Thread.sleep(200);  // Simula el tiempo de impresión por hoja
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Platform.runLater(() -> {
                        tareas.remove(tarea);
                        progressBar.setProgress(0);
                    });
                }
                try {
                    Thread.sleep(500);  // Pausa para evitar que el hilo consuma demasiados recursos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        simuladorThread.setDaemon(true);
        simuladorThread.start();
    }
}
