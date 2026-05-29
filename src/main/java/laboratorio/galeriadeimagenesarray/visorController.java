/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.galeriadeimagenesarray;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Jorge
 * @author Adrian
 */
public class visorController {

    @FXML
    private ImageView imgGrande;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblMB;

    @FXML
    private Label lblCategoria;

    @FXML
    private Label lblBytes;

    @FXML
    private Label lblFecha;

    @FXML
    private Button btnCerrar;

    private Imagen[] imagenes;

    private int posicionActual;

    public void iniciarDatos(
            Imagen[] imagenes,
            int posicion
    ) {

        this.imagenes = imagenes;

        this.posicionActual = posicion;

        mostrarImagen();

    }

    private void mostrarImagen() {

        try {

            Image imagen=new Image(getClass().getResourceAsStream(imagenes[posicionActual].getRuta()));

            imgGrande.setImage(imagen);

            imgGrande.setPreserveRatio(true);

            imgGrande.setFitWidth(1200);

            imgGrande.setFitHeight(700);

            lblTitulo.setText(imagenes[posicionActual].getTitulo());

            lblMB.setText(imagenes[posicionActual].getTamanoenMB()+ " MB");

            lblCategoria.setText(imagenes[posicionActual].getCategoria());

            lblBytes.setText("Bytes: "+ imagenes[posicionActual].getTamanoenBytes());

            lblFecha.setText(imagenes[posicionActual].getFecha().toString());

        } catch(Exception e){

            e.printStackTrace();

        }

    }

    @FXML
    private void siguienteImagen(){

        posicionActual++;

        if(posicionActual>=imagenes.length){

            posicionActual=0;

        }

        mostrarImagen();

    }

    @FXML
    private void anteriorImagen(){

        posicionActual--;

        if(posicionActual<0){

            posicionActual=
            imagenes.length-1;

        }

        mostrarImagen();

    }

    @FXML
    private void cerrarVisor(){

        Stage stage=(Stage)btnCerrar.getScene().getWindow();

        stage.close();

    }
}
