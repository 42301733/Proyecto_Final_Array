/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.galeriadeimagenesarray;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Jorge
 * @author Adrian
 */
public class loginController {

    @FXML
    private TextField txtLoginUsuario;

    @FXML
    private PasswordField pswLoginPassword;

    @FXML
    private Label lblError;

    // Evalúa credenciales sin revelar cuál campo es el erróneo
    @FXML
    protected void validarUsuario() {

        String usuario = txtLoginUsuario.getText().trim();
        String password = pswLoginPassword.getText().trim();

        // Validar campos vacíos
        if (usuario.isEmpty() || password.isEmpty()) {
            lblError.setVisible(true);
            lblError.setText("Complete todos los campos");
            return;
        }

        // Verificación de credenciales
        boolean usuarioCorrecto = usuario.equalsIgnoreCase("admin");
        boolean passwordCorrecto = password.equals("campusjalpa");

        // Inicio de sesión exitoso: abre la galería y cierra el login
        if (usuarioCorrecto && passwordCorrecto) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("galeria-view.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Galería de imágenes");
                stage.setMaximized(true);
                stage.show();

                // Cerrar ventana actual
                Stage loginStage = (Stage) txtLoginUsuario.getScene().getWindow();
                loginStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        //Mensaje de error si la contraseña es incorrecta
        lblError.setVisible(true);
        lblError.setText("Usuario y/o contraseña incorrectos");

        // Limpieza y reasignación de foco al primer campo
        txtLoginUsuario.clear();
        pswLoginPassword.clear();
        txtLoginUsuario.requestFocus();
    }
}

