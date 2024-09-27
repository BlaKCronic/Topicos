package org.example.topicos.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.topicos.models.ClienteDAO;

public class ListaClientes extends Stage {

    private TableView<ClienteDAO> tbvClientes;
    private ToolBar tlbMenu;
    private VBox vBox;
    private Scene escena;

    public ListaClientes(){
        CrerUI();
        this.setTitle("Lista de clientes");
        this.setScene(escena);
        this.show();
    }

    private void CrerUI() {

    }
}
