/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;


import controlador.Controlador;

import java.util.ArrayList;
import mundoServidor.Servidor;


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
        lista.add("wabba-wabba-w");
       Servidor server = new Servidor(lista);

    }
    
}
