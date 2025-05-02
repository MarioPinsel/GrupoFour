package interfaz;

import controlador.Controlador;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        String rutaEntrada = "texto\\Archivo 1.txt";
        String rutaSalida = "texto\\Salida.txt";

        controlador.ejecutar(rutaEntrada, rutaSalida);
    }
}
