/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import hilos.HiloMusica;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modelo.Alineacion;
import modelo.Carta;
import modelo.Partida;
import modelo.Tiempo;


/**
 *
 * @author Hugo Davila
 */
public class VntInicio {
    
    private StackPane root;
    private final double width = 900;
    private final double height = 500;
    public static HiloMusica musica; 
    

     
    public VntInicio(){
        createContent();
    }
    
    public StackPane getRoot(){
        return root;
    }
    
    /**
     *
     * Crea el contenido de la ventana Inicio
     */
    public void createContent(){
        root=new StackPane();
        root.setId("principal");
        
        
        //declarar contenedor
        VBox columna=new VBox(40);
        
        StackPane banner=new StackPane();
        Label titulo=new Label("LOTERIA");
        Label titulo2=new Label("MEXICANA");
        Rectangle cuadro=new Rectangle(750,100);
        
        cuadro.setId("cuadro");
        
        //contenedor de los dos labels que forman el titulo
        HBox titulos=new HBox(25);
        titulos.getChildren().addAll(titulo,titulo2);
        titulo.setId("loteria");
        titulo2.setId("mexicana");
        titulos.setAlignment(Pos.CENTER);
        
        banner.getChildren().addAll(cuadro,titulos);
        
        //contenedor de los botones
        VBox cnt_botones=new VBox(15);
        Button btn_nuevo=new Button("Nuevo Juego");
        Button btn_config=new Button("Configuraciones");
        Button btn_report=new Button("Reporte");
        cnt_botones.getChildren().addAll(btn_nuevo,btn_config,btn_report);
        cnt_botones.setAlignment(Pos.CENTER);
        
        columna.getChildren().addAll(banner,cnt_botones);
        columna.setAlignment(Pos.CENTER);
        
        
        try{
            //establecer la imagen de fondo
            ImageView fondo=new ImageView(new Image("/recursos/background2.png"));
            fondo.setFitHeight(height);
            fondo.setPreserveRatio(true);
            root.getChildren().add(fondo);
        }
        catch(Exception e){
            System.out.println("->ERROR AL CARGAR LA IMAGEN");
        }
        
        root.getChildren().addAll(columna);
        
        //evento del boton Nuevo Juego
        btn_nuevo.setOnAction(e->{
              sonido();
              VntNuevo nuevo=new VntNuevo();
              nuevo.getSalir().setOnMouseClicked(k->{
                  sonido();
                  nuevo.getSalir().getScene().setRoot(root);
              });
              root.getScene().setRoot(nuevo.getBackRoot());
        });
        
        
        //evento del boton Configuraciones
        btn_config.setOnAction(e->{
            sonido();
            VntConfiguracion config=new VntConfiguracion();
            config.getBack().setOnMouseClicked(m->{
                sonido();
                config.actualizarConfig();
                config.getBack().getScene().setRoot(root);
            });
            root.getScene().setRoot(config.getRoot());
        });
        
        //evento del boton Reporte
        btn_report.setOnAction(e->{
            sonido();
            VntReporte reporte=new VntReporte();
            reporte.getBack().setOnMouseClicked(m->{
            sonido();
            reporte.getBack().getScene().setRoot(root);
            });
            root.getScene().setRoot(reporte.getRoot());
            
            
        });
        
        //reproducir musica de fondo
        musica=new HiloMusica();
        musica.iniciar();
        
        
        //generar configuracion incial por defecto;
        crearConfig();
        
        
        
    }
    /**
     *
     * Reproduce el sonido del boton
     */
    public static void sonido(){
        try{
        String path="src/audios/button2.wav";
        Media media = new Media(new File(path).toURI().toString());

        MediaPlayer cad=new MediaPlayer(media);
        
        cad.play();
        System.out.println("--------------\nSE REPRODUCE");
        }
        catch(Exception e){
            System.out.println("NO SE REPRODUCE");
        }
    }
    
    
    /**
     * Genera el archivo con la configuracion incial por defecto al abrir el juego
     */
    public void crearConfig(){
       
        try {
            ObjectOutputStream fout=new ObjectOutputStream(new FileOutputStream("src/partidas/config.ser"));
            
            
            //generar alineacion
            ArrayList<Alineacion> arreglo=new ArrayList<>();
            arreglo.add(Alineacion.COLUMNA);
            arreglo.add(Alineacion.FILA);
            arreglo.add(Alineacion.CUALQUIERESQUINA);
            arreglo.add(Alineacion.ESQUINAINFDERECHA);
            arreglo.add(Alineacion.ESQUINAINFIZQUIERDA);
            arreglo.add(Alineacion.ESQUINASUPDERECHA);
            arreglo.add(Alineacion.ESQUINASUPIZQUIERDA);
            
            Random aleatorio=new Random();
            int indice=aleatorio.nextInt(arreglo.size());
           
            Partida partida=new Partida("",arreglo.get(indice),null,1); //(nombre inicial, alineacion aleatoria, tiempo nulo (aun no se ha jugado),cantidad oponentes por defecto)
 
            fout.writeObject(partida);
            
            fout.close();
            System.out.println("CONFIGURACION GENERADA");
            
        } catch (Exception ex) {
            System.err.println("CONFIGURACION NO GENERADA");
        }
        
    }
     
}
