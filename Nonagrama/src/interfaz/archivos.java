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
    private String[] PistasColumna;

    public archivos() {
        PistasFila = new String[10];
        PistasColumna = new String[10];
    }

    public void cargarPistasDesdeArchivo() {
        File archivo = new File("src\\texto\\Puzzle 1.txt");

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while (lectura != null) {
                int index;
                for (int i = 0; i < 19; i++) {
                    index=0;
                    if (lectura.charAt(i) != ' ') {
                        PistasColumna[index] = String.valueOf(lectura.charAt(i) + "\n");               
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
        for (int i = 0; i < PistasColumna.length; i++) {
            System.out.println(PistasColumna[i]);
        }
    }
}
