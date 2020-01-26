
package paneles;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import modelo.Alineacion;

/**
 *
 * @author Hugo Davila
 */

/**
 *
 * Clase mediante la cual se puede instanciar el panel alineacion que devuelve un contenedor con la alineacion
 * determinada de manera aleatoria
 */

public class PanelAlineacion {
    private Alineacion alineacion;
    private VBox columna;
    private BorderPane root;
    
    /**Constructor del panel Alineacion que recibe un estado del Enum Alineacion 
     * @param alineacion recibe el objeto de tipo Enum de la alineacion que se usara
     * 
     */
    public PanelAlineacion(Alineacion alineacion){
        this.alineacion=alineacion;
        createContent();
    }
    
    /**Devuelve el panel de alineacion generado 
     * @return root contenedor del panel con la alineacion generada
     * 
     */
    public BorderPane getRoot(){
        return root;
    }
    
    
    
     /**
     *
     * Metodo que generada el contenido en el contenedor principal
     * 
     */
    public void createContent(){
        root=new BorderPane();
        columna=new VBox();
        crearAlineacion();
        root.setCenter(columna);
    }
    
    
    
    
    /**Metodo que recibe una alineacion y genera el tablero que se muestra correspondiente a esa alineacion
     * 
     */
    public void crearAlineacion(){
        
        Label lblTitulo=new Label("Forma de ganar");
        lblTitulo.setAlignment(Pos.CENTER);
        
        HBox header=new HBox();
        header.getChildren().add(lblTitulo);
        header.setAlignment(Pos.CENTER);
        root.setTop(header);
        
        Label forma=new Label();
        
        
        
        GridPane cuadro=new GridPane();
        cuadro.setAlignment(Pos.CENTER);
        
        //Dependiendo del caso se creara la alineacion grafica
        switch(alineacion){
            case FILA:
                cuadro=crearTabla(4,1); //(cantidad de columnas, cantidad de filas)
                forma.setText("4 EN UNA FILA");
                root.setBottom(forma);
                break;
            case COLUMNA:
                cuadro=crearTabla(1,4);
                forma.setText("4 EN UNA COLUMNA");
                root.setBottom(forma);
                break;
            case ESQUINASUPIZQUIERDA:
                cuadro=crearTabla(2,2);
                forma.setText("4 EN ESQUINA SUPERIOR IZQUIERDA");
                root.setBottom(forma);
                break;
            case ESQUINASUPDERECHA:
                cuadro=crearTabla(2,2);
                forma.setText("4 EN ESQUINA SUPERIOR DERECHA");
                root.setBottom(forma);
                break;
            case ESQUINAINFIZQUIERDA:
                cuadro=crearTabla(2,2);
                forma.setText("4 EN ESQUINA INFERIOR IZQUIERDA");
                root.setBottom(forma);
                break;
            case ESQUINAINFDERECHA:
                cuadro=crearTabla(2,2);
                forma.setText("4 EN ESQUINA INFERIOR DERECHA");
                root.setBottom(forma);
                break;
            case CUALQUIERESQUINA:
                cuadro=crearTabla(2,2);
                forma.setText("4 EN UNA CUALQUIER ESQUINA");
                root.setBottom(forma);
                break;
        }
        
        
        cuadro.setAlignment(Pos.CENTER);
        columna.getChildren().addAll(cuadro);
        
        
    }
    /**
     * Genera tabla con imagenes de cartas que serviran de referencia de la alineacion a cumplir
     * @param dimx filas de la tabla
     * @param dimy columnas de la tabla
     * @return 
     */
    public GridPane crearTabla(int dimx,int dimy){
        GridPane tabla=new GridPane();
        tabla.setHgap(10);
        tabla.setVgap(10);

        Rectangle bloqueB=new Rectangle(16,16);
        bloqueB.setFill(Color.BLACK);
        
        for(int i=0;i<dimy;i++){
        
            for(int j=0;j<dimx;j++){
                //nodo,columna,fila
                Rectangle bloqueN=new Rectangle(50,80);
                bloqueN.setFill(Color.BLACK);
                tabla.add(bloqueN,j,i);
                
                tabla.add(new Label("CARTA"),j,i);
            }
        }
        
        return tabla;
    }
}