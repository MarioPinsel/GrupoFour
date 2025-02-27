package mundo;


/**
 *
 * @author User
 */
public class Verificar {
    private char[][] matriz;
    private int vidasRestantes;

    public Verificar() {
        matriz = new char[][]{
            {'0', '0', '0', '0', '0', '0', 'X', 'X', 'X', 'X'},
            {'0', '0', '0', '0', 'X', 'X', '0', 'X', 'X', 'X'},
            {'0', 'X', '0', '0', 'X', 'X', '0', 'X', 'X', 'X'},
            {'0', 'X', '0', '0', '0', '0', '0', '0', '0', 'X'},
            {'0', '0', '0', '0', '0', '0', '0', 'X', 'X', '0'},
            {'0', 'X', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', 'X', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', 'X', '0', '0', '0', '0', 'X', 'X', '0', '0'},
            {'0', '0', '0', '0', '0', '0', 'X', 'X', '0', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', '0', '0', 'X', 'X'}
        };
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
}

