package controlador;

import mundo.Decodificador;

public class Controlador {
    private Decodificador decodificador;

    public Controlador() {
        decodificador = new Decodificador();
    }

    public Decodificador getDecodificador() {
        return decodificador;
    }

}
