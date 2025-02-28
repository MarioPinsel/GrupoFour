package interfaz;

import java.awt.Color;
import javax.swing.JFrame;

import controlador.Controlador;

/**
 *
 * @author Esteban
 */
public class InterfazApp extends JFrame {
    panelBotones pnlB;
    panelVidas pnlV;
    panelPistas pnlP = new panelPistas();

    public InterfazApp(Controlador controlador) {
        this.setLayout(null);
        pnlB = new panelBotones(controlador);
        pnlV = new panelVidas();
        controlador.setInstance(pnlV);
        this.setTitle("Nonograma");
        this.setSize(700, 700);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlB.setBounds(150, 150, 500, 500);
        add(pnlB);

        pnlV.setBounds(190, 10, 120, 80);
        pnlV.setBorder(null);
        add(pnlV);

        pnlP.setBounds(50, 50, 600, 600);
        // pnlP.setBorder(null);
        add(pnlP);

    }

    public static void main(String[] args) {
        InterfazApp frmMain = new InterfazApp(new Controlador());
        frmMain.setVisible(true);
    }

}
