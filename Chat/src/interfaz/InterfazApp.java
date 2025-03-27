package interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 *
 * @author Esteban
 */
public class InterfazApp extends JFrame{

    public InterfazApp() {
        panelInformacion pnlInfo = new panelInformacion();
        panelChat pnlC = new panelChat();
        panelMensaje pnlmsg = new panelMensaje();
        
        this.setTitle("Personal Chat");
        this.setSize(400, 300);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(pnlInfo, BorderLayout.NORTH);        
        add(pnlC, BorderLayout.CENTER);
        add(pnlmsg,BorderLayout.SOUTH );
    }
   
    public static void main(String[] args) {
        InterfazApp frmMain = new InterfazApp();
        frmMain.setVisible(true);
    }
    
}
