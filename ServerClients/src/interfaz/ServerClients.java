package interfaz;

import controlador.Controlador;

import java.util.ArrayList;
import mundoServidor.*;

/**
 *
 * @author Esteban
 */
public class ServerClients {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Controlador controlador = new Controlador();
        controlador.ejecutar();

        ArrayList<String> lista = new ArrayList<>();
        lista.add("wabba-wabba-wabba-wabba-ctm-ctm-ctm.Yucapapi");
        Codificador cod = new Codificador(lista);

    }

}
