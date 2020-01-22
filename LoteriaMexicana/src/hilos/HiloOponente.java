/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import javafx.application.Platform;
import modelo.Tablero;

import javafx.scene.layout.GridPane;

/**
 *
 * @author DANIEL
 */
public class HiloOponente extends Thread {
    private GridPane tablero;
    public HiloOponente(){
        tablero=new GridPane();
    }
    
    @Override
    public void run(){
            
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
    
    
    
    
}
