package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        String puzzle;
        int random = (int) (Math.random() * 2 + 1); 

        switch (random) {
            case 1:
                puzzle = "texto/Puzzle 1.txt";
                break;
            case 2:
                puzzle = "texto/Puzzle 2.txt";
                break;
            default:
                throw new AssertionError("Valor inesperado: " + random);
        }

        for (int i = 0; i < pistasColumna.length; i++) {
            pistasColumna[i] = "";
        }
        
        
        InputStream archivoStream = getClass().getClassLoader().getResourceAsStream(puzzle);

        if (archivoStream == null) {
            System.err.println("Error: No se pudo encontrar el archivo '" + puzzle + "' en resources/texto/");
            return;
        }

        int index1;
        int index2 = 0;
        int index3;

        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(archivoStream))) {
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
                        StringBuilder temp = new StringBuilder();
                        for (int i = 0; i < lectura.length(); i++) {
                            if (lectura.charAt(i) == ' ') {
                                continue;
                            }
                            if (i < lectura.length() - 1 && lectura.charAt(i) == '1' && lectura.charAt(i + 1) == '0') {
                                temp.append("10 ");
                                i++;
                            } else if (lectura.charAt(i) == '0') {
                                temp.append(' ');
                            } else {
                                temp.append(lectura.charAt(i)).append(' ');
                            }
                        }
                        pistasFila[index2] = temp.toString();
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
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public boolean verificarTablero(String lectura) {
        return lectura.contains("X");
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


