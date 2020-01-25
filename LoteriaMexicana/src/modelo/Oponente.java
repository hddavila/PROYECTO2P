///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package modelo;
//import java.util.ArrayList;
//
/**
 *
 * @author Doménica Briones
 */
//public class Oponente extends Jugador{
//    ArrayList<Carta> anunciadas;
//    ArrayList<Carta> cartas_jug;
//    public Oponente(){
//        super();
//    }
//    
//    /*
//    * El método Jugar recibe la carta que acaba de salir del mazo 
//    * y la compara con las cartas que se encuentran en el tablero, 
//    * si la encuentra, la selecciona
//    */
//    public boolean Jugar(){
//        for (i=0;i<=cartas_jug.lenght();i++){
//            if(anunciadas[-1].equals(carta_jug[i])){
//                //System.out.println("Carta seleccionada");
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//    }
//    
//    /*
//    * El método ganar llama al botón "LOTERÍA" para presionar
//    */
//    public void Ganar(){
//        
//        
//    }
//}

package modelo;

/**
 *
 * @author Gabriel Castro
 */

public class Oponente extends Jugador {
    private static int n = 1;
    private String nombre  = "CPU" + n;
    
/**
 *
 * Constructor que enumera los objetos creados.
 */    
    public Oponente() {
        super();
        n += 1;
    }
    
/**
 *
 * Metodo toString sobreescrito.
 */    
    @Override
    public String toString(){
        return nombre;
    }
    

}