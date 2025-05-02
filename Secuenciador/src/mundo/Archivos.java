package mundo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Archivos {

    public List<String> leerArchivo(String rutaEntrada) {
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

    public void escribirArchivo(String rutaSalida, List<String> contenido) {
        try (BufferedWriter Escritor = new BufferedWriter(new FileWriter(rutaSalida))) {
            for (String linea : contenido) {
                Escritor.write(linea);
                Escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
        }
    }
}
