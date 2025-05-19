package interfaz;

import controlador.Controlador;

public class InterfazApp {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";   // Entrada principal (paco)
    public static final String ANSI_RED = "\u001B[31m";    // Entrada secundaria (luis)

    public static void main(String[] args) {
        int[] pollo = {119, 97, 98, 98, 97, 45, 256, 258, 260, 262, 259, 261, 257, 266,
                       99, 116, 109, 45, 270, 272, 274, 46, 89, 117, 99, 97, 112, 281,
                       282, 32, 32};

        Controlador ctrl = new Controlador();

        String textoAnterior = "";
        for (int i = 0; i < pollo.length - 1; i++) {
            int paco = pollo[i];
            int luis = pollo[i + 1];
            String resultado = ctrl.getDecodificador().decodificar(paco, luis);
            
            String nuevoTexto = resultado.substring(textoAnterior.length());

            
            String entradaPaco = ctrl.getDecodificador().obtenerEntrada(paco);
            String entradaLuis = ctrl.getDecodificador().obtenerEntrada(luis);

            System.out.print(ANSI_RED + textoAnterior + ANSI_RESET);
            System.out.print(ANSI_BLUE + entradaPaco + ANSI_RESET);
            System.out.print(ANSI_RED + (entradaLuis.length() > 0 ? entradaLuis.charAt(0) : "") + ANSI_RESET);
            System.out.println();

            textoAnterior = resultado;
        }

        System.out.println("\nDiccionario generado:");
        ctrl.getDecodificador().imprimir();
    }
}
