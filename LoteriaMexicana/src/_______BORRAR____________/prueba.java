/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _______BORRAR____________;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Alineacion;
import modelo.Cronometro;
import hilos.CronometroFull;
import modelo.Oponente;
import paneles.PanelAlineacion;

/**
 *
 * @author DANIEL
 */
public class prueba extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
//        Map<Integer,String> mapa=new HashMap<Integer,String>();
//        mapa.put(1,"Pablo");
//        System.out.println(mapa);
//        
//        String h="marcos,juan";
//        h.split(",");
        
    
        
        
        
//        PanelAlineacion ali=new PanelAlineacion(Alineacion.CUALQUIERESQUINA);
//        
        VBox box = new VBox(25);
        CronometroFull crono = new CronometroFull();
        box.getChildren().add(crono.getLabel());
        
        Button start = new Button("Iniciar");
        start.setOnAction(e->{
            crono.iniciarCronometro();
        });
        
        Button stop = new Button("Parar");
        stop.setOnAction(f->{
            crono.pararCronometro();
        });
        
        Button restart = new Button("Reiniciar");
        restart.setOnAction(g->{
           crono.reiniciarCronometro();
        });
        
        HBox botones = new HBox(10);
        botones.getChildren().addAll(start, stop, restart);
        box.getChildren().add(botones);
        
        Scene scene = new Scene(box, 300, 230);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        
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
