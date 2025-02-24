
package controlador;

import mundo.Rubik;

/**
 *
 * @author fabian
 */
public class Controlador {

    
    public void giroXSup(int giros, Rubik rubik) {
        switch (giros) {
            case 1:
                rubik.Horizontal(0);
                break;
            case 3:
                rubik.Horizontal(0);
                rubik.Horizontal(0);
                rubik.Horizontal(0);
                break;
        }
    }

    public void giroXInf(int giros, Rubik rubik) {
        switch (giros) {
            case 1:
                rubik.Horizontal(1);
                break;
            case 3:
                rubik.Horizontal(1);
                rubik.Horizontal(1);
                rubik.Horizontal(1);
                break;
        }
    }

    public void giroYIzq(int giros, Rubik rubik) {
        switch (giros) {
            case 1:
                rubik.Vertical(0);
                break;
            case 3:
                rubik.Vertical(0);
                rubik.Vertical(0);
                rubik.Vertical(0);
                break;
        }
    }

    public void giroYDer(int giros, Rubik rubik) {
        switch (giros) {
            case 1:
                rubik.Vertical(1);
                break;
            case 3:
                rubik.Vertical(1);
                rubik.Vertical(1);
                rubik.Vertical(1);
                break;
        }
    }

    public void giroZFro(int giros, Rubik rubik) {
        switch (giros) {
            case 1:
                rubik.Transversal(0);
                break;
            case 3:
                rubik.Transversal(0);
                rubik.Transversal(0);
                rubik.Transversal(0);
                break;
        }
    }

    public void giroZPos(int giros, Rubik rubik) {
        switch (giros) {
            case 1:
                rubik.Transversal(1);
                break;
            case 3:
                rubik.Transversal(1);
                rubik.Transversal(1);
                rubik.Transversal(1);
                break;
        }
    }
}
    
