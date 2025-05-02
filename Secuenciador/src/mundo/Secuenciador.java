package mundo;

import java.util.ArrayList;
import java.util.List;

public class Secuenciador {
    private List<String> salida = new ArrayList<>();

    public List<String> selector(List<String> lineasEntrada) {
        for (int i = 0; i < lineasEntrada.size(); i++) {
        }
        secuenciaIf(lineasEntrada);

        return salida;
    }

    public List<String> secuenciaIf(List<String> lineasEntrada) {

        int contador = 1;

        for (int i = 0; i < lineasEntrada.size(); i++) {
            String linea = lineasEntrada.get(i).trim();

            if (linea.isEmpty())
                continue;

            if (linea.startsWith("if")) {
                String condicion = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                int instruccionesEnBloque = contarInstruccionesDelBloque(lineasEntrada, i + 2);
                int salto = contador + instruccionesEnBloque;
                salida.add(contador + "\t" + condicion + "\t" + salto);
                contador++;
            } else if (linea.equals("{") || linea.equals("}")) {
                if (linea.equals("}"))
                    salida.add("");
                continue;
            } else {
                salida.add(contador + "\t" + linea + "\t-");
                contador++;
            }
        }

        return salida;
    }

    private int contarInstruccionesDelBloque(List<String> lineas, int inicio) {
        int contador = 0;
        int llaves = 0;
        boolean bloqueAbierto = false;

        for (int i = inicio; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();

            if (linea.contains("{")) {
                llaves++;
                bloqueAbierto = true;
                continue;
            }

            if (linea.contains("}")) {
                llaves--;
                if (llaves == 0 && bloqueAbierto)
                    break;
                continue;
            }

            if (!linea.isEmpty() && !linea.equals("{") && !linea.equals("}")) {
                contador++;
            }
        }

        return contador;
    }
}
