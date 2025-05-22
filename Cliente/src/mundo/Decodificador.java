package mundo;

import java.util.ArrayList;

public class Decodificador {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";

    private int paco = 0;
    private String traduccion = "";
    private ArrayList<String> diccionario = new ArrayList<>();
    private boolean usarRojo = true;
    private ArrayList<String> bloquesColoreados = new ArrayList<>();

    public void procesar(int primeraEntrada) {

        if (paco == 0) {
            paco = primeraEntrada;
        } else {
            decodificar(paco, primeraEntrada);
            paco = primeraEntrada;
        }
    }

    public String obtenerEntrada(int codigo) {
        if (codigo <= 255) {
            return String.valueOf((char) codigo);
        } else if (codigo - 256 < diccionario.size()) {
            return diccionario.get(codigo - 256);
        } else {
            // Caso especial LZW: código aún no en diccionario
            String entradaAnterior = obtenerEntrada(paco); // usa PE
            return entradaAnterior + entradaAnterior.charAt(0);
        }
    }

    public void decodificar(int paco, int luis) {
        String pe = obtenerEntrada(paco);
        String se = obtenerEntrada(luis);

        String ps = pe + se.charAt(0);

        if (!diccionario.contains(ps)) {
            diccionario.add(ps);
        }

        traduccion += pe;

        if (usarRojo) {
            bloquesColoreados.add(RED + pe + RESET);
        } else {
            bloquesColoreados.add(BLUE + pe + RESET);
        }
        usarRojo = !usarRojo;
    }

    public void imprimir() {
        if (paco != 0) {
            String ultimo = obtenerEntrada(paco);
            traduccion += ultimo;

            if (usarRojo) {
                bloquesColoreados.add(RED + ultimo + RESET);
            } else {
                bloquesColoreados.add(BLUE + ultimo + RESET);
            }
            usarRojo = !usarRojo;
            paco = 0;
        }

        for (String bloque : bloquesColoreados) {
            System.out.print(bloque);
        }
        System.out.println();
    }

}
