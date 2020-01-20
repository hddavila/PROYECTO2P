/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
    

/**
 *
 * @author Doménica Briones
 */
public class Tiempo implements Serializable{
    Date fecha;
    String duracion;

    public Tiempo(Date fecha, String duracion) {
        this.fecha = fecha;
        this.duracion = duracion;
    }
    
    public Tiempo(){
        
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

    @Override
    public String toString() {
        String[] msjFecha=fecha.toString().split(" ");
        String dia=msjFecha[2];
        String mes=msjFecha[1];
        String anio=msjFecha[5];
        
        return dia+"/"+mes+"/"+anio+" - " + duracion;
    }
    
    
}
