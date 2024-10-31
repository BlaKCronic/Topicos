package org.example.topicos.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetalleVentaDAO {

    private int idVenta;
    private String fechaVenta;
    private String tituloCancion;
    private float precioCancion;
    private float totalVenta;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getTituloCancion() {
        return tituloCancion;
    }

    public void setTituloCancion(String tituloCancion) {
        this.tituloCancion = tituloCancion;
    }

    public float getPrecioCancion() {
        return precioCancion;
    }

    public void setPrecioCancion(float precioCancion) {
        this.precioCancion = precioCancion;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

    public ObservableList<DetalleVentaDAO> SELECT_ALL() {
        ObservableList<DetalleVentaDAO> listaDetalle = FXCollections.observableArrayList();
        String query = "SELECT v.idVenta, v.fechaVenta, c.tituloCan, c.precioCan, " +
                "(SELECT SUM(c2.precioCan) FROM detalleVenta dv2 " +
                "JOIN tblCancion c2 ON dv2.idCancion = c2.idCancion " +
                "WHERE dv2.idVenta = v.idVenta) AS totalVenta " +
                "FROM detalleVenta dv " +
                "JOIN tblVenta v ON dv.idVenta = v.idVenta " +
                "JOIN tblCancion c ON dv.idCancion = c.idCancion " +
                "ORDER BY v.idVenta";

        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                DetalleVentaDAO detalle = new DetalleVentaDAO();
                detalle.setIdVenta(res.getInt("idVenta"));
                detalle.setFechaVenta(res.getString("fechaVenta"));
                detalle.setTituloCancion(res.getString("tituloCan"));
                detalle.setPrecioCancion(res.getFloat("precioCan"));
                detalle.setTotalVenta(res.getFloat("totalVenta"));
                listaDetalle.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDetalle;
    }
}
