/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.Jugador;
import modelo.Partida;

/**
 *
 * @author Hugo Davila
 */
public class VntPartida {
    
    private BorderPane contenedor;
    private Jugador player;
    private VBox izquierda;
    private VBox centro;
    private VBox derecha;
    
    
    private Partida config;
    private VBox info;
    
    public VntPartida(){
        createContent();
    }
    
    public BorderPane getContenedor(){
        return contenedor;
    }
    
    public void createContent(){
        //incializar todos lo contenedores
        contenedor=new BorderPane();
        izquierda=new VBox();
        centro=new VBox();
        derecha=new VBox();
        info=new VBox();
        
        contenedor.setCenter(centro);
        contenedor.setRight(derecha);
        
         //derecha
            //inforamacion del jugador
            try {
                config=recuperarConfig();
                Label nombre=new Label(config.getNombreUsuario());
                info.getChildren().add(nombre);
                derecha.getChildren().addAll(info);
            } catch (Exception ex) {
                System.out.println("NO SE PUDO CARGAR LA INFORMACION");;
            }


    }
    
    /**
     * Recupera el objeto de tipo Partida que tiene la configuracion temporal del juego
     */
    public Partida recuperarConfig() throws Exception{
        ObjectInputStream saut=new ObjectInputStream(new FileInputStream("src/partidas/config.ser"));
        Partida ge=(Partida) saut.readObject();
        return ge;
    }
    
}
