package org.example.topicos.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.topicos.components.ButtonCell;
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
        tlbMenu = new ToolBar();
        ImageView imv = new ImageView(getClass().getResource("/images/derecha.png").toString());
        Button btnAddCte = new Button();
        btnAddCte.setOnAction(actionEvent -> new FormCliente());
        btnAddCte.setGraphic(imv);
        tlbMenu.getItems().add(btnAddCte);

        tbvClientes = new TableView<>();
        CrearTable();

        vBox = new VBox(tlbMenu, tbvClientes);
        escena = new Scene(vBox, 600, 250);
    }

    private void CrearTable() {
        ClienteDAO objCte = new ClienteDAO();
        TableColumn<ClienteDAO,String> tbcNmCte = new TableColumn<>("Cliente");
        tbcNmCte.setCellValueFactory(new PropertyValueFactory<>("nombreCte"));

        TableColumn<ClienteDAO,String> tbcEmailCte = new TableColumn<>("Email");
        tbcEmailCte.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

        TableColumn<ClienteDAO,String> tbcTelCte = new TableColumn<>("Telefono");
        tbcTelCte.setCellValueFactory(new PropertyValueFactory<>("telCte"));

        TableColumn<ClienteDAO,String> tbcEditar = new TableColumn<>("");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> clienteDAOStringTableColumn) {
                return new ButtonCell();
            }
        });

        TableColumn<ClienteDAO,String> tbcEliminar = new TableColumn<>("");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> clienteDAOStringTableColumn) {
                return new ButtonCell();
            }
        });

        tbvClientes.getColumns().addAll(tbcNmCte,tbcEmailCte,tbcTelCte,tbcEditar);
        tbvClientes.setItems(objCte.SELECTALL());
    }
}