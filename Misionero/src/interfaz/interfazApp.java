
package interfaz;

import mundo.Movimientos;

import java.util.ArrayList;

import gfutria.SearchStateSpaces;

/**
 *
 * @author pmlas
 */
public class interfazApp {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Movimientos movimientos = new Movimientos(3, 3, 0, 0, 0);
    SearchStateSpaces ia = new SearchStateSpaces("0 0 1 3 3", movimientos, 5);

    ArrayList<String> solucion = ia.solve();

    System.out.println("Pasos: " + ia.steps());
    for (String paso : solucion) {
      System.out.println(paso);
    }
  }

}
