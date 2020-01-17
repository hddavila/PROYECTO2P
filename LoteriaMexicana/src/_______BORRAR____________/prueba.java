/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _______BORRAR____________;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Alineacion;
import paneles.PanelAlineacion;

/**
 *
 * @author DANIEL
 */
public class prueba extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        PanelAlineacion ali=new PanelAlineacion(Alineacion.CUALQUIERESQUINA);
        
        Scene scene = new Scene(ali.getRoot(), 300, 230);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        Random aleatorio=new Random();
//        System.out.println(aleatorio.nextInt(2));
//           
//        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
