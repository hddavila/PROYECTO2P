
package modelo;

/**
 *
 * @author Hugo Davila
 */

import java.io.Serializable;
import javafx.scene.image.ImageView;


public class Carta implements Serializable {
    
    private String nombre;
    private int numero;
    private ImageView imagen;
    
    
    /**Crea una carta con nombre,numero e imagen
     *@param nombre nombre de la carta
     * @param numero numero de la carta
     * @param imagen ImageView de la imagen de la carta
     */
    public Carta(String nombre,int numero,ImageView imagen){
        this.nombre=nombre;
        this.numero=numero;
        this.imagen=imagen;
    }
    
    /**
     * Recupera el nombre de la carta
     * @return nombre nombre de la carta
     */
    public String getNombre() {
        return nombre;
    }
    
    /**Recuper el numero de la carta
     * 
     * @return numero numero de la carta
     */
    public int getNumero() {
        return numero;
    }
    
    /**Recupera el ImageView de la carta
     * 
     * @return imagen Imagen de la carta
     */
    public ImageView getImagen() {
        return imagen;
    }

    
    
    
}

