package interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author POWER
 */
public class archivos {

    private String[] PistasFila;
    private String[] pistasColumna;

    public archivos() {
        PistasFila = new String[10];
        pistasColumna = new String[10];
    }

    public void cargarPistasDesdeArchivo() {
        for (int i = 0; i < pistasColumna.length; i++) {
            pistasColumna[i] = "";
        }
        File archivo = new File("src\\texto\\Puzzle 1.txt");

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while (lectura != null) {
                int index = 0;
                for (int i = 0; i < lectura.length(); i++) {

                    if (lectura.charAt(i) != ' ') {
                        if (lectura.charAt(i) == '0') {
                            pistasColumna[index] += "\n";
                        } else {
                            pistasColumna[index] += lectura.charAt(i) + "\n";
                        }

                        continue;
                    }
                    index++;                    
                }
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        for (int i = 0; i < pistasColumna.length; i++) {
            System.out.println(pistasColumna[i]);

        }
    }
}
