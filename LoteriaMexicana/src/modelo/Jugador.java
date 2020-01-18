
package modelo;

/**
 *
 * @author Gabriel Castro
 */
public class Jugador {
    private String nombre;
    private Tablero tablero;
    
    public Jugador(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", tablero=" + tablero + '}';
    }
    
    
}
