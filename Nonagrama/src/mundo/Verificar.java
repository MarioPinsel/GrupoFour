package mundo;

import controlador.Controlador;
import controlador.Controlador.Coordenada;
import java.util.List;

/**
 *
 * @author User
 */
public class Verificar {

    private Controlador ctrl;
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
    }

    public boolean errorX() {
        List<Coordenada> posiciones = ctrl.obtenerPosicionesConX();
        for (Coordenada pos : posiciones) {
            int fila = pos.getFila();
            int columna = pos.getColumna();
            if (matriz[fila][columna] == '0') {
                return true;
            }
        }
        return false;
    }

    public boolean error0() {
        List<Coordenada> posiciones = ctrl.obtenerPosicionesConX();
        for (Coordenada pos : posiciones) {
            int fila = pos.getFila();
            int columna = pos.getColumna();
            if (matriz[fila][columna] == 'X') {
                return true;
            }
        }
        return false;
    }

    public int contador() {
        int indicePerder = 3;
        if (errorX() || error0()) {
            if (vidasRestantes > 0) {
                indicePerder -= vidasRestantes;
                vidasRestantes--;
            }
        }
        return indicePerder;
    }
}
