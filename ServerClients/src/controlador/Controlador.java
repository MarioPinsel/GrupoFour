
package controlador;

import java.util.ArrayList;
import java.util.List;
import mundoServidor.Archivos;
import mundoServidor.Servidor;

/**
 *
 * @author User
 */
public class Controlador {        
        private Servidor servidor;

    public Controlador() {        
    }
    
    public void setInstance(ArrayList<String> ips){
        servidor = new Servidor(ips);
        servidor.añadirData();
    }
    
    


}
