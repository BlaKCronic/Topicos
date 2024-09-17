package org.example.topicos.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private Button btnAnt, btnSig, btnIni;
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

        hBoxButtons = new HBox(btnAnt, btnSig);
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
        btnIni = new Button("Iniciar juego");
        btnIni.getStyleClass().addAll("btn-sm", "btn-danger");
        vBoxMazo = new VBox(lbTimer, imMazo, btnIni);
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
                Button btn = new Button();
                btn.setGraphic(imv);
                arTab[k][i * 4 + j] = btn;
                gdTab.add(btn, i, j);
            }
        }
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
