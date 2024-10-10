package org.example.topicos.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {
    private int idCliente;
    private String nombreCte;
    private String telCte;
    private String emailCte;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCte() {
        return nombreCte;
    }

    public void setNombreCte(String nombreCte) {
        this.nombreCte = nombreCte;
    }

    public String getTelCte() {
        return telCte;
    }

    public void setTelCte(String telCte) {
        this.telCte = telCte;
    }

    public String getEmailCte() {
        return emailCte;
    }

    public void setEmailCte(String emailCte) {
        this.emailCte = emailCte;
    }

    public int Insert(){
        int rowCount;
        String query = "INSERT INTO tblcliente(nombreCte,emailCte,telCte)" + " values('"+this.nombreCte+"', '"+this.emailCte+"', '"+this.telCte+"')";
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

        String query = "UPDATE tblcliente SET nombreCte = '"+this.nombreCte+"'," + "telCte = '"+this.emailCte+"', emailCte = '"+this.telCte+"' WHERE idCliente = "+this.idCliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DELETE(){

        String query = "DELETE FROM tblcliente WHERE idCliente = " + this.idCliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ClienteDAO> SELECTALL(){
        ClienteDAO objCte;
        String query = "SELECT * FROM tblcliente";
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        try{
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objCte = new ClienteDAO();
                objCte.idCliente = res.getInt(1);
                objCte.nombreCte = res.getString(2);
                objCte.emailCte = res.getString(3);
                objCte.telCte = res.getString(4);
                listaC.add(objCte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaC;
    }
}
