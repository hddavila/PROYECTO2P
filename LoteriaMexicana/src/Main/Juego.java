
package Main;

/**
 *
 * @author Hugo Davila
 */


import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.geometry.Pos;
import javafx.scene.AccessibleAction;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import ventanas.VntInicio;

/**
 *
 * Clase principal de la aplicacion que contiene a la ventana de incio del juego
 */

public class Juego extends Application {
    private final double width = 900;
    private final double height = 500;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        
        VntInicio inicio=new VntInicio();
        
        Scene escena=new Scene(inicio.getRoot(),width,height);

        escena.getStylesheets().add("/css/estiloInicio.css");
        
        stage.setScene(escena);
        stage.setTitle("Loteria Mexicana");
//        stage.setResizable(false);
        stage.show();
        
    }
}
