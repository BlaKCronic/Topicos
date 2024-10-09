package org.example.topicos.components;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import org.example.topicos.models.ClienteDAO;

public class ButtonCell extends TableCell<ClienteDAO, String> {

    Button btnCelda;

    public ButtonCell(String str){
        btnCelda = new Button(str);
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b){
            this.setGraphic(btnCelda);
        }
    }
}
