package org.example.topicos.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Button[][] arrbtn;
    private Button btnClear;
    private TextField txtPantalla;
    private GridPane teclado;
    private VBox vbox;
    private Scene escena;
    private String[] strTeclas = {"7","8","9","*","4","5","6","/","1","2","3","+","0",".","=","-"};

    private void CrearUI(){
        arrbtn = new Button[4][4];
        txtPantalla = new TextField("");
        txtPantalla.setAlignment(Pos.CENTER_RIGHT);
        txtPantalla.setEditable(false);
        teclado = new GridPane();
        btnClear = new Button("C");
        btnClear.setPrefSize(50,50);
        btnClear.setOnAction(actionEvent -> txtPantalla.clear());
        CrearTeclado();
        vbox = new VBox(txtPantalla, teclado);
        vbox.setAlignment(Pos.CENTER);
        escena = new Scene(vbox, 200, 200);
    }

    private void CrearTeclado(){
        teclado.add(btnClear, 0,0);
        for (int i = 0; i < arrbtn.length; i++){
            for (int j = 0; j < arrbtn.length; j++) {
                arrbtn[j][i] = new Button(strTeclas[4*i+j]);
                arrbtn[j][i].setPrefSize(50, 50);
                int finalI = i;
                int finalJ = j;
                arrbtn[j][i].setOnAction(actionEvent -> detectarTecla(strTeclas[4* finalI + finalJ]));
                teclado.add(arrbtn[j][i],j + 1,i + 1);
            }
        }
    }

    public Calculadora(){
        CrearUI();
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void detectarTecla(String tecla){
        txtPantalla.appendText(tecla);
    }

}