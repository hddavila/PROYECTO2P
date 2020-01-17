
package modelo;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Gabriel Castro
 */
class Tablero {
    private ArrayList<Carta> cartas_Jug;
    private ArrayList<Carta> anunciadas;
    private GridPane root;
    
    
    public Tablero(ArrayList<Carta> cartas_Jug){
        root = new GridPane();
        root.setVgap(5);
        root.setHgap(5);
        this.cartas_Jug = cartas_Jug;
        crearTablero();
    }
    
    
    public void crearTablero(){
        for(int column = 0; column < 4; column++){
            for(int row = 0; row < 4; row++){
                ImageView carta;
                carta = cartas_Jug.get(row+column).getImagen();
                 
                //Funcion al clicar la carta
                carta.setOnMouseClicked(e->{
                    
                    
                });
                root.add(carta,column, row);
                
            }
        }
    }
    
    public void anunciar(){
        
        
    }

    public GridPane getRoot() {
        return root;
    }
    
    
    
}
