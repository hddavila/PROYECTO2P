
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

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public ImageView getImagen() {
        return imagen;
    }

    
    
    
}

