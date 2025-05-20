
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
    ArrayList<String> lineas;

    public Archivos() {
        leerArchivo();
    }
    
    
    private String rutaEntrada = "data//texto.txt";
    
    private List<String> leerArchivo() {
        InputStream ruta = getClass().getClassLoader().getResourceAsStream(rutaEntrada);
        lineas = new ArrayList<>();
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

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }
    
}
