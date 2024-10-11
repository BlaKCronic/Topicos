package org.example.topicos.Vistas;

import javafx.scene.control.TableView;
import org.example.topicos.models.ClienteDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormCliente  extends Stage {

    private TextField txtNomCte;
    private TextField txtCorrCte;
    private TextField txtTelCte;
    private Button btnGuardar;
    private VBox vBox;
    private ClienteDAO objCte;
    private Scene escena;
    private TableView<ClienteDAO> tbvCliente;

    public FormCliente(TableView<ClienteDAO> tbv, ClienteDAO objC) {
        tbvCliente = tbv;
        CrearUI();

        if (objC != null) {
            this.objCte = objC;
            txtNomCte.setText(objCte.getNombreCte());
            txtCorrCte.setText(objCte.getEmailCte());
            txtTelCte.setText(objCte.getTelCte());
        }else {
            this.objCte = new ClienteDAO();
        }

        this.setTitle("Agregar Cliente :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtNomCte = new TextField();
        txtNomCte.setPromptText("Ingrese su Nombre Completo :)");
        txtCorrCte = new TextField();
        txtCorrCte.setPromptText("Ingrese su correo :)");
        txtTelCte = new TextField();
        txtTelCte.setPromptText("Ingrese su telefono :)");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> GuardarCliente());
        vBox = new VBox(txtNomCte, txtCorrCte, txtTelCte, btnGuardar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 150, 150);
    }

    private void GuardarCliente(){
        objCte.setNombreCte(txtNomCte.getText());
        objCte.setTelCte(txtTelCte.getText());
        objCte.setEmailCte(txtCorrCte.getText());
        String msj;
        Alert.AlertType type;
        if (objCte.Insert() > 0){
            msj = "Registro Insertado";
            type = Alert.AlertType.INFORMATION;

        }else{
            msj = "Registro NO Insertado, intente de nuevo";
            type = Alert.AlertType.ERROR;

        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensaje del Sistema :)");
        alerta.setContentText(msj);
        alerta.showAndWait();

        tbvCliente.setItems(objCte.SELECTALL());
        tbvCliente.refresh();
    }






}