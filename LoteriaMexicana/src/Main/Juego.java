
package Main;

/**
 *
 * @author Hugo Davila
 */


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * Clase principal de la aplicacion que contiene a la ventana de incio del juego
 */

public class Juego extends Application {
   
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        
        StackPane root=new StackPane();
        
        
        //declarar contenedor
        VBox columna=new VBox(15);
        
        //elementos del contenedor
        Label titulo=new Label("Loteria Mexicana");
        Button btn_nuevo=new Button("Nuevo Juego");
        Button btn_config=new Button("Configuraciones");
        Button btn_report=new Button("Reporte");
        
        columna.getChildren().addAll(titulo,btn_nuevo,btn_config,btn_report);
        columna.setAlignment(Pos.CENTER);
        
        
        try{
        //establecer la imagen de fondo
        ImageView fondo=new ImageView(new Image("/src/recursos/background2.png"));
        }
        catch(Exception e){
            System.out.println("->ERROR AL CARGAR LA IMAGEN");
        }
        
        root.getChildren().addAll(columna);
        
        //evento del boton Nuevo Juego
        btn_nuevo.setOnAction(e->{
//            ventanaNuevo vn=new VentanaNuevo();
//            Scene escenaVn=new Scene(vn.getRoot(),900,500);
//            Stage stgVn=new Stage();
//            
//            stgVn.setTitle("Inicar");
//            stgVn.setScene(escenVn);
//            stgVn.show();
        });
        
        
        //evento del boton Configuraciones
        btn_config.setOnAction(e->{
            
        });
        
        //eventodel boton Reporte
        btn_report.setOnAction(e->{
        
        });
        
        Scene inicio=new Scene(root,900,500);
        stage.setScene(inicio);
        stage.setTitle("Loteria Mexicana");
        stage.show();
       
    }
}
