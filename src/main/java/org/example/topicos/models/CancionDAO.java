package org.example.topicos.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CancionDAO {

    private int idCancion;
    private String tituloCan;
    private String duracionCan;
    private int idGenero; // Llave foránea
    private String nombreGen; // Nombre del género

    public float getPrecioCan() {
        return precioCan;
    }

    public void setPrecioCan(float precioCan) {
        this.precioCan = precioCan;
    }

    private float precioCan;

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getTituloCan() {
        return tituloCan;
    }

    public void setTituloCan(String tituloCan) {
        this.tituloCan = tituloCan;
    }

    public String getDuracionCan() {
        return duracionCan;
    }

    public void setDuracionCan(String duracionCan) {
        this.duracionCan = duracionCan;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGen() {
        return nombreGen;
    }

    public void setNombreGen(String nombreGen) {
        this.nombreGen = nombreGen;
    }

    public int INSERT() {
        int rowCount;
        String query = "INSERT INTO tblCancion (tituloCan, duracionCan, idGenero, precioCan) " +
                "VALUES('" + this.tituloCan + "', '" + this.duracionCan + "', " + this.idGenero + ", " + this.precioCan + ")";
        try {
            Statement stmt = Conexion.connection.createStatement();
            rowCount = stmt.executeUpdate(query);
        } catch (SQLException e) {
            rowCount = 0;
            e.printStackTrace();
        }
        return rowCount;
    }

    public void UPDATE() {
        String query = "UPDATE tblCancion SET tituloCan = '" + this.tituloCan + "', " +
                "duracionCan = '" + this.duracionCan + "', idGenero = " + this.idGenero + ", " +
                "precioCan = " + this.precioCan +
                " WHERE idCancion = " + this.idCancion;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM tblcancion WHERE idCancion = " + this.idCancion;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CancionDAO> SELECTALL() {
        ObservableList<CancionDAO> listaCanciones = FXCollections.observableArrayList();
        String query = "SELECT c.idCancion, c.tituloCan, c.duracionCan, c.idGenero, c.precioCan, g.nombreGen " +
                "FROM tblCancion c " +
                "JOIN tblGenero g ON c.idGenero = g.idGenero";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                CancionDAO cancion = new CancionDAO();
                cancion.setIdCancion(res.getInt("idCancion"));
                cancion.setTituloCan(res.getString("tituloCan"));
                cancion.setDuracionCan(res.getString("duracionCan"));
                cancion.setIdGenero(res.getInt("idGenero"));
                cancion.setPrecioCan(res.getFloat("precioCan"));
                cancion.setNombreGen(res.getString("nombreGen"));
                listaCanciones.add(cancion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCanciones;
    }
}