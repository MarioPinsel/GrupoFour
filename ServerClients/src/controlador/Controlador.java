
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
        private Archivos archivos;
        private Servidor servidor;

    public Controlador() {
        this.archivos = new Archivos();
    }

    public void ejecutar() {
        List<String> lineas = new ArrayList<>();
        lineas = archivos.leerArchivo();

        
        
    }
}
