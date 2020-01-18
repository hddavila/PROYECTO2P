/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

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
                  nuevo.getSalir().getScene().setRoot(root);
              });
              root.getScene().setRoot(nuevo.getRoot());
        });
        
        
        //evento del boton Configuraciones
        btn_config.setOnAction(e->{
            sonido();
            configuracion();
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
        musica();
        
        
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
     *
     * Reproduce la musica de fondo
     */
    public void musica(){
        try{
        String path="src/audios/mexico.wav";
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
     *
     * Muestra la ventana configuracion en donde se podra ajustar el juego y al salir de esta ventana se actualizara
     * el archivo que existia por defecto de la configuracion del juego
     */
    public void configuracion(){
        BorderPane config=new BorderPane();
        
        Rectangle base1=new Rectangle(350,100);
        base1.setId("base1");
        
        Rectangle base2=new Rectangle(350,100);
        base2.setId("base2");
        
        StackPane f1=new StackPane();
        StackPane f2=new StackPane();
        
        ImageView back=new ImageView("/recursos/back.png");
        back.setPreserveRatio(true);
        back.setFitWidth(50);

        Label lblConfig=new Label("Configuracion");
        HBox header=new HBox(40);
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(back,lblConfig);
        
        config.setTop(header);
        
        HBox fila1=new HBox(10);
        Label lblCantidad=new Label("Cantidad de oponentes");
        ComboBox cantidad=new ComboBox();
        cantidad.getItems().addAll(0,1,2);
        cantidad.setValue(1);
        
        fila1.getChildren().addAll(lblCantidad,cantidad);
        fila1.setAlignment(Pos.CENTER);
        
        
        HBox fila2=new HBox(10);
        Label lblVisibilidad=new Label("Visibilidad oponentes");
        Button btn_activar=new Button("activar");
        
        fila2.getChildren().addAll(lblVisibilidad,btn_activar);
        fila2.setAlignment(Pos.CENTER);
        
        
        f1.getChildren().addAll(base1,fila1);
        f1.setAlignment(Pos.CENTER);
        
        
        f2.getChildren().addAll(base2,fila2);
        f2.setAlignment(Pos.CENTER);
        
        
        VBox columna2=new VBox(20);
        columna2.setAlignment(Pos.CENTER);
        columna2.getChildren().addAll(f1,f2);
        
        config.setCenter(columna2);
        
        root.getScene().setRoot(config);
        
        
        
        back.setOnMouseClicked(m->{
            sonido();
            config.getScene().setRoot(root);
        });
        
        
        
    }
    
    /**
     * Metodo que genera el archivo con la configuracion incial por defecto al abrir el juego
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
           
            Partida partida=new Partida("",arreglo.get(indice),null);
 
            fout.writeObject(partida);
            
            fout.close();
            System.out.println("CONFIGURACION GENERADA");
            
        } catch (Exception ex) {
            System.err.println("CONFIGURACION NO GENERADA");
        }
        
    }
     
}
