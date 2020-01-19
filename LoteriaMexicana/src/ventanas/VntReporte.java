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
    
    private void createContent(){
        root=new BorderPane();
        
        String[] datos={"Usuario","Alineacion","Tiempo", "CantidadOponente", "Visibilidad"};
        tableView= new TableView();
        tableView.setPlaceholder(new Label("No existen partidas jugadas"));
        for (String campo:datos){
            
            TableColumn<String,Partida> columna=new TableColumn<>(campo);
            columna.setMinWidth(150);
            columna.setMaxWidth(200);
            columna.setCellValueFactory(new PropertyValueFactory<>(campo.toLowerCase()));
            tableView.getColumns().add(columna);
        }
        
        //agregar los datos del arralyst al table view
        for(Partida p:partida){
            tableView.getItems().add(p);
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
    
     
    private void cargarPartidas(){
        partida=new ArrayList<>();
        Path path =Paths.get(archivo);
        if (Files.exists(path)){
            ObjectInputStream in = null;
            try{
                in=new ObjectInputStream(new FileInputStream(archivo));
                partida=(ArrayList<Partida>) in.readObject();
                in.close();
            }
            catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            catch(ClassNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            finally{
                try{
                    in.close();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println(partida);
    }
    
    public void actualizarTableView(){
        tableView.getItems().clear();
        //agregar datos del array al tableview
        for(Partida p:partida){
            tableView.getItems().add(p);
        }
    }
     
    public void actualizarReporte(){
        FileOutputStream fout=null;
        try{
            fout=new FileOutputStream(archivo);
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(partida);
            out.flush();
            fout.close();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                fout.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        
    }

    public ImageView getBack() {
        return back;
    }
    
    
    
    
}