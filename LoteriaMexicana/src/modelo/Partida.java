/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class Partida implements Serializable{
    String nombreUsuario;
    Alineacion alineacion;
    Tiempo tiempo;
    int cantidadOponentes;
    
    public Partida(){
    
    }
    
    public Partida(String nombreUsuario, Alineacion alineacion, Tiempo tiempo, int cantidadOponentes) {
        this.nombreUsuario = nombreUsuario;
        this.alineacion = alineacion;
        this.tiempo = tiempo;
        this.cantidadOponentes = cantidadOponentes;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Alineacion getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(Alineacion alineacion) {
        this.alineacion = alineacion;
    }

    public Tiempo getTiempo() {
        return tiempo;
    }

    public void setTiempo(Tiempo tiempo) {
        this.tiempo = tiempo;
    }

    public int getCantidadOponentes() {
        return cantidadOponentes;
    }

    public void setCantidadOponentes(int cantidadOponentes) {
        this.cantidadOponentes = cantidadOponentes;
    }

    @Override
    public String toString() {
        return "Partida{" + "nombreUsuario=" + nombreUsuario + ", alineacion=" + alineacion + ", tiempo=" + tiempo + ", cantidadOponentes=" + cantidadOponentes + '}';
    }
    
    
}
