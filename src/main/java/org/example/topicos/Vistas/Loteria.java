package org.example.topicos.Vistas;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Loteria extends Stage {

    private HBox hBoxMain, hBoxButtons;
    private VBox vBoxTablilla, vBoxMazo;
    private Button btnAnt, btnSig, btnIni, btnFin;
    private Label lbTimer;
    private GridPane gdTab;
    private ImageView imMazo;
    private Scene escena;
    private String[] arrImages = {
            "chat.jpg", "temu.jpg", "chems.jpg", "rub.jpg", "mondongo.jpg",
            "mc.jpg", "rizz.jpg", "escalera.jpg", "barril.jpg", "botella.jpg",
            "catrin.jpg", "chavorruco.jpg", "concha.jpg", "luchador.jpg",
            "maceta.jpg", "rosa.jpg", "tacos.jpg", "venado.jpg"
    };
    private Button[][] arTab;
    private Panel pnlMain;
    private int currentTab = 0;
    private volatile boolean juegoActivo = false;

    public Loteria() {
        CrearUI();
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imAnt = new ImageView(new Image(getClass().getResource("/images/izquierda.png").toString()));
        imAnt.setFitWidth(50);
        imAnt.setFitHeight(50);

        ImageView imSig = new ImageView(new Image(getClass().getResource("/images/derecha.png").toString()));
        imSig.setFitWidth(50);
        imSig.setFitHeight(50);

        arTab = new Button[5][4 * 4]; // 5 tablillas, 4x4 buttons each
        for (int k = 0; k < 5; k++) {
            GridPane gdTabk = new GridPane();
            CrearTablilla(gdTabk, k);
            if (k == 0) {
                gdTab = gdTabk;
            }
        }

        btnAnt = new Button();
        btnAnt.setGraphic(imAnt);
        btnAnt.setOnAction(e -> {
            currentTab = (currentTab - 1 + 5) % 5;
            actualizarTab();
        });

        btnSig = new Button();
        btnSig.setGraphic(imSig);
        btnSig.setOnAction(e -> {
            currentTab = (currentTab + 1) % 5;
            actualizarTab();
        });

        btnFin = new Button("Finalizar juego");
        btnFin.setOnAction(e -> reiniciarJuego());

        btnIni = new Button("Iniciar juego");
        btnIni.getStyleClass().addAll("btn-sm", "btn-danger");
        btnIni.setOnAction(e -> iniciarJuego());

        hBoxButtons = new HBox(btnAnt, btnSig, btnFin, btnIni);
        vBoxTablilla = new VBox(gdTab, hBoxButtons);

        CrearMazo();

        hBoxMain = new HBox(vBoxTablilla, vBoxMazo);
        pnlMain = new Panel("Loteria Mexicana");
        pnlMain.getStyleClass().add("panel-success");
        pnlMain.setBody(hBoxMain);
        hBoxMain.setSpacing(80);
        hBoxMain.setPadding(new Insets(30));
        escena = new Scene(pnlMain, 900, 800);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena.getStylesheets().add(getClass().getResource("/styles/Lot.css").toString());
    }

    private void CrearMazo() {
        Image imgMazo = new Image(getClass().getResource("/images/dorso.jpg").toString());
        lbTimer = new Label("00:00");
        imMazo = new ImageView(imgMazo);
        imMazo.setFitHeight(450);
        imMazo.setFitWidth(300);
        vBoxMazo = new VBox(lbTimer, imMazo);
    }

    private void iniciarJuego() {
        if (juegoActivo) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Juego ya iniciado");
            alert.setHeaderText("El juego ya está en progreso");
            alert.setContentText("No puedes iniciar un nuevo juego mientras otro está en progreso.");
            alert.showAndWait();
            return;
        }

        currentTab = 0;
        juegoActivo = true;
        actualizarTab();

        List<String> cartas = new ArrayList<>(Arrays.asList(arrImages));
        Collections.shuffle(cartas);

        new Thread(() -> {
            for (String carta : cartas) {
                if (!juegoActivo) return; // Si el juego no está activo, salir

                mostrarCarta(carta);
                try {
                    Thread.sleep(5000); // Esperar 5 segundos
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            verificarGanador();
        }).start();

        btnAnt.setDisable(true);
        btnSig.setDisable(true);
    }

    private void mostrarCarta(String carta) {
        Image imgCarta = new Image(getClass().getResource("/images/" + carta).toString());
        imMazo.setImage(imgCarta);

        for (Button btn : arTab[currentTab]) {
            if (btn.getGraphic() instanceof StackPane stackPane) {
                ImageView imv = (ImageView) stackPane.getChildren().get(0);
                if (imv.getImage().getUrl().equals(imgCarta.getUrl())) {
                    btn.setDisable(false);
                } else {
                    btn.setDisable(true);
                }
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void verificarGanador() {
        if (!juegoActivo) return;

        boolean todasMarcadas = Arrays.stream(arTab[currentTab])
                .allMatch(btn -> {
                    if (btn.getGraphic() instanceof StackPane stackPane) {
                        Label xLabel = (Label) stackPane.getChildren().get(1);
                        return xLabel.isVisible(); // Verificar si la "X" es visible
                    }
                    return false; // Si no hay gráfico, no está marcado
                });

        Platform.runLater(() -> {
            String mensaje = todasMarcadas ? "¡Ganaste!" : "¡Perdiste!";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje);
            alert.setTitle("Resultado");
            alert.showAndWait();
        });
    }

    private void reiniciarJuego() {
        juegoActivo = false; // Terminar el juego actual
        // Reiniciar el estado de los botones y la interfaz
        for (int k = 0; k < 5; k++) {
            for (Button btn : arTab[k]) {
                btn.setDisable(false); // Habilitar todos los botones
                if (btn.getGraphic() instanceof StackPane stackPane) {
                    Label xLabel = (Label) stackPane.getChildren().get(1);
                    xLabel.setVisible(false); // Ocultar la "X"
                }
            }
        }
        lbTimer.setText("00:00"); // Reiniciar el timer
        imMazo.setImage(new Image(getClass().getResource("/images/dorso.jpg").toString())); // Reiniciar el mazo

        btnAnt.setDisable(false);
        btnSig.setDisable(false);
    }

    private void CrearTablilla(GridPane gdTab, int k) {
        List<String> imageList = new ArrayList<>(Arrays.asList(arrImages));
        Collections.shuffle(imageList);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String imagePath = "/images/" + imageList.get(i * 4 + j);
                Image img = new Image(getClass().getResource(imagePath).toString());
                ImageView imv = new ImageView(img);
                imv.setFitWidth(100);
                imv.setFitHeight(150);

                Button btn = getButton1(imv);
                arTab[k][i * 4 + j] = btn;
                gdTab.add(btn, i, j);
            }
        }
    }

    private Button getButton1(ImageView imv) {
        Label xLabel = new Label("X");
        xLabel.setStyle("-fx-text-fill: red; -fx-font-size: 30;");
        xLabel.setVisible(false);

        StackPane stackPane = new StackPane(imv, xLabel);
        Button btn = new Button();
        btn.setGraphic(stackPane);
        btn.setOnAction(e -> {
            if (btn.getGraphic() instanceof StackPane stackPane1) {
                ImageView imv1 = (ImageView) stackPane1.getChildren().get(0);
                Label xLabel1 = (Label) stackPane1.getChildren().get(1);
                if (imv1.getImage().getUrl().equals(imMazo.getImage().getUrl())) {
                    xLabel1.setVisible(true);
                    btn.setDisable(true);
                }
            }
        });
        return btn;
    }

    private Button getBtn(ImageView imv) {
        Label xLabel = new Label("X");
        xLabel.setStyle("-fx-text-fill: red; -fx-font-size: 30;");
        xLabel.setVisible(false);

        StackPane stackPane = new StackPane(imv, xLabel);
        Button btn = new Button();
        btn.setGraphic(stackPane);
        btn.setOnAction(e -> {
            if (btn.getGraphic() instanceof StackPane stackPane1) {
                ImageView imv1 = (ImageView) stackPane1.getChildren().get(0);
                Label xLabel1 = (Label) stackPane1.getChildren().get(1);
                if (imv1.getImage().getUrl().equals(imMazo.getImage().getUrl())) {
                    xLabel1.setVisible(true);
                    btn.setDisable(true);
                }
            }
        });
        return btn;
    }

    private static Button getButton(ImageView imv) {
        Label xLabel = new Label("X");
        xLabel.setStyle("-fx-text-fill: red; -fx-font-size: 30;");
        xLabel.setVisible(false);

        StackPane stackPane = new StackPane(imv, xLabel);
        Button btn = new Button();
        btn.setGraphic(stackPane);
        btn.setDisable(true);
        btn.setOnAction(e -> {
            if (btn.getGraphic() instanceof StackPane stackPane1) {
                ImageView imv1 = (ImageView) stackPane1.getChildren().get(0);
                Label xLabel1 = (Label) stackPane1.getChildren().get(1);
                xLabel1.setVisible(true);
                btn.setDisable(true);
            }
        });
        return btn;
    }

    private void actualizarTab() {
        gdTab.getChildren().clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gdTab.add(arTab[currentTab][i * 4 + j], i, j);
            }
        }
    }
}