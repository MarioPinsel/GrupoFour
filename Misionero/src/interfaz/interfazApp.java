
package interfaz;

import mundo.Movimientos;

/**
 *
 * @author pmlas
 */
public class interfazApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Movimientos mov= new Movimientos();
        System.out.println(mov.mostrarResultado());
        mov.unCanibalUnMisionero();
        mov.unMisionero();
        mov.dosCanibales();
        mov.unCanibal();
        mov.dosMisioneros();
        mov.unCanibalUnMisionero();
        mov.dosMisioneros();
        mov.unCanibal();
        mov.dosCanibales();
        mov.unCanibal();
        mov.dosCanibales();
        System.out.println(mov.mostrarResultado());
    }
    
}
