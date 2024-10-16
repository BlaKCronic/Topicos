package org.example.topicos.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.topicos.components.CorredorThread;

public class Pista extends Stage {

    private String[] strCorredores = {"Eurety ","Abuelo ","Donato ","Temach ","quique "};
    private GridPane gdpPista;
    private ProgressBar[] pgbCarriles;
    private Button btnIniciar;
    private Scene escena;
    private Label[] lblCorredores;
    private CorredorThread[] thrCorredores;
    private VBox vBox;

    public Pista(){
        CrearUI();
        this.setTitle("Pista");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        lblCorredores = new Label[5];
        pgbCarriles = new ProgressBar[5];
        gdpPista = new GridPane();
        for (int i = 0; i < 5; i++){
            lblCorredores[i] = new Label(strCorredores[i]);
            gdpPista.add(lblCorredores[i],0,i);
            pgbCarriles[i] = new ProgressBar(0);
            gdpPista.add(pgbCarriles[i],1,i);
        }

        btnIniciar = new Button("iniciar carrera");
        vBox = new VBox(gdpPista,btnIniciar);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena = new Scene(vBox,200,200);
    }
}
