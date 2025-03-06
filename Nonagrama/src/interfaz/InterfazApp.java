package interfaz;

import javax.swing.JFrame;

import controlador.Controlador;

/**
 *
 * @author Esteban
 */
public class InterfazApp extends JFrame {

    panelBotones pnlB;
    panelVidas pnlV;
    panelPistas pnlP;

    public InterfazApp(Controlador controlador) {

        this.setLayout(null);
        
        pnlB = new panelBotones(controlador);
        pnlV = new panelVidas();
        pnlP = new panelPistas(controlador);
        
        controlador.setInstance(pnlV);
        this.setTitle("Nonograma");
        this.setSize(650, 650);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlB.setBounds(100, 150, 450, 450);
        add(pnlB);

        pnlV.setBounds(265, 10, 120, 40);
        pnlV.setBorder(null);
        add(pnlV);

        pnlP.setBounds(0, 50, 600, 600);
        pnlP.setBorder(null);
        add(pnlP);

    }

    public static void main(String[] args) {

        InterfazApp frmMain = new InterfazApp(new Controlador());

        frmMain.setVisible(true);
    }

}
