
package modelo;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
                  Carta carta=cartas_Jug.get(pos);
                  ImageView agregar=(carta).getImagen();
                  agregar.setScaleX(0.80);
                  agregar.setScaleY(0.80);
                  
                  //declarar la accion de la carta
                  //ESTA ES UNA PRUEBA DE LA ACCION DE LA IMAGEN (NO ES LA FUNCION FINAL)
                  agregar.setOnMouseClicked(e->{
                      Alert mensaje=new Alert(AlertType.INFORMATION);
                      mensaje.setHeaderText(null);
                      mensaje.setContentText(carta.getNumero()+" "+carta.getNombre());
                      mensaje.showAndWait();
                      
                  });
                  
                  
                  root.add(agregar,columna,fila);
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
