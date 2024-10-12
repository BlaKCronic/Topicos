package org.example.topicos.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.topicos.components.ButtonCell;
import org.example.topicos.models.VentasDAO;

public class ListaVenta extends Stage {

    private TableView<VentasDAO> tblVenta;
    private ToolBar tlbMenu;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaVenta(){
        CrearUI();
        this.setTitle("Table de ventas");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tlbMenu = new ToolBar();
        ImageView imv = new ImageView(getClass().getResource("/images/derecha.png").toString());
        Button btnAddVen = new Button();
        btnAddVen.setOnAction(actionEvent -> new FormVenta(tblVenta, null));
        btnAddVen.setGraphic(imv);
        tlbMenu.getItems().add(btnAddVen);

        tblVenta = new TableView<>();
        CrearTabla();

        vBox = new VBox(tlbMenu, tblVenta);
        escena = new Scene(vBox, 300, 550);
    }

    private void CrearTabla() {
        VentasDAO objVnt = new VentasDAO();
        TableColumn<VentasDAO, String> tbvDate = new TableColumn<>("Fecha de Venta");
        tbvDate.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));

        TableColumn<VentasDAO, String> tbvEditar = new TableColumn<>("");
        tbvEditar.setCellFactory(new Callback<TableColumn<VentasDAO, String>, TableCell<VentasDAO, String>>() {
            @Override
            public TableCell<VentasDAO, String> call(TableColumn<VentasDAO, String> ventasDAOStringTableColumn) {
                return new ButtonCell("Editar");
            }
        });

        TableColumn<VentasDAO,String> tbvEliminar = new TableColumn<>("");
        tbvEliminar.setCellFactory(new Callback<TableColumn<VentasDAO, String>, TableCell<VentasDAO, String>>() {
            @Override
            public TableCell<VentasDAO, String> call(TableColumn<VentasDAO, String> ventasDAOStringTableColumn) {
                return new ButtonCell("Eliminar");
            }
        });

        tblVenta.getColumns().addAll(tbvDate,tbvEditar,tbvEliminar);
        tblVenta.setItems(objVnt.SELECALL());
    }
}
