/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import modelo.Partida;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author Hugo Davila
 */

/**
 *
 * Clase que contiene a la ventana de configuraciones
 */

public class VntConfiguracion {
    private BorderPane root;
    private ComboBox cmbCantidad;
    private boolean visibilidad;
    private ImageView back;
    
    public VntConfiguracion(){
        createContent();
    }
    
    public BorderPane getRoot(){
        return root;
    }

    public ImageView getBack() {
        return back;
    }
    
    
    /**
     * Genera el contenido de la ventana configuraciones
     */
    public void createContent(){
        root=new BorderPane();
        
        Rectangle base1=new Rectangle(350,100);
        base1.setId("base1");

        Rectangle base2=new Rectangle(350,100);
        base2.setId("base2");

        StackPane f1=new StackPane();
        StackPane f2=new StackPane();

        back=new ImageView("/recursos/back.png");
        back.setPreserveRatio(true);
        back.setFitWidth(50);
        
        Label lblConfig=new Label("Configuracion");
        HBox header=new HBox(40);
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(back,lblConfig);

        root.setTop(header);

        HBox fila1=new HBox(10);
        Label lblCantidad=new Label("Cantidad de oponentes");
        cmbCantidad=new ComboBox();
        cmbCantidad.getItems().addAll(0,1,2);
        cmbCantidad.setValue(1);

        fila1.getChildren().addAll(lblCantidad,cmbCantidad);
        fila1.setAlignment(Pos.CENTER);


        HBox fila2=new HBox(10);
        Label lblVisibilidad=new Label("Visibilidad oponentes");
        Button btn_activar=new Button("activar");

        fila2.getChildren().addAll(lblVisibilidad,btn_activar);
        fila2.setAlignment(Pos.CENTER);


        f1.getChildren().addAll(base1,fila1);
        f1.setAlignment(Pos.CENTER);


        f2.getChildren().addAll(base2,fila2);
        f2.setAlignment(Pos.CENTER);


        VBox columna2=new VBox(20);
        columna2.setAlignment(Pos.CENTER);
        columna2.getChildren().addAll(f1,f2);

        root.setCenter(columna2);
    }
    
    public void actualizarConfig(){
       try{
        ObjectInputStream saut=new ObjectInputStream(new FileInputStream("src/partidas/config.ser"));
        Partida ge=(Partida) saut.readObject();

        
        //sobreescribir el numero de oponentes al que se selecciono
        ge.setCantidadOponentes(Integer.parseInt(cmbCantidad.getValue().toString()));
        
                
        System.out.println(ge.toString()); //---------------borrar------------------------------------
        
        saut.close();
        //sobreescribir el archivo temporal
        ObjectOutputStream fout=new ObjectOutputStream(new FileOutputStream("src/partidas/config.ser"));
        
        fout.writeObject(ge);
        fout.close();
        
           System.out.println("ARCHIVO ACTUALIZADO CORRECTAMENTE");
  
       }
       catch(Exception e){
           System.out.println("El ARCHIVO NO SE PUDO ACTUALIZAR");
       }
    }
    
}
