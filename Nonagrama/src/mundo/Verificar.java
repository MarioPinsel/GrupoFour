package mundo;

public class Verificar {

    private char[][] matriz;
    private String pistasFila[];
    private String pistasColumna[];
    private int vidasRestantes;
    private Archivos arc;

    public Verificar() {

        matriz = new char[10][10];
        pistasFila = new String[10];
        pistasColumna = new String[10];
        arc = new Archivos(matriz.length, matriz.length);
        cargarPistas(arc.getPistasColumna(), arc.getPistasFila());
        cargarTablero(arc.getTablero());
        vidasRestantes = 3;
    }

    public boolean verificarX(int fila, int columna) {
        if (matriz[fila][columna] == '0') {
            reducirVidas();
            return true;
        }
        return false;
    }

    public boolean verificar0(int fila, int columna) {
        if (matriz[fila][columna] == 'X') {
            reducirVidas();
            return true;
        }
        return false;
    }

    private void reducirVidas() {
        if (vidasRestantes > 0) {
            vidasRestantes--;
        }
    }

    public int getVidasRestantes() {
        return vidasRestantes;
    }

    private char[][] cargarTablero(char[][] tablero) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = tablero[i][j];
            }
        }
        return matriz;
    }

    private void cargarPistas(String[] pistasN, String[] pistasO) {
        pistasColumna = new String[10];
        pistasFila = new String[10];
        for (int i = 0; i < pistasColumna.length; i++) {
            pistasColumna[i] = pistasN[i];
        }
        for (int i = 0; i < pistasFila.length; i++) {
            pistasFila[i] = pistasO[i];
        }

    }

    public String[] getPistasFila() {
        return pistasFila;
    }

    public String[] getPistasColumna() {
        return pistasColumna;
    }

    
}
