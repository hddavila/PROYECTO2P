/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author DANIEL
 */
public class VntNuevo {
    TextField nombre;
    BorderPane root;
    
    public VntNuevo(){
        
        createContent();
    }
    
    public BorderPane getRoot(){
        return root;
    }
    public void createContent(){
        root=new BorderPane();
        nombre=new TextField();
        nombre.setMaxSize(300, 200);
        nombre.setPromptText("dinosaurio");
        
        StackPane ventana=new StackPane();
        Rectangle cuadro=new Rectangle(500,200);
        cuadro.setId("cuadroNuevo");
        
        VBox columna=new VBox(10);
        
        Label titulo=new Label("Escriba su nombre...");
        titulo.setStyle("-fx-text-fill:black;-fx-font-family: Helvetica;");
        Button btn_iniciar=new Button("Iniciar");
        btn_iniciar.setId("iniciar");
        
        btn_iniciar.setOnAction(e->{
            sonido();
        });
        
        columna.getChildren().addAll(titulo,nombre,btn_iniciar);
        columna.setAlignment(Pos.CENTER);
        
        ventana.getChildren().addAll(cuadro,columna);
        root.setCenter(ventana);
    }
    
}
