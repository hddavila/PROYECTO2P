/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author DANIEL
 */

/**
 *
 * Clase qie contiene a la ventana de configuraciones
 */

public class VntConfiguracion {
    private BorderPane root;
    private ComboBox cantidad;
    private boolean visibilidad;
    
    public VntConfiguracion(){
        createContent();
    }
    
    public BorderPane getRoot(){
        return root;
    }
    
    public void createContent(){
// 
//        root.setTop(new Label("Configuracion"));
//        
//        HBox fila1=new HBox(10);
//        fila1.setStyle("-fx-background-color: #B3F6F3;");
//        Label lblCantidad=new Label("Cantidad de oponentes");
//        cantidad=new ComboBox();
//        cantidad.getItems().addAll(0,1,2);
//        
//        fila1.getChildren().addAll(lblCantidad,cantidad);
//        fila1.setAlignment(Pos.CENTER);
//        
//        
//        HBox fila2=new HBox(10);
//        
//        root.setCenter(fila1);
        

    }
    
}
