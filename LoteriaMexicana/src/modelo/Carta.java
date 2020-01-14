
package modelo;

/**
 *
 * @author Hugo Davila
 */

import java.io.Serializable;
import javafx.scene.image.ImageView;

/**
 *
 * Clase con la cual podemos instanciar objetos de tipo Carta
 */
public class Carta implements Serializable {
    
    private String nombre;
    private int numero;
    ImageView imagen;
    
    
    /**
     *
     * Crea una carta con nombre,numero e imagen
     */
    public Carta(String nombre,int numero,ImageView imagen){
        this.nombre=nombre;
        this.numero=numero;
        this.imagen=imagen;
    }
    
}

