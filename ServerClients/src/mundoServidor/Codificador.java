package mundoServidor;

import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class Codificador {

    private ArrayList<String> lista; // Lista de txt
    private ArrayList<Integer> salida; // Lista de numeros
    private ArrayList<String> diccionario; // Lista de combinaciones

    public Codificador() {

        Archivos arc = new Archivos();        
        this.lista = arc.getLineas();       
        salida = new ArrayList<>();
        diccionario = new ArrayList<>();

        codificacion();
    }

    public void codificacion() {        
        String enunciado = String.join("", lista); 

        String PE = "";  
        String PS = "";  
        String SE = "";  

        if (enunciado.length() < 2) return; 

        PE = enunciado.charAt(0) + "";
        SE = enunciado.charAt(1) + "";

        for (int i = 2; i <= enunciado.length(); i++) {
            PS = PE + SE;

            if (!diccionario.contains(PS)) {
                diccionario.add(PS);

                if (PE.length() == 1) {
                    salida.add((int) PE.charAt(0));
                } else {
                    salida.add(diccionario.indexOf(PE) + 256);
                }

                PE = SE;
                if (i < enunciado.length()) {
                    SE = enunciado.charAt(i) + "";
                }
            } else {
                PE = PS;
                if (i < enunciado.length()) {
                    SE = enunciado.charAt(i) + "";
                }
                
            }
        }

        
        if (PE.length() == 1) {
            salida.add((int) PE.charAt(0));
        } else {
            salida.add(diccionario.indexOf(PE) + 256);
        }

        
        salida.add(-1);
                
        for (String entrada : diccionario) {
            System.out.println(entrada);
        }
              
        for (int codigo : salida) {
            System.out.println(codigo);
        }
    }

    private boolean buscarDiccionario(String entrada) {
        return diccionario.contains(entrada);
    }

    public ArrayList<Integer> getSalida() {
        return salida;
    }
    
}
