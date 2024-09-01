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
        //se agrega elboton de borrar('C').
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

    //accion de latecla =
    private void detectarTecla(String tecla){
        if (tecla.equals("=")) {
            evaluarExpresion();
        } else {
            txtPantalla.appendText(tecla);
        }
    }

    //metodo que verifica expresiones,en caso de ser erronea imprime un "ERROR"
    private void evaluarExpresion(){
        String expresion = txtPantalla.getText().replace("=", "");
        try{
            double resultado = evaluar(expresion);
            txtPantalla.setText(String.valueOf(resultado));
        }
        catch (Exception e){
            txtPantalla.setText("ERROR");
            //esta excepcion no esta pulida aun, ya que el "ERROR" lo reconoce como caracter, deberá limpiarce la pantalla para que no cause problemas
        }
    }

    /*
    pos: La posición actual en la expresión.
    ch: El carácter actual en la expresión.
    nextChar(): Avanza a la siguiente posición en la expresión y actualiza el valor de ch. Si se ha alcanzado el final de la expresión, asigna -1 a ch.
    eat(int charToEat): Verifica si el carácter actual es igual a charToEat. Si es así, avanza a la siguiente posición en la expresión y devuelve true.Si no, devuelve false. Ignora los espacios en blanco.
    */
    private double evaluar(String expresion){
        return new Object(){
            int pos = -1, ch;

            void nextChar(){
                ch = (++pos < expresion.length()) ? expresion.charAt(pos) : -1;
            }

            boolean eat(int charToEat){
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return  true;
                }
                return  false;
            }

            //procesos de parsing(analisis matematico)
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expresion.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            //metodo responsable de las sumas y restas
            double parseExpression(){
                double x = parseTerm();
                for (;;){
                    if (eat('+')) x += parseTerm(); //suma
                    else if (eat('-')) x -= parseTerm(); // resta
                    else return x;
                }
            }

            //metodo responsable de divisiones y multilicaciones
            double parseTerm(){
                double x = parseFactor();
                for (;;){
                    if (eat('*')) x *= parseFactor(); // multiplicación
                    else if (eat('/')) x /= parseFactor(); // división
                    else return x;
                }
            }

            //metodo de verificacion de signos.
            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expresion.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                return x;
            }
        }.parse();
    }
}