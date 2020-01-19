
package modelo;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Gabriel
 */
public class CronometroFull extends Thread {
    private Thread hilo;
    private String time;
    private boolean activo;
    private Label show;
    
    public CronometroFull(){
        this.activo = false;
        this.time = "00:00:00:00";
        this.show = new Label(time);
        this.hilo = null;
    }
    
    @Override
    public void run(){
        int horas = 0, minutos = 0, segundos = 0, milesimas = 0;
        String hor = "", min = "", seg = "", mil = "";
        
        try{
            while(activo){
                hilo.sleep(5);
                milesimas += 5;
                if (milesimas == 1000){
                    milesimas = 0;
                    segundos += 1;
                    if(segundos == 60){
                        segundos = 0;
                        minutos += 1;
                        if(minutos == 60){
                            minutos = 0;
                            horas++;
                        }
                        
                    }
                }
                if (horas < 10) hor = "0" + horas;
                if( minutos < 10 ) min = "0" + minutos;
                else min = Integer.toString(minutos);
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = Integer.toString(segundos);
                if( milesimas < 10 ) mil = "00" + milesimas;
                else if( milesimas < 100 ) mil = "0" + milesimas;
                else mil = Integer.toString(milesimas);
                 
                
                time =  hor + ":" + min + ":" + seg + ":" + mil ;
                Platform.runLater(()->show.setText(time));
            }
        }
        catch (Exception e){
            time = "00:00:00:00";
        }
    }
    
    public void iniciarCronometro(){
        activo = true;
//        hilo = new Thread(this);
//        hilo.start();
        
        if(hilo == null){
            hilo = new Thread(this);
            hilo.start();
        }
    }
    
    public void pararCronometro(){
        activo = false;
    }
    
    public void reiniciarCronometro(){
        time = "00:00:00:00";
        show.setText(time);
    }
    
    public Label getLabel(){
        return show;
    }
    

    
}
