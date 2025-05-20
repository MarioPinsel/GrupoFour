package mundo;

import java.util.ArrayList;

public class Decodificador {

    private int paco = 0;
    private String traduccion = "";
    private ArrayList<String> diccionario = new ArrayList<>();

    public void procesar(int primeraEntrada) {
        if (paco == 0) {
            paco = primeraEntrada;
        } else {
            decodificar(paco, primeraEntrada);
            paco = primeraEntrada;
            
        }
    }

    public String obtenerEntrada(int codigo) {
        if (codigo <= 255) {
            return String.valueOf((char) codigo);
        } else {
            return diccionario.get(codigo - 256);
        }
    }

    public void decodificar(int paco, int luis) {
        String pe = obtenerEntrada(paco);
        String se = obtenerEntrada(luis);

        String ps = pe + se.charAt(0);

        if (!diccionario.contains(ps)) {
            diccionario.add(ps);
        }

        traduccion += pe;
        System.out.println(traduccion);
    }

    public ArrayList<String> getDiccionario() {
        return diccionario;
    }
    
    public String getTraduccion(){
        System.out.println(traduccion);
        return traduccion;
    }
}
