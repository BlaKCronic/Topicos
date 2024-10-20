package org.example.topicos.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import org.example.topicos.Vistas.FormAlbum;
import org.example.topicos.models.AlbumDAO;

import java.util.Optional;

public class ButtonCellAlbum extends TableCell<AlbumDAO, String> {

    private Button btnCelda;

    public ButtonCellAlbum(String str){
        btnCelda= new Button(str);
        btnCelda.setOnAction(actionEvent -> EventoBoton(str));
    }

    private void EventoBoton(String str) {
        AlbumDAO objAlb = this.getTableView().getItems().get(this.getIndex());
        if (str.equals("Editar")){
            new FormAlbum(this.getTableView(), objAlb);
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("Deseas eliminar el registro?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK){
                objAlb.DELETE();
                this.getTableView().setItems(objAlb.SELECTALL());
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
