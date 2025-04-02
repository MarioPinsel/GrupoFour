
package interfaz;

import javax.swing.JFrame;

/**
 *
 * @author Esteban
 */
public class InterfazApp extends JFrame{

    public InterfazApp() {
        this.setTitle("Personal Chat");
        this.setSize(650, 650);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    public static void main(String[] args) {
        InterfazApp frmMain = new InterfazApp();
        frmMain.setVisible(true);
    }
    
}
