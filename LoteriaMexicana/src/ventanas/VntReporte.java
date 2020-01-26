package ventanas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Partida;
import static ventanas.VntInicio.sonido;

/**
 *
 * @author Dom�nica Briones
 */
public class VntReporte{
    
    BorderPane root;
    TableView tableView;
    ArrayList<Partida> partida;
    String archivo="partida.ser";
    ImageView back;

    public VntReporte(){
        cargarPartidas();
        createContent();
    }
    
    
    
    public Pane getRoot(){
      return root;  
    }
    
    /**
     * Genera el contenido de la ventana reporte
     */
    private void createContent(){
        root=new BorderPane();
        
        String[] datos={"nombreUsuario","alineacion","tiempo", "cantidadOponentes"};//, "Visibilidad"};
        tableView= new TableView();
        tableView.setPlaceholder(new Label("No existen partidas jugadas"));
        for (String campo:datos){
            
            TableColumn<String,Partida> columna;
            switch(campo){
                case "nombreUsuario":
                    columna=new TableColumn<>("Nombre");
                    columna.setMinWidth(200);
                    columna.setMaxWidth(250);
                    columna.setCellValueFactory(new PropertyValueFactory<>(campo));
                    tableView.getColumns().add(columna);
                    break;
                
                case "alineacion":
                    columna=new TableColumn<>("Alineacion");
                    columna.setMinWidth(200);
                    columna.setMaxWidth(250);
                    columna.setCellValueFactory(new PropertyValueFactory<>(campo));
                    tableView.getColumns().add(columna);
                    break;
                case "tiempo":
                    columna=new TableColumn<>("Tiempo");
                    columna.setMinWidth(200);
                    columna.setMaxWidth(250);
                    columna.setCellValueFactory(new PropertyValueFactory<>(campo));
                    tableView.getColumns().add(columna);
                    break;
                case "cantidadOponentes":
                    columna=new TableColumn<>("Cantidad Oponentes");
                    columna.setMinWidth(200);
                    columna.setMaxWidth(250);
                    columna.setCellValueFactory(new PropertyValueFactory<>(campo));
                    tableView.getColumns().add(columna);
                    break;
            }
            
            
        }
        
        
        cargarPartidas();
        
        //agregar los datos del arralyst al table view
        try{
            for(Partida p:partida){
                tableView.getItems().add(p);
            }
        }
        catch(Exception m){
            System.out.println("NO EXISTEN PARTIDAS");
        }
        HBox conTitulo=new HBox(10);
        Label lbl= new Label("Reporte");
        
        back=new ImageView("/recursos/back.png");
        back.setPreserveRatio(true);
        back.setFitWidth(50);
        
        
        
        conTitulo.getChildren().addAll(back,lbl);
        conTitulo.setAlignment(Pos.CENTER);
        
        
        root.setTop(conTitulo);
        root.setCenter(tableView);
    }
    
    /**
     * Abre el archivo con el arreglo de las partidas y lo carga en el arreglo partida
     */ 
    private void cargarPartidas(){
        try{
            ObjectInputStream leer=new ObjectInputStream(new FileInputStream("src/partidas/partida.ser"));
            partida=(ArrayList<Partida>) leer.readObject();
            
        }
        catch(Exception m){
            System.err.println("NO SE PUEDIERON CARGAR LAS PARTIDAS");
        }
        System.out.println(partida);
    }
    
    /**
     * Actualiza el TableView con los objetos de las partidas del arreglo
     * que se genero al cargar las partidas
     */
    public void actualizarTableView(){
        tableView.getItems().clear();
        //agregar datos del array al tableview
        for(Partida p:partida){
            tableView.getItems().add(p);
        }
    }
    
    public ImageView getBack() {
        return back;
    }
    
    
    
    
}