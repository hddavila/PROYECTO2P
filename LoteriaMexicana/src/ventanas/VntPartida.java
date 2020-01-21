/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modelo.Carta;
import modelo.Cronometro;
import hilos.CronometroFull;
import modelo.Jugador;
import modelo.Partida;
import paneles.PanelAlineacion;
import static ventanas.VntInicio.sonido;

import modelo.Tablero;
import modelo.Tiempo;
import paneles.PanelMazo;

/**
 *
 * @author Hugo Davila
 */
public class VntPartida {
    
    private BorderPane contenedor;
    private Jugador player;
    private VBox izquierda;
    private HBox centro;
    private VBox derecha;
    
    
    private Partida config;
    private VBox info;
    
    private ArrayList<ImageView> arregloCartas;
    private ArrayList<Carta> guardadas;
   
    
    private Label lblCronometro;
    

    
    PanelMazo mazo;
    
    public VntPartida(){
        arregloCartas=new ArrayList<>();
        guardadas=new ArrayList<>();
        createContent();
    }
    
    public BorderPane getContenedor(){
        return contenedor;
    }
    
    /**
     * Genera el contenido de la ventana Partida
     */
    public void createContent(){
        
       actualizarArreglo();
       crearCartas("src/recursos/cartasloteria.csv", arregloCartas);
       
        //incializar todos lo contenedores
        contenedor=new BorderPane();
        izquierda=new VBox();
        centro=new HBox();
        derecha=new VBox();
        info=new VBox();
        
        contenedor.setCenter(centro);
        contenedor.setRight(derecha);
        contenedor.setLeft(izquierda);
        
        
        
       
         
        try {
            config=recuperarConfig();
            
            //DERECHA
                //informacion del jugador
                StackPane cuadro=new StackPane();
                Rectangle cuadroInfo=new Rectangle(300,100);
                cuadroInfo.setId("cuadroInfo");
                Label nombre=new Label(config.getNombreUsuario());
                nombre.setId("jugador");
                
                CronometroFull crono = new CronometroFull();
                crono.iniciarCronometro();
                lblCronometro=crono.getLabel();
                lblCronometro.setId("cronometro");
                

                info.getChildren().addAll(nombre,lblCronometro);
                info.setAlignment(Pos.CENTER);
                cuadro.getChildren().addAll(cuadroInfo,info);
                
                
                mazo=new PanelMazo(guardadas);
                mazo.anunciar();
                
                
                
                //boton finalizar partida(no se registra el juego porque no se termino)
                Button finalizar=new Button("Finalizar partida");
                finalizar.setAlignment(Pos.CENTER);
                finalizar.setOnAction(e->{
                    Stage stage=new Stage();
                    nuevo(stage);
                    finalizar.getScene().getWindow().hide();
                });
                
                derecha.getChildren().addAll(cuadro,mazo.getImgCarta(),finalizar);
            
            //IZQUIERDA
                //panel alineacion
                PanelAlineacion alineacion=new PanelAlineacion(config.getAlineacion());
                BorderPane panelAlineacion=alineacion.getRoot();
                panelAlineacion.setId("alineacion");
                izquierda.getChildren().add(panelAlineacion);
                
                
                
            //CENTRO
                Tablero tablero=new Tablero(guardadas);
                tablero.setMapa(mazo.getAnunciadas());
                
                centro.getChildren().add(tablero.getRoot());

                //creacion del boton loteria
                Button loteria=new Button("L\nO\nT\nE\nR\nI\nA");
                loteria.setId("loteria");
                loteria.setAlignment(Pos.CENTER);
                loteria.setMinWidth(90);
                loteria.setMaxWidth(30);
                loteria.setMinHeight(600);
                loteria.setMaxHeight(100);
                
                loteria.setOnMouseEntered(e->{
                    loteria.setStyle("-fx-opacity: 1;");
                });
                
                loteria.setOnMouseExited(e->{
                     loteria.setStyle("-fx-opacity: 0.6;");
                });
                
                loteria.setOnAction(e->{
                    sonido();
                    if(verificarAlineacion()==true){
                        crono.pararCronometro();
                        mazo.parar();
                        victoria();
                        Alert mensaje=new Alert(AlertType.INFORMATION);
                        DialogPane dialogPane = mensaje.getDialogPane();
                        dialogPane.getStylesheets().add("/css/estiloMensajes.css");
                        
                        mensaje.setTitle(null);
                        mensaje.setHeaderText(null);
                        mensaje.setGraphic(null);
                        mensaje.setContentText("GANASTE!!!");
                        mensaje.showAndWait();
                        
                        actualizarConfig();
                        guardarPartida();
                        
                        Stage stage=new Stage();
                        nuevo(stage);
                        loteria.getScene().getWindow().hide();
                        
                    }
                    else{
                        crono.pararCronometro();
                        mazo.parar();
                        perdida();
                        Alert mensaje=new Alert(AlertType.INFORMATION);
                        DialogPane dialogPane = mensaje.getDialogPane();
                        dialogPane.getStylesheets().add("/css/estiloMensajes.css");
                        
                        mensaje.setTitle(null);
                        mensaje.setHeaderText(null);
                        mensaje.setGraphic(null);
                        mensaje.setContentText("PERDISTE :(");
                        mensaje.showAndWait();
                    
                        actualizarConfig();
                        guardarPartida();
                        
                        Stage stage=new Stage();
                        nuevo(stage);
                        loteria.getScene().getWindow().hide();
                        
                    }
                    
                });

                centro.getChildren().add(loteria);
                centro.setAlignment(Pos.CENTER);
                
   
        } catch (Exception ex) {
            System.err.println("NO SE PUDO CARGAR LA INFORMACION");
            System.out.println(ex.getMessage());
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
    
    /**
     * Almacena la partida finalizada como objeto Partida en el archivo partida.ser
     */
    public void guardarPartida(){
      try{
        ObjectInputStream saut=new ObjectInputStream(new FileInputStream("src/partidas/partida.ser"));
        ArrayList<Partida> ge=(ArrayList<Partida>) saut.readObject();

        ge.add(recuperarConfig());
               
        System.out.println(ge.toString()); //---------------borrar------------------------------------
        
        saut.close();
        //sobreescribir el archivo 
        ObjectOutputStream fout=new ObjectOutputStream(new FileOutputStream("src/partidas/partida.ser"));
        
        fout.writeObject(ge);
        fout.close();
        
        System.out.println("ARCHIVO ACTUALIZADO CORRECTAMENTE");
  
       }
       catch(Exception e){
           System.err.println("El ARCHIVO NO SE PUDO ACTUALIZAR");
           System.err.println("SE GENERA EL ARCHIVO");
           try{
               ObjectOutputStream saud=new ObjectOutputStream(new FileOutputStream("src/partidas/partida.ser"));
               ArrayList<Partida> partidas=new ArrayList<>();
               partidas.add(recuperarConfig());
               saud.writeObject(partidas);
               saud.flush();
               saud.close();
           }
           catch(Exception j){
               System.err.println("NO SE PUEDO GENERAR EL ARCHIVO DE LAS PARTIDAS");
               System.out.println(j.getMessage());
           }
       }
        
    }
    
    
    
    
    
    
    /**
     * Actualiza el archivo temporal de configuracion con el tiempo jugado y la fecha actual to en un objeto Tiempo
     */
     public void actualizarConfig(){
       try{
        ObjectInputStream saut=new ObjectInputStream(new FileInputStream("src/partidas/config.ser"));
        Partida ge=(Partida) saut.readObject();

        
        //Sobreescribir el Onjeto tiempo al que se genero al terminar la partida
        Date fecha=new Date();
        
        Tiempo tiempo=new Tiempo(fecha,lblCronometro.getText());
        ge.setTiempo(tiempo);
        
                
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
    
    
    
    /**
     * Metodo que reproduce el sonido de button3.wav
     */
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
     
     /**
      * reproduce un sonido de victoria
      */
     public  void victoria(){
        try{
        String path="src/audios/victoria.wav";
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
      * reproduce un sonido de perdida
      */
     public  void perdida(){
        try{
        String path="src/audios/perdida.wav";
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
     
     /**
      * Genera el arreglo de con los Objetos ImageView de las cartas recortadas de los arhivos con las cartas
      *  
      */
     public void obtenerCartas(String ruta,int ancho,int alto)throws Exception{
        
         Image tabla=new Image(ruta);
 
        //crear una representacion en pixeles de la imagen
        PixelReader leer=tabla.getPixelReader();
        
        int posx=0;
        int posy=0;
        
        for(int i=0;i<4;i++){
            posx=0;
            for(int j=0;j<4;j++){
                WritableImage carta=new WritableImage(leer,posx,posy,ancho,alto);  //(pixeles,posx,posy,ancho,alto)
                ImageView cartaImagen=new ImageView(carta);
                arregloCartas.add(cartaImagen);

                posx=posx+ancho;
                }
            
            posy=posy+alto;    
        }   
     }    
     /**
      * Actualiza el arreglo de las Imagenes de la cartas que contienen cartas blancas debido al ultimo archivo de las cartas
      */
     public void actualizarArreglo(){
        
        //genera la lista de cartas a partir de la imagenes
        try{
//            System.out.println("dentro");
            obtenerCartas("/recursos/cartas/cartas1.jpg",178,256); //(ruta,ancho de las cartas px,alto de las cartas px)
            obtenerCartas("/recursos/cartas/cartas2.jpg",178,256);
            obtenerCartas("/recursos/cartas/cartas3.jpg",178,256);
            obtenerCartas("/recursos/cartas/cartas4.jpg",178,256);
//            System.out.println(arregloCartas.size());
//            
            //elimina las cartas blancas del arreglo, estas se generaron por una imagen con pocas cartas     
            for(int i=0;i<10;i++){
                if(arregloCartas.size()!=53)
                    arregloCartas.remove(arregloCartas.size()-1);
            }    
        }
        catch(Exception m)
        {
            System.err.println("NO SE PUDIERON OBTENER LAS CARTAS");
//            System.out.println(m.getMessage());
        }
        
     }
     
     /**
      * Crea un archivo de objeto de tipo Cartas con la informacion del arreglo de las imagenes de las cartas y el archivo con la descripcion de estas
      */
     public void crearCartas(String ruta, ArrayList<ImageView> arrCartas){
        guardadas=new ArrayList<>();
         try{
         //abrir archivo con la descripcion en orden
         FileReader ge=new FileReader(ruta);
         BufferedReader br=new BufferedReader(ge);

         String linea;
         while ((linea=br.readLine())!= null){
             
             String[] info=linea.split(",");
             int numero=Integer.parseInt(info[0]);
             String nombre=info[1];
             
             Carta carta=new Carta(nombre,numero,arrCartas.get(numero-1));
             guardadas.add(carta);
         }

         }
         catch(Exception  m){
             System.err.println("CARTAS NO GENERADAS");
             System.out.println(m.getMessage());
         }
     } 
}
