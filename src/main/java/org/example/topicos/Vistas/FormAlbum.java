package org.example.topicos.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.topicos.models.AlbumDAO;

public class FormAlbum extends Stage {

    private TextField txtTituloAlbum;
    private TextField txtFechaAlbum;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private AlbumDAO objAlbum;
    private TableView<AlbumDAO> tblAlbum;

    public FormAlbum(TableView<AlbumDAO> tableView, AlbumDAO album) {
        tblAlbum = tableView;
        crearUI();

        if (album != null) {
            this.objAlbum = album;
            txtTituloAlbum.setText(objAlbum.getTitulo());
            txtFechaAlbum.setText(objAlbum.getLanzamiento());
            this.setTitle("Editar Album");
        } else {
            this.objAlbum = new AlbumDAO();
            this.setTitle("Agregar Album");
        }

        this.setScene(escena);
        this.show();
    }

    private void crearUI() {
        txtTituloAlbum = new TextField();
        txtTituloAlbum.setPromptText("Título del Álbum");
        txtFechaAlbum = new TextField();
        txtFechaAlbum.setPromptText("Fecha del Álbum (YYYY-MM-DD)");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> GuardarAlbum());
        vBox = new VBox(txtTituloAlbum, txtFechaAlbum, btnGuardar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 150, 150);
    }

    private void GuardarAlbum(){
        objAlbum.setTitulo(txtTituloAlbum.getText());
        objAlbum.setLanzamiento(txtFechaAlbum.getText());
        String msj;
        Alert.AlertType type;

        if (objAlbum.getIdAlbum() > 0){
            objAlbum.UPDATE();
            msj = "Registro Actualizado con éxito";
            type = Alert.AlertType.INFORMATION;
        }else {
            if (objAlbum.INSERT() > 0) {
                msj = "Registro Insertado con éxito";
                type = Alert.AlertType.INFORMATION;

            } else {
                msj = "Registro NO Insertado, intente de nuevo";
                type = Alert.AlertType.ERROR;

            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensaje del Sistema :)");
            alerta.setContentText(msj);
            alerta.showAndWait();
        }

        tblAlbum.setItems(objAlbum.SELECTALL());
        tblAlbum.refresh();
    }
}