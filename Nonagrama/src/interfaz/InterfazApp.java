package interfaz;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Esteban
 */
public class InterfazApp extends JFrame{
    panelBotones pnlB = new panelBotones();
    panelVidas pnlV = new panelVidas();
    panelPistas pnlP = new panelPistas();

    public InterfazApp() {
        this.setLayout(null);
        
        this.setTitle("Nonograma");
        this.setSize(700, 700);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnlB.setBounds(100, 100, 300, 300);
        add(pnlB);
        
        pnlV.setBounds(190, 10, 120, 80);
        add(pnlV);
        
        pnlP.setBounds(100, 200, 400, 400);
        //pnlP.setBorder(null);
        add(pnlP);
        
        
    }

    
    public static void main(String[] args) {
        InterfazApp frmMain = new InterfazApp();
        frmMain.setVisible(true);
    }
  
}
