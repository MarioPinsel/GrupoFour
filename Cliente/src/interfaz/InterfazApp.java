package interfaz;

import controlador.Controlador;

public class InterfazApp {
    public static void main(String[] args) {
        int[] pollo = {
                119,
                97,
                98,
                98,
                97,
                45,
                127,
                129,
                131
        };

        Controlador ctrl = new Controlador();
        for (int i = 0; i < pollo.length; i++) {
            System.out.println(ctrl.getDecodificador().decodificar(pollo[i], pollo[i + 1]));
        }

    }

}
