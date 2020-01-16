
package paneles;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    
    
    public PanelAlineacion(){
        createContent();
    }
    
     /**
     *
     * Metodo que generada el contenido en el contenedor pi
     * 
     */
    public void createContent(){
        root=new BorderPane();
        
        
    }
    
    
    
    /**
     *
     * Metodo que recibe una alineacion y genera el tablero que se muestra correspondiente a esa alineacion
     * 
     */
    public void creaAlineacion(Alineacion alineacion){
        
        //Dependiendo del caso se creara la alineacion grafica
        switch(alineacion){
            case FILA:
                break;
            case COLUMNA:
                break;
            case ESQUINASUPIZQUIERDA:
                break;
            case ESQUINASUPDERECHA:
                break;
            case ESQUINAINFIZQUIERDA:
                break;
            case ESQUINAINFDERECHA:
                break;
            case CUALQUIERESQUINA:
                break;
        }
        
        
        
    }
    
    public GridPane crearTabla(int dimx,int dimy){
        GridPane tabla=new GridPane();
        Rectangle bloqueN=new Rectangle(16,16);
        bloqueN.setFill(Color.BLACK);
        
        Rectangle bloqueB=new Rectangle(16,16);
        bloqueB.setFill(Color.BLACK);
        
        
        for(int i=0;i<dimx;i++){
            //nodo,columna,fila
            tabla.add(bloqueN, 0, 1);
        }
        
        return tabla;
    }
}