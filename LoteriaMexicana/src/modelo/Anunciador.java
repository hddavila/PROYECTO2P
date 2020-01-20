
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Anunciador extends Thread{
    private Thread hilo;
    private ArrayList<Carta> mazo;
    private Carta cartaActual;
    
    public Anunciador(ArrayList<Carta> mazo){
        this.hilo = new Thread(this);
        this.mazo = mazo;
        
    }
    
    @Override
    public void run(){
        
    }
    
    public Carta getCartaActual(){
        return cartaActual;
    }
}
