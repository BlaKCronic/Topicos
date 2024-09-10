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
    private String[] strTeclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "-"};

    // Variables para almacenar los números y el operador
    private double num1 = 0;
    private double num2 = 0;
    private String operador = "";
    private boolean nuevoNumero = true; // Variable para controlar la entrada de nuevos números

    private void CrearUI() {
        arrbtn = new Button[4][4];
        txtPantalla = new TextField("");
        txtPantalla.setAlignment(Pos.CENTER_RIGHT);
        txtPantalla.setEditable(false);
        teclado = new GridPane();
        teclado.getStyleClass().add("grid-pane"); // Agregar la clase CSS al GridPane

        // Se agrega el botón de borrar ('C').
        btnClear = new Button("C");
        btnClear.setPrefSize(50, 50);
        btnClear.setOnAction(actionEvent -> limpiarCalculadora());

        CrearTeclado();
        vbox = new VBox(txtPantalla, teclado);
        vbox.setAlignment(Pos.CENTER);
        escena = new Scene(vbox, 250, 300);
        escena.getStylesheets().add(getClass().getResource("/styles/cal.css").toExternalForm());
    }

    private void CrearTeclado() {
        teclado.add(btnClear, 0, 0);
        for (int i = 0; i < arrbtn.length; i++) {
            for (int j = 0; j < arrbtn.length; j++) {
                arrbtn[j][i] = new Button(strTeclas[4 * i + j]);
                arrbtn[j][i].setPrefSize(50, 50);
                int finalI = i;
                int finalJ = j;
                arrbtn[j][i].setOnAction(actionEvent -> detectarTecla(strTeclas[4 * finalI + finalJ]));
                teclado.add(arrbtn[j][i], j + 1, i + 1);
            }
        }
    }

    public Calculadora() {
        CrearUI();
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void detectarTecla(String strTecla) {
        switch (strTecla) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (nuevoNumero) {
                    txtPantalla.setText(strTecla);
                    nuevoNumero = false;
                } else {
                    txtPantalla.appendText(strTecla);
                }
                if (operador.isEmpty()) {
                    num1 = Double.parseDouble(txtPantalla.getText());
                } else {
                    num2 = Double.parseDouble(txtPantalla.getText());
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!operador.isEmpty()) {
                    realizarOperacion();
                }
                operador = strTecla;
                txtPantalla.setText(operador);
                nuevoNumero = true;
                break;
            case "=":
                realizarOperacion();
                nuevoNumero = true;
                break;
            case ".":
                if (nuevoNumero) {
                    txtPantalla.setText("0.");
                    nuevoNumero = false;
                } else if (!txtPantalla.getText().contains(".")) {
                    txtPantalla.appendText(".");
                }
                if (operador.isEmpty()) {
                    num1 = Double.parseDouble(txtPantalla.getText());
                } else {
                    num2 = Double.parseDouble(txtPantalla.getText());
                }
                break;
        }
    }

    private void realizarOperacion() {
        if (!operador.isEmpty()) {
            switch (operador) {
                case "+":
                    num1 = num1 + num2;
                    break;
                case "-":
                    num1 = num1 - num2;
                    break;
                case "*":
                    num1 = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        num1 = num1 / num2;
                    } else {
                        txtPantalla.setText("Error: División por cero");
                        num1 = 0;
                        num2 = 0;
                        operador = "";
                        return;
                    }
                    break;
            }
            txtPantalla.setText(String.valueOf(num1));
            num2 = 0;
            operador = "";
        }
    }

    private void limpiarCalculadora() {
        txtPantalla.clear();
        num1 = 0;
        num2 = 0;
        operador = "";
        nuevoNumero = true; // Restablecer el estado de nuevo número
    }
}
