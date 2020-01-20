/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author Gabriel
 */
public class HiloIncorrecto extends Thread{
    private ImageView incorrecto;
    private Thread hilo;
    
    
    public HiloIncorrecto(){
        this.hilo = new Thread(this);
        this.incorrecto=new ImageView();
        incorrecto.setFitWidth(50);
        incorrecto.setFitHeight(50);
    }
    
    
    @Override
    public void run(){
  
        try{
            sonido();
            Image mostrar=new Image("/recursos/salir.png");
            incorrecto.setImage(mostrar);
            
            hilo.sleep(500);
            incorrecto.setImage(null);
            }
        catch(Exception m){
                System.out.println("ERROR AL PONER LA EQUIS");
                System.out.println(m.getMessage());
            }
    }

    public ImageView getIncorrecto() {
        return incorrecto;
    }
    
    
}
