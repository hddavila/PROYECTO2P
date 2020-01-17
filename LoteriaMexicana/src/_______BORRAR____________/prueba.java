/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _______BORRAR____________;

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
        PanelAlineacion ali=new PanelAlineacion(Alineacion.FILA);
        
        Scene scene = new Scene(ali.getRoot(), 300, 400);
        scene.getStylesheets().add("src/css/estiloPanel.css");
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
