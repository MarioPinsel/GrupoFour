package mundoServidor;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Esteban
 */
public class Codificador {

    private ArrayList<String> lista; //Lista de txt
    private ArrayList<Integer> salida; // lista de numeros
    private ArrayList<String> diccionario; //lista de combinaciones
    private Queue<Integer> lastttt;

    public Codificador(ArrayList<String> lista) {
        this.lista = lista;
        salida = new ArrayList<>();
        diccionario = new ArrayList<>();
        
        codificacion();
    }

    public void codificacion() {
        String PE = "";
        String PS = "";
        String SE = "";
        boolean first = false;

        for (String enunciado : lista) {
            if (!first) {
                PE = enunciado.charAt(0) + "";
                SE = enunciado.charAt(1) + "";
                first = true;
            }
            for (int i = 0; i <= enunciado.length() - 2; i++) {
                PS = PE + SE;
                if (!buscarDiccionario(PS)) {
                    diccionario.add(PS);
                    if (PE.length() == 1) {
                        salida.add((int) PE.charAt(0));
                    } else {
                        salida.add(diccionario.indexOf(PE) + 255 + 1);
                    }
                    PE = SE;
                    if (i < enunciado.length() - 2) {
                        SE = enunciado.charAt(i + 2) + "";
                    }

                } else {
                    PE = PS;
                    if (i < enunciado.length() - 2) {
                        SE = enunciado.charAt(i + 2) + "";
                    }
                }
            }
            PE =  SE;
            diccionario.add(PE);
            salida.add(diccionario.indexOf(PE) + 255 + 1);
        }
        for (int num : salida) {
            System.out.println(num);
        }
        for (String wa : diccionario) {
            System.out.println(wa);
        }
    }

    private boolean buscarDiccionario(String entrada) {
        return diccionario.contains(entrada);
    }
}
