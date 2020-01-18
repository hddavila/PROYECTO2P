
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
    
    public Cronometro(){
        this.activo = false;
        this.now = 3; 
        show = new Label(Double.toString(now));
    }
    
    public void inciar(){
        this.activo = true;
        hilo = new Thread(this);
        hilo.start();
    }
    
    public void parar(){
        this.activo  = false;
    }
    
    @Override
    public void run(){
        
        try{
            while(activo){
                
                hilo.sleep(100);
                now -= 0.1;
                if (now == 2.0){
                    show.setText(Double.toString(now));
                }
                else if(now == 1.0){
                    show.setText(Double.toString(now));
                }
                else if(now == 0.0){
                    show.setText(Double.toString(now));
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
