/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import modelo.Carta;


/**
 *
 * @author DANIEL
 */
public class PanelMazo extends Thread{
    private Thread hilo;
    private VBox contenedor;
    private ArrayList<Carta> arregloCartas;
    private Map<Integer,ImageView> anunciadas;
    private boolean activo;
    private ImageView imgCarta;
    
    
    
    public PanelMazo(ArrayList<Carta> arregloCartas){
        this.arregloCartas=arregloCartas;
        anunciadas=new HashMap<Integer,ImageView>();
        this.hilo=null;
        imgCarta=new ImageView();
        imgCarta.setFitWidth(350);
        imgCarta.setFitHeight(500);
        
    }

    
    
    @Override
    public void run() {
        
        
        ArrayList<Integer> salieron=new ArrayList<>();
        try{
            while(activo){
               //genera un la posicion aleatoria de donde se escojera el objeto Carta
                Random aleatorio=new Random();
                Integer posicion=aleatorio.nextInt(arregloCartas.size());
                if (salieron.contains(posicion)==false){
                    Carta carta=arregloCartas.get(posicion);
                    //guardar la carta en el mapa de anunciadas
                    anunciadas.put(carta.getNumero(),carta.getImagen());
                    salieron.add(posicion);
                    Image imagen=carta.getImagen().getImage();
                    
                    
                    Platform.runLater(()-> imgCarta.setImage(imagen));
                    //duracion 4 segundos
                    hilo.sleep(4000);
                }
            }
        }
        catch(Exception e){
            System.err.println("NO SE PUDO MOSTRAR LA CARTA DEL MAZO");
        }
    
    }
    
    public void anunciar(){
        activo=true;
        if(hilo == null){
            hilo = new Thread(this);
            hilo.start();
        }
    }
    
    public void parar(){
        activo=false;
    }
    
    public ImageView getImgCarta() {
        return imgCarta;
    }

    public Map<Integer, ImageView> getAnunciadas() {
        return anunciadas;
    }
    
    
    
    
    
}
