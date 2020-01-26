/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author Gabriel
 */
public class HiloIncorrecto extends Thread{
    private ImageView incorrecto;
    private Thread hilo;
    
    /**Devuelve un hilo que posiciona por tres segundos una equis y luego la quita
     */
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
    
    /**
     * Metodo que reproduce el sonido de error.wav
     */
     public  void sonido(){
        try{
        String path="src/audios/error.wav";
        Media media = new Media(new File(path).toURI().toString());

        MediaPlayer cad=new MediaPlayer(media);
        
        cad.play();
        System.out.println("--------------\nSE REPRODUCE");
        }
        catch(Exception e){
            System.out.println("NO SE REPRODUCE");
        }
    }
    
    
}
