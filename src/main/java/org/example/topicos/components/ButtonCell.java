package org.example.topicos.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import org.example.topicos.Vistas.FormCliente;
import org.example.topicos.models.ClienteDAO;

import java.util.Optional;

public class ButtonCell extends TableCell<ClienteDAO, String> {

    Button btnCelda;

    public ButtonCell(String str){
        btnCelda = new Button(str);
        btnCelda.setOnAction(actionEvent -> EventoBoton(str));
    }

    private void EventoBoton(String str) {
        ClienteDAO objCte = this.getTableView().getItems().get(this.getIndex());
        if (str.equals("Editar")){
            new FormCliente(this.getTableView(), objCte);
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("Deseas eliminar el registro?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK){
                objCte.DELETE();
                this.getTableView().setItems(objCte.SELECTALL());
                this.getTableView().refresh();
            }
        }
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b){
            this.setGraphic(btnCelda);
        }
    }
}
