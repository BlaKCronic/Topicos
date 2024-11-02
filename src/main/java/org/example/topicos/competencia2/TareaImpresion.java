package org.example.topicos.competencia2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TareaImpresion {
    private IntegerProperty noArchivo;
    private StringProperty nombreArchivo;
    private IntegerProperty numHojas;
    private StringProperty horaAcceso;

    public TareaImpresion(int noArchivo, String nombreArchivo, int numHojas, String horaAcceso) {
        this.noArchivo = new SimpleIntegerProperty(noArchivo);
        this.nombreArchivo = new SimpleStringProperty(nombreArchivo);
        this.numHojas = new SimpleIntegerProperty(numHojas);
        this.horaAcceso = new SimpleStringProperty(horaAcceso);
    }

    // Getters y setters para las propiedades
    public int getNoArchivo() {
        return noArchivo.get();
    }

    public void setNoArchivo(int noArchivo) {
        this.noArchivo.set(noArchivo);
    }

    public IntegerProperty noArchivoProperty() {
        return noArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo.get();
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo.set(nombreArchivo);
    }

    public StringProperty nombreArchivoProperty() {
        return nombreArchivo;
    }

    public int getNumHojas() {
        return numHojas.get();
    }

    public void setNumHojas(int numHojas) {
        this.numHojas.set(numHojas);
    }

    public IntegerProperty numHojasProperty() {
        return numHojas;
    }

    public String getHoraAcceso() {
        return horaAcceso.get();
    }

    public void setHoraAcceso(String horaAcceso) {
        this.horaAcceso.set(horaAcceso);
    }

    public StringProperty horaAccesoProperty() {
        return horaAcceso;
    }
}

