package mundo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author POWER
 */
public class Archivos {

    private final String[] pistasFila;
    private final String[] pistasColumna;
    private final char[][] tablero;

    public Archivos(int filas, int columnas) {
        pistasFila = new String[filas];
        pistasColumna = new String[columnas];
        tablero = new char[filas][columnas];
        cargarPistas();
    }

    private void cargarPistas() {
        for (int i = 0; i < pistasColumna.length; i++) {
            pistasColumna[i] = "";
        }
        File archivo = new File("src\\texto\\Puzzle 1.txt");
        int index1;
        int index2 = 0;
        int index3;

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while (lectura != null) {
                if (!verificarTablero(lectura)) {
                    if (lectura.length() > 10) {
                        index1 = 0;
                        for (int i = 0; i < lectura.length(); i++) {

                            if (lectura.charAt(i) != ' ') {
                                if (lectura.charAt(i) == '0') {
                                    pistasColumna[index1] += "\n";
                                } else {
                                    pistasColumna[index1] += lectura.charAt(i) + "\n";
                                }

                                continue;
                            }
                            index1++;
                        }
                        lectura = entrada.readLine();
                    }
                    if (lectura.length() <= 10) {
                        String temp = "";
                        for (int i = 0; i < lectura.length(); i++) {
                            if (lectura.charAt(i) == ' ') {
                                continue;
                            }
                            if (i < lectura.length() - 1 && lectura.charAt(i) == '1' && lectura.charAt(i + 1) == '0') {
                                temp += "10 ";
                                i++;
                            }
                            if (lectura.charAt(i) == '0') {
                                temp += ' ';
                            } else {
                                temp += "" + lectura.charAt(i) + ' ';
                            }

                        }
                        pistasFila[index2] = temp;
                        index2++;
                        lectura = entrada.readLine();

                    }
                } else {
                    for (int i = 0; i < pistasFila.length; i++) {
                        index3 = 0;
                        
                        for (int j = 0; j < lectura.length(); j++) {                            
                            if (lectura.charAt(j) == ' ') {
                                continue;
                            }

                            tablero[i][index3] = lectura.charAt(j);
                            index3++;
                        }
                        lectura = entrada.readLine();
                    }
                }

            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }             
    }

    public boolean verificarTablero(String lectura) {
        for (int i = 0; i < lectura.length(); i++) {
            if (lectura.charAt(i) == 'X') {
                return true;
            }
        }
        return false;
    }

    public String[] getPistasColumna() {
        return pistasColumna;
    }

    public String[] getPistasFila() {
        return pistasFila;
    }

    public char[][] getTablero() {
        return tablero;
    }
    

}
