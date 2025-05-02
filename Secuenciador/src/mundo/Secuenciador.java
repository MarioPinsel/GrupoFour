package mundo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Secuenciador {

    private final List<String> salida = new ArrayList<>();
    private int contador = 1;

    public List<String> selector(List<String> lineasEntrada) {
        salida.clear();
        contador = 1;
        procesarBloque(lineasEntrada, 0, lineasEntrada.size());
        return salida;
    }

    private int procesarBloque(List<String> lineas, int inicio, int fin) {
        for (int i = inicio; i < fin;) {
            String linea = lineas.get(i).trim();

            if (linea.isEmpty() || linea.equals("{") || linea.equals("}")) {
                i++;
                continue;
            }

            if (linea.startsWith("for")) {
                String contenido = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                String[] partes = contenido.split(";");
                int idxStart = contador;
                salida.add(formatear(contador++, partes[0].trim(), "-")); 
                int idxBool = contador;
                salida.add(formatear(contador++, partes[1].trim(), -1)); 

                int bloqueInicio = encontrarInicioBloque(lineas, i + 1);
                int bloqueFin = encontrarFinBloque(lineas, bloqueInicio);
                i = bloqueInicio + 1;

                i = procesarBloque(lineas, i, bloqueFin);

                salida.add(formatear(contador++, partes[2].trim(), "-")); 
                salida.add(formatear(contador++, "Jump", idxBool));       
                actualizarSalto(idxBool, contador); 
                i = bloqueFin + 1;
            } else if (linea.startsWith("while")) {
                String condicion = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                int idxCond = contador;
                salida.add(formatear(contador++, condicion, -1));

                int bloqueInicio = encontrarInicioBloque(lineas, i + 1);
                int bloqueFin = encontrarFinBloque(lineas, bloqueInicio);
                i = bloqueInicio + 1;

                i = procesarBloque(lineas, i, bloqueFin);
                salida.add(formatear(contador++, "Jump", idxCond));
                actualizarSalto(idxCond, contador);
                i = bloqueFin + 1;
            } else if (linea.startsWith("if")) {
                String condicion = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                int idxCond = contador;
                salida.add(formatear(contador++, condicion, -1));

                int bloqueInicio = encontrarInicioBloque(lineas, i + 1);
                int bloqueFin = encontrarFinBloque(lineas, bloqueInicio);
                i = bloqueInicio + 1;

                i = procesarBloque(lineas, i, bloqueFin);

                if (i + 1 < lineas.size() && lineas.get(i + 1).trim().equals("else")) {
                    int idxJump = contador;
                    salida.add(formatear(contador++, "Jump", -1));
                    actualizarSalto(idxCond, contador);

                    int elseInicio = encontrarInicioBloque(lineas, i + 2);
                    int elseFin = encontrarFinBloque(lineas, elseInicio);
                    i = elseInicio + 1;

                    i = procesarBloque(lineas, i, elseFin);
                    actualizarSalto(idxJump, contador);
                    i = elseFin + 1;
                } else {
                    actualizarSalto(idxCond, contador);
                    i = bloqueFin + 1;
                }
            } else {
                salida.add(formatear(contador++, linea, "-"));
                i++;
            }
        }
        return fin;
    }

    private void actualizarSalto(int index, int destino) {
        String linea = salida.get(index - 1);
        int tab1 = linea.indexOf('\t');
        int tab2 = linea.indexOf('\t', tab1 + 1);
        String actual = linea.substring(0, tab2 + 1);
        salida.set(index - 1, actual + destino);
    }

    private String formatear(int num, String instruccion, Object salto) {
        return num + "\t" + instruccion + "\t" + salto;
    }

    private int encontrarInicioBloque(List<String> lineas, int inicio) {
        while (inicio < lineas.size() && !lineas.get(inicio).trim().equals("{")) {
            inicio++;
        }
        return inicio;
    }

    private int encontrarFinBloque(List<String> lineas, int inicio) {
        Stack<String> pila = new Stack<>();
        for (int i = inicio; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();
            if (linea.equals("{")) {
                pila.push("{");
            } else if (linea.equals("}")) {
                pila.pop();
                if (pila.isEmpty()) {
                    return i;
                }
            }
        }
        return lineas.size();
    }
}
