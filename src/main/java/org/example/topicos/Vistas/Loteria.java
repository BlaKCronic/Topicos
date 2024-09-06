package org.example.topicos.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Loteria extends Stage {

    private HBox hBoxMain, hBoxButtons;
    private VBox vBoxTablilla, vBoxMazo;
    private Button btnAnt, btnSig, btnIni;
    private Label lbTimer;
    private GridPane gdTab;
    private ImageView imMazo;
    private Scene escena;
    private String[] arrImages = {"barril.jpg","botella.jpg","catrin.jpg","chavorruco.jpg","concha.jpg","luchador.jpg","maceta.jpg","rosa.jpg","tacos.jpg","venado.jpg"};
    private Button[][] arTab;

    public Loteria(){
        CrearUI();
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        ImageView imAnt, imSig;
        imAnt = new ImageView(new Image(getClass().getResource("/images/izquierda.png").toString()));
        imSig = new ImageView(new Image(getClass().getResource("/images/derecha.png").toString()));

        gdTab = new GridPane();
        CrearTablilla();

        btnAnt = new Button();
        btnAnt.setGraphic(imAnt);
        btnSig =new Button();
        btnSig.setGraphic(imSig);

        hBoxButtons =new HBox(btnAnt, btnSig);
        vBoxTablilla = new VBox(gdTab, hBoxButtons);

        hBoxMain = new HBox(vBoxTablilla);
        escena = new Scene(hBoxMain, 1000, 800);
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
