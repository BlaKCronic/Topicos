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

public class Loteria extends Stage {

    private HBox hBoxMain, hBoxButtons;
    private VBox vBoxTablilla, vBoxMazo;
    private Button btnAnt, btnSig, btnIni;
    private Label lbTimer;
    private GridPane gdTab;
    private ImageView imMazo;
    private Scene escena;
    private String[] arrImages = {"chat.jpg","temu.jpg","chems.jpg","rub.jpg","mondongo.jpg","mc.jpg","rizz.jpg","escalera.jpg","barril.jpg","botella.jpg","catrin.jpg","chavorruco.jpg","concha.jpg","luchador.jpg","maceta.jpg","rosa.jpg","tacos.jpg","venado.jpg"};
    private Button[][] arTab;
    private Panel pnlMain;

    public Loteria(){
        CrearUI();
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        ImageView imAnt, imSig;
        imAnt = new ImageView(new Image(getClass().getResource("/images/izquierda.png").toString()));
        imAnt.setFitWidth(50);
        imAnt.setFitHeight(50);
        imSig = new ImageView(new Image(getClass().getResource("/images/derecha.png").toString()));
        imSig.setFitWidth(50);
        imSig.setFitHeight(50);

        gdTab = new GridPane();
        CrearTablilla();

        btnAnt = new Button();
        btnAnt.setGraphic(imAnt);
        btnSig =new Button();
        btnSig.setGraphic(imSig);

        hBoxButtons =new HBox(btnAnt, btnSig);
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
        btnIni.getStyleClass().setAll("btn-sm","btn-danger");
        vBoxMazo = new VBox(lbTimer, imMazo, btnIni);
    }

    private void CrearTablilla() {
        arTab = new Button[4][4];
        Image img;
        ImageView imv;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                img = new Image(getClass().getResource("/images/barril.jpg").toString());
                imv = new ImageView(img);
                imv.setFitWidth(100);
                imv.setFitHeight(150);
                arTab[i][j] = new Button();
                arTab[i][j].setGraphic(imv);
                gdTab.add(arTab[i][j],i,j);
            }

        }
    }
}
