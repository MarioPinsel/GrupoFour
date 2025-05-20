package interfaz;

import controlador.Controlador;

import java.util.ArrayList;
import java.util.Scanner;
import mundoServidor.*;

/**
 *
 * @author Esteban
 */
public class ServerClients {

    Controlador ctrl;

    public ServerClients() {
        ctrl = new Controlador();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Scanner key = new Scanner(System.in);
        ArrayList<String> lista = new ArrayList<>();
//        boolean flag = true;
//        
//        while (flag) {
//            
//            System.out.println(" -------- CODIFICADOR LZW --------");
//            System.out.println("1. Agregar IP's"
//                    + "\n2. Enviar mensaje");
//            int opcion = key.nextInt();
//
//            switch (opcion) {
//                case 1:
//                    System.out.println("Cuantas IP's añadira");
//                    int wap = key.nextInt();
//                    System.out.println("Agregue la IP: ");
//                    
//                    for (int i = 0; i < wap; i++) {                        
//                        String ip = key.next();
//                        lista.add(ip);
//                    }
//                    break;
//                case 2:
//                    
//                    break;
//                default:
//                    System.out.println("Escoja bien");
//            }
//        }

//      
//        controlador.ejecutar();
//
//        ArrayList<String> lista = new ArrayList<>();        
//        lista.add("wabba-wabba-wabba-wabba-ctm-ctm-ctm.Yucapapi");
       Codificador cod = new Codificador();
    }

}
