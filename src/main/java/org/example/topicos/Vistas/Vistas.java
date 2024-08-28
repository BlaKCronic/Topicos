package org.example.topicos.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Button[] arrbtn;
    private TextField txtPantalla;
    private GridPane teclado;
    private VBox vbox;
    private Scene escena;

    private void CrearUI(){
        arrbtn = new Button[16];
        txtPantalla = new TextField("0");
        teclado = new GridPane();
        vbox = new VBox();
        escena = new Scene();

    }

    public calc(){
        this.setTitle("Calculadora");
        this.setScene();
        this.show();
    }
}