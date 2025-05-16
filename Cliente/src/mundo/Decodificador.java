
package mundo;

public class Decodificador {
    private int paco = 0;
    private String traduccion = "";

    public void procesar(int luis) {
        if (paco == 0) {
            paco = luis;
        } else {
            decodificar(paco, luis);
            paco = luis;
        }
    }

    public String decodificar(int paco, int luis) {
        String pe = String.valueOf((char) paco);
        String se = String.valueOf((char) luis);
        String ps = pe + se;

        traduccion = traduccion + pe;

        return traduccion;
    }
}
