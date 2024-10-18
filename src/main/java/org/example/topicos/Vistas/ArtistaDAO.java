package org.example.topicos.Vistas;

import org.example.topicos.models.Conexion;

import java.sql.SQLException;
import java.sql.Statement;

public class ArtistaDAO {

    private int idArtista;
    private String nombreArt;

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArt() {
        return nombreArt;
    }

    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }

    public int INSERT(){
        int rowCount;
        String query = "INSERT INTO tblArtista(nombreArt)" + " values('"+this.nombreArt+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            rowCount = stmt.executeUpdate(query);
        } catch (SQLException e) {
            rowCount = 0;
            e.printStackTrace();
        }
        return rowCount;
    }

    public void UPDATE(){
        String query = "UPDATE tblArtista SET nombreArt = '" + this.nombreArt + "' WHERE idArtista = " + this.idArtista;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DELETE(){

    }
}
