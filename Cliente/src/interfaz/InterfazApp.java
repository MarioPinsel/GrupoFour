package interfaz;

import controlador.Controlador;
import java.util.Scanner;

public class InterfazApp {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {      
        Controlador ctrl = new Controlador();;
    }
}
