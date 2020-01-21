/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author DANIEL
 */
public class HiloMusica extends Thread {
    private boolean activo;
    private Thread hilo;
    
    public HiloMusica(){
         this.hilo = new Thread(this);
         this.activo=false;
    }
    
    @Override 
    public void run(){
        while (activo){
            try{
                String path="src/audios/mexico.wav";
                Media media = new Media(new File(path).toURI().toString());

                MediaPlayer cad=new MediaPlayer(media);
                cad.setVolume(0.50);
                cad.play();
                System.out.println("--------------\nSE REPRODUCE MUSICA");
                hilo.sleep(22000);
                
                }
            catch(Exception e){
                System.out.println("NO SE REPRODUCE");
            }  
        }   
    }
     /**
     *
     * Reproduce la musica de fondo
     */
    public void iniciar(){
        this.activo=true;
        hilo.start();
    }
    
    public void parar(){
        this.activo=false;
    }
    
}
