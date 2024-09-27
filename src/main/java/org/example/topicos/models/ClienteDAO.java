package org.example.topicos.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
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

    public void INSERT(){
        String query = "INSERT INTO tblCliente(nombreCte,telCte,emailCte)" + " VALUES('"+this.nombreCte+"','"+this.telCte+"','"+this.emailCte+"')";

        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void UPDATE(){

        String query = "UPDATE tblCliente SET nombreCte = '"+this.nombreCte+"'," + "telCte = '"+this.telCte+"', emailCte = '"+this.emailCte+"' WHERE idCliente = "+this.idCliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DELETE(){

        String query = "DELETE FROM tblCliente WHERE idCliente = " + this.idCliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ClienteDAO> SELECTALL(){
        ClienteDAO objCte;
        String query = "SELECT * FROM tblCliente";
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        try{
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objCte = new ClienteDAO();
                objCte.idCliente = res.getInt(0);
                objCte.nombreCte = res.getString(1);
                objCte.telCte = res.getString(2);
                objCte.emailCte = res.getString(3);
                listaC.add(objCte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaC;
    }
}
