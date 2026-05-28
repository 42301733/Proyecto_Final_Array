package laboratorio.galeriadeimagenesarray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class galeriaController {

    @FXML
    private GridPane grdGaleria;

    // Arreglo principal de objetos Imagen
    private Imagen[] imagenes = {

        new Imagen("/imagenes/Paisaje1.jpg","Paisaje 1","Naturaleza",LocalDate.of(2026,5,1),2,2411724),
        new Imagen("/imagenes/Paisaje2.jpg","Paisaje 2","Montaña",LocalDate.of(2026,5,2),1,1572864),
        new Imagen("/imagenes/Paisaje3.jpg","Paisaje 3","Bosque",LocalDate.of(2026,5,3),6,7025459),
        new Imagen("/imagenes/Paisaje4.jpg","Paisaje 4","Nocturno",LocalDate.of(2026,5,4),8,8493465),
        new Imagen("/imagenes/Paisaje5.jpg","Paisaje 5","Lagos",LocalDate.of(2026,5,5),4,4404019),

        new Imagen("/imagenes/Paisaje6.jpg","Paisaje 6","Paisaje",LocalDate.of(2026,5,6),5,6081740),
        new Imagen("/imagenes/Paisaje7.jpg","Paisaje 7","Ciudad",LocalDate.of(2026,5,7),9,9751756),
        new Imagen("/imagenes/Paisaje8.jpg","Paisaje 8","Atardecer",LocalDate.of(2026,5,8),2,2202009),
        new Imagen("/imagenes/Paisaje9.jpg","Paisaje 9","Río",LocalDate.of(2026,5,9),3,3565158),
        new Imagen("/imagenes/Paisaje10.jpg","Paisaje 10","Playa",LocalDate.of(2026,5,10),7,8178892),

        new Imagen("/imagenes/Paisaje11.jpg","Paisaje 11","Naturaleza",LocalDate.of(2026,5,11),1,1992294),
        new Imagen("/imagenes/Paisaje12.jpg","Paisaje 12","Montaña",LocalDate.of(2026,5,12),2,2936012),
        new Imagen("/imagenes/Paisaje13.jpg","Paisaje 13","Bosque",LocalDate.of(2026,5,13),5,5976883),
        new Imagen("/imagenes/Paisaje14.jpg","Paisaje 14","Nocturno",LocalDate.of(2026,5,14),4,4613734),
        new Imagen("/imagenes/Paisaje15.jpg","Paisaje 15","Lagos",LocalDate.of(2026,5,15),6,6396313),

        new Imagen("/imagenes/Paisaje16.jpg","Paisaje 16","Paisaje",LocalDate.of(2026,5,16),3,3460300),
        new Imagen("/imagenes/Paisaje17.jpg","Paisaje 17","Ciudad",LocalDate.of(2026,5,17),8,9017753),
        new Imagen("/imagenes/Paisaje18.jpg","Paisaje 18","Atardecer",LocalDate.of(2026,5,18),2,2306867),
        new Imagen("/imagenes/Paisaje19.jpg","Paisaje 19","Río",LocalDate.of(2026,5,19),4,5138022),
        new Imagen("/imagenes/Paisaje20.jpg","Paisaje 20","Playa",LocalDate.of(2026,5,20),7,7444889)

    };

    // Guarda únicamente las 6 visibles
    private Imagen[] mostradas = new Imagen[6];

    @FXML
    public void initialize() {

        grdGaleria.setHgap(20);
        grdGaleria.setVgap(20);

        mostrarImagenes();

    }

    private void mostrarImagenes() {

        Random random = new Random();

        boolean usados[] = new boolean[20];

        int fila = 0;
        int columna = 0;
        int cantidad = 0;

        while(cantidad < 6){

            int indice = random.nextInt(imagenes.length);

            if(usados[indice]){

                continue;

            }

            usados[indice]=true;

            mostradas[cantidad]=imagenes[indice];

            Image imagen = new Image(getClass().getResourceAsStream(imagenes[indice].getRuta()));

            ImageView img = new ImageView(imagen);

            img.setFitWidth(300);
            img.setFitHeight(180);
            img.setPreserveRatio(false);

            img.getStyleClass().add("imagenes");

            VBox tarjeta = new VBox();

            tarjeta.setSpacing(8);

            tarjeta.setPadding(new Insets(10));

            tarjeta.setPrefWidth(340);
            tarjeta.setPrefHeight(320);

            tarjeta.getStyleClass().add("tarjeta");

            tarjeta.setOnMouseEntered(event -> tarjeta.getStyleClass().add("imagenSeleccionada"));

            tarjeta.setOnMouseExited(event -> tarjeta.getStyleClass().remove("imagenSeleccionada"));

            int posicionEnGaleria=cantidad;

            tarjeta.setOnMouseClicked(event ->abrirVisor(posicionEnGaleria));

            Label lblTitulo=new Label("Título: "+imagenes[indice].getTitulo());

            Label lblFecha=new Label("Fecha: "+imagenes[indice].getFecha());

            Label lblCategoria=new Label("Categoría: "+imagenes[indice].getCategoria());

            Label lblMB=new Label("Tamaño: "+imagenes[indice].getTamanoenMB()+" MB");

            Label lblBytes=new Label("Bytes: "+imagenes[indice].getTamanoenBytes());

            tarjeta.getChildren().addAll(img,lblTitulo,lblFecha,lblCategoria,lblMB,lblBytes);

            grdGaleria.add(tarjeta,columna,fila);

            columna++;

            if(columna==3){

                columna=0;

                fila++;

            }

            cantidad++;

        }
    }
    private void abrirVisor(int posicionEnGaleria){

        try{

            FXMLLoader loader=new FXMLLoader(getClass().getResource("visor.fxml"));

            Parent root=loader.load();

            visorController c=loader.getController();

            c.iniciarDatos(mostradas,posicionEnGaleria);

            Stage stage=new Stage();

            stage.initStyle(StageStyle.TRANSPARENT);

            Scene scene=new Scene(root);

            scene.setFill(null);

            stage.setScene(scene);

            stage.setMaximized(true);

            stage.show();

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }
}