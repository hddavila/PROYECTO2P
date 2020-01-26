
package modelo;

import hilos.HiloIncorrecto;
import hilos.HiloOponente;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ventanas.VntPartida;

/**
 *
 * @author Gabriel Castro
 */
public class Tablero {
    private ArrayList<Carta> cartas_Jug;
    private GridPane root;
    private Map<Integer,ImageView> mapa;
    private boolean condicion;
    private boolean maquina;
    private boolean oculto;
    
    private int[][] arrAlineacion;
        
    /**Constructor que genera el objeto tablero
     * @param cartas_Jug cartas existentes del mazo
     * @param maquina condicion que establece si el tablero es de un oponente jugador=false oponente=true
     * @param oculto condicion que establece si el tablero generado se muestra oculto o no
     */
    
    public Tablero(ArrayList<Carta> cartas_Jug,boolean maquina,boolean oculto){
        root = new GridPane();
//        root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);
        
        root.setVgap(-60);
        root.setHgap(-50);
        this.cartas_Jug = cartas_Jug;
        this.condicion=true;
        this.mapa=new HashMap<Integer,ImageView>();
        this.maquina=maquina;
        this.oculto=oculto;
        
        this.arrAlineacion=new int[4][4];

        
        crearTablero();
    }
    
    /**Devuelve el contenedor principal del objeto Tablero
     * 
     * @return GridPane del tablero generado
     */
     public GridPane getRoot() {
        return root;
    }
    
    
    /**
     * Genera el contenido del tablero con las cartas para el Jugador
     */
    public void crearTablero(){
          
      ArrayList<Integer> salieron=new ArrayList<>();
      salieron.clear();
      
      //LLena el GridPane las imagenes de los objetos Carta escojidos de manera aleatoria del arreglo
      for (int fila=0;fila<4;fila++){
          for(int columna=0;columna<4;columna++){
              Random posicion=new Random();
              Integer pos=posicion.nextInt(cartas_Jug.size());
              if(salieron.contains(pos)==false){
                  //contenedor para posicionar la tarjeta y el posible frijol
                  StackPane tarjeta=new StackPane();
                  //obtenet la carta de la posicion aleatoria
                  Carta carta=cartas_Jug.get(pos);
                  ImageView agregar=(carta).getImagen();
                  agregar.setScaleX(0.80);
                  agregar.setScaleY(0.80);
                  
                  /*si la visibilidad es verdadera entonces no se ocultan las cartas
                  de otra forma se sustituyen las cartas por bloques blancos*/
                  if(oculto && maquina){
                      StackPane cartaInvisible=new StackPane();
                      Rectangle bloqueB=new Rectangle(178,256);
                      bloqueB.setFill(Color.WHITE);
                      bloqueB.setStrokeWidth(5);
                      bloqueB.setStroke(Color.BLACK);
                      Label lblLeyenda=new Label("CARTA");
                      lblLeyenda.setStyle("-fx-font-size: 45;-fx-font-weight: bold;");
                      cartaInvisible.getChildren().addAll(bloqueB,lblLeyenda);
                      cartaInvisible.setScaleX(0.80);
                      cartaInvisible.setScaleY(0.80);
                      
                      tarjeta.getChildren().add(cartaInvisible);
                      root.setVgap(-35);
                      root.setHgap(-25);
                  }else{
                      tarjeta.getChildren().add(agregar);
                  }
                  
                  
                 
                 int posFila=fila;
                 int posColumna=columna;
                  //declarar la accion de la carta
                  agregar.setOnMouseClicked(e->{
                      
                      verificarCarta(carta,mapa);
                      //si la condicion es correcta añade el frijol a la carta y esta se bloquea
                      if(condicion){
                          //agregar el frijol encima
                          try{
                              ImageView frijol=new ImageView("/recursos/frijol3.png");
                              frijol.setFitWidth(50);
                              frijol.setFitHeight(50);
                              frijol.setStyle("-fx-opacity:0.90;");
                              tarjeta.getChildren().add(frijol);
                              arrAlineacion[posFila][posColumna]=1;
                              
                          }catch(Exception m){
                              System.out.println("No se pudo agregar el frijol");
                          }
                          //bloquear el stackpane luego de posicionar el frijol
                          agregar.setStyle("-fx-opacity:0.3;");
                          tarjeta.setDisable(true);
                      }
                      //si la condicion es incorrecta se agrega una image de una equis por 2 segundos y luego se quita
                      else{
                          
                          HiloIncorrecto fail = new HiloIncorrecto();
                          tarjeta.getChildren().add(fail.getIncorrecto());
                          fail.start();
                          
                           
                      }
                      
                  });
                  
                  
                  //si maquina=true entonces este tablero pertenece a un bot que juega solo
                  if(maquina){
                     HiloOponente oponente= new HiloOponente(carta,arrAlineacion,posFila,posColumna);
                     oponente.jugar();
                     tarjeta.getChildren().add(oponente.getFrijol());
                     arrAlineacion=oponente.getArrAlineacion();
                  }
                  
                  //agregar el contenedor tarjeta
                  root.add(tarjeta,columna,fila);
                  salieron.add(pos);
              }
              else{
                  columna--;
              }
          }
      }

    }
    
     
    /** Verfica que la carta ingresada se encuentre en el mapa de las cartas ya anunciadas
     * 
     * @param carta Objeto carta del cual usando su numero compramos que sea una clave existente del mapa
     * de cartas ya anunciadas
     * @param mapa mapa de las cartas ya anunciadas
     */
    public void verificarCarta(Carta carta,Map<Integer,ImageView> mapa){
        if(mapa.containsKey(carta.getNumero())){
            condicion=true;
        }
        else{
            condicion=false;
        }
        
    }

    
    public void setMapa(Map<Integer, ImageView> mapa) {
        this.mapa = mapa;
    }

    public void setMaquina(boolean maquina) {
        this.maquina = maquina;
    }

    public void setOculto(boolean oculto) {
        this.oculto = oculto;
    }

    public int[][] getArrAlineacion() {
        return arrAlineacion;
    }
    
    
    
    
}
