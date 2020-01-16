/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

<<<<<<< HEAD
/**
 *
 * @author Usuario
 */
class Tiempo {
    
=======
import java.util.Date;

/**
 *
 * @author Doménica Briones
 */
public class Tiempo {
    Date fecha;
    String duracion;

    public Tiempo(Date fecha, String duracion) {
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
>>>>>>> 42674e023767d27899f21f511bc4f4727b0bc629
}
