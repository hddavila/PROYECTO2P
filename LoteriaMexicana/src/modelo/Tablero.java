
package modelo;

import hilos.HiloIncorrecto;
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
    
    /**
     * 
     * Construcor que recibe un arreglo de objetos de tipo Carta
     */
    public Tablero(ArrayList<Carta> cartas_Jug){
        root = new GridPane();
//        root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);
        
        root.setVgap(-60);
        root.setHgap(-50);
        
        this.cartas_Jug = cartas_Jug;
        this.condicion=true;
        this.mapa=new HashMap<Integer,ImageView>();
        crearTablero();
    }
    
    
     public GridPane getRoot() {
        return root;
    }
    
    
    /**
     * Genera el contenido del tablero con las cartas para el Jugador
     */
    public void crearTablero(){
          
      ArrayList<Integer> salieron=new ArrayList<>();
      
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
                  tarjeta.getChildren().add(agregar);
                  
                  //declarar la accion de la carta
                  //ESTA ES UNA PRUEBA DE LA ACCION DE LA IMAGEN (NO ES LA FUNCION FINAL)
                  agregar.setOnMouseClicked(e->{
                      
                      verificarCarta(carta.getNumero(),mapa);
                      //si la condicion es correcta añade el frijol a la carta y esta se bloquea
                      if(condicion){
                          //agregar el frijol encima
                          try{
                              ImageView frijol=new ImageView("/recursos/frijol3.png");
                              frijol.setFitWidth(50);
                              frijol.setFitHeight(50);
                              frijol.setStyle("-fx-opacity:0.90;");
                              tarjeta.getChildren().add(frijol);   
                          }catch(Exception m){
                              System.out.println("No se pudo agregar el frijol");
                          }
                          //bloquear el stackpane luego de posicion el frijol

                          agregar.setStyle("-fx-opacity:0.3;");
                          tarjeta.setDisable(true);

//                          Alert mensaje=new Alert(AlertType.INFORMATION);
//                          mensaje.setHeaderText(null);
//                          mensaje.setContentText(carta.getNumero()+" "+carta.getNombre());
//                          mensaje.showAndWait();
                      }
                      //si la condicion es incorrecta se agrega una image de una equis por 2 segundos y luego se quita
                      else{
                          
                          HiloIncorrecto fail = new HiloIncorrecto();
                          tarjeta.getChildren().add(fail.getIncorrecto());
                          fail.start();
                          
                           
                      }
                      
                  });
                  
                  //agregar el contenedor tarjeta
                  root.add(tarjeta,columna,fila);
                  salieron.add(pos);
              }
              else{
                  columna--;
              }
          }
      }
//                
    }
    
     

    public void verificarCarta(Integer numero,Map<Integer,ImageView> mapa){
        if(mapa.containsKey(numero)){
            condicion=true;
        }
        else{
            condicion=false;
        }
        
    }

    public void setMapa(Map<Integer, ImageView> mapa) {
        this.mapa = mapa;
    }
    

   
    
}
