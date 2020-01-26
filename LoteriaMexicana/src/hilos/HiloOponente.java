/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.ArrayList;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Tablero;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import paneles.PanelMazo;
import ventanas.VntPartida;

/**
 *
 * @author DANIEL
 */
public class HiloOponente extends Thread {
    private GridPane tablero;
    private Thread hilo;
    private boolean activo;
    private boolean condicion;
    private Carta carta;
    private ObservableList<Node> cartas; 
    private StackPane tarjeta;
    private int[][] arrAlineacion;
    private int fila;
    private int columna;
    
    ImageView frijol;
    
    /**
     * Hilo que constantmente esta verificando si la carta ya fue anunciada para poner marcarla con un frijol
     * @param carta carta que se esta observando
     */
    public HiloOponente(Carta carta,int[][] arrAlineacion,int fila, int columna){
        tablero=new GridPane();
        this.hilo = new Thread(this);
        this.carta=carta;
        activo=false;
        this.arrAlineacion=arrAlineacion;
        this.fila=fila;
        this.columna=columna;
        
        this.frijol=new ImageView();
    }
    
    @Override
    public void run(){
         while(activo){
            try{
            hilo.sleep(3000);
//             System.out.println(PanelMazo.anunciadas);
            verificarCarta(carta,PanelMazo.anunciadas);
              //si la condicion es correcta añade el frijol a la carta y esta se bloquea
              if(condicion){
                  hilo.sleep(3000);
                  System.out.println("Encontre "+carta);
                  //agregar el frijol encima
                  try{
                      Image imagen=new Image("/recursos/frijol3.png");
                      frijol.setFitWidth(70);
                      frijol.setFitHeight(70);
                      frijol.setStyle("-fx-opacity:0.90;"); 
                      frijol.setImage(imagen);
                      arrAlineacion[fila][columna]=1;
                      VntPartida.btnMaquina.fire();
                  }catch(Exception m){
                      System.out.println("No se pudo agregar el frijol");
                  }
                  break;

             }
             
            }
            catch(Exception e){
                System.out.println("Excepcion");
            }
           
         }
    }
    
    
    public void verificarCarta(Carta carta,Map<Integer,ImageView> mapa){
        if(mapa.containsKey(carta.getNumero())){
            condicion=true;
        }
        else{
            condicion=false;
        }
        
    }

    public void setTablero(GridPane tablero) {
        this.tablero = tablero;
    }

 
    
    

    public GridPane getTablero() {
        tablero.setDisable(true);
        tablero.setScaleX(0.30);
        tablero.setScaleY(0.30);
        return tablero;
    }
    
    public void jugar(){
        this.activo=true;
        hilo.start();
    
    }

    public ImageView getFrijol() {
        return frijol;
    }

    public int[][] getArrAlineacion() {
        return arrAlineacion;
    }

   
    
    
}