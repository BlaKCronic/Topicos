package org.example.topicos.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.topicos.models.ClienteDAO;

public class FormClientes extends Stage {

    private Scene escena;

    private TextField txtNombre;
    private TextField txtEmailCte;
    private TextField txtTelCte;
    
    private Button btnGuardar;
    private VBox vBox;
    ClienteDAO objCte;
    private void GuardarCliente;

    public FormClientes(){
        objCte = new ClienteDAO();
        CrearUI();
        this.setTitle("Agregar Cliente");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtEmailCte = new TextField();
        txtEmailCte.setPromptText("Email");
        txtTelCte = new TextField();
        txtTelCte.setPromptText("Telefono");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> GuardarCliente);
        vBox = new VBox(txtNombre,txtEmailCte,txtTelCte,btnGuardar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 300, 150);
    }
}
