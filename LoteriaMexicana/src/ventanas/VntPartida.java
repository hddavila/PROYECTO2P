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
import hilos.CronometroFull;
import hilos.HiloOponente;
import javafx.scene.layout.GridPane;
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
    

    
    public static Button btnMaquina;
    
    
    
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
        izquierda=new VBox(50);
        centro=new HBox();
        derecha=new VBox(10);
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
                Button btnFinalizar=new Button("Finalizar partida");
                btnFinalizar.setAlignment(Pos.CENTER);
                btnFinalizar.setOnAction(e->{
                    crono.pararCronometro();
                    mazo.parar();
                    Stage stage=new Stage();
                    nuevo(stage);
                    btnFinalizar.getScene().getWindow().hide();
                });
                HBox cnt_finalizar=new HBox();
                cnt_finalizar.setAlignment(Pos.CENTER);
                cnt_finalizar.getChildren().add(btnFinalizar);
                derecha.getChildren().addAll(cuadro,mazo.getImgCarta(),cnt_finalizar);
            
            //IZQUIERDA
                //panel alineacion
                PanelAlineacion alineacion=new PanelAlineacion(config.getAlineacion());
                BorderPane panelAlineacion=alineacion.getRoot();
                panelAlineacion.setMinWidth(300);
                panelAlineacion.setMaxWidth(50);
                panelAlineacion.setId("alineacion");
                izquierda.getChildren().add(panelAlineacion);
                
                //panel oponentes                
                for(int i=0;i< config.getCantidadOponentes();i++){
                    Tablero tb_oponente=new Tablero(guardadas,true,config.isOculto());
                    //establecer que el tablero es de un bot
                    tb_oponente.setMaquina(true);
                    System.out.println("oculto"+config.isOculto());
                    tb_oponente.getRoot().setDisable(true);
                    tb_oponente.getRoot().setScaleX(0.30);
                    tb_oponente.getRoot().setScaleY(0.30);

                    btnMaquina=new Button();
                    
                    btnMaquina.setOnAction(e->{
                        if(verificarAlineacion(tb_oponente.getArrAlineacion())){
                            System.out.println("LA MAQUINA GANOOOOOOOOOO");
                            crono.pararCronometro();
                            mazo.parar();
                            perdida();
                            
                            perder();
                        }
                    });
                    
                   
                    
                    
                    
                    
                    
                    HBox cnt_oponente=new HBox();
                    cnt_oponente.setAlignment(Pos.CENTER);
                    cnt_oponente.setMinWidth(200);
                    cnt_oponente.setMaxWidth(200);
                    cnt_oponente.setMinHeight(200);
                    cnt_oponente.setMaxHeight(200);
                    cnt_oponente.getChildren().add(tb_oponente.getRoot());
                    izquierda.getChildren().add(cnt_oponente);
   
                }
               
                
                
                
            //CENTRO
                Tablero tablero=new Tablero(guardadas,false,config.isOculto());
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
                    if(verificarAlineacion(tablero.getArrAlineacion())==true){
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
                        Alert mensaje=new Alert(AlertType.ERROR);

                        mensaje.setTitle("ERROR");
                        mensaje.setHeaderText(null);
                        mensaje.setContentText("NO CUMPLE CON LA ALINEACION REQUERIDA!");
                        mensaje.showAndWait();
                    }
                    
                });

                centro.getChildren().add(loteria);
                centro.setAlignment(Pos.CENTER);
                
   
        } catch (Exception ex) {
            System.err.println("NO SE PUDO CARGAR LA INFORMACION");
            System.out.println(ex.getMessage());
        }


    }
    
    /**Recupera el objeto de tipo Partida que tiene la configuracion temporal del juego
     * @return ge Objeto con la informacion de la Partida recuperado del archivo serializado
     * @throws Exception Excepcion cuando no puede obtener la configuracion inicial generada
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
     
     
     
     /**Verifica los tableros cumplan con la alineacion requerida
      * 
      * @return valor true(si cumple con la alineacion) false(si no cumple con la alineacion)
      */
     public boolean verificarAlineacion(int[][] arrAlineacion){
         boolean cumple=false;
         
         switch(config.getAlineacion()){
            case FILA:
                //verificar primera fila
                if(arrAlineacion[0][0]==1 && arrAlineacion[0][1]==1 && arrAlineacion[0][2]==1 && arrAlineacion[0][3]==1){
                    cumple=true;
                    break;
                }
                
                //verificar segunda fila
                else if(arrAlineacion[1][0]==1 && arrAlineacion[1][1]==1 && arrAlineacion[1][2]==1 && arrAlineacion[1][3]==1){
                    cumple=true;
                    break;
                }
                
                
                //verificar tercera fila
                else if(arrAlineacion[2][0]==1 && arrAlineacion[2][1]==1 && arrAlineacion[2][2]==1 && arrAlineacion[2][3]==1){
                    cumple=true;
                    break;
                }
                
                //verificar cuarta fila
                else if(arrAlineacion[3][0]==1 && arrAlineacion[3][1]==1 && arrAlineacion[3][2]==1 && arrAlineacion[3][3]==1){
                    cumple=true;
                    break;
                }
                
                break;
            case COLUMNA:
                //verificar primera columna
                if(arrAlineacion[0][0]==1 && arrAlineacion[1][0]==1 && arrAlineacion[2][0]==1 && arrAlineacion[3][0]==1){
                    cumple=true;
                    break;
                }
                //verificar segunda columna
                else if(arrAlineacion[0][1]==1 && arrAlineacion[1][1]==1 && arrAlineacion[2][1]==1 && arrAlineacion[3][1]==1){
                    cumple=true;
                    break;
                }
                //verificar tecera columna
                else if(arrAlineacion[0][2]==1 && arrAlineacion[1][2]==1 && arrAlineacion[2][2]==1 && arrAlineacion[3][2]==1){
                    cumple=true;
                    break;
                }
                //verificar cuarta columna
                else if(arrAlineacion[0][3]==1 && arrAlineacion[1][3]==1 && arrAlineacion[2][3]==1 && arrAlineacion[3][3]==1){
                    cumple=true;
                    break;
                }
                
                break;
            case ESQUINASUPIZQUIERDA:
                //verificar esquina superior izquierda
                if(arrAlineacion[0][0]==1 && arrAlineacion[0][1]==1 && arrAlineacion[1][0]==1 && arrAlineacion[1][1]==1){
                    cumple=true;
                    break;
                }    
               
                break;
            case ESQUINASUPDERECHA:
                //verificar esquina superior derecha
                if(arrAlineacion[0][2]==1 && arrAlineacion[0][3]==1 && arrAlineacion[1][2]==1 && arrAlineacion[1][3]==1){
                    cumple=true;
                    break;
                }    
               
                break;
            case ESQUINAINFIZQUIERDA:
                  //verificar esquina inferior izquierda
                if(arrAlineacion[2][0]==1 && arrAlineacion[2][1]==1 && arrAlineacion[3][0]==1 && arrAlineacion[3][1]==1){
                    cumple=true;
                    break;
                }    
               
               
                break;
            case ESQUINAINFDERECHA:
                 //verificar esquina inferior derecha
                if(arrAlineacion[2][2]==1 && arrAlineacion[2][3]==1 && arrAlineacion[3][2]==1 && arrAlineacion[3][3]==1){
                    cumple=true;
                    break;
                }    
                
                break;
            case CUALQUIERESQUINA:
                //verificar esquina superior izquierda
                if(arrAlineacion[0][0]==1 && arrAlineacion[0][1]==1 && arrAlineacion[1][0]==1 && arrAlineacion[1][1]==1){
                    cumple=true;
                    break;
                }
                //verificar esquina superior derecha
                else if(arrAlineacion[0][2]==1 && arrAlineacion[0][3]==1 && arrAlineacion[1][2]==1 && arrAlineacion[1][3]==1){
                    cumple=true;
                    break;
                }
                 //verificar esquina inferior izquierda
                else if(arrAlineacion[2][0]==1 && arrAlineacion[2][1]==1 && arrAlineacion[3][0]==1 && arrAlineacion[3][1]==1){
                    cumple=true;
                    break;
                }
                 //verificar esquina inferior derecha
                else if(arrAlineacion[2][2]==1 && arrAlineacion[2][3]==1 && arrAlineacion[3][2]==1 && arrAlineacion[3][3]==1){
                    cumple=true;
                    break;
                }    
                
               
                break;
         }
         
         return cumple;
     }
     
     /**Genera una nueva ventana de inicio 
      * 
      * @param stage Stage vacio para contener a la nueva ventana de inicio
      */
     public void nuevo(Stage stage){
        VntInicio inicio=new VntInicio();
        
        Scene escena=new Scene(inicio.getRoot(),900,500);

        escena.getStylesheets().add("/css/estiloInicio.css");
        
        stage.setScene(escena);
        stage.setTitle("Loteria Mexicana");
//        stage.setResizable(false);
        stage.show();
     }
     
     /**Genera el arreglo con los Objetos ImageView de las cartas recortadas de los arhivos con las cartas
      * @param ruta ruta donde se encuentra la imagen de cartas a recortar
      * @param ancho ancho individual de cada carta a recortar
      * @param alto alto individual de cada carta a recortar
      * @throws Exception Excepcion de algun fallo al generar las cartas
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
     
     /**Crea un archivo de objeto de tipo Cartas con la informacion del arreglo de 
      * las imagenes de las cartas y el archivo con la descripcion de estas
      * 
      * @param ruta ruta del archivo que dispone de la descripcion de las carta en orden (numero,nombre)
      * @param arrCartas arreglo de las objetos ImageView de la cartas a ser etiquetadas
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
     
     public void perder(){
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

        
        
     }
}
