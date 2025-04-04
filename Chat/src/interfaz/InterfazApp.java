
package interfaz;

import controlador.Controlador;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class InterfazApp extends JFrame {

    public InterfazApp(Controlador ctrl) {

        panelInformacion pnlInfo = new panelInformacion();
        panelChat pnlChat = new panelChat();
        panelMensaje pnlmsg = new panelMensaje(ctrl);
        ctrl.setInstance(pnlInfo, pnlChat);

        this.setTitle("Personal Chat");
        this.setSize(400, 300);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(pnlInfo, BorderLayout.NORTH);
        add(pnlChat, BorderLayout.CENTER);
        add(pnlmsg, BorderLayout.SOUTH);
        this.setTitle("Personal Chat");
        this.setSize(650, 650);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        InterfazApp frmMain = new InterfazApp(new Controlador());
        frmMain.setVisible(true);
    }

}
