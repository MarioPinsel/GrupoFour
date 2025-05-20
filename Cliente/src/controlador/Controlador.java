package controlador;

import java.util.ArrayList;
import mundo.Decodificador;
import mundo.Receptor;

public class Controlador {

    private Decodificador decodificador;
    private Receptor receptor;

    public Controlador() {
        decodificador = new Decodificador();
        receptor = new Receptor(decodificador);

    }

    public Decodificador getDecodificador() {
        return decodificador;
    }

    public Receptor getReceptor() {
        return receptor;
    }
    
    
    public String getTraduccion() {
        System.out.println(decodificador.getTraduccion());
        return decodificador.getTraduccion();
    }

}
