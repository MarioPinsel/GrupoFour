package controlador;

import mundo.*;

public class Controlador {

    private Decodificador decodificador;
    private Receptor receptor;

    public Controlador() {
        decodificador = new Decodificador();
        receptor = new Receptor(decodificador);

    }

}
