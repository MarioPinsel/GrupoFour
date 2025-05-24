package mundo;

import java.util.ArrayList;

public class Decodificador {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";

    private int segundaEntrada = 0;
    private String traduccion = "";
    private ArrayList<String> diccionario = new ArrayList<>();
    private boolean usarRojo = true;
    private ArrayList<String> bloquesColoreados = new ArrayList<>();

    public void procesar(int primeraEntrada) {

        if (segundaEntrada == 0) {
            segundaEntrada = primeraEntrada;
        } else {
            decodificar(segundaEntrada, primeraEntrada);
            segundaEntrada = primeraEntrada;
        }
    }

    public String obtenerEntrada(int codigo) {
        if (codigo <= 255) {
            return String.valueOf((char) codigo);
        } else if (codigo - 256 < diccionario.size()) {
            return diccionario.get(codigo - 256);
        } else {

            String entradaAnterior = obtenerEntrada(segundaEntrada);
            return entradaAnterior + entradaAnterior.charAt(0);
        }
    }

    public void decodificar(int segundaEntrada, int primeraEntrada) {
        String pe = obtenerEntrada(segundaEntrada);
        String se = obtenerEntrada(primeraEntrada);

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
        if (segundaEntrada != 0) {
            String ultimo = obtenerEntrada(segundaEntrada);
            traduccion += ultimo;

            if (usarRojo) {
                bloquesColoreados.add(RED + ultimo + RESET);
            } else {
                bloquesColoreados.add(BLUE + ultimo + RESET);
            }
            usarRojo = !usarRojo;
            segundaEntrada = 0;
        }

        for (String bloque : bloquesColoreados) {
            System.out.print(bloque);
        }
        System.out.println();
    }

}
