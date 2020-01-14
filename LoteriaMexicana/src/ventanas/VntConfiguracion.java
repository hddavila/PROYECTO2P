/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

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
        
    }
    
}
