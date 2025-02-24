package mundo;

import javax.swing.JButton;

/**
 *
 * @author sg701-15
 */
public class Movimiento {

    public void Cambio(JButton B16, JButton B17, JButton botones[][], int i, int j) {

        int Bx = B16.getX();
        int By = B16.getY();
        int dx = Bx - B17.getX();
        int dy = By - B17.getY();
        JButton aux;

        if ((Math.abs(B17.getX() - Bx) == 100 && Math.abs(B17.getY() - By) == 0) || (Math.abs(B17.getY() - By) == 100 && Math.abs(B17.getX() - Bx) == 0)) {           
            
            B16.setBounds(B17.getX(), B17.getY(), 100, 100);
            botones[B16.getY() / 100][B16.getX() / 100] = B16;

            B17.setBounds(Bx, By, 100, 100);
            botones[By / 100][Bx / 100] = B17;
            
            for (int k = 0; k < botones.length; k++) {
                for (int l = 0; l < botones[k].length; l++) {
                    if (botones[k][l] != null) {
                        System.out.print("[" + botones[k][l].getText() + "]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
                System.out.println(); // Salto de línea al final de cada fila
            }

        } else if (dx == 300 && dy == 0) {

            aux = B17;// El boton opuesto s eguarda en vacio 

            botones[i][3].setBounds(0, 300, 100, 100);// Posicion matricial del blanco , posicion interfaz del opuesto 
            botones[i][0] = B16;//posicion matricial del opuesto pasa a recibir el boton en blanco

            botones[i][(3 - 1)].setBounds(300, 300, 100, 100);// Posicion matricial del blanco menos 1, posicion interfaz del siguiente (Blnaco) 
            botones[i][3] = botones[i][2]; // Posicion matricial del blanco pasa a rrecibir  el blanco menos 1 

            botones[i][3 - 2].setBounds(200, 300, 100, 100); //Posicion matricial del blanco menos 2 , posicion interfaz del blanco menos 1
            botones[i][2] = botones[i][1]; // Posicion matricial del blanco menos 1 pasa a recibir al blanco menos 2 

            aux.setBounds(100, 300, 100, 100); // Vacio con el boton opuesto, posicion interfaz del blanco menos 2 
            botones[i][1] = aux; // Posicion
            
            
            
            for (int k = 0; k < botones.length; k++) {
                for (int l = 0; l < botones[k].length; l++) {
                    if (botones[k][l] != null) {
                        System.out.print("[" + botones[k][l].getText() + "]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
                System.out.println(); // Salto de línea al final de cada fila
            }

        }

    }

}
