
package modelo;

import java.util.ArrayList;
import java.util.Random;
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
        root.setHgap(0.10);
        root.setVgap(0.10);
        
        root.setVgap(5);
        root.setHgap(5);
        this.cartas_Jug = cartas_Jug;
        crearTablero();
    }
    
    public void crearTablero(){
          
          ArrayList<Integer> salieron=new ArrayList<>();
          
//          root.add((cartas_Jug.get(0)).getImagen(),0,0);
//          
          
          for (int fila=0;fila<4;fila++){
              for(int columna=0;columna<4;columna++){
                  Random posicion=new Random();
                  Integer pos=posicion.nextInt(cartas_Jug.size());
                  if(salieron.contains(pos)==false){
                      ImageView agregar=(cartas_Jug.get(pos)).getImagen();
                      agregar.setScaleX(1);
                      agregar.setScaleY(1);
                      root.add(agregar,columna,fila);
                      salieron.add(pos);
                  }
                  else{
                      columna++;
                  }
              }
          }
//                  if(salieron.contains(pos)){
//                      columna++;
//                      
//                  }
//                  else{
//                      root.add((cartas_Jug.get(pos)).getImagen(),columna,fila);
//                      salieron.add(pos);
////                  }
//              }
          
////          root.add(new Label("hola"),0,0);
    }
    
    public void anunciar(){
        
        
    }

    public GridPane getRoot() {
        return root;
    }
    
    
    
}
