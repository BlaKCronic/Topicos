package org.example.topicos.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.topicos.models.DetalleVentaDAO;

public class ListaDetalleVenta extends Stage {
    private TableView<DetalleVentaDAO> tblDetalleVenta;
    private ToolBar tlbMenu;
    private VBox vBox;
    private Scene escena;

    public ListaDetalleVenta() {
        CrearUI();
        this.setTitle("Detalles de la Venta");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tlbMenu = new ToolBar();
        tblDetalleVenta = new TableView<>();
        CrearTabla();

        vBox = new VBox(tlbMenu, tblDetalleVenta);
        escena = new Scene(vBox, 600, 400);
    }

    private void CrearTabla() {
        DetalleVentaDAO detalleVenta = new DetalleVentaDAO();

        TableColumn<DetalleVentaDAO, Integer> tbcIdVenta = new TableColumn<>("ID Venta");
        tbcIdVenta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));

        TableColumn<DetalleVentaDAO, String> tbcFechaVenta = new TableColumn<>("Fecha de Venta");
        tbcFechaVenta.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));

        TableColumn<DetalleVentaDAO, String> tbcTituloCancion = new TableColumn<>("Canción");
        tbcTituloCancion.setCellValueFactory(new PropertyValueFactory<>("tituloCancion"));

        TableColumn<DetalleVentaDAO, Float> tbcPrecioCancion = new TableColumn<>("Precio Canción");
        tbcPrecioCancion.setCellValueFactory(new PropertyValueFactory<>("precioCancion"));

        TableColumn<DetalleVentaDAO, Float> tbcTotalVenta = new TableColumn<>("Total Venta");
        tbcTotalVenta.setCellValueFactory(new PropertyValueFactory<>("totalVenta"));

        tblDetalleVenta.getColumns().addAll(tbcIdVenta, tbcFechaVenta, tbcTituloCancion, tbcPrecioCancion, tbcTotalVenta);
        tblDetalleVenta.setItems(detalleVenta.SELECT_ALL());
    }
}
