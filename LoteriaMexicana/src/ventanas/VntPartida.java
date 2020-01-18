/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Partida;
import paneles.PanelAlineacion;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author Hugo Davila
 */
public class VntPartida {
    
    private BorderPane contenedor;
    private Jugador player;
    private VBox izquierda;
    private VBox centro;
    private VBox derecha;
    
    
    private Partida config;
    private VBox info;
    
    public VntPartida(){
        createContent();
    }
    
    public BorderPane getContenedor(){
        return contenedor;
    }
    
    public void createContent(){
        //incializar todos lo contenedores
        contenedor=new BorderPane();
        izquierda=new VBox();
        centro=new VBox();
        derecha=new VBox();
        info=new VBox();
        
        contenedor.setCenter(centro);
        contenedor.setRight(derecha);
        contenedor.setLeft(izquierda);
        
       
         
        try {
            config=recuperarConfig();
            
            //DERECHA
                //informacion del jugador
                Label nombre=new Label(config.getNombreUsuario());
                nombre.setId("jugador");

                info.getChildren().add(nombre);
                derecha.getChildren().addAll(info);
            
            //IZQUIERDA
                //panel alineacion
                PanelAlineacion alineacion=new PanelAlineacion(config.getAlineacion());
                BorderPane panelAlineacion=alineacion.getRoot();
                panelAlineacion.setId("alineacion");
                izquierda.getChildren().add(panelAlineacion);
            //ABAJO
                HBox abajo=new HBox();
                Button loteria=new Button("LOTERIA");
                loteria.setAlignment(Pos.CENTER);
                loteria.setMinWidth(500);
                
                loteria.setOnAction(e->{
                    sonido();
                    if(verificarAlineacion()==true){
                        Alert mensaje=new Alert(AlertType.INFORMATION);
                        DialogPane dialogPane = mensaje.getDialogPane();
                        dialogPane.getStylesheets().add("/css/estiloMensajes.css");
                        
                        mensaje.setTitle(null);
                        mensaje.setHeaderText(null);
                        mensaje.setGraphic(null);
                        mensaje.setContentText("GANASTE!!!");
                        mensaje.showAndWait();
                    
                        Stage stage=new Stage();
                        nuevo(stage);
                        loteria.getScene().getWindow().hide();
                        
                    }
                    
                });
                
                
                
                abajo.getChildren().add(loteria);
                abajo.setAlignment(Pos.CENTER);
                
                contenedor.setBottom(abajo);
                
                
                
                
        } catch (Exception ex) {
            System.out.println("NO SE PUDO CARGAR LA INFORMACION");;
        }


    }
    
    /**
     * Recupera el objeto de tipo Partida que tiene la configuracion temporal del juego
     */
    public Partida recuperarConfig() throws Exception{
        ObjectInputStream saut=new ObjectInputStream(new FileInputStream("src/partidas/config.ser"));
        Partida ge=(Partida) saut.readObject();
        return ge;
    }
    
     public  void sonido(){
        try{
        String path="src/audios/button3.wav";
        Media media = new Media(new File(path).toURI().toString());

        MediaPlayer cad=new MediaPlayer(media);
        
        cad.play();
        System.out.println("--------------\nSE REPRODUCE");
        }
        catch(Exception e){
            System.out.println("NO SE REPRODUCE");
        }
    }
     
     public boolean verificarAlineacion(){
         return true;
     }
     
     
     public void nuevo(Stage stage){
        VntInicio inicio=new VntInicio();
        
        Scene escena=new Scene(inicio.getRoot(),900,500);

        escena.getStylesheets().add("/css/estiloInicio.css");
        
        stage.setScene(escena);
        stage.setTitle("Loteria Mexicana");
//        stage.setResizable(false);
        stage.show();
     }
}
