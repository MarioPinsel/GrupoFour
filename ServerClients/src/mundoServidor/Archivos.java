
package mundoServidor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Archivos {
    
    private String rutaEntrada = "data\\text.txt";
    
    public List<String> leerArchivo() {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaEntrada))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return lineas;
    }
    
    
    
}
