package controlador;

import mundo.*;
import java.util.List;

public class Controlador {

    private Archivos archivos;
    private Secuenciador secuenciador;

    public Controlador() {
        this.archivos = new Archivos();
        this.secuenciador = new Secuenciador();
    }

    public void ejecutar(String rutaEntrada, String rutaSalida) {

        List<String> lineasEntrada = archivos.leerArchivo(rutaEntrada);

        List<String> resultado = secuenciador.selector(lineasEntrada);

        archivos.escribirArchivo(rutaSalida, resultado);

        System.out.println("Archivo generado correctamente en: " + rutaSalida);
    }
}
