
package mundoServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Archivos {
    
    private String rutaEntrada = "data//texto.txt";
    
    public List<String> leerArchivo() {
        InputStream ruta = getClass().getClassLoader().getResourceAsStream(rutaEntrada);
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea+"");
                lineas.add(linea);
                
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return lineas;
    }
    
    
    
}
