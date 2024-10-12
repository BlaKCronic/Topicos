package org.example.topicos.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VentasDAO {

    private int idVenta;
    private String fechaVenta;
    private int idCliente;

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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int Insert(){
        int rowCount;
        String query = "INSERT INTO tblventa(fechaVenta, idCliente)" + " values('" + this.fechaVenta + "', " + this.idCliente + ")";
        try {
            Statement stmt = Conexion.connection.createStatement();
            rowCount = stmt.executeUpdate(query);
        }catch (SQLException e){
            rowCount = 0;
            e.printStackTrace();
        }
        return rowCount;
    }

    public void UPDATE(){
        String query = "UPDATE tblventa SET fechaVenta = '" + this.fechaVenta + "', " + "idCliente = " + this.idCliente + " WHERE idVenta = " + this.idVenta;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void  DELETE(){
        String query = "DELETE FROM tblventa WHERE idVenta = " + this.idVenta;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<VentasDAO> SELECALL(){
        VentasDAO objVen;
        String query = "SELECT * FROM tblventa";
        ObservableList<VentasDAO> listaV = FXCollections.observableArrayList();
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objVen = new VentasDAO();
                objVen.idVenta = res.getInt(1);
                objVen.fechaVenta = res.getString(2);
                objVen.idCliente = res.getInt(3);
                listaV.add(objVen);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaV;
    }
}
