
package modelo;

import javafx.scene.control.Label;

/**
 *
 * @author Gabriel 
 */
public class Cronometro extends Thread {
    private Thread hilo;
    private boolean activo;
    private Label show;
    private double now;
    
    
/**
 *
 * Constructor que setea el cronometro como inactivo, inicia en 3 y 
 * crea la Label para mostrar el tiempo.
 */
    public Cronometro(){
        this.activo = false;
        this.now = 3; 
        show = new Label(Double.toString(now));
    }
    
 /**
 *
 * Funcion para iniciar el cronometro, cambia el estado a activo, crea el hilo
 * y lo inicia.
 */
    
    public void inciar(){
        this.activo = true;
        hilo = new Thread(this);
        hilo.start();
    }
 /**
 *
 * Funcion para parar el cronometro, cambia el estado a inactivo y detienen el
 * hilo.
 */
    
    public void parar(){
        this.activo  = false;
        
    }
/**
 *
 * Funcion para reiniciar el cronometro, settea el tiempo en 3.
 */
    public void reiniciar(){
        this.now = 3;
    }
    
/**
 *
 * Metodo run sobreescrito.
 * Mientras el estado del cronometro sea activo manda al hilo a dormir por 0.1 s
 * y verifica el tiempo.
 * Si el tiempo es 2 cambia el label a 2, si es 1 a 1 y si es 0 a 0.
 */
    @Override
    public void run(){
        
        try{
            while(activo){
                
                hilo.sleep(100);
                now -= 0.1;
                if (now == 2.0){
                    show = new Label(Double.toString(now));
                }
                else if(now == 1.0){
                    show = new Label(Double.toString(now));
                }
                else if(now == 0.0){
                    show = new Label(Double.toString(now));
                }
            }
        }
        catch(Exception e){
            
        }
        
    }
    
    public double getTime(){
        return this.now;
    }

    public Label getShow(){
        return this.show;
    }
    
}
