
package modelo;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Gabriel Castro
 */
public class Tablero {
    private ArrayList<Carta> cartas_Jug;
    private ArrayList<Carta> anunciadas;
    private GridPane root;
    
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
        crearTablero();
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
                     
                      Alert mensaje=new Alert(AlertType.INFORMATION);
                      mensaje.setHeaderText(null);
                      mensaje.setContentText(carta.getNumero()+" "+carta.getNombre());
                      mensaje.showAndWait();
                      
                  });
                  
                  //agregar el contenedor tarjeta
                  root.add(tarjeta,columna,fila);
                  salieron.add(pos);
              }
              else{
                  columna++;
              }
          }
      }
//                
    }
    
    public void anunciar(){
        
        
    }

    public GridPane getRoot() {
        return root;
    }
    
    
    
}
