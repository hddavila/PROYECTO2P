
package modelo;

import java.util.ArrayList;
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
                root.add(cartas_Jug.get(row+column).getImagen(),column, row);
            }
        }
    }

    public GridPane getRoot() {
        return root;
    }
    
    
    
}
