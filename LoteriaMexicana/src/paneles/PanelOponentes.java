
package paneles;

import java.util.ArrayList;
import modelo.Oponente;

/**
 *
 * @author Gabriel
 */
public class PanelOponentes {
    private boolean visibilidad;
    private ArrayList<Oponente> oponentes;
    
    public PanelOponentes(boolean visibilidad, int cantOponentes){
        this.visibilidad = visibilidad;
        this.oponentes = new ArrayList(cantOponentes);
        for(int i = 0; i < cantOponentes; i++){
            this.oponentes.add(new Oponente());
        }
    }
    
}
