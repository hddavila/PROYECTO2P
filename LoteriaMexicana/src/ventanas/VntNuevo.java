/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modelo.Partida;
import static ventanas.VntInicio.musica;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author DANIEL
 */
public class VntNuevo {
    TextField txtNombre;
    StackPane back;
    BorderPane root;
    ImageView salir;
    
    /**Constructor de un objeto de tipo Ventana Nuevo jugador
     * 
     */
    public VntNuevo(){
        
        createContent();
    }
    
    public BorderPane getRoot(){
        return root;
    }
    
    public StackPane getBackRoot(){
        return back;
    }
    /**Genera el contenido de la ventana para escribir el nombre del jugador en iniciar la partida
     * 
     */
    public void createContent(){
        back = new StackPane();
        try{
            ImageView background = new ImageView(new Image("/recursos/background.png"));
        }
        catch(Exception e){
            System.err.println("No se pudo cargar el fondo frijoles");
        }
        
        root=new BorderPane();
        root.setId("fondoNuevo");
        txtNombre=new TextField();
        txtNombre.setId("caja");
        txtNombre.setMaxSize(400,400);
        txtNombre.setPromptText("dinosaurio");
        
        StackPane ventana=new StackPane();
        Rectangle cuadro=new Rectangle(500,200);
        cuadro.setId("cuadroNuevo");
        
        salir=new ImageView("/recursos/salir.png");
        salir.setScaleX(0.25);
        salir.setScaleY(0.25);
        salir.setTranslateX(230);
        salir.setTranslateY(-90);
  
        
        
        VBox columna=new VBox(10);
        
        Label titulo=new Label("Escriba su nombre...");
        titulo.setStyle("-fx-text-fill:black;-fx-font-family: Helvetica;");
        Button btn_iniciar=new Button("Iniciar");
        btn_iniciar.setId("iniciar");
        
        //accion del boton iniciar
        btn_iniciar.setOnAction(e->{
            sonido();
            musica.parar();
            if(txtNombre.getText().trim().length()>0){
                actualizarConfig();
                
                Stage stage=new Stage();
                VntPartida pantalla=new VntPartida();
                Scene escena=new Scene(pantalla.getContenedor(),1300,800);
                escena.getStylesheets().add("/css/estiloPartida.css");
                        
                stage.setScene(escena);
                stage.setTitle("LOTERIA MEXICANA (JUGANDO)");
                stage.show();
                
                //esconder la ventana de ingreso
                titulo.getScene().getWindow().hide();
            }
            else{
                Alert mensaje=new Alert(AlertType.WARNING);
                mensaje.setHeaderText(null);
                mensaje.setContentText("Debe ingresar un nombre");
                mensaje.showAndWait();
            }
            
        });
        
        columna.getChildren().addAll(titulo,txtNombre,btn_iniciar);
        columna.setAlignment(Pos.CENTER);
        
        ventana.getChildren().addAll(cuadro,columna,salir);
        root.setCenter(ventana);
        back.getChildren().add(root);
    }
    
    /**
     * Accede al archivo temporal de configuracion y actualiza el objeto Partida dentro y le agrega el nombre que se acaba de ingresar
     */
    public void actualizarConfig(){
       try{
        ObjectInputStream saut=new ObjectInputStream(new FileInputStream("src/partidas/config.ser"));
        Partida ge=(Partida) saut.readObject();

        
        //sobreescribir el nombre del jugador al que se ingreso
        ge.setNombreUsuario(txtNombre.getText());
        
                
        System.out.println(ge.toString()); //---------------borrar------------------------------------
        
        saut.close();
        //sobreescribir el archivo temporal
        ObjectOutputStream fout=new ObjectOutputStream(new FileOutputStream("src/partidas/config.ser"));
        
        fout.writeObject(ge);
        fout.close();
        
           System.out.println("ARCHIVO ACTUALIZADO CORRECTAMENTE");
  
       }
       catch(Exception e){
           System.out.println("El ARCHIVO NO SE PUDO ACTUALIZAR");
       }
    }
    
    /**Devuelve el boton de regresar
     * 
     * @return salir Boton para regresar a la ventana de inicio 
     */
    public ImageView getSalir() {
        return salir;
    }
    
    
}
